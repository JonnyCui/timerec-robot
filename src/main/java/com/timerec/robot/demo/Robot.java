package com.timerec.robot.demo;

import com.timerec.robot.util.HttpClientEllo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Robot {

    private String[] addressArray = {
            "https://ello.co/idaspida", // 摄影
            "https://ello.co/minusbaby", // 摄影 文艺
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

    @Scheduled(cron = "0 * * * * ? ") //测试用 每60秒执行一次
//    @Scheduled(cron = "0 0 10-22 * * ?") // 10Am - 10Pm  每小时执行一次
    public void jobScheduled() {

        // 编写要运行的任务Job
        System.out.println("======== Robot Start ! ========");

        HttpClientEllo httpClientEllo = new HttpClientEllo();

        // 按照addressArray列中顺序指定地址
        if (count % 11 == 0){
            d++;
            count = 1;
        }

        for (String address : addressArray) {
            System.out.println("Count: " + count + " d: " + d + " address: " + address);
            httpClientEllo.main(address);
        }
//        LinkedList<ArrayList<String>> media = HttpClientEllo.getResources();

        System.out.println("------ Uploading Capsule ------");
//        Capsule capsule = new Capsule();
//        com.timerec.robot.entity.Resource resource = new com.timerec.robot.entity.Resource();
//        // 随机选取机器人用户（1/120）
////            int guid = 1000000 + new Random().nextInt(120); // 0-119
////            // 特定用户
////            if (address.equals("https://ello.co/conami")){guid=1000065;} //
////            else if (address.equals("https://ello.co/fokality")){guid=1000088;}
////            else{
////                while (guid==1000065||guid==1000088||guid==1000089){
////                    guid = 1000000 + new Random().nextInt(120);
////                }
////        }
//        capsule.setUserGuid(Integer.toString(guid));
////        capsule.setContentStr();
////        capsule.setCapsuleGuid();

//        //添加数据到数据库
//        robotService.addCapsule(capsule);

        // 分配媒体文件
//        ArrayList<String> resourceList;
//        if (! media.isEmpty()){
//            resourceList = media.get(count-1);
//            for (String imgUrl: resourceList) {
//                if (!imgUrl.isEmpty()){
//                    // 根据url 获取宽高
//                    int[] arr = ImgWH.getImgWH(imgUrl);
//                    resource.setWidth(arr[0]);
//                    resource.setHeight(arr[1]);
//                    resource.setGuid(UUID.randomUUID().toString().replaceAll("-",""));
//                    resource.setDataGuid(capsule.getCapsuleGuid());
//                    resource.setMediaType("JPG");
//                    imgUrl = imgUrl.replace("http://","")
//                            .replace("https://","")
//                            .replace("//","");
//                    String domain = imgUrl.substring(0,imgUrl.indexOf("/"));
//                    resource.setDomain(domain);
//                    String path = imgUrl.substring(imgUrl.indexOf("/")+1);
//                    resource.setPathName(path);
//                    resource.setDataType(0);
//                    resource.setPlayTime(0);
//                    resource.setTypeId(1);
//                    resourceService.addResource(resource);
//                }
//            }
//            System.out.println("------ Resource Uploaded ------");
//        }

       //查询数据 胶囊总数
//        System.out.println("Current Capsules Total: " + robotService.capsTotal());

        System.out.println("======== Robot Finish ! ======== \n");
        count++;
    }
}
