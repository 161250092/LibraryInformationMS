package cn.tycoding.service.user;

import cn.tycoding.dao.PermissionDAO;
import cn.tycoding.entity.Permission;
import cn.tycoding.entity.User;
import cn.tycoding.util.PageBean;
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
    private PermissionDAO permissionDAO;
    private User user;

    @Autowired
    public void setWrapped(UserServiceBean wrapped) {
        this.wrapped = wrapped;
    }
    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public ResultMessage addUser(User user) {
        if (isPermitted("addUser"))
            return wrapped.addUser(user);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage deleteUser(long userId) {
        if (isPermitted("deleteUser"))
            return wrapped.deleteUser(userId);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyUser(User user) {
        if (isPermitted("modifyUser"))
            return wrapped.modifyUser(user);
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public User findUser(long userId) {
        return wrapped.findUser(userId);
    }

    @Override
    public User findUserByName(String userName) {
        if (isPermitted("findUserByName"))
            return wrapped.findUserByName(userName);
        else
            return null;
    }

    @Override
    public List<User> findAllUsers() {
        if (isPermitted("findAllUsers"))
            return wrapped.findAllUsers();
        else
            return new ArrayList<>();
    }

    @Override
    public PageBean findUsersByConPage(int pageCode, int pageSize) {
        if (isPermitted("findUsersByConPage"))
            return wrapped.findUsersByConPage(pageCode, pageSize);
        else
            return null;
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
        return isPermitted("grantPermission") && wrapped.grantPermission(permission);
    }

    @Override
    public Report generateReport(long userId) {
        if (isPermitted("generateReport"))
            return wrapped.generateReport(userId);
        else
            return null;
    }

    private boolean isPermitted(String method){
        if (user != null){
            List<Permission> permissions = permissionDAO.findAll();
            permissions.removeIf(permission -> !permission.authorize(user.getUserId())
                    || method.matches(permission.getType()));
            return !permissions.isEmpty();
        } else {
            return false;
        }
    }
}
