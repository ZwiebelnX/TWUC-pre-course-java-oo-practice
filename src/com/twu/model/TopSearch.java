package com.twu.model;

import com.twu.model.type.TopSearchType;

import java.util.Objects;

public class TopSearch implements Comparable<TopSearch>{

    private String name;

    private int heat;

    private int prize;

    private boolean isPurchase;

    private String createUserName;

    private TopSearchType topSearchType;

    public String getCreateUserName() { return createUserName; }

    public void setCreateUserName(String createUserName) { this.createUserName = createUserName; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public boolean isPurchase() {
        return isPurchase;
    }

    public void setPurchase(boolean purchase) {
        isPurchase = purchase;
    }

    public TopSearchType getTopSearchType() {
        return topSearchType;
    }

    public void setTopSearchType(TopSearchType topSearchType) {
        this.topSearchType = topSearchType;
    }

    @Override
    public int compareTo(TopSearch topSearch) {
        if (this.isPurchase || topSearch.isPurchase) {
            return 0;
        } else {
            return topSearch.heat - this.heat;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TopSearch topSearch = (TopSearch) o;
        return name.equals(topSearch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, heat, prize, isPurchase, createUserName, topSearchType);
    }
}
