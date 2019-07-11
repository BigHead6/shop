package com.nyist.vo;

import com.nyist.entity.Returns;

import java.math.BigDecimal;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/16 15:45
 * @description：${description}
 * @version: $version$
 */
public class ReturnsVo extends Returns {
    private BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
