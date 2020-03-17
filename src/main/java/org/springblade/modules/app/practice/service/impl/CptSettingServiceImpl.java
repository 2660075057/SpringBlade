package org.springblade.modules.app.practice.service.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.app.practice.entity.CptSetting;
import org.springblade.modules.app.practice.mapper.CptSettingMapper;
import org.springblade.modules.app.practice.service.ICptSettingService;
import org.springframework.stereotype.Service;

@Service
public class CptSettingServiceImpl extends BaseServiceImpl<CptSettingMapper, CptSetting> implements ICptSettingService {
}
