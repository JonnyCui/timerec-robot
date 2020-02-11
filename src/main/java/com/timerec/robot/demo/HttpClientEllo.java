package com.timerec.robot.demo;


import com.timerec.robot.entity.Capsule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HttpClientEllo {
    private static Capsule capsule = new Capsule();

    public static Capsule main(String address) {

        //生成httpclient，打开一浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //创建get请求，输入网址
        HttpGet request = new HttpGet(address);
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        String caption = "";
        try{
            //执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);
            //判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");

                //Jsoup解析html
                Document document = Jsoup.parse(html);
                //通过标签获取article
//               System.out.println(document.getElementsByTag("article"));

                for (Element article : document.getElementsByTag("article")){
//                    System.out.println(StringUtils.repeat("=",50) + " separator " + StringUtils.repeat("=",50));
                    String imgUrl = article.getElementsByTag("img").attr("src");
                    // 转到原po地址并解析
                    String postUrl = article.getElementsByTag("a").eq(1).attr("href");
                    Document content = Jsoup.connect(postUrl).get();
                    // 获取文字内容
                    String p = content.select("p").text();
                    int index = p.length();
                    // 节选+筛选文字内容
                    if (p.length() > 500){
                        index = 499;
                    }
                    caption = p.substring(0,index).replace("Onwards! +", "")
                            .replace("#Ello", "")
                            .replace("#ello", "");

                    if (caption.equals("")){
                        caption = content.getElementsByTag("span").text().substring(0,11);
                    }
                    // 控制台打印结果
//                    System.out.println(imgUrl+"\n"+caption);
                    capsule.setContentStr(caption);
                }
            } else{
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭 httpclient
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return capsule;
    }

}
