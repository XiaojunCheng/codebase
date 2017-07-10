package com.codebase.framework.mybatis;

import com.codebase.framework.mybatis.dao.UserDAO;
import com.codebase.framework.mybatis.dataobject.UserDO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2017/4/1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"mybatis/dao.xml"});

        UserDAO userDAO = (UserDAO) appContext.getBean("userDao");

        List<UserDO> userDOs = userDAO.getAll();
        System.out.println("first query size: " + userDOs.size());

        int batchSize = 5;
        List<UserDO> users = new ArrayList<>(batchSize);
        for (int id = 0; id < batchSize; id++) {
            UserDO user = new UserDO();
            user.setId(Long.valueOf(id * 10 + 1));
            user.setName("user-" + id);
            user.setAge(id);
            user.setAddress("address-" + id);
            users.add(user);
        }
        int size = userDAO.batchInsert(users);
        System.out.println("batch insert size: " + size);

        List<UserDO> queryUsers = userDAO.getAll();
        System.out.println("query size: " + queryUsers.size());
        for (UserDO user : queryUsers) {
            System.out.println(user.getId() + ", " + user.getName());
        }
    }

}
