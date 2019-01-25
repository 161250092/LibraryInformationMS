package cn.tycoding.service.notification;

import cn.tycoding.vo.Update;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
public interface NotificationService {
    List<Update> receiveUpdates();
    void confirmUpdates(List<Update> updates);
}
