package com.twu.manageer;

public class TopSearchRankingManager {

    private final static TopSearchRankingManager INSTANCE = new TopSearchRankingManager();

    private TopSearchRankingManager(){}

    public static TopSearchRankingManager getInstance() {
        return INSTANCE;
    }




}
