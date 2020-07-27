package com.twu.model;

import com.twu.model.type.TopSearchType;

public class TopSearch {

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
}
