package com.kangyonggan.metadata.controller.dashboard;

import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Menu;
import com.kangyonggan.metadata.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("dashboard/system/menu")
public class DashboardSystemMenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    public String index(Model model) {
        List<Menu> all_menus = menuService.findAllMenus();

        model.addAttribute("all_menus", all_menus);
        return getPathIndex();
    }

    /**
     * 添加菜单
     *
     * @param pcode
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    public String create(@RequestParam(value = "pcode", defaultValue = "") String pcode,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parent_menu", menuService.findMenuByCode(pcode));
        return getPathFormModal();
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            menuService.saveMenu(menu);
        } else {
            setResultMapFailure(resultMap);
        }
        return resultMap;
    }

    /**
     * 编辑菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuService.findMenuById(id);

        model.addAttribute("menu", menu);
        model.addAttribute("parent_menu", menuService.findMenuByCode(menu.getPcode()));
        return getPathFormModal();
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    public Map<String, Object> update(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();

        if (!result.hasErrors()) {
            menuService.updateMenu(menu);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    public Map<String, Object> delete(@ModelAttribute("menu") Menu menu) {
        menuService.deleteMenu(menu);
        return getResultMap();
    }

}
