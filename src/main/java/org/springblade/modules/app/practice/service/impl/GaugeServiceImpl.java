package org.springblade.modules.app.practice.service.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.app.practice.entity.Gauge;
import org.springblade.modules.app.practice.entity.Practice;
import org.springblade.modules.app.practice.mapper.GaugeMapper;
import org.springblade.modules.app.practice.mapper.PracticeMapper;
import org.springblade.modules.app.practice.service.IGaugeService;
import org.springblade.modules.app.practice.service.IPracticeService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author tjt
 */
@Service
public class GaugeServiceImpl extends BaseServiceImpl<GaugeMapper, Gauge> implements IGaugeService {
}
