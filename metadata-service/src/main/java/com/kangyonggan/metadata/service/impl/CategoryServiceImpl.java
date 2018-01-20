package com.kangyonggan.metadata.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.metadata.constants.AppConstants;
import com.kangyonggan.metadata.model.Category;
import com.kangyonggan.metadata.service.CategoryService;
import com.kangyonggan.metadata.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Override
    public List<Category> searchCategories(int pageNum, String code, String name, String field, String db) {
        Example example = new Example(Category.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLikeString(name));
        }
        if (StringUtils.isNotEmpty(field)) {
            criteria.andEqualTo("field", field);
        }
        if (StringUtils.isNotEmpty(db)) {
            criteria.andEqualTo("db", db);
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public void saveCategory(Category category) {
        myMapper.insertSelective(category);
    }

    @Override
    @Log
    public Category findCategoryById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    public void updateCategory(Category category) {
        myMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    @Log
    public boolean existsCategoryCode(String code) {
        Category category = new Category();
        category.setCode(code);

        return super.exists(category);
    }

    @Override
    public List<Category> findAllCategories() {
        Example example = new Example(Category.class);

        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("id desc");

        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public Category findCategoryByCode(String categoryCode) {
        Category category = new Category();
        category.setCode(categoryCode);

        return myMapper.selectOne(category);
    }
}
