package cn.tycoding.dao;

import cn.tycoding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface UserDAO extends JpaRepository<User, Long>{
}
