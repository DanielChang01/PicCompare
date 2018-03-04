package com.yuan.biz;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by danielchang on 2018/2/1.
 */
public interface PicHandleBiz {

    /**
     * 将得到的两个图片矩阵转化成对比后的图片输出
     * @param resourceRGB 背景图矩阵
     * @param comparedRGB 对比矩阵
     * @param baseCoord 两图片基准
     * @return
     * @throws IOException
     */
    BufferedImage getComparedImage(int[][] resourceRGB, int[][] comparedRGB, String baseCoord) throws IOException;

    /**
     * 将两图片文件的路径转化成对比后的图像输出
     * @param sourceFile
     * @param compareFile
     * @param baseCoord
     * @return
     */
    BufferedImage getComparedImage(String sourceFile, String compareFile, String baseCoord);

    /**
     * 根据文件 路径，输出图片文件RGB矩阵
     * @param filePath
     * @return
     */
    int[][] targetRGB(String filePath);

    int[][] targetRGB(BufferedImage file);

    List<Integer> getRGB(String filePath);

    List<Integer> getRGB(BufferedImage file);
}
