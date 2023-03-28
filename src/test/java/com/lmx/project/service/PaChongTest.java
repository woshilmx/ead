package com.lmx.project.service;


import com.lmx.project.model.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class PaChongTest {
    @Resource
    private NewsService newsService;
//    @Resource
//    private RestTemplate restTemplate;

    /**
    * 爬取央视网新闻
    * */
    @Test
    public void pachong() throws IOException, ParseException {
        String preurl = "https://search.cctv.com/search.php?qtext=濒危&sort=relevance&type=web&vtime=&datepid=1&channel=&page=%d";

        for ( int page = 1; page <=30; page++) {
           String url = String.format(preurl, page);
            System.out.println(url);
            Document document = Jsoup.connect(url)
                    .get();
            if (document == null) {
                return;
            }
//        System.out.println(document);
            Elements select = document.select(".tright");
            ArrayList<News> newsArrayList = new ArrayList<>();
            for (Element element : select) {
//            System.out.println(element);
                String title = element.select(".bre").text();
//                System.out.printf("标题是：%s\n", title);
                String data = element.select(".tim").text().replace("发布时间：", "");
//                System.out.printf("出版时间：%s\n", data);
                String orgin = element.select(".src").text().replace("来源：", "");
//                System.out.printf("来源：%s\n", orgin);
                String imageurl = element.select("img").attr("src");
//                System.out.printf("图片:%s\n", imageurl);
                String contenturl = element.selectXpath("//h3[@class=\"tit\"]/span").attr("lanmu1");
//                System.out.printf("内容url:%s\n", contenturl);
//                新建新闻对象
                News news = new News();
                news.setName(title);
                news.setCoverimg(imageurl);
                news.setContent(contenturl);
//            2023-01-03 14:10:01
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (!data.equals("")) {
                    Date parse = simpleDateFormat.parse(data);
                    news.setReleasetime(parse);
                }
                news.setNewscontent(orgin);
                newsArrayList.add(news);
//                newsService.saveBatch(news);

            }
            newsService.saveBatch(newsArrayList);
        }
    }

//    @Test
//    public void  get(){
//        ResponseEntity<Object> forEntity = restTemplate.getForEntity("http://www.kepu.gov.cn/restful/search/article?keys=%E6%BF%92%E5%8D%B1&page=1&pageSize=10", Object.class);
//        System.out.println(forEntity);
//    }


}
