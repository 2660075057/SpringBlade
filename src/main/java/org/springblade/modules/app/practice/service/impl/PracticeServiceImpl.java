package org.springblade.modules.app.practice.service.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.app.practice.entity.Practice;
import org.springblade.modules.app.practice.mapper.PracticeMapper;
import org.springblade.modules.app.practice.service.IPracticeService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author tjt
 */
@Service
public class PracticeServiceImpl extends BaseServiceImpl<PracticeMapper,Practice> implements IPracticeService {
}
