package cn.tycoding.service.borrow;

import cn.tycoding.entity.*;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
public interface Borrower {
    void borrow(Undergraduate user);
    void borrow(Graduate user);
    void borrow(Administrator user);
    void borrow(Teacher user);
    default void borrow(User user){
        System.err.println("An abstract user can't borrow books");
    }
}
