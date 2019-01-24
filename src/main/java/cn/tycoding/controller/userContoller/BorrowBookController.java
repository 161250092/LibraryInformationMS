package cn.tycoding.controller.userContoller;


import cn.tycoding.entity.User;
import cn.tycoding.service.borrow.BorrowService;
import cn.tycoding.service.literature.LiteratureService;
import cn.tycoding.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BorrowBookController {

    @Autowired
    private BorrowService borrowService;

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    User user =(User)attributes.getRequest().getSession().getAttribute("user");


    @RequestMapping("/borrowBooks")
    public Result borrowBooks(@RequestBody Long... ids) {

        List<Long> books = new ArrayList<Long>();
        try {
            books.addAll(Arrays.asList(ids));
            borrowService.borrowBooks(user,books);
            return new Result(true, "借阅成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "借阅失败错误");
        }

    }

    @RequestMapping("/returnBooks")
    public Result returnBooks(@RequestBody Long... ids) {


        try {
            for(long bookId:ids)
            {

            }

            return new Result(true, "借阅成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "借阅失败错误");
        }

    }











}
