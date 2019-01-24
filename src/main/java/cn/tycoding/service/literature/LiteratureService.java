package cn.tycoding.service.literature;

import cn.tycoding.entity.Book;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
public interface LiteratureService {
    ResultMessage addBook(Book book);

    ResultMessage deleteBook(long bookId);

    ResultMessage modifyBook(Book book);

    Book findBook(long bookId);

    List<Book> findAllBooks();

    /**
     * 根据pageSize把数据等分，在取出其中的第pageCode份，取出pageSize个数据，赋值到List row里
     * @param pageCode 从0计数
     * @param pageSize page容量
     * @return page
     */
    PageBean findByConPage(int pageCode, int pageSize);
}
