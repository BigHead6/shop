package com.nyist.vo;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/27 16:15
 * @description：返回数据vo
 * @version: $version$
 */
public class LayuiPageVo {
    private Integer code;
    private String msg;
    // 数据总数
    private Long total;
    // 返回数据
    private Object data;

    public LayuiPageVo() {
    }

    public LayuiPageVo(Integer code, String msg, Long total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiPageVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
