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

import static cn.tycoding.util.BookCategory.ALL_LEVEL;
import static cn.tycoding.util.BookCategory.FACULTY_LEVEL;
import static cn.tycoding.util.BorrowState.OVERDUE;

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
        //check if there is overdue borrowing before
        boolean hasOverdue = false;
        for (Borrowing borrowing : borrowingDAO.findBorrowingsByUser(user)) {
            if (borrowing.getState() == OVERDUE){
                hasOverdue = true;
                break;
            }
        }

        //check if there is any books inaccessible for graduate
        boolean isAccessible = true;
        for (Book book : books) {
            if (book.getCategory() != ALL_LEVEL){
                isAccessible = false;
                break;
            }
        }

        if (!hasOverdue && isAccessible && user.getBorrowingNum() + books.size() <= STUDENT_MAX_NUM) {
            return borrowAfterQualifying(user, books, COMMON_PERIOD);
        } else {
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage borrow(Graduate user, List<Book> books) {
        //check if there is overdue borrowing before
        boolean hasOverdue = false;
        for (Borrowing borrowing : borrowingDAO.findBorrowingsByUser(user)) {
            if (borrowing.getState() == OVERDUE){
                hasOverdue = true;
                break;
            }
        }

        //check if there is any books inaccessible for graduate
        boolean isAccessible = true;
        for (Book book : books) {
            if (book.getCategory() == FACULTY_LEVEL){
                isAccessible = false;
                break;
            }
        }

        if (!hasOverdue && isAccessible && user.getBorrowingNum() + books.size() <= STUDENT_MAX_NUM) {
            return borrowAfterQualifying(user, books, ADVANCED_PERIOD);
        } else {
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage borrow(Administrator user, List<Book> books) {
        return borrowAfterQualifying(user, books, ADVANCED_PERIOD);
    }

    @Override
    public ResultMessage borrow(Teacher user, List<Book> books) {
        //check if there is overdue borrowing before
        boolean hasOverdue = false;
        for (Borrowing borrowing : borrowingDAO.findBorrowingsByUser(user)) {
            if (borrowing.getState() == OVERDUE){
                hasOverdue = true;
                break;
            }
        }

        if (!hasOverdue && user.getBorrowingNum() + books.size() <= TEACHER_MAX_NUM) {
            return borrowAfterQualifying(user, books, ADVANCED_PERIOD);
        } else {
            return ResultMessage.FAILURE;
        }
    }

    /**
     * util method, for borrowing after user is qualified
     * @param user user
     * @param books books to borrow
     * @param period period for borrowing
     * @return if it succeeds to  borrow
     */
    private ResultMessage borrowAfterQualifying(User user, List<Book> books, int period){
        List<Borrowing> borrowings = new ArrayList<>(books.size());
        for (Book book : books) {
            borrowings.add(new Borrowing(0, user, book,
                    LocalDate.now(), LocalDate.now().plusMonths(period), BorrowState.UNDERWAY));
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
}
