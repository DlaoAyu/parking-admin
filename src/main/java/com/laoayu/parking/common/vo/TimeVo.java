package com.laoayu.parking.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: LaoAyu
 * @date: 2023/04/16
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeVo {

    /**
     * 天
     */
    private Long day;
    /**
     * 时
     */
    private Long hour;
    /**
     * 分
     */
    private Long min;
}
