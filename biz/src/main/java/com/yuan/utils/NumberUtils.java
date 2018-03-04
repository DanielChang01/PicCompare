package com.yuan.utils;

/**
 * Created by danielchang on 2018/2/28.
 */
public class NumberUtils {

    public static boolean isOrderNumeric(String numOrStr) {
        boolean flag = true;
        for (int i = 0; i < numOrStr.length(); i++) {
            if (i > 0) {// 判断如123456
                int num = Integer.parseInt(numOrStr.charAt(i) + "");
                int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
                if (num != num_) {
                    flag = false;
                    break;
                }
            }
        }
        if (!flag) {
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {// 判断如654321
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
