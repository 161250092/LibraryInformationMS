package cn.tycoding.service.user;

import cn.tycoding.entity.Permission;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import cn.tycoding.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    @Autowired
    public void setWrapped(UserServiceBean wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ResultMessage addUser(User user) {
        return wrapped.addUser(user);
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        return wrapped.deleteUser(userId);
    }

    @Override
    public ResultMessage modifyUser(User user) {
        return wrapped.modifyUser(user);
    }

    @Override
    public User findUser(long userId) {
        return wrapped.findUser(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return wrapped.findAllUsers();
    }

    @Override
    public ResultMessage login(long userId, String password) {
        return wrapped.login(userId, password);
    }

    @Override
    public boolean grantPermission(Permission permission) {
        return wrapped.grantPermission(permission);
    }

    @Override
    public Report generateReport(long userId) {
        return wrapped.generateReport(userId);
    }

    private boolean isPermitted(){
        if (user == null){
            return false;
        }
        return true;
    }
}
