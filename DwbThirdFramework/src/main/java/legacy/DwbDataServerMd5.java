package legacy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DwbDataServerMd5 {
	/*
	 * 计算字符串的md5值
	 */
	public static String getVaneDataServerMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		char[] charArray = inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; ++i) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; ++i) {
			int val = md5Bytes[i] & 0xFF;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/*
	 * 将md5值得小写字母转化为大写字母
	 */
	public static String getMD5ToLarge(String str) {

		char letters[] = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {

			char letter = str.charAt(i);
			if (letter >= 'a' && letter <= 'z')
				letter = (char) (letter - 32);
			else if (letter >= 'A' && letter <= 'Z')
				letter = (char) (letter + 32);
			letters[i] = letter;
		}

		return new String(letters);
	}

	public static String getTimestamp() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String dateString = formatter.format(currentTime);
		System.out.println(dateString);
		return dateString;
	}

	public static void main(String[] args) {
		// String
		// res=getVaneDataServerMD5(getVaneDataServerMD5(1394621880+"B_b@s#1.1.0"));
		String str = "1f3ert5678SQQQasaasa3902-010-1-21";
		String md5 = getVaneDataServerMD5(str);
		System.out.println("md5 is:" + md5);
		String str2 = getMD5ToLarge(md5);
		System.out.println(str2);
	}

}
