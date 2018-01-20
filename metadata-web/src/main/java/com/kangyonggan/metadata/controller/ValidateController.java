package com.kangyonggan.metadata.controller;

import com.kangyonggan.metadata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 2016/12/3
 */
@Controller
@RequestMapping("validate")
public class ValidateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private WordService wordService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 校验用户名是否可用
     *
     * @param username
     * @param oldUsername
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateUsername(@RequestParam("username") String username,
                                    @RequestParam(value = "oldUsername", required = false, defaultValue = "") String oldUsername) {
        if (username.equals(oldUsername)) {
            return true;
        }

        return !userService.existsUsername(username);
    }


    /**
     * 校验角色代码是否可用
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "role", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateRoleCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !roleService.existsRoleCode(code);
    }

    /**
     * 校验菜单代码是否可用
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "menu", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateMenuCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !menuService.existsMenuCode(code);
    }

    /**
     * 校验字根名称是否可用
     *
     * @param name
     * @param oldName
     * @return
     */
    @RequestMapping(value = "word", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateWordName(@RequestParam("name") String name,
                                    @RequestParam(value = "oldName", required = false, defaultValue = "") String oldName) {
        if (name.equals(oldName)) {
            return true;
        }

        return !wordService.existsWordName(name);
    }

    /**
     * 校验类型代码是否可用
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "category", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateCategoryCode(@RequestParam("code") String code,
                                        @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !categoryService.existsCategoryCode(code);
    }

    /**
     * 校验字典名称是否可用
     *
     * @param name
     * @param oldName
     * @return
     */
    @RequestMapping(value = "dictionary", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateDictionaryName(@RequestParam("name") String name,
                                        @RequestParam(value = "oldName", required = false, defaultValue = "") String oldName) {
        if (name.equals(oldName)) {
            return true;
        }

        return !dictionaryService.existsDictionaryName(name);
    }

}