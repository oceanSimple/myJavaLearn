package com.ocean.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.common.R;
import com.ocean.entity.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    /**
     * 登录功能，通过账号密码确认是否允许登录
     * @param userinfo  用户登录提交的信息
     * @return
     */
    R<UserInfo> selectByUsercodeAndUserpwd(UserInfo userinfo);

    /**
     * 用户注册功能
     * @param userInfo  用户登录提交的信息
     * @return
     */
    R<String> register(UserInfo userInfo);
}
