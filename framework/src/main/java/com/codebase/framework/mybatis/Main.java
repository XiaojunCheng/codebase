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

        int batchSize = 20;
        for (int batchId = 1; batchId <= 100; batchId++) {

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<UserDO> users = new ArrayList<>(batchSize);
            for (int i = 0; i < batchSize; i++) {
                int id = batchId * batchSize + i;
                UserDO user = new UserDO();
                user.setName("user-" + id);
                user.setAge(id);
                user.setAddress("address-" + id);
                users.add(user);
            }
            int size = userDAO.batchInsert(users);
            System.out.println("batch insert size: " + size);

            List<UserDO> queryUsers = userDAO.getAll();
            System.out.println("query size: " + queryUsers.size());
        }

    }

}
