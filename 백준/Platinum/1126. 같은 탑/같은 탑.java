import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// N개의 직사각형 블록
	// 두 개의 탑 만들기, 서로 높이 같게
	// 각 탑은 적어도 한 개의 블록 포함
	// 최대한 높이, 모든 블록 사용할 필요는 없음
	// 만들 수 있는 탑 높이의 최댓값은?
	// dp[i][j] = i번째 블럭을 이용해 높이 차가 j인 두 개의 탑을 쌓았을 때 최대 높이

	static int N, total, blocks[], dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		blocks = new int[N];
		st = new StringTokenizer(br.readLine());
		total = 0;
		for (int i = 0; i < N; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
			total += blocks[i];
		}
		int half = total / 2;
		dp = new int[N][total + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < total + 1; j++) {
				dp[i][j] = -1;
			}
		}
		dp[0][0] = 0;
		dp[0][blocks[0]] = blocks[0];
		for (int i = 1; i < N; i++) { // 최대 50
			for (int j = 0; j <= total; j++) { // 최대 500,000
				// 기저 조건이 될 두 개 탑이 존재하지 않는 경우
				if (dp[i - 1][j] == -1) {
					continue;
				}
				// i 번째 블록 쓰지 않는 경우
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				// i 번째 블록을 더 높은 타워에 쌓는 경우
				dp[i][j + blocks[i]] = Math.max(dp[i][j + blocks[i]], dp[i - 1][j] + blocks[i]);
				// i 번째 블록을 더 낮은 타워에 쌓는 경우
				if (blocks[i] > j) { // 블록이 차이보다 커서 최대 높이가 바뀌는 경우
					dp[i][blocks[i] - j] = Math.max(dp[i][blocks[i] - j], (blocks[i] - j) + dp[i - 1][j]);
				} else { // 블록이 차이보다 작아서 최대 높이가 안바뀌는 경우
					dp[i][j - blocks[i]] = Math.max(dp[i][j - blocks[i]], dp[i - 1][j]);
				}
			}
		}
		int ret = dp[N - 1][0];
		if (ret > half || ret == 0) {
			System.out.println(-1);
		} else {
			System.out.println(ret);
		}
	}

}
