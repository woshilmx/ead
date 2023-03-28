package com.lmx.project.until;

import com.lmx.project.model.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PaChongUntil {

    /**
    *从央视网获取新闻
    * */
    public List<News> getnewsByNetwork(String text,int page) throws ParseException, IOException {
        String preurl = "https://search.cctv.com/search.php?qtext=%s&sort=relevance&type=web&vtime=&datepid=1&channel=&page=%d";
        ArrayList<News> newsArrayList = new ArrayList<>();
//        for ( int page = 1; page <=30; page++) {
            String url = String.format(preurl,text, page);
            System.out.println(url);
            Document document = Jsoup.connect(url)
                    .get();
//        System.out.println(document);
            Elements select = document.select(".tright");

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
//        }
        return newsArrayList;
    }
}
