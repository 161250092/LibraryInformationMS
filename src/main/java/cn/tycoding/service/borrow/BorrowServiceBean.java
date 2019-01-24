package cn.tycoding.service.borrow;

import cn.tycoding.entity.Book;
import cn.tycoding.entity.User;
import cn.tycoding.util.ResultMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Service
public class BorrowServiceBean implements BorrowService{

    @Override
    public ResultMessage borrowBooks(User user, List<Long> books) {
        return null;
    }

    @Override
    public ResultMessage returnBooks(User user, List<Long> books) {
        return null;
    }
}
