package com.kangyonggan.metadata.handle;

import com.alibaba.fastjson.JSON;
import com.kangyonggan.extra.core.handle.LogHandle;
import lombok.extern.log4j.Log4j2;

/**
 * @author kangyonggan
 * @since 2017/11/5 0005
 */
@Log4j2
public class Log4j2LogHandle implements LogHandle {

    private String packageName;

    public Log4j2LogHandle(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public void logBefore(String methodName, Object... params) {
        log(methodName, String.format("method args：%s", JSON.toJSONString(params)));
    }

    @Override
    public Object logAfter(String methodName, Long startTime, Object returnValue) {
        log(methodName, String.format("method return：%s", JSON.toJSONString(returnValue)));
        log(methodName, String.format("method used time：%dms", System.currentTimeMillis() - startTime));
        return returnValue;
    }

    @Override
    public void log(String methodName, String msg) {
        log.info(String.format("[%s]<%s>: %s", packageName, methodName, msg));
    }
}
