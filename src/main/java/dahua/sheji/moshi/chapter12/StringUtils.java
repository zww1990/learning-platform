package dahua.sheji.moshi.chapter12;

public class StringUtils {
	public static String repeat(String str, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.repeat("-", 3));
	}
}
