package com.alan.bean;

import lombok.Builder;
import lombok.Data;


/**
 * Created by alan on 17/1/6.
 */
@Data
@Builder
public class Record {

    /**
     *  访问时间
     */
    private Long visit;

    private String url;

    private String title;

    /**
     * 访问数量
     */
    private int num;

    /**
     * 访问类型
     */
    private String types;

}
