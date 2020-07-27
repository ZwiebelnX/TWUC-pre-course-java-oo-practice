package com.twu;

import com.twu.manageer.TopSearchRankingManager;
import com.twu.manageer.UserManager;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    public static boolean IS_LOGIN = false;

    public static boolean EXIT_FLAG = false;

    private static Deque<String> currentPath = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        Scanner in = new Scanner(System.in);

        TopSearchRankingManager topSearchRankingManager = TopSearchRankingManager.getInstance();
        UserManager userManager = UserManager.getInstance();

        while (!EXIT_FLAG) {
            if (!IS_LOGIN) {
                currentPath.clear();
                currentPath.push("登录");
                printCurrentPath();
                userManager.login(in);
            } else {
                currentPath.clear();
                currentPath.push("主页");
            }
        }
    }

    private static void printCurrentPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">>>>>> ");
        currentPath.forEach(s -> stringBuilder.append(s).append(" -> "));
        System.out.println(stringBuilder.toString());
    }
}
