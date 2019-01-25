package cn.tycoding.controller.userContoller;

import cn.tycoding.entity.User;
import cn.tycoding.service.user.UserService;
import cn.tycoding.util.Result;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/personInformation")
public class PersonalInformationController {

    @Autowired
    private UserService userServiceProxy;

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestParam("oldPass") String oldPass, @RequestParam("pass") String newPass) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user = (User) attributes.getRequest().getSession().getAttribute("user");

        if (user != null&&user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            return new Result(true, "密码修改成功");
        }
        else if(user != null&&!user.getPassword().equals(oldPass))
            return new Result(false, "密码错误");
        else
            return new Result(false, "登陆国旗");

    }


}