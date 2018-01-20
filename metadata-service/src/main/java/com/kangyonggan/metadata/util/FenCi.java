package com.kangyonggan.metadata.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/20/18
 */
public class FenCi {

    /**
     * 分词器
     */
    private static final JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * 中文分词
     *
     * @param data
     * @return
     */
    public static List<String> process(String data) {
        List<String> resultList = new ArrayList();
        if (StringUtils.isEmpty(data)) {
            return resultList;
        }

        List<SegToken> list = segmenter.process(data, JiebaSegmenter.SegMode.INDEX);
        list.forEach(segToken -> resultList.add(segToken.word));

        return resultList;
    }

}
