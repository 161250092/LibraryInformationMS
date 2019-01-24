package cn.tycoding.dao;

import cn.tycoding.entity.Borrowing;
import cn.tycoding.util.BorrowState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface BorrowingDAO extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findBorrowingsByState(BorrowState state);
}
