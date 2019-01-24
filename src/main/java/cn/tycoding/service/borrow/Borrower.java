package cn.tycoding.service.borrow;

import cn.tycoding.entity.*;
import cn.tycoding.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
public interface Borrower {
    ResultMessage borrow(Undergraduate user, List<Book> books);
    ResultMessage borrow(Graduate user, List<Book> books);
    ResultMessage borrow(Administrator user, List<Book> books);
    ResultMessage borrow(Teacher user, List<Book> books);

    default ResultMessage borrow(User user, List<Book> books){
        System.err.println("An abstract user can't borrow books");
        return ResultMessage.FAILURE;
    }
}
