import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int sequence[] = new int[N];
			int dp[] = new int[N];
			int ret = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				sequence[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (sequence[j] < sequence[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				ret = Math.max(ret, dp[i]);
			}
			sb.append("#" + test_case + " " + ret + "\n");
		}
		System.out.println(sb);
	}

}
