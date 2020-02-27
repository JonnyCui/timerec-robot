package com.timerec.robot.util;

import com.timerec.robot.entity.ContentArticle;
import com.timerec.robot.service.ICapsuleTopicService;
import com.timerec.robot.service.IContentArticleService;
import com.timerec.robot.service.ITopicService;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;

@Component
public class HttpClientEllo {

// 被 Autowired注解 报空指针，注入失败搞的绝望过吗？
//private final ITopicService topicService;
//
//private final ICapsuleTopicService capsuleTopicService;
//
//private final IContentArticleService contentArticleService;
//
//    public HttpClientEllo(ITopicService topicService, ICapsuleTopicService capsuleTopicService, IContentArticleService contentArticleService) {
//        this.topicService = topicService;
//        this.capsuleTopicService = capsuleTopicService;
//        this.contentArticleService = contentArticleService;
//    }

    @Resource
    private ITopicService topicService;

    @Resource
    private ICapsuleTopicService capsuleTopicService;

    @Resource
    private IContentArticleService contentArticleService;

    public void httpclient(String address) {

        //生成httpclient，打开一浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //创建get请求，输入网址
        HttpGet request = new HttpGet(address);
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        String caption = "";
//        ArrayList<String> imgUrls;
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
//                System.out.println(document.getElementsByTag("article"));

                for (Element article : document.getElementsByTag("article")) {
                    // 分隔符
                    System.out.println(StringUtils.repeat("-", 50) + " separator " + StringUtils.repeat("-", 50));
                    ContentArticle contentArticle = new ContentArticle();

                    // 特定用户
                    if (address.equals("https://ello.co/conami")){
                        contentArticle.setAssignedUserGuid("1000065"); } // Joyce Cha
                    else if (address.equals("https://ello.co/fokality")){
                        contentArticle.setAssignedUserGuid("1000088");} // Rico Carver
                    else if (address.equals("https://ello.co/alexgzarate")){
                        contentArticle.setAssignedUserGuid("1000041"); } // Alex G Zarate
                    else if (address.equals("https://ello.co/bonniegrrl")){
                        contentArticle.setAssignedUserGuid("1000048"); }  // Bonnie
                    // 打印查看文章
//                    System.out.println(article);
//                    System.out.println("\n Resource image: " + imgUrls);

                    // 转到原po地址并解析
                    String postUrl = article.getElementsByTag("a").eq(1).attr("href");
                    if (postUrl.equals("https://ello.co/minusbaby/post/rsndlmv5bgc3rc57iwyeqw")){
                        postUrl ="https://ello.co/kseniaanske/post/im0vxa4b5penad-ziyq12w";
                    }
                    Document content = Jsoup.connect(postUrl).get();

                    // elloposts_ + 原po地址后22位(为所有Ello post的唯一码)生成 32位唯一 capsuleGuid，防止生成重复胶囊
                    contentArticle.setArticleGuid("elloposts_" + postUrl.substring(postUrl.lastIndexOf("/")+1));

                    // 获取文字内容
                    String p = content.select("p").text();
                    p = p.replaceAll("@","#").replace("ig : jonathan_tsc", "");
                    int index;
                    // 节选+筛选文字内容
                    if (!p.contains("http")){
                        index = p.length();
                    }else{
                        index = p.indexOf("http");
                    }
                    if (p.length() > 500) {
                        index = 499;
                    }
                    // 获取外链之前的字符串,并筛选#标签
                    caption = StringUtils.substringBefore(p.substring(0, index), "Read more in")
                            .replace("Full recipe", "")
                            .replace("#Ello", "")
                            .replace("#ello", "")
                            .replace("#conamicf", "Healthy")
                            .replace("https://ello.co/","");

                    if (address.equals("https://ello.co/alexgzarate")){
                        caption = caption.replace("#onwards", "")
                                .replace("Onwards! + ", "")
                                .substring(0, caption.indexOf(" #"));
                    }

                    // 判断空文字内容
                    while (caption.equals("") || caption.length()<2) {
                        String[] randStr = new String[]{"Love it", "#Timerec","Felling Good #Today","#timerec","#CoolStuff","Awesome","Good","#Hello"};
                        caption = randStr[new Random().nextInt(7)];
                    }

                    // 处理#话题标签
                    if (caption.contains("#")){
                        // 截取前5个#
                        int tSpace = StringUtils.ordinalIndexOf(caption, "#", 6);
                        if (tSpace != -1){caption = caption.substring(0, tSpace);}
                    }

                    // Content Article 实体 内容赋值/更新数据
                    contentArticle.setContentStr(caption);

                    // 获取图片地址
                    String imgUrls = null;
                    for (Element el : article.getElementsByTag("img")) {
                        String img = el.attr("src");
                        if (img.contains(".jpg") || img.contains(".png")) {
                            img.substring(img.indexOf("//") + 2);
                            contentArticle.setResourceType(1);
                            if (imgUrls == null) {
                                imgUrls = img;
                            } else {
                                imgUrls += " " + img;
                            }
                        }
                    }
                    contentArticle.setResourceUrl(imgUrls);

//                    // 插入数据
                    contentArticleService.addArticle(contentArticle);
                }
            } else {
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
        System.out.println("HCE Done Running!");
    }

//    public static LinkedList<ArrayList<String>> getResources() {
//        return resources;
//    }

//    public static String getElloId(int d){
//
//        System.out.println(posts.get(d));
//        // elloposts_ + 原po地址后22位(为所有Ello post的唯一码)生成 32位唯一 capsuleGuid，防止生成重复胶囊
//        return "elloposts_" + posts.get(d).substring(postUrl.lastIndexOf("/")+1);
//    }
}
