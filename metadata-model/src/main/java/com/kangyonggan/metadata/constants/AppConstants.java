package com.kangyonggan.metadata.constants;

/**
 * 系统常量
 *
 * @author kangyonggan
 * @since 1/19/18
 */
public interface AppConstants {

    /**
     * 分页大小
     */
    int PAGE_SIZE = 10;

    /**
     * 默认角色编码
     */
    String DEFAULT_ROLE_CODE = "ROLE_USER";

    /**
     * Shiro常量
     */
    String HASH_ALGORITHM = "SHA-1";
    int HASH_INTERATIONS = 2;
    int SALT_SIZE = 8;

    /**
     * 是否删除
     */
    byte IS_DELETED_NO = 0;
    byte IS_DELETED_YES = 1;

    /**
     * 把验证码存放在session中的key
     */
    String KEY_CAPTCHA = "key-captcha";
}
