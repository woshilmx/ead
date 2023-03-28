package com.lmx.project.until;

import com.google.gson.Gson;
import com.lmx.project.config.FileConfig;
//import com.qiniu.common.QiniuException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class FileUntil {
    @Resource
    private FileConfig fileConfig;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextpath;

    @Value("${ipaddress.huawei}")
    private String address;

    @Value("${qiniu.accessKey}")
    private String accessKey = "j62JgoY3YH4qlPDXWuh-eVDLA-ykn89FOEGypuIH";
    @Value("${qiniu.secretKey}")
    private String secretKey = "stAvnzTLh9c0-sh_Ts8nkN-hn9B5H3ldxbCnEVQR";

    @Value("${qiniu.preurl}")
    private String preurl;

    public String saveFile(InputStream inputStream, String filepath) throws IOException {
        File file = new File(fileConfig.getPre() + filepath);
        log.info(fileConfig.getPre() + filepath);

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

        String bucket = "ead-animer";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filepath;
        //            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);


        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println("成功" + putRet.key);
            System.out.println(putRet.hash);
            return preurl+filepath;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
                ex2.printStackTrace();
            }
        }
        return null;
    }


//        try {
//            if (!file.exists()) {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            }
//            FileOutputStream out = new FileOutputStream(file);
//
//            byte[] bytes = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, length);
//            }
//            out.close();
//            inputStream.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }


    public String getIpaddress() throws UnknownHostException {
//        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String pre = "http://";

        return pre + address + ":" + port + contextpath + "/file/";
    }


}
