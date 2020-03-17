package org.springblade.modules.app.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.app.practice.entity.Gauge;
import org.springblade.modules.app.practice.entity.Practice;
import org.springblade.modules.app.practice.service.IGaugeService;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blade-gauge")
@Api(value = "量表设置", tags = "量表设置接口")
public class GaugeController {

	private IGaugeService gaugeService;
	private IUserService userService;


	@PostMapping("/getOne")
	public R queryBuUserId(@RequestParam(value = "userId") String userId) {
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("account", userId);
		User user = userService.getOne(userQueryWrapper);
		QueryWrapper<Gauge> gaugeQueryWrapper = new QueryWrapper<>();
		gaugeQueryWrapper.eq("user_id", user.getId());
		Gauge gauge = gaugeService.getOne(gaugeQueryWrapper);
		gauge.setUserName(user.getName());
		return R.data(gauge);
	}

	@PostMapping("/list")
	public R list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		QueryWrapper<Gauge> gaugeQueryWrapper = new QueryWrapper<>();
		gaugeQueryWrapper.orderByDesc("create_time");
		IPage<Gauge> pages = gaugeService.page(new Page<Gauge>(pageNum, pageSize), gaugeQueryWrapper);
		for (Gauge str : pages.getRecords()) {
			QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
			userQueryWrapper.eq("id", str.getUserId());
			User user = userService.getOne(userQueryWrapper);
			str.setUserName(user.getName());
		}
		return R.data(pages);
	}

	@PostMapping("/update")
	public R update(Gauge gauge) {
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("account", gauge.getUserName());
		User user = userService.getOne(userQueryWrapper);
		gauge.setUserId(user.getId());
		if (gauge.getId() == 0) {
			gaugeService.save(gauge);
		} else {
			gaugeService.updateById(gauge);
		}
		return R.data("成功");
	}
}
