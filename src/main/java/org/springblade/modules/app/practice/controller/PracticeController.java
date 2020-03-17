package org.springblade.modules.app.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.app.practice.entity.Practice;
import org.springblade.modules.app.practice.service.IPracticeService;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 练习模块
 *
 * @author tjt
 */
@RestController
@AllArgsConstructor
@RequestMapping("/blade-practice")
@Api(value = "用户练习", tags = "用户练习接口")

public class PracticeController extends BladeController {

	private IPracticeService practiceService;
	private IUserService userService;

	/**
	 * 查询多条(分页)
	 */
	@PostMapping("/list")
	public R<IPage<Practice>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "userId") String userId) {
//		@ApiIgnore @RequestParam Map<String, Object> practice, Query query
//		, Condition.getQueryWrapper(practice, Practice.class)
		QueryWrapper<Practice> practiceQueryWrapper = new QueryWrapper<>();
		if (!getUser().getRoleId().equals("1")) {
			QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
			userQueryWrapper1.eq("account", userId);
			User user1 = userService.getOne(userQueryWrapper1);
			practiceQueryWrapper.eq("user_id", user1.getId());
		}
		practiceQueryWrapper.orderByDesc("id");
		IPage<Practice> pages = practiceService.page(new Page<Practice>(pageNum, pageSize), practiceQueryWrapper);
		for (Practice str : pages.getRecords()) {
			QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
			userQueryWrapper.eq("id", str.getUserId());
			User user = userService.getOne(userQueryWrapper);
			str.setUserName(user.getName());
		}
		return R.data(pages);
	}

	/**
	 * 查询多条(分页)
	 */
	@PostMapping("/queryCreateTime")
	public R queryCreate(@RequestParam(value = "userId") String userId, @RequestParam(value = "createTime") String createTime) {
		QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
		userQueryWrapper1.eq("account", userId);
		User user1 = userService.getOne(userQueryWrapper1);
		QueryWrapper<Practice> practiceQueryWrapper = new QueryWrapper<>();
		practiceQueryWrapper.eq("user_id", user1.getId());
		practiceQueryWrapper.like("create_time", createTime);
		practiceQueryWrapper.orderByDesc("id");
		List<Practice> practiceList = practiceService.list(practiceQueryWrapper);
		return R.data(practiceList);
	}

	@PostMapping("/add")
	public R add(Practice practice) {
		LocalDateTime localDateTime3 = LocalDateTime.now();
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("account", practice.getUserName());
		practice.setCreateTime(localDateTime3.now());
		User user = userService.getOne(userQueryWrapper);
		practice.setUserId(user.getId());
		Boolean flg = practiceService.save(practice);
		if (flg) {
			return R.data("保存成功");
		} else {
			return R.data("保存失败");
		}

	}

	@PostMapping("/queryList")
	public R queryList(@RequestParam(value = "userId") String userId) {
//		@ApiIgnore @RequestParam Map<String, Object> practice, Query query
//		, Condition.getQueryWrapper(practice, Practice.class)
		QueryWrapper<Practice> practiceQueryWrapper = new QueryWrapper<>();
		if (!getUser().getRoleId().equals("1")) {
			QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
			userQueryWrapper1.eq("account", userId);
			User user1 = userService.getOne(userQueryWrapper1);
			practiceQueryWrapper.eq("user_id", user1.getId());
		}
		practiceQueryWrapper.orderByDesc("id");
		List<Practice> pageList = practiceService.list(practiceQueryWrapper);
		for (Practice str : pageList) {
			QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
			userQueryWrapper.eq("id", str.getUserId());
			User user = userService.getOne(userQueryWrapper);
			str.setUserName(user.getName());
		}
		return R.data(pageList);
	}

}
