package cn.tycoding.service.login;

import cn.tycoding.entity.User;

public interface UserService {
    public User findByName(String username);

    public boolean UpdateUserInfo(User user);
}
