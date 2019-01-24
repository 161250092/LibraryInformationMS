package cn.tycoding.service.literature;

import cn.tycoding.dao.BookDAO;
import cn.tycoding.entity.Book;
import cn.tycoding.util.PageBean;
import cn.tycoding.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.tycoding.util.ResultMessage.FAILURE;
import static cn.tycoding.util.ResultMessage.SUCCESS;

@Service
public class LiteratureServiceImpl implements LiteratureService {
    private BookDAO bookDAO;

    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public ResultMessage addBook(Book book) {
        try {
            bookDAO.save(book);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public ResultMessage deleteBook(long bookId) {
        try {
            bookDAO.deleteById(bookId);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public ResultMessage modifyBook(Book book) {
        try {
            bookDAO.saveAndFlush(book);
            return SUCCESS;
        } catch (Exception e){
            return FAILURE;
        }
    }

    @Override
    public Book findBook(long bookId) {
        return bookDAO.getOne(bookId);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public PageBean findByConPage(int pageCode, int pageSize) {
    return null;
    }
}
