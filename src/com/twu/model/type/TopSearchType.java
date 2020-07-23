package com.twu.model.type;

public enum TopSearchType {
    SEARCH_NORMAL("普通热搜"), SEARCH_SUPER("超级热搜");

    private final String name;

    TopSearchType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
