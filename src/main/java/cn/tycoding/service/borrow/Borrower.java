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
    void borrow(User user);
}
