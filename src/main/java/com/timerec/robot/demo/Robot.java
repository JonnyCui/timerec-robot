package com.timerec.robot.demo;

import com.timerec.robot.entity.Capsule;
import com.timerec.robot.entity.ContentArticle;
import com.timerec.robot.entity.Resource;
import com.timerec.robot.service.IContentArticleService;
import com.timerec.robot.service.IResourceService;
import com.timerec.robot.service.IRobotService;
import com.timerec.robot.util.ImgWH;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;



@Component
public class Robot {

    private String[] addressArray = {
//            "https://ello.co/idaspida", // 摄影
//            "https://ello.co/minusbaby", // 摄影 文艺
            "https://ello.co/floatingkitchen", //美食
            "https://ello.co/alexgzarate", //日常图文 鸡汤
            "https://ello.co/conami",   //美食 摄影
            "https://ello.co/notexist", //摄影 台湾
            "https://ello.co/doughayes", // 旅拍 生活
            "https://ello.co/nino1972", // 摄影 日常
            "https://ello.co/draganadz", // 美食
            "https://ello.co/fokality",  // 摄影景物
            "https://ello.co/jonathan_tsc",     //美食
            "https://ello.co/tm_l", // 文艺黑白旧照片
            "https://ello.co/theheathermarie",  // 景色
            "https://ello.co/andrew_murdoch",   // 老照片
            "https://ello.co/andrewlodgephoto",  // 生活
            "https://ello.co/darkskieskindeyes", // 文艺青年的日常
};
    private int d = 0;
    private int count = 1;
    private com.timerec.robot.util.HttpClientEllo HttpClientEllo;
    @Autowired
    private IContentArticleService contentArticleService;
    @Autowired
    private IRobotService robotService;
    @Autowired
    private IResourceService resourceService;

    // 加载时 先添加待发送文章，若文章已存在则跳过
    public Robot(com.timerec.robot.util.HttpClientEllo httpClientEllo) {
        HttpClientEllo = httpClientEllo;

        for (String address : addressArray) {
            System.out.println("Count: " + count + " address: " + address);
            HttpClientEllo.httpclient(address);
            count++;
        }
    }

//    @Scheduled(cron = "0/30 * * * * ? ") //测试用 每30秒执行一次
    @Scheduled(cron = "0 0 10-22 * * ?") // 10Am - 10Pm  每小时执行一次
    public void jobScheduled() {

        // 编写要运行的任务Job
        System.out.println(StringUtils.repeat("=", 50) + " Robot Start ! " + StringUtils.repeat("=", 50));

        System.out.println("------ Uploading Capsule ------");

        Capsule capsule = new Capsule();
        Resource resource = new Resource();

        // 从待发送中随机选取文章
        int randId = new Random().nextInt(contentArticleService.getCount() + 1);
        ContentArticle chosenArticle = contentArticleService.selectArticle(randId);
        System.out.println(chosenArticle);

        // 随机选取机器人用户（1/120）
        int guid = 1000000 + new Random().nextInt(120); // 0-119
        // 特定用户
        while (guid == 1000065 || guid == 1000088 || guid == 1000089) {
            guid = 1000000 + new Random().nextInt(120);
        }
        capsule.setUserGuid(Integer.toString(guid));
        capsule.setContentStr(chosenArticle.getContentStr());
        capsule.setCapsuleGuid(chosenArticle.getArticleGuid());

//        //添加数据到数据库
        robotService.addCapsule(capsule);

        // 分配媒体文件
        String[] resourceList = chosenArticle.getResourceUrl().split(" ");
        for (String imgUrl : resourceList) {
            if (!imgUrl.isEmpty()) {
                // 根据url 获取宽高
                int[] arr = ImgWH.getImgWH(imgUrl);
                resource.setWidth(arr[0]);
                resource.setHeight(arr[1]);
                resource.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                resource.setDataGuid(capsule.getCapsuleGuid());
                resource.setMediaType(imgUrl.substring(imgUrl.lastIndexOf(".")+1).toUpperCase());
                imgUrl = imgUrl.replace("http://", "")
                        .replace("https://", "")
                        .replace("//", "");
                String domain = imgUrl.substring(0, imgUrl.indexOf("/"));
                resource.setDomain(domain);
                String path = imgUrl.substring(imgUrl.indexOf("/") + 1);
                resource.setPathName(path);
                resource.setDataType(0);
                resource.setPlayTime(0);
                resource.setTypeId(1);
                resourceService.addResource(resource);
            }
        }
        System.out.println("------ Resource Uploaded ------");

       //查询数据 胶囊总数
        System.out.println("Current Capsules Total: " + robotService.capsTotal());

        System.out.println(StringUtils.repeat("=", 50) + " Robot Finish ! " + StringUtils.repeat("=", 50));
    }
}
