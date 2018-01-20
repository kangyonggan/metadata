package com.kangyonggan.metadata.service;

import com.kangyonggan.metadata.model.Word;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
public interface WordService {

    /**
     * 搜索字根
     *
     * @param pageNum
     * @param name
     * @param value
     * @return
     */
    List<Word> searchWords(int pageNum, String name, String value);

    /**
     * 保存字根
     *
     * @param word
     */
    void saveWord(Word word);

    /**
     * 查找字根
     *
     * @param id
     * @return
     */
    Word findWordById(Long id);

    /**
     * 跟新字根
     *
     * @param word
     */
    void updateWord(Word word);

    /**
     * 校验字跟名称是否存在
     *
     * @param name
     * @return
     */
    boolean existsWordName(String name);

    /**
     * 查找字根
     *
     * @param names
     * @return
     */
    List<Word> findWordsByNames(List<String> names);
}
