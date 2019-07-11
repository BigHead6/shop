package com.nyist.vo;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/28 13:58
 * @description：${description}
 * @version: $version$
 */
public class ResultVo {
    private Integer code;
    private String msg;
    private Object data;
    private Integer count;
    public ResultVo() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(Integer code, String msg,Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count=count;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}
