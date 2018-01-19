package com.kangyonggan.metadata.shiro;

import com.kangyonggan.metadata.constants.AppConstants;
import com.kangyonggan.metadata.model.Menu;
import com.kangyonggan.metadata.model.Role;
import com.kangyonggan.metadata.model.ShiroUser;
import com.kangyonggan.metadata.model.User;
import com.kangyonggan.metadata.service.MenuService;
import com.kangyonggan.metadata.service.RoleService;
import com.kangyonggan.metadata.service.UserService;
import com.kangyonggan.metadata.util.Encodes;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/5/15
 */
@Log4j2
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * 经测试：本例中该方法的调用时机为需授权资源被访问时
     * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        log.info("Shiro权限认证, username={}", shiroUser.getUsername());
        List<Role> roles = roleService.findRolesByUsername(shiroUser.getUsername());
        List<Menu> menus = menuService.findMenusByUsername(shiroUser.getUsername());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 基于Role的权限信息
        for (Role role : roles) {
            info.addRole(role.getCode());
        }
        // 基于Permission的权限信息
        for (Menu menu : menus) {
            addStringPermission(info, menu);
        }

        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        log.info("Shiro登录认证, username={}", token.getUsername());

        String username = token.getUsername();
        User user = userService.findUserByUsername(username);

        if (null == user) {
            throw new UnknownAccountException();
        }

        if (user.getIsDeleted() == 1) {
            throw new DisabledAccountException();
        }

        byte[] salt = Encodes.decodeHex(user.getSalt());
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getId());
        shiroUser.setUsername(user.getUsername());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(shiroUser,
                user.getPassword(), ByteSource.Util.bytes(salt), getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AppConstants.HASH_ALGORITHM);
        matcher.setHashIterations(AppConstants.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    private void addStringPermission(SimpleAuthorizationInfo info, Menu menu) {
        info.addStringPermission(menu.getCode());

        if (menu.getLeaf() != null) {
            for (Menu subMenu : menu.getLeaf()) {
                addStringPermission(info, subMenu);
            }
        }
    }
}
