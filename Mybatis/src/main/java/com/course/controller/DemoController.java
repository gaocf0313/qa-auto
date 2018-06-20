package com.course.controller;

import com.course.model.UserBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.apache.catalina.mbeans.UserMBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping(value = "v1")
public class DemoController {
    //首先获取一个执行sql语句的对象
    @Autowired //启动即加载，启动DemoController类,template对象加载赋值
    private SqlSessionTemplate template;


    @RequestMapping(value = "/getUserCount" ,method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public  int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value ="增加用户",httpMethod = "POST")
    public int addUser(@RequestBody UserBean user){
        return  template.insert("addUser",user);

    }

    @RequestMapping(value = "/updateUser" ,method = RequestMethod.POST)
    @ApiOperation(value = "更新用户",httpMethod = "POST")
    public int updateUser(@RequestBody UserBean user){
        return template.update("updateUser",user);
    }

@RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
@ApiOperation(value = "删除用户",httpMethod = "POST")
    public int deleteUser(@RequestParam int id){//key-value类型
        return template.delete("deleteUser",id);

    }


}
