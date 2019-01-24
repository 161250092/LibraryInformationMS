package cn.tycoding.service.user;

import cn.tycoding.entity.Permission;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import cn.tycoding.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Component
@Scope("prototype")
public class UserServiceProxy implements UserService{
    private UserServiceBean wrapped;
    private User user;

    public UserServiceProxy() {
        System.out.println("Proxy built!");
    }

    @Autowired
    public void setWrapped(UserServiceBean wrapped) {
        this.wrapped = wrapped;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public ResultMessage addUser(User user) {
        if (isPermitted())
            return wrapped.addUser(user);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        if (isPermitted())
            return wrapped.deleteUser(userId);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyUser(User user) {
        if (isPermitted())
            return wrapped.modifyUser(user);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public User findUser(long userId) {
        if (isPermitted())
            return wrapped.findUser(userId);
        else
            return null;
    }

    @Override
    public User findUserByName(String userName) {
        if (isPermitted())
            return wrapped.findUserByName(userName);
        else
            return null;
    }

    @Override
    public List<User> findAllUsers() {
        if (isPermitted())
            return wrapped.findAllUsers();
        else
            return new ArrayList<>();
    }

    @Override
    public ResultMessage login(String userName, String password) {
        ResultMessage message = wrapped.login(userName, password);
        if (message != ResultMessage.FAILURE){
            this.user = wrapped.findUserByName(userName);
        }
        return message;
    }

    @Override
    public boolean grantPermission(Permission permission) {
        return isPermitted() && wrapped.grantPermission(permission);
    }

    @Override
    public Report generateReport(long userId) {
        if (isPermitted())
            return wrapped.generateReport(userId);
        else
            return null;
    }

    private boolean isPermitted(){
        if (user == null){
            //TODO
            return false;
        }
        return true;
    }
}
