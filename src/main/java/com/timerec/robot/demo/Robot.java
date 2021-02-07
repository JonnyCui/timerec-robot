package com.timerec.robot.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.timerec.robot.entity.*;
import com.timerec.robot.service.*;
import com.timerec.robot.util.ImgWH;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;



@Component
public class Robot {

    private String[] addressArray = {
//           Insert urls
};
    private int count = 1;
    private com.timerec.robot.util.HttpClientEllo HttpClientEllo;

    @Autowired
    private IContentArticleService contentArticleService;

    @Autowired
    private IRobotService robotService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ITopicService topicService;

    @Autowired
    private ICapsuleTopicService capsuleTopicService;


    // 加载时 先添加待发送文章，若文章已存在则跳过
    public Robot(com.timerec.robot.util.HttpClientEllo httpClientEllo) {
        HttpClientEllo = httpClientEllo;

        if (addressArray.length != 0){

            for (String address : addressArray) {
                System.out.println("Count: " + count + " address: " + address);
                HttpClientEllo.httpclient(address);
                count++;
            }
        }
    }

//   @Scheduled(cron = "0 * * * * ? ") //测试用 每60秒执行一次
    @Scheduled(cron = "0 0/30 10-22 * * ?") // 10Am - 10Pm  每小时执行两次
    public void jobScheduled() {

        // 报时
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println("Execution Time: " + sdf.format(new Date()));

        // 编写要运行的任务Job
        System.out.println(StringUtils.repeat("=", 50) + " Robot Start ! " + StringUtils.repeat("=", 50));

        System.out.println("------ Generating Capsule ------");

        Capsule capsule = new Capsule();
        Resource resource = new Resource();

        // 从待发送中随机选取文章
        int randId = new Random().nextInt(contentArticleService.getCount());
        System.out.println("Random robot: <"+ randId + 1 + "> was chosen.");
        ContentArticle chosenArticle = contentArticleService.selectArticle(randId + 1);
        System.out.println(chosenArticle);


        // 判断文章是否待发送
        while(chosenArticle.getPending() != 1){
            System.out.println("~~~ Capsule already exist！~~~");
            System.out.println("~~ Randomly choosing another article ~~");
            // 重新选取文章
            chosenArticle = contentArticleService.selectArticle(new Random().nextInt(contentArticleService.getCount()) +1);
            System.out.println(chosenArticle);
        }

        capsule.setCapsuleGuid(chosenArticle.getArticleGuid());

        // 随机选取机器人用户（1/120）
        int guid = 1000000 + new Random().nextInt(120); // 0-119
        // 特定用户
        while (guid == 1000065 || guid == 1000088 || guid == 1000089 || guid == 1000041 || guid == 1000048) {
            guid = 1000000 + new Random().nextInt(120);
        }
        if (chosenArticle.getAssignedUserGuid() == null || chosenArticle.getAssignedUserGuid().isEmpty()){
            capsule.setUserGuid(Integer.toString(guid));
        }else{
            capsule.setUserGuid(chosenArticle.getAssignedUserGuid());
        }

        // 处理文字内容
       String content = chosenArticle.getContentStr();
        if (content.contains("#")){
            String tags = content.substring(content.indexOf("#"));
            String[] topics = tags.split("#");
            for (String t : topics){
                Topic topic = new Topic();
                if (t.length() > 16){
                    if (t.contains(" ")){ t = StringUtils.substringBefore(t, " ");
                    }else if (t.contains("_")){t = StringUtils.substringBefore(t, "_");}
                    else{ t = t.substring(0,16);}
                }
                if (StringUtils.isBlank(t))
                    continue;
                if (!topicService.isTopicExist(t)){
                    topic.setTopicGuid(UUID.randomUUID().toString().replaceAll("-",""));
                    topic.setTopicName(t);
                    topic.setCreateTime(new Date());
                    topic.setCapsuleNum(1);
                    topicService.addTopic(topic);
                }else{
                    topic.setTopicGuid(topicService.selectTopicGuid(t));
                    topic.setTopicName(t);
                    topic.setCapsuleNum(capsuleTopicService.countTopic(topic.getTopicGuid()));
                    topicService.update(topic, new LambdaQueryWrapper<Topic>().eq(Topic::getTopicGuid, topic.getTopicGuid()));
                }
                CapsuleTopic capTopic = new CapsuleTopic();
                capTopic.setCapsuleGuid(chosenArticle.getArticleGuid());
                capTopic.setTopicGuid(topicService.selectTopicGuid(t));
                if (!capsuleTopicService.isExist(capTopic)){
                    capsuleTopicService.addCapTopic(capTopic);
                    System.out.println("Topic: "+ t + " related with capsule");
                }else{
                    System.out.println("Capsule Topic: "+ t + "already exist!");
                }
            }
        }


        capsule.setContentStr(content);
        capsule.setResType(chosenArticle.getResourceType());

//        if (!chosenArticle.getResourceUrl().isEmpty()){ }

       // 处理本地时间与 GMT 时间之间的时间差，以分钟为单位。
       capsule.setTimeZoneOffsetMin((Calendar.ZONE_OFFSET -2) * 60);

        //添加数据到数据库
        robotService.addCapsule(capsule);

        chosenArticle.setPending(0);
        contentArticleService.update(chosenArticle, new LambdaQueryWrapper<ContentArticle>().eq(ContentArticle::getArticleGuid, capsule.getCapsuleGuid()));

        // 分配媒体文件
        if (!chosenArticle.getResourceUrl().isEmpty() && chosenArticle.getResourceUrl() != null){
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
                    resource.setCreateTime(new Date());
                    resourceService.addResource(resource);
                }
            }
            System.out.println("------ Resource Uploaded ------");
        }else{
            System.out.println("------ No Resource ------");
        }


       //查询数据 胶囊总数
        System.out.println("Current Capsules Total: " + robotService.capsTotal());

        System.out.println(StringUtils.repeat("=", 50) + " Robot Finish ! " + StringUtils.repeat("=", 50));
    }
}
