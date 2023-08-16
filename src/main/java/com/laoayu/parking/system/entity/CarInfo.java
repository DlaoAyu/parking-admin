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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 固定车信息
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
@TableName("car_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "car_id", type = IdType.AUTO)
    private Long carId;

    /**
     * 属于哪个车主的姓名
     */
    private String belongName;

    /**
     * 车主手机号
     */
    private String belongNum;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 车牌号
     */
    private String plateNum;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date beginTime;

    /**
     * 到期日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //设置返回日期格式
    private Date endTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 停车场Id列表
     */
    @TableField(exist = false)
    private List<Long> parkIdList;

    /**
     *  所属停车场名
     */
    @TableField(exist = false)
    private String parkName;
}
