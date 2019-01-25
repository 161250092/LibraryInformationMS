package cn.tycoding.service.borrow;

import cn.tycoding.dao.BookDAO;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Service
public class BorrowServiceBean implements BorrowService{
    private Borrower borrower;
    private BookDAO bookDAO;

    @Autowired
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public ResultMessage borrowBook(User user, long bookId) {
        return borrowBooks(user, Collections.singletonList(bookId));
    }

    @Override
    public ResultMessage borrowBooks(User user, List<Long> books) {
        return user.accept(borrower, bookDAO.findAllById(books));
    }

    @Override
    public ResultMessage returnBooks(User user, List<Long> books) {
        return null;
    }

    @Override
    public ResultMessage returnBook(User user, long bookId) {
        return null;
    }
}
