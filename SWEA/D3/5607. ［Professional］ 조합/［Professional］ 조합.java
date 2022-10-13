import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int INF = 1234567891;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		long fac[] = new long[1000001];
		fac[0] = 1;
		for (int i = 1; i < 1000001; i++) {
			fac[i] = (fac[i - 1] * i) % INF;
		}
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N, R;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			long divider = fac[R] * fac[N - R] % INF;
			divider = fermat(divider, INF - 2);
			long ret = fac[N] * divider % INF;
			sb.append("#" + test_case + " " + ret + "\n");
		}
		System.out.println(sb);
	}

	private static long fermat(long divider, int times) {
		long ret = 1;
		while (times > 0) {
			if (times % 2 == 1) {
				ret = (ret * divider) % INF;
			}
			times /= 2;
			divider = (divider * divider) % INF;
		}
		return ret;
	}

}
