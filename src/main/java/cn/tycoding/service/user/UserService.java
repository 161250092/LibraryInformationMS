package cn.tycoding.service.user;

import cn.tycoding.entity.Permission;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import cn.tycoding.vo.Report;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface UserService {
    ResultMessage addUser(User user);

    ResultMessage deleteUser(long userId);

    ResultMessage modifyUser(User user);

    User findUser(long userId);

    User findUserByName(String userName);

    List<User> findAllUsers();

    ResultMessage login(String userName, String password);

    boolean grantPermission(Permission permission);

    Report generateReport(long userId);
}
