import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int stairs[];
	static int dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		stairs = new int[num + 1];
		for (int i = 0; i < num; i++) {
			stairs[i + 1] = Integer.parseInt(br.readLine());
		}
		dp = new int[num + 1][3];
		if (num == 1) {
			System.out.println(stairs[1]);
		} else if (num == 2) {
			System.out.println(stairs[1] + stairs[2]);
		} else {
			dp[1][1] = stairs[1];
			dp[2][1] = stairs[2];
			dp[2][2] = stairs[1] + stairs[2];
			for (int n = 3; n <= num; n++) {
				dp[n][1] = Math.max(dp[n - 2][1] + stairs[n], dp[n - 2][2] + stairs[n]);
				dp[n][2] = dp[n - 1][1] + stairs[n];
			}
			System.out.println(Math.max(dp[num][1], dp[num][2]));
		}
	}

}
