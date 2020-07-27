package com.twu.manageer;

import com.twu.Main;
import com.twu.model.User;
import com.twu.model.type.UserType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {

    private static final UserManager INSTANCE = new UserManager();

    private static User currentUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        return INSTANCE;
    }

    public void login(Scanner in) {
        System.out.println("====== 请选择登录模式 ======");
        System.out.println("1. 普通用户");
        System.out.println("2. 管理员");
        switch (in.nextInt()) {
            case 1:
            default:
                System.out.println("====== 请输入名称 ======");
                String userName = in.next();

                currentUser = new User();
                currentUser.setName(userName);
                currentUser.setUserType(UserType.USER_NORMAL);

                Main.IS_LOGIN = true;
                System.out.printf("====== 欢迎登录：%s ======", currentUser.getName());
                break;
            case 2:
                Map<String, String> adminMap = new HashMap<>();
                File file = new File("./src/com/twu/static/admin.txt");
                try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] lineStrings = line.split(",");
                        adminMap.put(lineStrings[0].trim(), lineStrings[1].trim());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("====== 请输入用户名 ======");
                String adminName = in.next();
                if (!adminMap.containsKey(adminName)) {
                    System.out.println("!!!!!! 用户名不存在 !!!!!!");
                    break;
                }
                System.out.println("====== 请输入密码 ======");
                String adminPassword = in.next();
                if (!adminPassword.equals(adminMap.get(adminName))) {
                    System.out.println("!!!!!! 密码错误 !!!!!!");
                    break;
                } else {
                    Main.IS_LOGIN = true;
                    currentUser = new User();
                    currentUser.setName(adminName);
                    currentUser.setPassword(adminPassword);
                    currentUser.setUserType(UserType.USER_ADMIN);

                    System.out.printf("====== 欢迎登录：%s ======", currentUser.getName());
                }
                break;
        }
    }

    public void logout() {
        currentUser = null;
        Main.IS_LOGIN = false;
    }

}
