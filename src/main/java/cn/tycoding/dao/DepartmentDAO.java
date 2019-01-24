package cn.tycoding.dao;

import cn.tycoding.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface DepartmentDAO extends JpaRepository<Department, Long> {
}
