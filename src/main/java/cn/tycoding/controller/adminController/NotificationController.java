package cn.tycoding.controller.adminController;

import cn.tycoding.entity.Administrator;
import cn.tycoding.entity.Update;
import cn.tycoding.service.notification.NotificationService;
import cn.tycoding.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping("/receive")
    public List<Update> receiveUpdates(HttpSession session){
        System.out.println("Receive");
        Object user = session.getAttribute("user");
        if (user != null && user.getClass() == Administrator.class)
            return notificationService.receiveUpdates();
        else
            return new ArrayList<>();
    }

    @RequestMapping("/confirm")
    public Result confirmUpdates(@RequestBody long updateId, HttpSession session){
        Object user = session.getAttribute("user");
        if (user != null && user.getClass() == Administrator.class
                && notificationService.confirmUpdate(updateId)){
            return new Result(true, "通知信息接收");
        } else {
            return new Result(false, "通知失败");
        }
    }
}
