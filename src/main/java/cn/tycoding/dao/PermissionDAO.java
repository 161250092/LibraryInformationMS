package cn.tycoding.dao;

import cn.tycoding.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author miaomuzhi
 * @since 2019/1/24
 */

public interface PermissionDAO extends JpaRepository<Permission, Long> {
}
