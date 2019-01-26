package cn.tycoding.dao;

import cn.tycoding.entity.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
public interface UpdateDAO extends JpaRepository<Update, Long>{
    List<Update> findUpdatesByConfirmed(boolean confirmed);
}
