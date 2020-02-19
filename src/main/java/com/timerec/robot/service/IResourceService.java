package com.timerec.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.Resource;

public interface IResourceService extends IService<Resource> {
    /**
     * 插入新创建胶囊的图片
     * @param resource
     */
    void addResource(Resource resource);

}
