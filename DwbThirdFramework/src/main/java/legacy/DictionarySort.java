package legacy;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;


/*
 * 按字典排序
 */
public class DictionarySort {
	static StringBuffer sb = new StringBuffer();
	static int i, j, m;

	public static String getDictionarySort(ArrayList<String> al) {
		/*
		 * 运用Collections的sort（）方法对其进行排序 sort（）方法需要传 连个参数， 一个是需要进行排序的Collection
		 * 另一个是一个Comparator
		 */
		String strArray[] = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "" };
		ArrayList<String> list = al;
		for (i = 0; i < list.size(); i++) {
			strArray[i] = (String) list.get(i);
		}
		sb = new StringBuffer();
		for (m = 0; m < strArray.length; m++) {
			sb.append(strArray[m]);
		}
		// System.out.println("排序前："+sb.toString());
		Collections.sort(list, new SpellComparator());
		for (int i = 0; i < list.size(); i++) {
			strArray[i] = (String) list.get(i);// 转化为字符数组
		}
		// 将字符数组装化为字符串
		sb = new StringBuffer();
		for (int m = 0; m < strArray.length; m++) {
			sb.append(strArray[m]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// String
		// res=getVaneDataServerMD5(getVaneDataServerMD5(1394621880+"B_b@s#1.1.0"));
		ArrayList<String> al = new ArrayList<String>();
		al.add("red");
		al.add("rallow");
		al.add("bbck");
		al.add("bbcen");
		System.out.println("排序后：" + DictionarySort.getDictionarySort(al));

	}
}

/**
 * 汉字拼音排序比较器
 */
class SpellComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		try {
			// 取得比较对象的汉字编码，并将其转换成字符串
			String s1 = new String(o1.toString().getBytes("GB2312"),
					"ISO-8859-1");
			String s2 = new String(o2.toString().getBytes("GB2312"),
					"ISO-8859-1");
			// 运用String类的 compareTo（）方法对两对象进行比较
			return s1.compareTo(s2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// String
//		// res=getVaneDataServerMD5(getVaneDataServerMD5(1394621880+"B_b@s#1.1.0"));
//		String str = "1f3ert5678SQQQasaasa3902-010-1-21";
//		String md5 = getVaneDataServerMD5(str);
//		System.out.println("md5 is:" + md5);
//		String str2 = getMD5ToLarge(md5);
//		System.out.println(str2);
	}
}
