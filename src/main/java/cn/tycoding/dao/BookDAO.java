package cn.tycoding.dao;

import cn.tycoding.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface BookDAO extends JpaRepository<Book, Long>{
}
