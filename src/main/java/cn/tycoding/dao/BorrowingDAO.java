package cn.tycoding.dao;

import cn.tycoding.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface BorrowingDAO extends JpaRepository<Borrowing, Long> {
}
