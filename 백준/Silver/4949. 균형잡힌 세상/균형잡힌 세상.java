import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while (true) {
			Boolean ret = true;
			Stack<Character> stack = new Stack<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			Boolean flag = true;
			while (st.hasMoreTokens()) {
				flag = false;
				String str = st.nextToken();
				int len = str.length();
				for (int i = 0; i < len; i++) {
					char tmp = str.charAt(i);
					if (tmp == '[' || tmp == '(') {
						stack.push(tmp);
					} else if (tmp == ']') {
						if (stack.isEmpty() || stack.pop() != '[') {
							ret = false;
							break;
						}
					} else if (tmp == ')') {
						if (stack.isEmpty() || stack.pop() != '(') {
							ret = false;
							break;
						}
					}
				}
				if (!stack.isEmpty()) {
					ret = false;
				}
				if (ret) {
					sb.append("yes" + "\n");
				} else {
					sb.append("no" + "\n");
				}
			}
			if (flag) {
				break;
			}
		}
		System.out.println(sb);
		return;
	}
}
