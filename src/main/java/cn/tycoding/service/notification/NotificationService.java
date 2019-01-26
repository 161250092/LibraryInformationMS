package cn.tycoding.service.notification;

import cn.tycoding.entity.Update;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
public interface NotificationService {
    List<Update> receiveUpdates();
    boolean confirmUpdate(long updateId);
}
