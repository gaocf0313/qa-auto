package com.zhaopin.cases;

import com.zhaopin.config.TestConfig;
import com.zhaopin.model.GetUserListCase;
import com.zhaopin.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息")

    public  void getUserList() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
       GetUserListCase getUserListCase = session.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
    }
}