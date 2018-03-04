package com.yuan.biz;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 图片处理
 * Created by danielchang on 2018/2/1.
 */
public interface PicHandleBiz {

    /**
     * 测试方法
     * @param resourceRGB
     * @param comparedRGB
     * @return
     * @throws IOException
     */
    BufferedImage getComparedImage(int[][] resourceRGB, int[][] comparedRGB) throws IOException;

    /**
     * 将图片的RGB矩阵转化成图片
     * @param resourceRGB 背景图片
     * @param comparedRGB 对比图
     * @param baseCoord 两张图片的对比基准点
     * @return
     * @throws IOException
     */
    BufferedImage getComparedImage(int[][] resourceRGB, int[][] comparedRGB, String baseCoord) throws IOException;

    BufferedImage getComparedImage(String sourceFile, String compareFile, String baseCoord);

    /**
     * 将取到的图片文件路径转化为程序可处理的RGB矩阵
     * @param filePath
     * @return
     */
    int[][] targetRGB(String filePath);

    /**
     * 将取到的图片文件转化为程序可处理的RGB矩阵
     * @param file
     * @return
     */
    int[][] targetRGB(BufferedImage file);
}
