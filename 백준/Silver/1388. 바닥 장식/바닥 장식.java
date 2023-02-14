import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] matrix;
	static boolean[][] check;
	static int dx1[] = { -1, 1 };
	static int dy1[] = { 0, 0 };
	static int dx2[] = { 0, 0 };
	static int dy2[] = { -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N][M];
		check = new boolean[N][M];
		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == '-') {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = 1;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!check[i][j]) {
					dfs(i, j, matrix[i][j]);
					count++;
				}
			}
		}

		System.out.println(count);

	}

	private static void dfs(int i, int j, int k) {
		if (check[i][j]) {
			return;
		}
		check[i][j] = true;
		if (k == 0) { // 왼쪽 오른쪽
			for (int d = 0; d < 2; d++) {
				int newI = i + dx2[d];
				int newJ = j + dy2[d];
				if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) {
					if (!check[newI][newJ] && matrix[newI][newJ] == k) {
						dfs(newI, newJ, k);
					}
				}
			}
		} else { // 위 아래
			for (int d = 0; d < 2; d++) {
				int newI = i + dx1[d];
				int newJ = j + dy1[d];
				if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) {
					if (!check[newI][newJ] && matrix[newI][newJ] == k) {
						dfs(newI, newJ, k);
					}
				}
			}
		}
	}

}
