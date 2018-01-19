package com.kangyonggan.metadata.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Data
public class ShiroUser implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
}
