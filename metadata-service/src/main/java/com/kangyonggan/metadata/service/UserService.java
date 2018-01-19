package com.kangyonggan.metadata.service;


import com.kangyonggan.metadata.model.ShiroUser;
import com.kangyonggan.metadata.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/4/17
 */
public interface UserService {

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    ShiroUser getShiroUser();

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 按条件搜索用户
     *
     * @param pageNum
     * @param username
     * @param realname
     * @param email
     * @return
     */
    List<User> searchUsers(int pageNum, String username, String realname, String email);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUserWithDefaultRole(User user);

    /**
     * 根据用户名更新用户信息
     *
     * @param user
     */
    void updateUserByUsername(User user);

    /**
     * 根据用户id查找用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 更新用户密码
     *
     * @param user
     */
    void updateUserPassword(User user);

    /**
     * 更新用户角色
     *
     * @param username
     * @param roleCodes
     */
    void updateUserRoles(String username, String roleCodes);

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    boolean existsUsername(String username);

}
