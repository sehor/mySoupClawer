package clawer.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static long extAndToNumber(String input) {
		input = input.replaceAll("[\\s+\\-_;]", "");
		System.out.println(input);
		String regx = ".*第([一二三四五六七八九零十百千万1-9]{1,10})[章节卷话].*"; // 把中文数字提取出来；
		Pattern p = Pattern.compile(regx);
		Matcher matcher = p.matcher(input);
		String result = "未找到章节数";

		if (matcher.matches()) {

			result = matcher.group(1);

		} else {
			System.out.println("未找到章节数");
			return -1;
		}
		result = result.replaceFirst("^十", "一十"); // 以十开头的加“一”

		long resut_number = (long) ZhtoNumber(result);

		return resut_number;

	}

	private static double ZhtoNumber(String zhNumber) {
		if(zhNumber.matches("^\\d+$")) {
			return Long.valueOf(zhNumber);
		}
		int len = zhNumber.length();
		Map<Integer, String> map = new HashMap<>();
		if (zhNumber.length() == 0) {
			return 0;
		}

		int k = -1;
		if ((k = zhNumber.indexOf("万")) >= 1) {
			map.put(4, zhNumber.substring(k - 1, k));
		}
		if ((k = zhNumber.indexOf("千")) >= 1) {
			map.put(3, zhNumber.substring(k - 1, k));
		}
		if ((k = zhNumber.indexOf("百")) >= 1) {
			map.put(2, zhNumber.substring(k - 1, k));
		}
		if ((k = zhNumber.indexOf("十")) >= 1) {
			map.put(1, zhNumber.substring(k - 1, k));
		}
		map.put(0, zhNumber.substring(len - 1, len));

		Map<String, Integer> map_s2n = Map.of("一", 1, "二", 2, "三", 3, "四", 4, "五", 5, "六", 6, "七", 7, "八", 8, "九", 9);

		double result_num = 0;
		for (Entry<Integer, String> entry : map.entrySet()) {
			Integer m = map_s2n.get(entry.getValue());
			int k1 = m != null ? m : 0;
			result_num = result_num + k1 * Math.pow(10, entry.getKey());
		}

		return result_num;
	}
	
	public static String trimText(String text) {
		if(text==null) {
			return "";
		}
		return text.trim().replaceAll("[_\\-\\?？\\!！——]", "").replaceAll("\\s+", " ");
	}

}
