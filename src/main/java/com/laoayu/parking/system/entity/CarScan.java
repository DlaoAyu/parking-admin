package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 车辆出入信息表
 * </p>
 *
 * @author laoayu
 * @since 2023-04-06
 */
@TableName("car_scan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarScan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 车牌号
     */
    private String plateNum;

    /**
     * 是否固定车（0不是 1是）
     */
    private Integer type;

    /**
     * 方向（0驶入 1驶出）
     */
    private Integer direction;

    /**
     * 扫面图片地址
     */
    private String picture;

    /**
     * 扫描时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date createTime;

    /**
     * 停车场ID
     */
    private Long parkId;


    //订单信息表内数据

    /**
     * 进入停车场时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date entryTime;

    /**
     * 离开停车场时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date exitTime;

    /**
     * 停车时长
     */
    @TableField(exist = false)
    private Integer parkingDuration;

    /**
     * 停车收费金额
     */
    @TableField(exist = false)
    private BigDecimal parkFee;

}
