package com.kangyonggan.metadata.service;

import com.kangyonggan.metadata.model.Dictionary;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
public interface DictionaryService {

    /**
     * 保存字典
     *
     * @param dictionary
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 搜索字典
     *
     * @param pageNum
     * @param name
     * @param value
     * @return
     */
    List<Dictionary> searchDictionaries(int pageNum, String name, String value);

    /**
     * 查找字典
     *
     * @param id
     * @return
     */
    Dictionary findDictionaryById(Long id);


    /**
     * 更新字典
     *
     * @param dictionary
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 判断字典名称是否存在
     *
     * @param name
     * @return
     */
    boolean existsDictionaryName(String name);

    /**
     * 删除字典
     *
     * @param id
     */
    void deleteDictionary(Long id);
}
