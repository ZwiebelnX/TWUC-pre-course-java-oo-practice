package com.twu.manageer;

import com.twu.model.TopSearch;
import com.twu.model.type.TopSearchType;
import com.twu.model.type.UserType;
import com.twu.utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
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
            System.out.println("4. 投票热搜");
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
                if (currentUserType == UserType.USER_ADMIN) {
                    deleteTopSearch(in);
                } else {
                    voteTopSearch(in);
                }
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
        if (RANKING.size() == 0) {
            OutputFormatter.printInfo("目前热搜为空");
        } else {
            for (int i = 0; i < RANKING.size(); i++) {
                System.out.println(
                    String.format("%-10d %-10s %-10d %-10d", i + 1, RANKING.get(i).getName(), RANKING.get(i).getHeat(), RANKING.get(i).getPrize()));
            }
        }
    }

    private void addTopSearch(Scanner in) {
        OutputFormatter.printInfo("请输入热搜名称");
        String topSearchName = readNonEmptyString(in);
        TopSearch newTopSearch = new TopSearch();
        newTopSearch.setName(topSearchName);
        newTopSearch.setTopSearchType(TopSearchType.SEARCH_NORMAL);
        newTopSearch.setCreateUserName(UserManager.getCurrentUser().getName());
        RANKING.add(newTopSearch);
        OutputFormatter.printInfo("添加热搜成功");
    }

    private void addSuperTopSearch(Scanner in) {
        OutputFormatter.printInfo("请输入超级热搜名称");
        String superTopSearchName = readNonEmptyString(in);

        boolean isContains = false;
        for (TopSearch topSearch : RANKING) {
            if (superTopSearchName.equals(topSearch.getName())) {
                isContains = true;
                if (topSearch.getTopSearchType() != TopSearchType.SEARCH_SUPER) {
                    OutputFormatter.printError("已存在相同超级热搜");
                    break;
                }
                OutputFormatter.printInfo("热搜<" + superTopSearchName + ">已存在，是否升级为超级热搜？[y/n]");
                String confirmInput = readNonEmptyString(in);
                if (!confirmInput.equals("n")) {
                    topSearch.setTopSearchType(TopSearchType.SEARCH_SUPER);
                    OutputFormatter.printInfo("升级热搜成功");
                }
                break;
            }
        }
        TopSearch newSuperTopSearch;
        if (!isContains) {
            newSuperTopSearch = new TopSearch();
            newSuperTopSearch.setName(superTopSearchName);
            newSuperTopSearch.setTopSearchType(TopSearchType.SEARCH_SUPER);
            newSuperTopSearch.setCreateUserName(UserManager.getCurrentUser().getName());
            RANKING.add(newSuperTopSearch);
            OutputFormatter.printInfo("添加热搜成功");
        }
    }

    private void purchaseTopSearch(Scanner in) {

    }

    private void deleteTopSearch(Scanner in) {

    }

    private void voteTopSearch(Scanner in) {
        int haveTicket = UserManager.getCurrentUser().getVoteTicket();
        if (haveTicket == 0) {
            OutputFormatter.printError("您当前票数为0 不可投票");
            return;
        }
        if (RANKING.size() == 0) {
            OutputFormatter.printError("当前没有热搜 不可投票");
            return;
        }

        OutputFormatter.printInfo("请输入要投票热搜的排名");
        int ranking = readIllegalNumber(in, 1, RANKING.size() - 1, "请输入正确的排名");
        TopSearch votingSearch = RANKING.get(ranking - 1);

        OutputFormatter.printInfo(String.format("请输入票数，您现在拥有%d票", haveTicket));
        int tickets = readIllegalNumber(in, 1, haveTicket, "请输入合理的票数");
        votingSearch.setHeat(votingSearch.getHeat() + (votingSearch.getTopSearchType() == TopSearchType.SEARCH_SUPER
            ? tickets * 2 : tickets));

        // Collections.sort(RANKING);
        sortRanking();
    }

    private String readNonEmptyString(Scanner in) {
        String result;
        while (true) {
            result = in.next();
            if (result.length() > 0) {
                break;
            } else {
                OutputFormatter.printError("输入不能为空");
            }
        }
        return result;
    }

    private int readIllegalNumber(Scanner in, int min, int max, String errMsg) {
        int result;
        while (true) {
            while (true) {
                try {
                    result = in.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    OutputFormatter.printError("请输入数字");
                }
            }
            if (result >= min && result <= max) {
                break;
            } else {
                OutputFormatter.printError(errMsg);
            }
        }
        return result;
    }

    private void sortRanking() {
        Map<Integer, TopSearch> purchaseMap = new HashMap<>();

        // 逆序遍历防止越界
        for (int i = RANKING.size() - 1; i > 0 ; i--) {
            if (RANKING.get(i).isPurchase()) {
                purchaseMap.put(i, RANKING.get(i));
                RANKING.remove(i);
            }
        }
        Collections.sort(RANKING);
        purchaseMap.forEach(RANKING::add);
    }
}
