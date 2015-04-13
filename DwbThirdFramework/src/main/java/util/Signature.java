package util;

import com.vanelife.tools.VaneDataServerMd5;

public class Signature {

	public static String getSignature(String need_Signature_str, String secret) {
		String signature_str_low = VaneDataServerMd5
				.getVaneDataServerMD5(need_Signature_str);// 获取需要签名的参数MD5
		String signature_str_up = VaneDataServerMd5
				.getMD5ToLarge(signature_str_low);// 将获取的MD5小写字母转化为大写
		return signature_str_up;
	}
	
}
