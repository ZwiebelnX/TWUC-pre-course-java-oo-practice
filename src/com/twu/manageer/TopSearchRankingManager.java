package com.twu.manageer;

import com.twu.model.TopSearch;
import com.twu.model.type.TopSearchType;
import com.twu.model.type.UserType;
import com.twu.utility.OutputFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopSearchRankingManager {

    private final static TopSearchRankingManager INSTANCE = new TopSearchRankingManager();

    private final static List<TopSearch> RANKING = new ArrayList<>();

    private TopSearchRankingManager() {
    }

    public static TopSearchRankingManager getInstance() {
        return INSTANCE;
    }

    public void mainPage(Scanner in) {
        UserManager userManager = UserManager.getInstance();
        UserType currentUserType = UserManager.getCurrentUser().getUserType();
        OutputFormatter.printInfo("请选择功能");
        System.out.println("1. 查看热搜排行榜");
        System.out.println("2. 添加热搜");
        if (currentUserType == UserType.USER_ADMIN) {
            System.out.println("3. 添加超级热搜");
            System.out.println("4. 撤销热搜");
        } else {
            System.out.println("3. 购买热搜");
        }
        System.out.println("0. 退出登录");
        switch (in.nextInt()) {
            case 1:
                checkRanking();
                break;
            case 2:
                addTopSearch(in);
                break;
            case 3:
                if (currentUserType == UserType.USER_ADMIN) {
                    addSuperTopSearch(in);
                } else {
                    purchaseTopSearch(in);
                }
                break;
            case 4:
                deleteTopSearch(in);
                break;
            case 0:
            default:
                userManager.logout();
                break;
        }
    }

    private void checkRanking() {
        OutputFormatter.printInfo("热搜排行榜如下");
        System.out.println(String.format("%-10s %-10s %-10s %-10s", "排名", "名称", "热度", "价格"));
        for (int i = 0; i < RANKING.size(); i++) {
            System.out.println(
                String.format("%-10d %-10s %-10d %-10d", i + 1, RANKING.get(i).getName(), RANKING.get(i).getHeat(), RANKING.get(i).getPrize()));
        }
    }

    private void addTopSearch(Scanner in) {
        OutputFormatter.printInfo("请输入热搜名称");
        String topSearchName;
        while (true) {
            topSearchName = in.next();
            if (topSearchName.length() > 0) {
                break;
            } else {
                OutputFormatter.printError("热搜名称不能为空");
            }
        }
        TopSearch newTopSearch = new TopSearch();
        newTopSearch.setName(topSearchName);
        newTopSearch.setHeat(0);
        newTopSearch.setPrize(0);
        newTopSearch.setPurchase(false);
        newTopSearch.setTopSearchType(TopSearchType.SEARCH_NORMAL);
        newTopSearch.setCreateUserName(UserManager.getCurrentUser().getName());
        RANKING.add(newTopSearch);
        OutputFormatter.printInfo("添加热搜成功");
    }

    private void addSuperTopSearch(Scanner in) {

    }

    private void purchaseTopSearch(Scanner in) {

    }

    private void deleteTopSearch(Scanner in) {

    }

}
