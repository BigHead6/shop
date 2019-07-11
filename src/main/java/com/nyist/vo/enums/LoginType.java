package com.nyist.vo.enums;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/29 14:57
 * @description：${description}
 * @version: $version$
 */
public enum LoginType {
    USER("User"),  ADMIN("Admin");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
