package com.zhaopin.utils;
import com.zhaopin.model.InterfaceName;
import java.util.Locale;
import java.util.ResourceBundle;
public class ConfigFile {
    //加载配置文件的变量
    public  static ResourceBundle bundle = ResourceBundle.getBundle("application",Locale.CHINA);//配置文件后缀可省略
    //注意：工具类，我们都命名为静态方法，不用new直接可以使用
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri="";//进行赋值的uri
        //最终的测试地址
        String testUrl;

       if(name==InterfaceName.GETUSERLIST){
           uri = bundle.getString("test.getUserList.uri");
       }
       if(name==InterfaceName.ADDUSERINFO){
           uri = bundle.getString("test.addUser.uri");
       }
       if(name==InterfaceName.GETUSERINFO){
           uri = bundle.getString("test.getUserInfo.uri");

       }
       if (name==InterfaceName.LOGIN){
           uri = bundle.getString("test.login.uri");
       }
       if (name==InterfaceName.UPDATEUSERINFO){
           uri = bundle.getString("test.updateUserInfo.uri");
       }
        testUrl = address + uri;

        return  testUrl;

    }
}
