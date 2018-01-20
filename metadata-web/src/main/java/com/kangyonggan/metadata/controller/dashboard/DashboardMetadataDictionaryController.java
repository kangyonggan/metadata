package com.kangyonggan.metadata.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Dictionary;
import com.kangyonggan.metadata.service.CategoryService;
import com.kangyonggan.metadata.service.DictionaryService;
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
@RequestMapping("dashboard/metadata/dictionary")
public class DashboardMetadataDictionaryController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 字典管理
     *
     * @param pageNum
     * @param name
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("METADATA_DICTIONARY")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        @RequestParam(value = "value", required = false, defaultValue = "") String value,
                        Model model) {
        List<Dictionary> dictionaries = dictionaryService.searchDictionaries(pageNum, name, value);
        PageInfo<Dictionary> page = new PageInfo(dictionaries);

        model.addAttribute("page", page);
        return getPathList();
    }


    /**
     * 添加字典
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_DICTIONARY")
    public String create(Model model) {
        model.addAttribute("dictionary", new Dictionary());
        model.addAttribute("categories", categoryService.findAllCategories());
        return getPathFormModal();
    }

    /**
     * 保存字典
     *
     * @param dictionary
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_DICTIONARY")
    public Map<String, Object> save(@ModelAttribute("dictionary") @Valid Dictionary dictionary, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            dictionaryService.saveDictionary(dictionary);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑字典
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_DICTIONARY")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dictionary", dictionaryService.findDictionaryById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return getPathFormModal();
    }

    /**
     * 更新字典
     *
     * @param dictionary
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_DICTIONARY")
    public Map<String, Object> update(@ModelAttribute("dictionary") @Valid Dictionary dictionary, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            dictionaryService.updateDictionary(dictionary);
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
    @RequiresPermissions("METADATA_DICTIONARY")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        dictionary.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        dictionaryService.updateDictionary(dictionary);

        model.addAttribute("dictionary", dictionary);
        return getPathTableTr();
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("METADATA_DICTIONARY")
    @ResponseBody
    public Map<String, Object> remove(@PathVariable("id") Long id) {
        dictionaryService.deleteDictionary(id);

        return getResultMap();
    }

}
