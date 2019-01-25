package cn.tycoding.controller.userContoller;


import cn.tycoding.entity.User;
import cn.tycoding.service.borrow.BorrowService;

import cn.tycoding.util.Result;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BorrowBookController {

    @Autowired
    private BorrowService borrowService;


    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(@RequestParam("bookId")long bookId){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user =(User)attributes.getRequest().getSession().getAttribute("user");
        System.out.println("借一本书");
        if(borrowService.borrowBook(user,bookId)== ResultMessage.SUCCESS)
                return new Result(true," 借阅成功");
        else
            return new Result(false,"借阅失败");

    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public Result returnBook(@RequestParam("bookId")long bookId){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user =(User)attributes.getRequest().getSession().getAttribute("user");
        System.out.println("还一本书");
        //if(borrowService.borrowBook(user,bookId)== ResultMessage.SUCCESS)
            return new Result(true," 还书成功");
//        else
//            return new Result(false,"还书失败");

    }


    @RequestMapping("/borrowBooks")
    public Result borrowBooks(@RequestBody Long... ids) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user =(User)attributes.getRequest().getSession().getAttribute("user");

        try {
            List<Long> books = new ArrayList<Long>(Arrays.asList(ids));
            borrowService.borrowBooks(user,books);
            return new Result(true, "批量借阅成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "借阅失败错误");
        }

    }

    @RequestMapping("/returnBooks")
    public Result returnBooks(@RequestBody Long... ids) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        User user =(User)attributes.getRequest().getSession().getAttribute("user");


        try {
            List<Long> books = new ArrayList<Long>(Arrays.asList(ids));
            borrowService.returnBooks(user,books);
            return new Result(true, "还书成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "还书失败");
        }

    }











}
