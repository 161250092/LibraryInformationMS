package cn.tycoding.dao;

import cn.tycoding.SpringbootApplication;
import cn.tycoding.entity.Permission;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@Transactional
public class PermissionDAOTest {
    @Autowired
    private PermissionDAO permissionDAO;

    @Before
    public void setUp(){
        permissionDAO.deleteAll();
    }

    @Test
    @Rollback
    public void test1(){
        Permission p = new Permission();
        p.setType("addUser");
        permissionDAO.save(p);
        List<Permission> permissions = permissionDAO.findAll();
        assertEquals(1, permissions.size());
        assertEquals("addUser", permissions.get(0).getType());
    }

}