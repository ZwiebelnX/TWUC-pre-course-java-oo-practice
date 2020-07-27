package com.twu;

import com.twu.manageer.TopSearchRankingManager;
import com.twu.manageer.UserManager;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    public static boolean IS_LOGIN = false;

    public static boolean EXIT_FLAG = false;

    private static final Deque<String> CURRENT_PATH = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        Scanner in = new Scanner(System.in);

        TopSearchRankingManager topSearchRankingManager = TopSearchRankingManager.getInstance();
        UserManager userManager = UserManager.getInstance();

        while (!EXIT_FLAG) {
            if (!IS_LOGIN) {
                CURRENT_PATH.clear();
                CURRENT_PATH.push("登录");
                printCurrentPath();
                userManager.login(in);
            } else {
                CURRENT_PATH.clear();
                CURRENT_PATH.push("主页");
                printCurrentPath();
                topSearchRankingManager.mainPage(in);
            }
        }
    }

    private static void printCurrentPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">>>>>> ");
        CURRENT_PATH.forEach(s -> stringBuilder.append(s).append(" -> "));
        System.out.println(stringBuilder.toString());
    }
}
