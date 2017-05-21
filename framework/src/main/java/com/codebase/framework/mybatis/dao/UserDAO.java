package com.codebase.framework.mybatis.dao;

import com.codebase.framework.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/20
 */
public interface UserDAO {

    /**
     * 查询所有用户信息
     */
    List<UserDO> getAll();

    /**
     *  根据用户id查询所有用户信息
     * @param id 用户id
     */
    List<UserDO> getAllById(@Param("id") Long id);

    /**
     * 批量插入
     * @param users
     * @return 返回插入成功值
     */
    int batchInsert(List<UserDO> users);

}
