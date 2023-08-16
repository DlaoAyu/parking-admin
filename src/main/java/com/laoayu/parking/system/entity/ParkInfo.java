package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 停车场信息表
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
@TableName("park_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "park_id", type = IdType.AUTO)
    private Long parkId;

    /**
     * 车位数
     */
    private Long parkAmount;

    /**
     * 剩余车位数
     */
    private Long parkSpare;

    /**
     * 停车场地址
     */
    private String parkAddress;

    /**
     * 停车场名字
     */
    private String parkName;

    /**
     * 收费规则
     */
    private BigDecimal parkRule;

    /**
     * 停车场照片地址
     */
    private String parkPic;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

}
