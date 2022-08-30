package com.planning.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxc
 * @since 2020-05-23 17:06
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCacheBean {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
}