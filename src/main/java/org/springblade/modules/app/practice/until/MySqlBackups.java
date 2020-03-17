package org.springblade.modules.app.practice.until;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;

@EnableScheduling
@Configuration
public class MySqlBackups {
	@Scheduled(fixedRate = 1296000000)
	public void backup() {
		try {
			Runtime rt = Runtime.getRuntime();

			Process child = rt.exec(" C:\\Program Files\\MariaDB 10.2\\bin\\mysqldump -h localhost -P3306 -uroot -proot blade");
//			Process child = rt.exec(" C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -h localhost -P3306 -uroot -proot blade");
			//test是数据库名称，sys_logs是表名

			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream("E:\\testzl222.sql");
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			System.out.println("数据库备份成功");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库备份失败");
		}

	}


	public void restore(String path) {
		Runtime runtime = Runtime.getRuntime();
		//-u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字，--default-character-set=utf8，这句话一定的加
		//我就是因为这句话没加导致程序运行成功，但是数据库里面的内容还是以前的内容，最好写上完成的sql放到cmd中一运行才知道报错了
		//错误信息：
		//mysql: Character set 'utf-8' is not a compiled character set and is not specified in the '
		//C:\Program Files\MySQL\MySQL Server 5.5\share\charsets\Index.xml' file ERROR 2019 (HY000): Can't
		// initialize character set utf-8 (path: C:\Program Files\MySQL\MySQL Server 5.5\share\charsets\)，
		//又是讨人厌的编码问题，在恢复的时候设置一下默认的编码就可以了。
		Process process;
		try {
			process = runtime.exec("mysql -h127.0.0.1 -P3306 -uroot -proot blade");
			OutputStream outputStream = process.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\r\n");
			}
			str = sb.toString();
			System.out.println(str);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
			writer.write(str);
			writer.flush();
			outputStream.close();
			br.close();
			writer.close();
			System.out.println("数据库恢复成功");
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库恢复失败");
		}

	}


//	public static void main(String[] args) {
//
////backup();
//		restore("D:\\testzl222.sql");
//
//	}

}
