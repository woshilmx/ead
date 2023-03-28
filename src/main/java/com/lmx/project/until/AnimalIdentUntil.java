package com.lmx.project.until;

import com.baidu.aip.imageclassify.AipImageClassify;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;

/**
 * @author 李满祥
 * 动物识别的工具类
 */
@Component
@Data
public class AnimalIdentUntil {

    @Resource
    private AipImageClassify client;

    public JSONObject getAnimerByImage(InputStream imageinputstream) throws IOException {


        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("baike_num", "5");


        byte[] bytes = new byte[1024];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 0;
        while ((length = imageinputstream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, length);
        }
        byte[] resultbyte = byteArrayOutputStream.toByteArray();


        // 参数为二进制数组
//        byte[] file = readFile("test.jpg");
        JSONObject res = client.animalDetect(resultbyte, options);

        return res;
    }
}
