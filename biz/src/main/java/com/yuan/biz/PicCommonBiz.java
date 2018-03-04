package com.yuan.biz;

import java.util.List;

/**
 * Created by danielchang on 2018/2/28.
 */
public interface PicCommonBiz {

    /**
     * 返回两张照片对比的基准坐标
     * @param resourceRGB
     * @param comparedRGB
     * @return
     */
    public List<String> antiShake(int[][] resourceRGB, int[][] comparedRGB);

    /**
     * 返回两张照片对比的基准坐标
     * @param resourceRGB
     * @param comparedRGB
     * @return
     */
    public String antiShake01(int[][] resourceRGB, int[][] comparedRGB);
}
