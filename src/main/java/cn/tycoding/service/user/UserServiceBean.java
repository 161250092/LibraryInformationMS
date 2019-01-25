package cn.tycoding.service.user;

import cn.tycoding.dao.UserDAO;
import cn.tycoding.entity.Administrator;
import cn.tycoding.entity.Permission;
import cn.tycoding.entity.User;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.ResultMessage;
import cn.tycoding.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.tycoding.util.ResultMessage.*;
import static java.lang.Math.min;

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
        try {
            userDAO.deleteById(userId);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public ResultMessage modifyUser(User user) {
        try {
            userDAO.saveAndFlush(user);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public User findUser(long userId) {
        return userDAO.getOne(userId);
    }

    @Override
    public User findUserByName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public PageBean findUsersByConPage(int pageCode, int pageSize) {
        List<User> users = userDAO.findAll();
        int expectedStartIndex = (pageCode-1) * pageSize;
        int expectedEndIndex = min(expectedStartIndex + pageSize, users.size());//complementary for the last part

        PageBean pageBean = null;
        if (expectedStartIndex < users.size()){
            pageBean = new PageBean(users.size(), users.subList(expectedStartIndex, expectedEndIndex));
        } else {
            System.out.println("分页错误");
        }
        return pageBean;
    }

    @Override
    public ResultMessage login(String userName, String password) {
        User user = findUserByName(userName);
        if (user != null && user.getPassword().equals(password)){
            if (user.getClass() == Administrator.class){
                return LOGIN_ADMIN;
            } else {
                return SUCCESS;
            }
        }
        return FAILURE;
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
