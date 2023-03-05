import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num > cnt) {
				while (num != cnt) {
					stack.push(cnt++);
					sb.append("+" + "\n");
				}
				stack.push(cnt++);
				sb.append("+" + "\n");
				if (stack.isEmpty()) {
					System.out.println("NO");
					return;
				}
				stack.pop();
				sb.append("-" + "\n");
			} else if (num == cnt) {
				stack.push(cnt++);
				sb.append("+"+"\n");
				if (stack.isEmpty()) {
					System.out.println("NO");
					return;
				}
				stack.pop();
				sb.append("-" + "\n");
			} else {
				while (true) {
					if (stack.isEmpty()) {
						System.out.println("NO");
						return;
					}
					int tmp = stack.pop();
					sb.append("-" + "\n");
					if (tmp == num) {
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}

}
