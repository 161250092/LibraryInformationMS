package cn.tycoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 *
 * @auther TyCoding
 * @date 2018/9/28
 */
@Controller
public class HomeController {

    /**
     * 登陆
     *
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "user/login";
    }


    @GetMapping(value = {"/index"})
    public String index() {return "user/index";}

    /**
     * 商品列表页
     *
     * @return
     */
    @GetMapping(value = {"/books"})
    public String user() {
        return "site/books";
    }

    /**
     * 管理书籍页
     * @return
     */
    @GetMapping(value = {"/manageBooks"})
    public String manageBooks() {
        return "admin/manageBooks";
    }


    /**
     * 个人信息
     * @return
     */
    @GetMapping("/personInformation")
    public String personInformation() {
        return "user/personInformation";
    }



}
