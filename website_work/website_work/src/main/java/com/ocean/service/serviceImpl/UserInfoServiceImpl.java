package com.ocean.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.common.R;
import com.ocean.entity.UserInfo;
import com.ocean.mapper.UserInfoMapper;
import com.ocean.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public R<UserInfo> selectByUsercodeAndUserpwd(UserInfo userinfo) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("usercode", userinfo.getUsercode());
        UserInfo one = getOne(wrapper);
        if (one == null)
            return R.error("登录失败");
        if (Objects.equals(one.getUserpsw(),userinfo.getUserpsw())) {
            return R.success(one);
        }
        return R.error("登录失败");
    }

    @Override
    public R<String> register(UserInfo userInfo) {
        // 对用户的输入进行后端审核
        String codeRegex = "^\\d{10}$";
        if (!userInfo.getUsercode().matches(codeRegex))
            return R.error("帐号应为10位数字！");
        String pswRegex = "^[A-Za-z0-9]{6,30}+$";
        if (!userInfo.getUserpsw().matches(pswRegex))
            return R.error("密码应为6-30位数字和大小写字母");

        save(userInfo);
        return R.success("添加成功！");
    }
}
