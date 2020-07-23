package com.twu.model.type;

public enum UserType {
    USER_NORMAL("普通用户"), USER_ADMIN("管理员");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
