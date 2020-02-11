package com.timerec.robot.util;

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

public class HttpClientTumblr {
    public static void main(String[] args) {
        doGet("https://happifying.com/");
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
                System.out.println(document.getElementsByTag("article"));

                for (Element article : document.getElementsByTag("article")) {
                    System.out.println(StringUtils.repeat("=", 50) + " separator " + StringUtils.repeat("=", 50));
                    String imgUrl = article.getElementsByTag("img").attr("src");
                    // 处理话题标签#
                    StringBuilder tags = new StringBuilder();
                    if (article.getElementsByClass("tag-link").text().equals("")){
                        for (String tg : article.getElementsByClass("tags").eachText()){
                            if (tg.equals("happifying")){
                                tg = "";
                            }else{
                                tags.append("#").append(tg).append(" ");
                            }
                        }
                    }else {
                        for (String t : article.getElementsByClass("tag-link").eachText()){
                            if (t.equals("MemesInPyjamas")){
                                t = "";
                            }else{
                                tags.append("#").append(t).append(" ");
                            }
                        }
                    }
                    String caption = article.getElementsByClass("caption").text();
                    if (tags.equals("")){
                        caption += " #timerec";
                    }else{
                        caption += " " + tags;
                    }
                    // 控制台打印结果
                    System.out.println(imgUrl+"\n"+caption);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
