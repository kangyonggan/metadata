package com.kangyonggan.metadata.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Category;
import com.kangyonggan.metadata.service.CategoryService;
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
 * @since 1/19/18
 */
@Controller
@RequestMapping("dashboard/metadata/category")
public class DashboardMetadataCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 类型管理
     *
     * @param pageNum
     * @param code
     * @param name
     * @param field
     * @param db
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("METADATA_CATEGORY")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "code", required = false, defaultValue = "") String code,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        @RequestParam(value = "field", required = false, defaultValue = "") String field,
                        @RequestParam(value = "db", required = false, defaultValue = "") String db,
                        Model model) {
        List<Category> categories = categoryService.searchCategories(pageNum, code, name, field, db);
        PageInfo<Category> page = new PageInfo(categories);

        model.addAttribute("page", page);
        return getPathList();
    }


    /**
     * 添加类型
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_CATEGORY")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return getPathFormModal();
    }

    /**
     * 保存类型
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_CATEGORY")
    public Map<String, Object> save(@ModelAttribute("category") @Valid Category category, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            categoryService.saveCategory(category);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑类型
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_CATEGORY")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return getPathFormModal();
    }

    /**
     * 更新类型
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_CATEGORY")
    public Map<String, Object> update(@ModelAttribute("category") @Valid Category category, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            categoryService.updateCategory(category);
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
    @RequiresPermissions("METADATA_CATEGORY")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Category category = categoryService.findCategoryById(id);
        category.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        categoryService.updateCategory(category);

        model.addAttribute("category", category);
        return getPathTableTr();
    }

}
