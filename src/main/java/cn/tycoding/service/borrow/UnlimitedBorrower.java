package cn.tycoding.service.borrow;

import cn.tycoding.entity.*;
import cn.tycoding.util.ResultMessage;

import java.util.List;

/**
 * a sample for potential change
 * @author miaomuzhi
 * @since 2019/1/24
 */
public class UnlimitedBorrower implements Borrower{
    @Override
    public ResultMessage borrow(Undergraduate user, List<Book> books) {
        return null;
    }

    @Override
    public ResultMessage borrow(Graduate user, List<Book> books) {
        return null;
    }

    @Override
    public ResultMessage borrow(Administrator user, List<Book> books) {
        return null;
    }

    @Override
    public ResultMessage borrow(Teacher user, List<Book> books) {
        return null;
    }
}
