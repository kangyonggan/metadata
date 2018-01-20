package com.kangyonggan.metadata.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.metadata.controller.BaseController;
import com.kangyonggan.metadata.model.Word;
import com.kangyonggan.metadata.service.WordService;
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
@RequestMapping("dashboard/metadata/word")
public class DashboardMetadataWordController extends BaseController {

    @Autowired
    private WordService wordService;

    /**
     * 字跟管理
     *
     * @param pageNum
     * @param name
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("METADATA_WORD")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name,
                        @RequestParam(value = "value", required = false, defaultValue = "") String value,
                        Model model) {
        List<Word> words = wordService.searchWords(pageNum, name, value);
        PageInfo<Word> page = new PageInfo(words);

        model.addAttribute("page", page);
        return getPathList();
    }


    /**
     * 添加字根
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_WORD")
    public String create(Model model) {
        model.addAttribute("word", new Word());
        return getPathFormModal();
    }

    /**
     * 保存字根
     *
     * @param word
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_WORD")
    public Map<String, Object> save(@ModelAttribute("word") @Valid Word word, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            wordService.saveWord(word);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑字根
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("METADATA_WORD")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("word", wordService.findWordById(id));
        return getPathFormModal();
    }

    /**
     * 更新字根
     *
     * @param word
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("METADATA_WORD")
    public Map<String, Object> update(@ModelAttribute("word") @Valid Word word, BindingResult result) {
        Map<String, Object> resultMap = getResultMap();
        if (!result.hasErrors()) {
            wordService.updateWord(word);
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
    @RequiresPermissions("METADATA_WORD")
    public String delete(@PathVariable("id") Long id, @PathVariable("isDeleted") String isDeleted, Model model) {
        Word word = wordService.findWordById(id);
        word.setIsDeleted((byte) (isDeleted.equals("delete") ? 1 : 0));
        wordService.updateWord(word);

        model.addAttribute("word", word);
        return getPathTableTr();
    }

}
