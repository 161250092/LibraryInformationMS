package cn.tycoding.service.literature;

import cn.tycoding.entity.Book;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.ResultMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LiteratureServiceImpl implements LiteratureService{
    @Override
    public ResultMessage addBook(Book book) {
        return null;
    }

    @Override
    public ResultMessage deleteBook(long bookId) {
        return null;
    }

    @Override
    public ResultMessage modifyBook(Book book) {
        return null;
    }

    @Override
    public Book findBook(long bookId) {
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public PageBean findByConPage(int pageCode, int pageSize) {
    return null;
    }
}
