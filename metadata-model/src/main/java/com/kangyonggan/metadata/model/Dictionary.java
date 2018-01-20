package com.kangyonggan.metadata.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Dictionary implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典的值
     */
    private String value;

    /**
     * 类型代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 类型名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类型长度
     */
    private Integer length;

    /**
     * 是否允许为空
     */
    @Column(name = "can_empty")
    private Byte canEmpty;

    /**
     * 默认值
     */
    @Column(name = "default_value")
    private String defaultValue;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}