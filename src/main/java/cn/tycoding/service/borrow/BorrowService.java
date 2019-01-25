package cn.tycoding.service.borrow;

import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface BorrowService {
    ResultMessage borrowBook(User user, long bookId);
    ResultMessage borrowBooks(User user, List<Long> books);
    ResultMessage returnBooks(User user, List<Long> books);
}
