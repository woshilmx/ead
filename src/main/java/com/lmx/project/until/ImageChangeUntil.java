package com.lmx.project.until;

import com.baidu.aip.imageprocess.AipImageProcess;
import com.lmx.project.config.RabbitMQConfigure;
import com.lmx.project.model.enums.ImageMode;
import org.json.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * 人物图片动漫化的照片
 */
@Component
public class ImageChangeUntil {

    @Value("${file.pre}")
    private String pre;

    @Resource
    private FileUntil fileUntil;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private AipImageProcess aipImageProcess;
//    //设置APPID/AK/SK
//    public static final String APP_ID = "28150947";
//    public static final String API_KEY = "ymFaLFGtu3o5MK4CdDjXERBf";
//    public static final String SECRET_KEY = "S07GnTZEFVlH0R3eIZV9pSUlFeQkWHjS";


    /**
     * 传入inputstream，返回风格化后的图片
     */
    public String imageChange(InputStream inputStream, ImageMode imageMode) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, length);
        }
        byte[] result = byteArrayOutputStream.toByteArray();

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();

//            """ 如果有可选参数 """
        options.put("option", imageMode.toString());

        // 参数为二进制数组
        JSONObject jsonObject = aipImageProcess.styleTrans(result, options);
//        System.out.println(jsonObject);
        try {
            String image = jsonObject.get("image").toString();
            if (image != null) {
                String path = pre + "change/";
                String replace = UUID.randomUUID().toString().replace("-", "");
                replace = replace + ".jpg";
                path = path + replace;
                base64StrToImage(image, path);
                String ipaddress = fileUntil.getIpaddress();
                MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
//                设置Expiration属性
                        message.getMessageProperties().setExpiration("1800000");
                        return message;

                    }
                };
                rabbitTemplate.convertAndSend(RabbitMQConfigure.TocpicExchangeName, "imagechange.file", path,messagePostProcessor);
                return ipaddress + "change/" + replace;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * base64 转图片
     */
    private boolean base64StrToImage(String imgStr, String path) {

        if (imgStr == null)
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // 解密
            byte[] b = decoder.decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


