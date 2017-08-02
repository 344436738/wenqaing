package wenqiang_web.wenqiang_mavenWeb;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.Base64Utils;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util.RSAUtils;



public class TestLog {
   
	public static void main(String[] args) throws Exception {
		String str="2017-07-31";
		byte[] data=str.getBytes();
		String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI0zqrN2KojxCpdYSvzoOScc4AkPD+m1sH7bNDN7nrNXLcEqX2cIEUc/LKxED6OV3a698tnWhXWL5Z4o1u7jyEK20ltWhH6pkTl7BCdi6sGuNWdPo+ayvbxN0NA3hekASGmkFfm2U3DCxH0rFi2hqXjhYZX7lc64xzE2Gekc6sMPAgMBAAECgYBCDcNzBHpNaYUs247o1LG3zFg5pKQrDmWsUlsPImtRNh0fWfUbO7jpfXImfAhc3ETB8/XhVetE29bkr/Ir+r65Tt66x9VXrJ22rw7JyUNzd4QaQV16nxIy4EDHewOyS1FlypO5/qqnhSYKlFpMv0umiA+rmixPxXRX2y9AFangUQJBAParjvtmXmqh+HHEhWkArgSiBd0iunKWpp37SZIABbC2FC+YnsCSEEkRHHHekGVfSBFGHGU8L1EMIWobRLsvBDMCQQCSiuLyY3yZZ9Yp/FcCAPNBb49th7PreZXCG2+aDoT6iTB/UMb5hjS0krsaRcAPJTet3rA7c2AHfEpvkMsNXQm1AkEAvVffPzbLDssZrCqPxZQR/9hm6+H2gHMHnoj/ZiTCOWzqfcaHRYZZHd0WmomfCVFkPm406biTEYFgPNSFo7FpZQJACAJrURA6+S+Btx3Bq6xdn0/3qObn4JDurINPYD426nKhIfRKS80YCF6Ut2TkkG9aUbiALV+q+f3DG8HwNzTEmQJAc6cTFik9Zxc0SD5G/U7pVLgToL/+HthoQBEmqSHJbE5nXUEPu9yt+8WyR2ckallfadC6qho8NeFTc3rhvU1c9g==";
		String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNM6qzdiqI8QqXWEr86DknHOAJDw/ptbB+2zQze56zVy3BKl9nCBFHPyysRA+jld2uvfLZ1oV1i+WeKNbu48hCttJbVoR+qZE5ewQnYurBrjVnT6Pmsr28TdDQN4XpAEhppBX5tlNwwsR9KxYtoal44WGV+5XOuMcxNhnpHOrDDwIDAQAB";
		byte[] data1=	RSAUtils.encryptByPublicKey(data, publicKey);
		
		System.out.println(data1);
	String base64=	Base64Utils.encode(data1);
		System.out.println(base64);
		
		byte[] data2=	RSAUtils.decryptByPrivateKey(Base64Utils.decode(base64), privateKey);
		System.out.println(new String(data2));
		
		
		
	}
		
}
