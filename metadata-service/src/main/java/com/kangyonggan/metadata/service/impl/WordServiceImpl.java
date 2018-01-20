package com.kangyonggan.metadata.service.impl;

import com.kangyonggan.extra.core.annotation.Log;
import com.kangyonggan.metadata.model.Word;
import com.kangyonggan.metadata.service.WordService;
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
public class WordServiceImpl extends BaseService<Word> implements WordService {


    @Override
    public List<Word> searchWords(int pageNum, String name, String value) {
        Example example = new Example(Word.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLikeString(name));
        }
        if (StringUtils.isNotEmpty(value)) {
            criteria.andLike("value", StringUtil.toLikeString(value));
        }

        example.setOrderByClause("id desc");

        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    public void saveWord(Word word) {
        myMapper.insertSelective(word);
    }

    @Override
    @Log
    public Word findWordById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    public void updateWord(Word word) {
        myMapper.updateByPrimaryKeySelective(word);
    }

    @Override
    @Log
    public boolean existsWordName(String name) {
        Word word = new Word();
        word.setName(name);

        return super.exists(word);
    }
}
