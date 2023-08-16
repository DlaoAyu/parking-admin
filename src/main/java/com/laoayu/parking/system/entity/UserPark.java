package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户停车场关联表，用于连接管理员所管理的停车场
 * </p>
 *
 * @author laoayu
 * @since 2023-03-28
 */
@TableName("sys_user_park")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 停车场ID
     */
    private Long parkId;
}
