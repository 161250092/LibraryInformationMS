package cn.tycoding.controller.userContoller;

import cn.tycoding.entity.User;
import cn.tycoding.service.user.UserService;
import cn.tycoding.service.user.UserServiceProxy;
import cn.tycoding.util.Result;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @auther TyCoding
 * @date 2018/9/28
 */
@Controller
public class LoginController {
    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserService userService = context.getBean(UserServiceProxy.class);
        if (userService.login(username, password) == ResultMessage.SUCCESS){
            User user = userService.findUserByName(username);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            attributes.getRequest().getSession().setAttribute("user", user); //将登陆用户信息存入到session域对象中
            return new Result(true, "user");
        } else if(userService.login(username, password) == ResultMessage.LOGIN_ADMIN){
            User user = userService.findUserByName(username);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            attributes.getRequest().getSession().setAttribute("user", user); //将登陆用户信息存入到session域对象中
            return new Result(true, "admin");
        }
        return new Result(false, "登录失败");
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("user");
        return "home/login";
    }

    /**
     * 登录页
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "home/login";
    }


}
