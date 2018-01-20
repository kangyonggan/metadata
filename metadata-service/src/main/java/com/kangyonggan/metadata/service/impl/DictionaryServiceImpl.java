package com.kangyonggan.metadata.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.metadata.constants.AppConstants;
import com.kangyonggan.metadata.model.Category;
import com.kangyonggan.metadata.model.Dictionary;
import com.kangyonggan.metadata.service.CategoryService;
import com.kangyonggan.metadata.service.DictionaryService;
import com.kangyonggan.metadata.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
@Service
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {

    @Autowired
    private CategoryService categoryService;

    @Override
    @Log
    public void saveDictionary(Dictionary dictionary) {
        Category category = categoryService.findCategoryByCode(dictionary.getCategoryCode());
        dictionary.setCategoryName(category.getName());

        myMapper.insertSelective(dictionary);
    }

    @Override
    public List<Dictionary> searchDictionaries(int pageNum, String name, String value) {
        Example example = new Example(Dictionary.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLikeString(name));
        }
        if (StringUtils.isNotEmpty(value)) {
            criteria.andLike("value", StringUtil.toLikeString(value));
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return myMapper.selectByExample(example);
    }

    @Log
    @Override
    public Dictionary findDictionaryById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Log
    @Override
    public void updateDictionary(Dictionary dictionary) {
        Category category = categoryService.findCategoryByCode(dictionary.getCategoryCode());
        dictionary.setCategoryName(category.getName());

        myMapper.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    @Log
    public boolean existsDictionaryName(String name) {
        Dictionary dictionary = new Dictionary();
        dictionary.setName(name);

        return super.exists(dictionary);
    }

    @Override
    @Log
    public void deleteDictionary(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }
}
