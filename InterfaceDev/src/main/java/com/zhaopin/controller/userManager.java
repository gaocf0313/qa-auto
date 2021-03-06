package com.zhaopin.controller;

import com.zhaopin.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@Api(description = "描述用户管理系统")
@RequestMapping(value = "v1")
public class userManager {
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = template.selectOne("login", user);//查询数据库中是否存在该用户
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        System.out.println("查询到的结果是:" + i + "用户是:" + i);
        if (i == 1) {
            System.out.println("登录的用户是:" + user.getUserName());
            return true;
        }
        return false;

    }

    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        //验证cookies的方法可以抽象出来
        Boolean x = verIfCookies(request);
        int result = 0;
        if (x ==true) {
            result = template.insert("addUser", user);
        }

        if (result > 0) {
            System.out.println("添加的用户数为：" + result);
            return true;
        }
       return  false;
    }

    //获取用户信息、用户列表以及更新用户列表
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户（列表）信息接口", httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verIfCookies(request);//验证cookies
        if (x == true) {

            List<User> users = template.selectList("getUserInfo", user);
            System.out.println("getInfo获取到的用户数量为:" + users.size());

            return users;

        } else {
            return null;
        }

    }

    @ApiOperation(value = "更新删除用户接口", httpMethod = "POST")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verIfCookies(request);
        int i = 0;
        if (x == true) {
            i = template.update("updateUserInfo", user);
        }
        System.out.println("更新的条数为:" + i);
       return  i;

}


    private Boolean verIfCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if(Objects.isNull(cookies)){
        System.out.println("cookies为空!");
        return  false;
    }
    for (Cookie cookie: cookies){
        if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
            System.out.println("cookies验证通过");
            return true;
        }
    }
    return false;
    }


}
