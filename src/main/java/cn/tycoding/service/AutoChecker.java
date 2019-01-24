package cn.tycoding.service;

import cn.tycoding.dao.BorrowingDAO;
import cn.tycoding.entity.Borrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static cn.tycoding.util.BorrowState.OVERDUE;
import static cn.tycoding.util.BorrowState.UNDERWAY;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@Service
public class AutoChecker {
    private BorrowingDAO borrowingDAO;

    @Autowired
    public void setBorrowingDAO(BorrowingDAO borrowingDAO) {
        this.borrowingDAO = borrowingDAO;
    }

    /**
     * update the borrowing states automatically in each midnight
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateBorrowingState(){
        List<Borrowing> borrowingList = borrowingDAO.findBorrowingsByState(UNDERWAY);
        borrowingList.forEach(borrowing -> {
            if (borrowing.getEndDate().isBefore(LocalDate.now())){
                borrowing.setState(OVERDUE);
                borrowingDAO.save(borrowing);
            }
        });
        borrowingDAO.flush();
    }
}
