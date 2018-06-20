package com.zhaopin.cases;

import com.zhaopin.config.TestConfig;
import com.zhaopin.model.GetUserInfoCase;
import com.zhaopin.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")

    public void getUserInfo() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

    }
}
