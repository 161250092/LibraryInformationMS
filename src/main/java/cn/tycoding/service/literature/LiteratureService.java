package cn.tycoding.service.literature;

import cn.tycoding.entity.Book;
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
}
