package cn.tycoding.controller.userContoller;

import cn.tycoding.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class readOnlineController {
    public Context context;

    @RequestMapping("/pdf")
    @ResponseBody
    public URL getPdfURL(@RequestParam("bookId") long bookId){
        context = new Context(new PDFStrategy());
        return context.executeStrategy(bookId);
    }

    @RequestMapping("/doc")
    @ResponseBody
    public URL getDocURL(@RequestParam("bookId") long bookId){
        context = new Context(new DocStrategy());
        return context.executeStrategy(bookId);
    }


    @RequestMapping("/ppt")
    @ResponseBody
    public URL getPowerPointURL(@RequestParam("bookId") long bookId){
        context = new Context(new PPTStrategy());
        return context.executeStrategy(bookId);
    }



}
