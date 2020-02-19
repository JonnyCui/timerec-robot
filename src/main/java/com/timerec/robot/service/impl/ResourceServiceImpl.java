package com.timerec.robot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.Resource;
import com.timerec.robot.mapper.ResourceMapper;
import com.timerec.robot.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public void addResource(Resource resource) {
        // 判断新建胶囊是否已存在数据库中
        if (resourceMapper.checkRes(resource) == 0){
            System.out.println("------ Uploading Resource ------");
            System.out.println(resource);
            this.save(resource);
        }else{
            System.out.println("~~~ Resource already exist！~~~");
        }
    }
}
