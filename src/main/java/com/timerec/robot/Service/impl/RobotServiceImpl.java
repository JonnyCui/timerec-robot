package com.timerec.robot.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.Mapper.RobotMapper;
import com.timerec.robot.Service.IRobotService;
import com.timerec.robot.entity.Capsule;
import org.assertj.core.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Capsule> implements IRobotService {

    @Override
    public void addCapsule(Capsule capsule) {
        capsule.setDeleted(0);
        capsule.setCapsuleGuid("1");
        capsule.setCreateTime(DateUtil.now());
        capsule.setQueryTime(capsule.getCreateTime());
        this.save(capsule);
    }

    @Override
    public boolean save(Capsule entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<Capsule> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Capsule> entityList, int batchSize) {
        return false;
    }

    @Override
    public RobotMapper getBaseMapper() {
        return null;
    }

}
