package com.yuan.utils;

/**
 * Created by danielchang on 2018/2/28.
 */
public class ArrayUtils {

    public static String toPureString(int[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.toString();
        }
    }
}
