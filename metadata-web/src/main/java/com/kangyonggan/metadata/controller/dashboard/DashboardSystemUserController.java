package com.kangyonggan.metadata.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.metadata.constants.AppConstants;
import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Role;
import com.kangyonggan.metadata.model.User;
import com.kangyonggan.metadata.service.RoleService;
import com.kangyonggan.metadata.service.UserService;
import com.kangyonggan.metadata.util.Collections3;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/1/8
 */
@Controller
@RequestMapping("dashboard/system/user")
public class DashboardSystemUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户管理
     *
     * @param pageNum
     * @param username
     * @param realname
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "username", required = false, defaultValue = "") String username,
                        @RequestParam(value = "realname", required = false, defaultValue = "") String realname,
                        @RequestParam(value = "email", required = false, defaultValue = "") String email,
                        Model model) {
        List<User> users = userService.searchUsers(pageNum, username, realname, email);
        PageInfo<User> page = new PageInfo(users);

        model.addAttribute("page", page);
        return getPathList();
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return getPathFormModal();
    }

    /**
     * 保存用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    public Map<String, Object> save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.saveUserWithDefaultRole(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑用户
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String create(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathFormModal();
    }

    /**
     * 更新用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    public Map<String, Object> update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.updateUserByUsername(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 删除
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/delete", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("SYSTEM_USER")
    public String delete(@PathVariable("username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        user.setIsDeleted(AppConstants.IS_DELETED_YES);
        userService.updateUserByUsername(user);

        model.addAttribute("user", user);
        return getPathTableTr();
    }

    /**
     * 恢复
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/undelete", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("SYSTEM_USER")
    public String delete(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        user.setIsDeleted(AppConstants.IS_DELETED_NO);
        userService.updateUserByUsername(user);

        model.addAttribute("user", user);
        return getPathTableTr();
    }

    /**
     * 用户详情
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String detail(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathDetail();
    }

    /**
     * 修改密码
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/password", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String password(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathRoot() + "/password-modal";
    }

    /**
     * 修改密码
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    public Map<String, Object> password(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            userService.updateUserPassword(user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 设置角色
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/roles", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String roles(@PathVariable("username") String username, Model model) {
        List<Role> userRoles = roleService.findRolesByUsername(username);
        userRoles = Collections3.extractToList(userRoles, "code");
        List<Role> all_roles = roleService.findAllRoles();

        model.addAttribute("username", username);
        model.addAttribute("user_roles", userRoles);
        model.addAttribute("all_roles", all_roles);
        return getPathRoot() + "/roles-modal";
    }

    /**
     * 保存角色
     *
     * @param username
     * @param roles
     * @return
     */
    @RequestMapping(value = "{username:[\\w]+}/roles", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_USER")
    @ResponseBody
    public Map<String, Object> updateUserRoles(@PathVariable(value = "username") String username,
                                               @RequestParam(value = "roles", defaultValue = "") String roles) {
        User user = userService.findUserByUsername(username);
        userService.updateUserRoles(user.getUsername(), roles);

        return getResultMap();
    }

}
