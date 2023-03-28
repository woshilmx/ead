package com.lmx.project.until;

import cn.hutool.http.HttpRequest;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.lmx.project.model.chatgpt.ChatGptRepose;
import com.lmx.project.model.chatgpt.OpenAiChtGpt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class ChatGptUntil {

    @Value("${openai.token}")
    private String ApiKey;
    @Value("${proxy.host}")
    private String host;
    @Value("${proxy.port}")
    private int port;
    /**
     * 获取chatgpt的答案
     */
    public ChatGptRepose getRespost(List<OpenAiChtGpt> messagelist) {
        String url = "https://api.openai.com/v1/chat/completions";
        HashMap<String, Object> bodymap = new HashMap<>();

        bodymap.put("model", "gpt-3.5-turbo");
//        bodymap.put("stream",true);
        bodymap.put("messages", messagelist);
        Gson gson = new Gson();
        String s = gson.toJson(bodymap);
//        System.out.println(s);
        HttpRequest authorization1 = HttpRequest.post(url).setHttpProxy(host, port)
                .header("Authorization", "Bearer " + ApiKey)
                .header("Content-Type", "application/json")
                .body(s);
//        System.out.println(authorization1);
        String authorization = authorization1
                .execute()
                .body();
        ChatGptRepose o = gson.fromJson(authorization, new TypeToken<ChatGptRepose>() {
        }.getType());

        return o;
    }


}
