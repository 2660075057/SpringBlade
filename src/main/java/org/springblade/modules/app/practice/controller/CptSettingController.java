package org.springblade.modules.app.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.modules.app.practice.entity.CptSetting;
import org.springblade.modules.app.practice.service.impl.CptSettingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/blade-cpt-setting")
@Api(value = "cpt设置", tags = "cpt设置接口")
public class CptSettingController {

	private CptSettingServiceImpl cptSettingService;

	@PostMapping("/getOne")
	public R getOne() {
		QueryWrapper<CptSetting> cptSettingQueryWrapper = new QueryWrapper<>();
		cptSettingQueryWrapper.orderByDesc("create_time");
		CptSetting cptSetting = cptSettingService.getOne(cptSettingQueryWrapper);
		return R.data(cptSetting);
	}

	@PostMapping("/update")
	public R update(CptSetting cptSetting) {
		LocalDateTime localDateTime3 = LocalDateTime.now();
		cptSetting.setUpdateTime(localDateTime3.now());
		 cptSettingService.updateById(cptSetting);
		return R.data("修改成功");
	}


	@PostMapping("/updateScale")
	public R updateScale(CptSetting cptSetting) {
		LocalDateTime localDateTime3 = LocalDateTime.now();
		cptSetting.setUpdateTime(localDateTime3.now());
		cptSettingService.updateById(cptSetting);
		return R.data("修改成功");
	}
}
