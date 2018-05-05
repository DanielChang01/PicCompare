import com.yuan.biz.PicCommonBiz;
import com.yuan.biz.PicHandleBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by danielchang on 2018/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class InitPicTest {

    @Resource
    private PicCommonBiz picCommonBiz;
    @Resource
    private PicHandleBiz picHandleBiz;

    @Test
    public void picTest(){
        String resource = this.getClass().getClassLoader().getResource("files/WechatIMG183.jpeg").getPath();
        String compare = this.getClass().getClassLoader().getResource("files/WechatIMG183-compare.jpg").getPath();

        int[][] resourceRBG = picHandleBiz.targetRGB(resource);
        int[][] compareRBG = picHandleBiz.targetRGB(compare);

        String coord = picCommonBiz.antiShake01(resourceRBG, compareRBG);
        try {
            picHandleBiz.getComparedImage(resourceRBG, compareRBG, coord);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
