package cn.tycoding.controller.adminController;


import cn.tycoding.entity.User;
import cn.tycoding.service.user.UserService;
import cn.tycoding.service.user.UserServiceBean;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserManageController {

    @Autowired
    private UserServiceBean userService;

    @RequestMapping("/findUsersByConPage")
    public PageBean findByConPage(
            @RequestParam(value = "pageCode", required = false) int pageCode,
            @RequestParam(value = "pageSize", required = false) int pageSize) {


        return userService.findUsersByConPage(pageCode,pageSize);
    }

    @RequestMapping("/updateUserInformation")
    @ResponseBody
    public Result update(@RequestParam("userId")long userId,
                         @RequestParam("userName")String userName,
                         @RequestParam("password")String password,
                         @RequestParam("balance")double balance){
        System.out.println("修改用户信息");
        try{

            User user = userService.findUserByName(userName);
            if(!user.getPassword().equals(password))
                user.setPassword(password);

            if(user.getBalance()!=balance){
                user.setBalance(balance);
            }
            return new Result(true, "修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

}
