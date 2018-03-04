package com.yuan.biz.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.collect.Lists;
import com.yuan.biz.PicHandleBiz;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by danielchang on 2018/2/1.
 */
@Component
public class PicHandleBizImpl implements PicHandleBiz {

    public BufferedImage getComparedImage(int[][] resourceRGB, int[][] comparedRGB, String baseCoord) throws IOException {
        int width = resourceRGB.length;
        int height = resourceRGB[0].length;
        //将需要对比的图片转化为操作的背景图层
        String compare = this.getClass().getClassLoader().getResource("files/WechatIMG183-compare.jpg").getPath();
        BufferedImage img = ImageIO.read(new File(compare));
        Integer baseX = 0;
        Integer baseY = 0;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = (Graphics2D) bi.getGraphics();
//        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0,0,width, height);
        
        g2D.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.setColor(Color.black);
        g2D.drawImage(img, 0, 0, width,height,null);

        //如果基准点存在，则认为两图片有相同的区域，可以惊醒对比
        if (StringUtils.isEmpty(baseCoord)){
            //TODO
        } else {
            String[] split = baseCoord.split(",");
            //获取基准点
            baseX = Integer.parseInt(split[0]);
            baseY = Integer.parseInt(split[1]);
            //将列置黑
            setBackBlack(height, g2D, baseX);
            //将行置黑
            setBackBlack(width, g2D, baseY);
        }
        for (int line = baseX; line < width; line ++){
            for (int row = baseY; row < height; row ++){
                if (resourceRGB[line][row] == comparedRGB[line][row]){
                    //将对比相同的像素点置黑
                    //由于某些未知原因，相同像素点未必可以完全置黑，所以临时的解决方法是将置黑的像素点扩大10倍
                    //TODO
                    g2D.setPaint(Color.BLACK);
                    g2D.drawRect(line,row,10, 10);
                    g2D.fillRect(line,row,10, 10);
                    g2D.drawOval(line,row,10, 10);
                } else {
//                    g2D.setPaint(Color.CYAN);
//                    g2D.drawOval(row,line,width, height);
                }
            }
        }

        //对比后的图片输出
        //后期将拆出为单独的方法
        //TODO
        String path = PicHandleBizImpl.class.getClassLoader().getResource("").getPath();
        FileOutputStream out = new FileOutputStream(path+"/test.jpg");
        ImageIO.write(bi, "jpg",out);
        bi.flush();
        g2D.dispose();
        return bi;
    }

    public BufferedImage getComparedImage(String sourceFile, String compareFile, String baseCoord) {
        return null;
    }

    private void setBackBlack(int height, Graphics2D g2D, Integer baseX) {
        for (int outerX = 0; outerX < baseX; outerX ++) {
            for (int outerY = 0; outerY < height; outerY ++) {
                g2D.setPaint(Color.BLACK);
//                g2D.drawOval(outerX, outerY, 1, 1);
                g2D.drawRect(outerX, outerY, 1, 1);
                g2D.fillRect(outerX, outerY, 1, 1);
            }
        }
    }

//    private void setBackBlackLine(int wight, Graphics2D g2D, Integer baseY) {
//        for (int outerX = 0; outerX < baseY; outerX ++) {
//            for (int outerY = 0; outerY < wight; outerY ++) {
//                g2D.setPaint(Color.BLACK);
////                g2D.drawOval(outerX, outerY, 1, 1);
//                g2D.drawRect(outerX, outerY, 1, 1);
//                g2D.fillRect(outerX, outerY, 1, 1);
//            }
//        }
//    }

    public int[][] targetRGB(String filePath) {
        File file = new File(filePath);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            int[][] rgbArray = new int[width][height];
            System.out.println("width=" + width + ",height=" + height + ".");
            System.out.println("minx=" + minx + ",miny=" + miny + ".");
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    rgbArray[i][j] = bi.getRGB(i, j);
                }
            }
            return rgbArray;
        } catch (Exception e) {
            e.printStackTrace();
            return new int[0][];
        }

    }

    public int[][] targetRGB(BufferedImage file) {
        int width = file.getWidth();
        int height = file.getHeight();
        int minx = file.getMinX();
        int miny = file.getMinY();
        int[][] rgbArray = new int[width][height];
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                rgbArray[i][j] = file.getRGB(i, j);
            }
        }
        return rgbArray;
    }

    public List<Integer> getRGB(String filePath) {
        File file = new File(filePath);
        BufferedImage bi = null;
        List<Integer> rgbList = Lists.newArrayList();
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            int[][] rgbArray = new int[width][height];
            System.out.println("width=" + width + ",height=" + height + ".");
            System.out.println("minx=" + minx + ",miny=" + miny + ".");
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                    rgbArray[i][j] = bi.getRGB(i, j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rgbList;
    }

    public List<Integer> getRGB(BufferedImage file) {
        return null;
    }

}
