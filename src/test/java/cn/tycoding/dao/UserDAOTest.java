package cn.tycoding.dao;

import cn.tycoding.SpringbootApplication;
import cn.tycoding.entity.Undergraduate;
import cn.tycoding.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@Transactional
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO.deleteAll();
    }

    @Test
    @Rollback
    public void test1() {
        Undergraduate undergraduate = new Undergraduate();
        undergraduate.setMajor("Math");
        userDAO.save(undergraduate);
        User user = userDAO.findAll().get(0);
        assertEquals(Undergraduate.class, user.getClass());
        assertEquals("Math", ((Undergraduate)user).getMajor());
    }
}