package com.lmx.project.config;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imageprocess.AipImageProcess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;

@Configuration
public class BaiDuConfigure {

    //设置APPID/AK/SK
    @Value("${baidu.AppId}")
    private String appId ;
    @Value("${baidu.ApiKey}")
    private String apiKey;
    @Value("${baidu.SecretKey}")
    private  String secretKey ;



    //设置百度图像风格转化的
    @Value("${baiduimage.AppId}")
    private String imageappId ;
    @Value("${baiduimage.ApiKey}")
    private String imageapiKey;
    @Value("${baiduimage.SecretKey}")
    private  String imagesecretKey ;



    @Bean
    public AipImageClassify aipImageClassify(){
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    @Bean
    public AipImageProcess aipImageProcess(){
        // 初始化一个AipImageProcess
        AipImageProcess client = new AipImageProcess(imageappId, imageapiKey, imagesecretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        return client;
    }

}
