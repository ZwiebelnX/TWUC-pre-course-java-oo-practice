package com.twu.manageer;

import com.twu.Main;
import com.twu.model.User;
import com.twu.model.type.UserType;
import com.twu.utility.OutputFormatter;

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

    public static User getCurrentUser() {
        return currentUser;
    }

    public void login(Scanner in) {
        OutputFormatter.printInfo("请选择登录模式");
        System.out.println("1. 普通用户");
        System.out.println("2. 管理员");
        switch (in.nextInt()) {
            case 1:
            default:
                String userName;
                while (true) {
                    OutputFormatter.printInfo("请输入昵称");
                    userName = in.next();
                    if (userName.length() > 0) {
                        break;
                    } else {
                        OutputFormatter.printError("昵称不能为空");
                    }
                }

                currentUser = new User();
                currentUser.setName(userName);
                currentUser.setUserType(UserType.USER_NORMAL);

                Main.IS_LOGIN = true;
                OutputFormatter.printInfo(String.format("欢迎登录 %s", currentUser.getName()));
                break;
            case 2:
                Map<String, String> adminMap = new HashMap<>();
                File file = new File("./src/static/admin.txt");
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
                OutputFormatter.printInfo("请输入管理员用户名");
                String adminName = in.next();
                if (!adminMap.containsKey(adminName)) {
                    OutputFormatter.printError("用户名不存在");
                    break;
                }

                OutputFormatter.printInfo("请输入密码");
                String adminPassword = in.next();
                if (!adminPassword.equals(adminMap.get(adminName))) {
                    OutputFormatter.printError("密码错误");
                    break;
                } else {
                    Main.IS_LOGIN = true;
                    currentUser = new User();
                    currentUser.setName(adminName);
                    currentUser.setPassword(adminPassword);
                    currentUser.setUserType(UserType.USER_ADMIN);

                    OutputFormatter.printInfo(String.format("欢迎登录 %s", currentUser.getName()));
                }
                break;
        }
    }

    public void logout() {
        currentUser = null;
        Main.IS_LOGIN = false;
    }

}
