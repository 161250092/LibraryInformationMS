package cn.tycoding.service.user;

import cn.tycoding.dao.UserDAO;
import cn.tycoding.entity.Permission;
import cn.tycoding.entity.Undergraduate;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import cn.tycoding.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.tycoding.util.ResultMessage.FAILURE;
import static cn.tycoding.util.ResultMessage.SUCCESS;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Service
public class UserServiceBean implements UserService{
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ResultMessage addUser(User user) {
        try {
            userDAO.save(user);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        return null;
    }

    @Override
    public ResultMessage modifyUser(User user) {
        return null;
    }

    @Override
    public User findUser(long userId) {
        return null;
    }

    @Override
    public User findUserByName(String userName) {
        return new Undergraduate(123,"123","123",100.00,"软院");
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public ResultMessage login(String userName, String password) {
        return null;
    }

    @Override
    public boolean grantPermission(Permission permission) {
        return false;
    }

    @Override
    public Report generateReport(long userId) {
        return null;
    }
}
