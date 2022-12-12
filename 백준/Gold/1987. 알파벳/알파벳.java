import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int ret;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char matrix[][] = new char[R][C];
		boolean check[] = new boolean[26]; // 0 - A, 1 - B, ...
		for (int i = 0; i < R; i++) {
			matrix[i] = br.readLine().toCharArray();
		}
		check[matrix[0][0] - 65] = true;
		dfs(matrix, R, C, check, 0, 0, 0);
		System.out.println(ret+1);
	}

	private static void dfs(char[][] matrix, int R, int C, boolean[] c, int curR, int curC, int cnt) {
		ret = Math.max(ret, cnt);
		for (int i = 0; i < 4; i++) {
			int tmpR = curR + dr[i];
			int tmpC = curC + dc[i];
			if (0 <= tmpR && tmpR < R && 0 <= tmpC && tmpC < C) {
				int tmp = matrix[tmpR][tmpC] - 65;
				if (c[tmp] == false) {
					c[tmp] = true;
					dfs(matrix, R, C, c, tmpR, tmpC, cnt + 1);
					c[tmp] = false;
				}
			}
		}
	}

}
