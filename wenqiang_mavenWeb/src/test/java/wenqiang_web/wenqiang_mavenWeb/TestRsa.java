package wenqiang_web.wenqiang_mavenWeb;

import sun.misc.BASE64Encoder;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.Base64Utils;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.RSAUtils;

public class TestRsa {
	 public static void main(String[] args) throws Exception {
		 String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNM6qzdiqI8QqXWEr86DknHOAJDw/ptbB+2zQze56zVy3BKl9nCBFHPyysRA+jld2uvfLZ1oV1i+WeKNbu48hCttJbVoR+qZE5ewQnYurBrjVnT6Pmsr28TdDQN4XpAEhppBX5tlNwwsR9KxYtoal44WGV+5XOuMcxNhnpHOrDDwIDAQAB";
	        String source = "这是一行测试RSA数字签名的无意义文字";
	        System.out.println("原文字：\r\n" + source);
	        byte[] data = source.getBytes();
	        byte[] encodedData =RSAUtils.decryptByPublicKey(data, publicKey);
	        System.out.println("加密后：\r\n" + new String(encodedData));
	        String base64EncodedData = Base64Utils.encode(encodedData);
	        //System.out.println("加密后BASE64：\r\n" + base64EncodedData);
	        System.out.println("加密后BASE64：\r\n" + new BASE64Encoder().encode(encodedData));
	    }
}
