package com.kangyonggan.metadata.service;


import com.kangyonggan.metadata.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/3/17
 */
public interface RoleService {

    /**
     * 查找用户角色
     *
     * @param username
     * @return
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 校验角色代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsRoleCode(String code);

    /**
     * 查找所有角色
     *
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 搜索角色
     *
     * @param pageNum
     * @param code
     * @param name
     * @return
     */
    List<Role> searchRoles(int pageNum, String code, String name);

    /**
     * 保存角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 根据id查找角色
     *
     * @param id
     * @return
     */
    Role findRoleById(Long id);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 更新角色菜单
     *
     * @param code
     * @param menuCodes
     */
    void updateRoleMenus(String code, String menuCodes);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteRoleById(Long id);
}
