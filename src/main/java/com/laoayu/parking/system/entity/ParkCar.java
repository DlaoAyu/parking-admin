package com.laoayu.parking.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 固定车和停车场关联表
 * </p>
 *
 * @author laoayu
 * @since 2023-03-23
 */
@TableName("park_car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 固定车ID
     */
    private Long carId;

    /**
     * 停车场ID
     */
    private Long parkId;

}
