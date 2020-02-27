package com.timerec.robot.util;

import com.timerec.robot.entity.Capsule;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

// "https://the-memedaddy.tumblr.com/", // 搞笑
// "https://memesinpyjamas.tumblr.com/", // 搞笑
// "https://kittens-jpg.tumblr.com/", // 猫
// "https://endless-puppies.tumblr.com/", // 狗
// "https://tiktokarchive.tumblr.com/tagged/fave", //tiktok视频


public class HttpClientTumblr {
    private static Capsule capsule = new Capsule();

    private static LinkedList<String> capStrs = new LinkedList<>();

    private static LinkedList<ArrayList<String>> resources = new LinkedList<>();

    private static LinkedList<String> posts = new LinkedList<>();

    private static String postUrl;


    public static void main(String[] args) {
        doGet("https://cute.maxtree.me/post/190823681203");
    }

    public static void doGet(String urlStr) {
        //生成httpclient，打开一浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //创建get请求，输入网址
        HttpGet request = new HttpGet(urlStr);
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        try {
            //执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);
            //判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");

                //Jsoup解析html
                Document document = Jsoup.parse(html);
                //通过标签获取article
                for (Element article : document.getElementsByTag("article")) {
                    System.out.println(StringUtils.repeat("=", 50) + " separator " + StringUtils.repeat("=", 50));
                    System.out.println(article);

                    String imgUrl = article.getElementsByTag("img").attr("src");
                    // 处理话题标签#
                    String tags = "";
                    tags = article.getElementsByClass("post_tag").text();
                    if (tags.isEmpty()){
                        tags = article.getElementsByClass("tag-link").text();
                        if (tags.isEmpty()){
                            tags = article.getElementsByClass("tags").text();
                        }
                    }

                    String caption = article.getElementsByClass("caption").text()
                            .replace("irl","me").replace(" irl","me")
                            .replace("(Source)","");

                    if (!tags.equals("")) {
                        caption = "#"+ tags.replaceAll(" ", " #");
                        // 截取前6个#
                        int tSpace = StringUtils.ordinalIndexOf(caption, "#", 7);
                        if (tSpace != -1){
                            caption = caption.substring(0, tSpace);
                        }
                    }

                    String videoUrl = article.getElementsByTag("source").attr("src");
                    if (!caption.isEmpty()){
                        capsule.setCapsuleGuid(article.getElementsByTag("article").attr("id"));
                        capsule.setContentStr(caption);
                        if (!videoUrl.isEmpty()){
                            capsule.setResType(2);
                            System.out.println(videoUrl+"\n"+caption);
                        }else if (!imgUrl.isEmpty()){
                            capsule.setResType(1);
                            System.out.println(imgUrl+"\n"+caption);
                        }else{
                            System.out.println("***Nothing***");
                        }
                    }else{
                        System.out.println("***Skip***");
                    }

                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
