import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int board[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 64 * (50 - 8) * (50 - 8) = 112896
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		double answer = 3000;
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				answer = Math.min(answer, check(i, j));
			}
		}
		System.out.println((int)answer);
	}

	private static double check(int startI, int startJ) {
		double oneCount = 0;
		for (int i = startI; i < startI + 8; i++) {
			for (int j = startJ; j < startJ + 8; j++) {
				if ((i % 2) == (j % 2)) {
					if (board[i][j] != 'W') {
						oneCount++;
					}
				} else {
					if (board[i][j] != 'B') {
						oneCount++;
					}
				}
			}
		}
		double twoCount = 0;
		for (int i = startI; i < startI + 8; i++) {
			for (int j = startJ; j < startJ + 8; j++) {
				if ((i % 2) == (j % 2)) {
					if (board[i][j] != 'B') {
						twoCount++;
					}
				} else {
					if (board[i][j] != 'W') {
						twoCount++;
					}
				}
			}
		}
		return Math.min(oneCount, twoCount);
	}

}
