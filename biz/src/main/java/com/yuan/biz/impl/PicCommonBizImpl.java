package com.yuan.biz.impl;

import com.google.common.collect.Lists;
import com.yuan.biz.PicCommonBiz;
import com.yuan.utils.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by danielchang on 2018/2/28.
 */
@Component
public class PicCommonBizImpl implements PicCommonBiz {

    private static final int RECTANGLE_BASE_START = 20;
    private static final int RECTANGLE_BASE_END = 40;
    private static final double ACCEPTABLE_RATE = 0.95;

    public List<String> antiShake(int[][] resourceRGB, int[][] comparedRGB) {
        //以compared图片为基准，划定对比区域
        //坐标(20,20)-(40,40)
//        int[][] baseRBG = new int[20][20];
//        //1。找到图片基准域矩阵
//        int rectangle_diff_value = RECTANGLE_BASE_END - RECTANGLE_BASE_START;
//        for (int startX = RECTANGLE_BASE_START; startX < RECTANGLE_BASE_END; startX ++){
//            for (int startY = RECTANGLE_BASE_START; startY < RECTANGLE_BASE_END; startY ++) {
//                baseRBG[startX-rectangle_diff_value][startY-rectangle_diff_value] = comparedRGB[startX][startY];
//            }
//        }
//
//        List<String> baseList = Lists.newArrayList();
//        for (int i = 0; i < baseRBG.length; i ++) {
//            baseList.add(ArrayUtils.toPureString(baseRBG[i]));
//        }
//        List<String> resourceList = Lists.newArrayList();
//        for (int i = 0; i < resourceRGB.length; i ++){
//            resourceList.add(ArrayUtils.toPureString(resourceRGB[i]));
//        }
//
//        List<String> resultList = Lists.newArrayList();
//        for (String str : baseList) {
//            int numX = 0;
//            for (String item : resourceList) {
//                numX ++;
//                int coorY = item.indexOf(str);
//                if (coorY != -1){
//                    StringBuilder sb = new StringBuilder();
//                    String result = sb.append(numX).append(",").append(coorY).toString();
//                    resultList.add(result);
//                }
//            }
//
//        }
        return null;
    }

    public String antiShake01(int[][] resourceRGB, int[][] comparedRGB) {
        //以compared图片为基准，划定对比区域
        //坐标(20,20)-(40,40)
        int[][] baseRBG = new int[20][20];
        int rectangle_diff_value = RECTANGLE_BASE_END - RECTANGLE_BASE_START;
        double baseRate = rectangle_diff_value*rectangle_diff_value;
        //获取对比基准域
        for (int startX = RECTANGLE_BASE_START; startX < RECTANGLE_BASE_END; startX ++){
            for (int startY = RECTANGLE_BASE_START; startY < RECTANGLE_BASE_END; startY ++) {
                baseRBG[startX-rectangle_diff_value][startY-rectangle_diff_value] = comparedRGB[startX][startY];
            }
        }

        for (int outX = 0; outX < comparedRGB.length; outX ++){
            for (int outY = 0; outY < comparedRGB[outX].length; outY ++){
                if (baseRBG[0][0] == comparedRGB[outX][outY]) {
                    //平移对比
                    double coverRate = getCoverRate(baseRBG, comparedRGB, baseRate, outX, outY);
                    if (coverRate >= ACCEPTABLE_RATE) {
                        StringBuilder sb = new StringBuilder();
                        return sb.append(outX).append(",").append(outY).toString();
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    //平移对比
    private double getCoverRate(int[][] baseRBG, int[][] comparedRGB, double baseRate, int outX, int outY) {
        int divisor = 0;
        for (int x = 0; x < baseRBG.length; x ++) {
            for (int y = 0; y < baseRBG[x].length; y ++) {
                if (baseRBG[x][y] == comparedRGB[x + outX][y + outY])
                    ++ divisor;
            }
        }
        return divisor/baseRate;
    }

//    public static void main(String[] args) {
//        int[][] resource = {
//                { 1, 1, 1, 1, 1, 1},
//                { 1, 1, 1, 1, 1, 1},
//                { 1, 1, 1, 1, 1, 1},
//                { 1, 1, 1, 1, 1, 1},
//                { 1, 1, 1, 1, 1, 1},
//                { 1, 1, 1, 1, 1, 1}};
//
//        int[][] compare = {
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
//
//        String s = antiShakeTest(resource, compare);
//        System.out.println(s);
//    }
}
