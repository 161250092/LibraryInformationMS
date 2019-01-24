package cn.tycoding.service.loginImpl;

import cn.tycoding.entity.Undergraduate;
import cn.tycoding.entity.User;
import cn.tycoding.service.login.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findByName(String username) {
        return new Undergraduate(123,"123","123",100.00,"软院");
    }

    @Override
    public boolean UpdateUserInfo(User user) {
        return false;
    }



}
