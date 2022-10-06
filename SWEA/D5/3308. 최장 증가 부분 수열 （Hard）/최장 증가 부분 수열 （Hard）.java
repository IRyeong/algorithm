import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				sequence[i] = Integer.parseInt(st.nextToken());
			}
			int ret = 0;
			for (int i = 0; i < N; i++) {
				int idx = Arrays.binarySearch(dp, 0, ret, sequence[i]);
				idx = Math.abs(idx)-1;
				dp[idx]=sequence[i];
				if(ret == idx) {
					ret++;
				}
			}
			sb.append("#" + test_case + " " + ret + "\n");
		}
		System.out.println(sb);
	}

}
