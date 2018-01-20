package com.kangyonggan.metadata.service;

import com.kangyonggan.metadata.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
public interface CategoryService {

    /**
     * 搜索类型
     *
     * @param pageNum
     * @param code
     * @param name
     * @param field
     * @param db
     * @return
     */
    List<Category> searchCategories(int pageNum, String code, String name, String field, String db);

    /**
     * 保存类型
     *
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 查找类型
     *
     * @param id
     * @return
     */
    Category findCategoryById(Long id);

    /**
     * 跟新类型
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 判断类型代码是否存在
     *
     * @param code
     * @return
     */
    boolean existsCategoryCode(String code);

    /**
     * 查找所有类型
     *
     * @return
     */
    List<Category> findAllCategories();

    /**
     * 查找类型
     *
     * @param categoryCode
     * @return
     */
    Category findCategoryByCode(String categoryCode);
}
