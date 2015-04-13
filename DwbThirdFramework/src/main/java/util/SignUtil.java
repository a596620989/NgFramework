package util;

public class SignUtil {
	
	public static String sign(String input){
		
		System.out.println("input is ::"+input);
		String sign = Signature.getSignature(input, "");
		
		return sign;
		
	}

}
