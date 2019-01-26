package cn.tycoding.service.notification;

import cn.tycoding.dao.UpdateDAO;
import cn.tycoding.entity.Observer;
import cn.tycoding.entity.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@Service
public class NotificationServiceBean implements NotificationService, Observer {
    private UpdateDAO updateDAO;

    @Autowired
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    @Override
    public List<Update> receiveUpdates() {
        return updateDAO.findUpdatesByConfirmed(false);
    }

    @Override
    public boolean confirmUpdate(long updateId) {
        try {
            Update modifiedUpdate = updateDAO.getOne(updateId);
            modifiedUpdate.setConfirmed(true);
            updateDAO.saveAndFlush(modifiedUpdate);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public void update(Update update) {
        updateDAO.saveAndFlush(update);
    }
}
