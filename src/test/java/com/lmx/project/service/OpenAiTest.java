package com.lmx.project.service;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lmx.project.model.chatgpt.ChatGptRepose;
import com.lmx.project.model.chatgpt.OpenAiChtGpt;
import com.lmx.project.until.ChatGptUntil;
import com.theokanning.openai.completion.CompletionChoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * openAi测试类
 *
 * @author wuKeFan
 * @date 2023-02-10 15:37:01
 */
@Slf4j
@SpringBootTest
public class OpenAiTest {


    @Resource
    private ChatGptUntil chatGptUntil;

    @Test
    public void test() {

        ArrayList<OpenAiChtGpt> messagelist = new ArrayList<>();
        messagelist.add(new OpenAiChtGpt("user", "详细介绍一下大熊猫"));
        ChatGptRepose respost = chatGptUntil.getRespost(messagelist);
        System.out.println(respost.getChoices().get(0).getMessage().getContent());
    }


    /**
     * openAi接口请求API
     */
//    @Test
//    public void test() {
//        List<CompletionChoice> questionAnswer = OpenAiUtils.getQuestionAnswer("如何在java程序中接入百度图像接口");
//        for (CompletionChoice completionChoice : questionAnswer) {
//            System.out.println(completionChoice.getText());
//        }
////        List<CompletionChoice> openAiApi = OpenAiUtils.getOpenAiApi("使用SpringBoot框架进行Http请求");
////        for (CompletionChoice completionChoice : openAiApi) {
////            System.out.println(completionChoice.getText());
////        }
//    }


//    @Test
//    public void chatgpt() throws JSONException {
////        String url = "https://api.openai.com/v1/chat/completions";
////        HashMap<String, String> headermap = new HashMap<>();
////        HashMap<String, Object> bodymap = new HashMap<>();
////        bodymap.put("model", "gpt-3.5-turbo");
////        ArrayList<Object> messagelist = new ArrayList<>();
//        OpenAiChtGpt e = new OpenAiChtGpt("user", "用户-题库表\n" +
//                "\n" +
//                "- 用户id \n" +
//                "- 题目id\n" +
//                "- 状态 0 - 错误 1- 正确\n" +
//                "- 创建时间\n" +
//                "- 更新时间\n" +
//                "- 是否删除，请根据这段文字生成mysql的建表语句");
//        ArrayList<OpenAiChtGpt> messagelist = new ArrayList<>();
//        messagelist.add(e);
//
//
//        ChatGptRepose o = getRespost(messagelist);
//        if (o != null) {
//            for (Choice choice : o.getChoices()) {
//                System.out.println(choice.getMessage().getRole() + ":" + choice.getMessage().getContent());
//                messagelist.add(choice.getMessage());
//            }
//            OpenAiChtGpt two = new OpenAiChtGpt("user", "将汉字添加到comment属性");
//            messagelist.add(two);
//            System.out.println(messagelist);
//            ChatGptRepose respost = getRespost(messagelist);
//            System.out.println(respost);
//        }
//
//
//    }
}

//@Data
//@AllArgsConstructor
//class OpenAiChtGpt {
//    private String role;
//    private String content;
//}
//
//@Data
//class Choice {
//    private OpenAiChtGpt message;
//}
//
//@AllArgsConstructor
//@Data
//class ChatGptRepose {
//
//    private String id;
//    private String object;
//    private Long created;
//    private String model;
//    private Object usage;
//    private List<Choice> choices;
//    private String finish_reason;
//    private int index;
//
//
//}