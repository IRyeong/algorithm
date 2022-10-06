import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static long dp[][];
	static char s[];
	static char start[] = { '[', '(', '{' };
	static char end[] = { ']', ')', '}' };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		s = new char[N];
		for (int i = 0; i < N; i++) {
			s[i] = input.charAt(i);
		}
		dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		long ret = find(0, N - 1);
		if (ret >= 100000) {
			System.out.printf("%05d\n", ret % 100000);
		} else {
			System.out.println(ret);
		}
	}

	private static long find(int i, int j) {
		if (i > j) {
			return 1;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		dp[i][j] = 0;
		for (int k = i + 1; k <= j; k += 2) {
			for (int d = 0; d < 3; d++) {
				if (s[i] == start[d] || s[i] == '?') {
					if (s[k] == end[d] || s[k] == '?') {
						dp[i][j] += find(i + 1, k - 1) * find(k + 1, j);
						if (dp[i][j] >= 100000) {
							dp[i][j] %= 100000;
							dp[i][j] += 100000;
						}
					}
				}
			}
		}
		return dp[i][j];
	}

}
