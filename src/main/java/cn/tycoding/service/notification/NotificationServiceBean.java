package cn.tycoding.service.notification;

import cn.tycoding.entity.Observer;
import cn.tycoding.vo.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@Service
public class NotificationServiceBean implements NotificationService, Observer {
    private List<Update> updates = new ArrayList<>();

    @Override
    public List<Update> receiveUpdates() {
        return null;
    }

    @Override
    public void confirmUpdates(List<Update> updates) {

    }

    @Override
    public void update(Update update) {

    }
}
