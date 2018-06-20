package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping(value = "v1")
public class MyPostMethod {
    //这个变量是用来装我们cookies信息的
    private  static Cookie cookie;
    //用户登录成功，获取cookies，然后在访问其他接口获取列表


    @RequestMapping(value = "/login",method = RequestMethod.POST)//必须是post方法才能进行访问
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")//告诉用户是个post接口

    // 1 、变量名称可以跟前端名称不对应
    //2、属性: value-前端，被调用，显示的名字;required = true参数必须传
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String passWord){
       if(userName.equals("zhangsan")&& passWord.equals("123456")){
           Cookie cookie = new Cookie("login","true");
           response.addCookie(cookie);
           return "恭喜你登录成功了！";
       }
       return "用户名或密码错误!";
    }
@RequestMapping(value = "/getUserList",method = RequestMethod.POST)
@ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){ //入参
    User user;
        //获取cookies
          Cookie [] cookies = request.getCookies();
          //验证cookies是否合法
         for(Cookie c: cookies){
             if(c.getName().equals("login")
                     && c.getValue().equals("true")
                     && u.getUserName().equals("zhangsan")
                     && u.getPassWord().equals("123456")){

                  user = new User();
                 user.setName("lisi");
                 user.setAge("18");
                 user.setSex("man");
                 return user.toString();


             }
         }
         return "参数不合法";


    }
}
