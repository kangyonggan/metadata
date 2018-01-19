package com.kangyonggan.metadata.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Menu;
import com.kangyonggan.metadata.model.Role;
import com.kangyonggan.metadata.service.MenuService;
import com.kangyonggan.metadata.service.RoleService;
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
 * @since 2017/1/9
 */
@Controller
@RequestMapping("dashboard/system/role")
public class DashboardSystemRoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 角色管理
     *
     * @param pageNum
     * @param code
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "code", required = false, defaultValue = "") String code,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        Model model) {
        List<Role> roles = roleService.searchRoles(pageNum, code, name);
        PageInfo<Role> page = new PageInfo(roles);

        model.addAttribute("page", page);
        return getPathList();
    }

    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return getPathFormModal();
    }

    /**
     * 保存角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_ROLE")
    public Map<String, Object> save(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            roleService.saveRole(role);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return getPathFormModal();
    }

    /**
     * 更新角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_ROLE")
    public Map<String, Object> update(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            roleService.updateRole(role);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 删除/恢复
     *
     * @param id
     * @param isDeleted
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/{isDeleted:\\bundelete\\b|\\bdelete\\b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("SYSTEM_ROLE")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Role role = roleService.findRoleById(id);
        role.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        roleService.updateRole(role);

        model.addAttribute("role", role);
        return getPathTableTr();
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Map<String, Object> remove(@PathVariable("id") Long id) {
        roleService.deleteRoleById(id);
        return super.getResultMap();
    }

    /**
     * 角色详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return getPathDetailModal();
    }

    /**
     * 修改角色的菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/menus", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String menus(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findRoleById(id);
        List<Menu> role_menus = menuService.findRoleMenus(role.getCode());
        if (role_menus != null) {
            role_menus = Collections3.extractToList(role_menus, "code");
        }

        List<Menu> all_menus = menuService.findAllMenus();

        model.addAttribute("role_menus", role_menus);
        model.addAttribute("all_menus", all_menus);
        model.addAttribute("roleId", id);
        return getPathRoot() + "/menus-modal";
    }

    /**
     * 更新角色菜单
     *
     * @param id
     * @param menus
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/menus", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Map<String, Object> menus(@PathVariable Long id, @RequestParam(value = "menus") String menus) {
        Role role = roleService.findRoleById(id);

        roleService.updateRoleMenus(role.getCode(), menus);
        return getResultMap();
    }
}
