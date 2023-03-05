import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while (true) {
			String str = br.readLine();
			if (str.equals("0")) {
				break;
			}
			int len = str.length();
			boolean ret = true;
			for (int i = 0; i < len / 2; i++) {
				if (str.charAt(i) != str.charAt(len - i - 1)) {
					ret = false;
					break;
				}
			}
			if (ret) {
				sb.append("yes" + "\n");
			} else {
				sb.append("no" + "\n");
			}
		}
		System.out.println(sb);
	}

}
