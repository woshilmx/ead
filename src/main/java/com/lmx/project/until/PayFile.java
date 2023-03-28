//package com.lmx.project.until;
//
//import com.alipay.easysdk.factory.Factory;
//import com.alipay.easysdk.factory.Factory.Payment;
//import com.alipay.easysdk.kernel.Config;
//import com.alipay.easysdk.kernel.util.ResponseChecker;
//import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
//public class PayFile {
//    public static void main(String[] args) throws Exception {
//        // 1. 设置参数（全局只需设置一次）
//        Factory.setOptions(getOptions());
//        try {
//            // 2. 发起API调用（以创建当面付收款二维码为例）
//            AlipayTradePrecreateResponse response = Payment.FaceToFace()
//                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
//            // 3. 处理响应或异常
//            if (ResponseChecker.success(response)) {
//                System.out.println("调用成功");
//            } else {
//                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
//            }
//        } catch (Exception e) {
//            System.err.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
//    private static Config getOptions() {
//        Config config = new Config();
//        config.protocol = "https";
//        config.gatewayHost = "openapi.alipay.com";
//        config.signType = "RSA2";
//        config.appId = "2021003182673424";
//        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
//        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGb4/6tzNHzNhGiMi4TVdFkdng8nakNgyZc9NcbnCuRxGAOz9f7hyFeB4/AMXpZmVNpFng0MiNYXnARlkkqU6R1O+9kDh/TTHhivljqZ0BTcyOhOgK3ApyosPCnrKt2T/VZd7nNIX+rNHnKaqkxOYObRtWLWF6tkMoaMjTbDf4L5LwfhxIxNrT5OUgFjEvYKu0HFXtW733vHA/pnMHUJ863NcBN5+Ckx8AkVRYj2MfpUpNVNe1J7EzxpAVD3SPF8K5CGLPLQZ59c4J75pOcMBoizyfEsiHCMdvyFt4CfqnyC2VzaM0aof7njlSS6BC6BF+1uMVZWwoW+Bd2AM49TJXAgMBAAECggEAYNTYqyjr+eqVWXDKyt2CX+znN7zNMghwWNNxyWEPLqin9Wo3grx6tLRJbO38n9oOW7dmuaUI37T3QkQpj1uX6TisGilYWHFc3W0BpwSTv02vanRWwwOtTtboFDXE063o6ad/v584aLFIW63KaPYeEwQJra/wR7gidWfMR2Tqh4uJM1B5SeMbHm3aTv3QhpPTk3GmwlXSD69Y8OtD8IjWjADyjLdD8AYtV6okbtwRIRkB8SnEU5dpcYx1httSEL3twOCiVAdtZ2oRXKUyuDKfpcepTXl6UxnQnKyE49KvlRFTN4PWPrNUvdTiflIcJcRcH2AvBBoeAIzRI5vgUF+0UQKBgQDDyJ0Fth7Q82j37wJwLXTYDV5UGFPb8Q/qwQG4t0lNjdJPh2CaWTQe51ABzsKazhElKiIhrXaQRCRCNovCbN81qZaIIPhgb9qn291xtIOwjho0BlXNn5ks+9SCxAIVZ0wJ+B0iiCA3oe5/ysd3bS+SZm0BdvKP4qAhkGrdUSlvjwKBgQCvyJzKhwTqqIcz44fjUrakHEmws6t5w2AysUoIHqQhDYQwcm1u5/ecg53qtOD2TPqnP7bw8Jia3tgtaO+UPaxF5LEhZnbNpUgY8vM5Ex2KU96lW4tltFrAu3pQ46uNowIDeUtcssSQzbus87MRN7mlkvbhlxxEs9Ru5pshpDksuQKBgDGhGBTLjtI+8bvvzXlsPHNMKD4/EHrvh65lkK8nXyCWs9pPIfDebfTrg/+BTxl45SW7oX1SAH0fmOVj+Feny4lJ3QvS98NSd2YXuPHK5ZsK2p+Xf4apT2X0zHD9iN2uGrtoNtJ7GVeg59ljKN8gQW/mIPm/EkvhEeCQQFar7UoHAoGBAJsO6U+1fl7IPktFP88EXVuyIEaglngYIblVriovbTRCygI6TdHxCPilGR26ZF6fVNNHXsZ2VQb339VepUcsh9Q3gRAnZFDOpSRs2qPT+tDUXqeacwJJ72vndGP6EJAl5FbQHewtPTtGX3CWn7RmZXpqgyBHvYXU42TYQquQQ2bxAoGBAK9IJ5wK6HvcSs/Da9iA/42jCJKwtzvZeUOrNtRvzrU77s5jl5TXHx2iKTefz4tjzZNxyEWpa0OwgnUWVhIZn5/3a5vkpigjxBPaHlF+j7FLcjGfxgkPmEaAKPDDi2wCCUVF3YZ9EMC2dOAtXp7j4XOFrZiB6JWUckviy6tMBUha";
//        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "E:\\javaProject2023\\ead\\animer-backend\\src\\main\\resources\\appCertPublicKey_2021003182673424.crt";
//        config.alipayCertPath = "E:\\javaProject2023\\ead\\animer-backend\\src\\main\\resources\\alipayCertPublicKey_RSA2.crt";
//        config.alipayRootCertPath = "E:\\javaProject2023\\ead\\animer-backend\\src\\main\\resources\\alipayRootCert.crt";
//        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
//        // config.alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";
////        //可设置异步通知接收服务地址（可选）
////        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";
////        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
////        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";
//        return config;
//    }
//}
