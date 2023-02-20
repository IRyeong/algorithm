import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		int status = 1<<21;
		int num;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			switch (command) {
			case "add":
				num = Integer.parseInt(st.nextToken());
				status |= 1<<num;
				break;
			case "remove":
				num = Integer.parseInt(st.nextToken());
				status &= ~(1<<num);
				break;
			case "check":
				num = Integer.parseInt(st.nextToken());
				if ((status & 1<<num) == 1<<num) {
					sb.append(1+"\n");
				} else {
					sb.append(0+"\n");
				}
				break;
			case "toggle":
				num = Integer.parseInt(st.nextToken());
				if ((status & 1<<num) == 1<<num) {
					status &= ~(1<<num);
				} else {
					status |= 1<<num;
				}
				break;
			case "all":
				status = ~(1<<21);
				break;
			case "empty":
				status = 1<<21;
			}
		}
		System.out.println(sb);
	}

}
