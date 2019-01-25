package cn.tycoding.springboot;

import cn.tycoding.SpringbootApplication;
import cn.tycoding.dao.*;
import cn.tycoding.entity.*;
import cn.tycoding.util.BookCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@Transactional
public class DataGenerator {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BorrowingDAO borrowingDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private UserDAO userDAO;

    @Test
    public void generate() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            bookList.add(new Book(0L, new Random().toString(), BookCategory.values()[(int)(Math.random()*3)],
                    (int)(Math.random()*10),0, new ArrayList<>()));
        }
        bookDAO.saveAll(bookList);
        bookDAO.flush();

        Administrator admin = new Administrator();
        admin.setUserName("admin");
        admin.setPassword("123456");
        admin.setBalance(1000);
        Undergraduate undergraduate = new Undergraduate();
        undergraduate.setUserName("123");
        undergraduate.setPassword("123");
        undergraduate.setMajor("软院");
        undergraduate.setBalance(100);
        userDAO.saveAll(Arrays.asList(admin, undergraduate));
        userDAO.flush();//in case of unordered saving, flushing here is necessary

        Department se = new Department();
        se.setDepartmentName("软院");
        se.getMembers().add(undergraduate);
        Department cs = new Department();
        cs.setDepartmentName("计科");
        departmentDAO.saveAll(Arrays.asList(se, cs));
        departmentDAO.flush();

        Permission all = new Permission();
        all.setType(".*");
        all.getPermittedIndividuals().add(admin);
        Permission search = new Permission();
        search.setType("find.*");
        permissionDAO.saveAll(Arrays.asList(all, search));
        permissionDAO.flush();
    }
}
