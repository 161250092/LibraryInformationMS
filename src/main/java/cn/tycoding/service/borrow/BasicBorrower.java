package cn.tycoding.service.borrow;

import cn.tycoding.dao.BorrowingDAO;
import cn.tycoding.dao.UserDAO;
import cn.tycoding.entity.*;
import cn.tycoding.util.BorrowState;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Service
public class BasicBorrower implements Borrower{
    private static final Integer COMMON_PERIOD = 2;
    private static final Integer ADVANCED_PERIOD = 3;

    private static final Integer STUDENT_MAX_NUM = 10;
    private static final Integer TEACHER_MAX_NUM = 20;

    private BorrowingDAO borrowingDAO;
    private UserDAO userDAO;

    @Autowired
    public void setBorrowingDAO(BorrowingDAO borrowingDAO) {
        this.borrowingDAO = borrowingDAO;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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
        List<Borrowing> borrowings = new ArrayList<>(books.size());
        for (Book book : books) {
            borrowings.add(new Borrowing(0, user, book,
                    LocalDate.now(), LocalDate.now().plusMonths(COMMON_PERIOD), BorrowState.UNDERWAY));
        }

        try {
            borrowingDAO.saveAll(borrowings);
            user.setBorrowingNum(user.getBorrowingNum() + borrowings.size());
            userDAO.saveAndFlush(user);
        } catch (Exception e){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage borrow(Teacher user, List<Book> books) {
        return null;
    }
}
