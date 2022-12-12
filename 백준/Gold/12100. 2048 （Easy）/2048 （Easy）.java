import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max = Integer.MIN_VALUE;
	static int matrix[][];
	static String strt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		matrix = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0, "");
		System.out.println(max);
	}

	private static void perm(int cnt, String str) {
		if (cnt == 5) {
			int tmpMatrix[][] = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					tmpMatrix[i][j] = matrix[i][j];
				}
			}
			for (int i = 0; i < 5; i++) {
				move(str.charAt(i) - 48, tmpMatrix);
			}
			int tmp = Integer.MIN_VALUE;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					tmp = Math.max(tmp, tmpMatrix[i][j]);
				}
			}
			max = Math.max(max, tmp);
			return;
		}
		for (int dir = 1; dir <= 4; dir++) {
			perm(cnt + 1, str + dir);
		}
	}

	private static void move(int dir, int[][] m) {
		int flag = 0, curIdx = 0;
		switch (dir) {
		case 1: // 오른쪽
			for (int i = 1; i <= N; i++) {
				flag = 0;
				curIdx = N;
				int tmpArr[] = new int[N + 1];
				for (int j = N; j >= 1; j--) {
					if (m[i][j] == 0) {
						continue;
					} else { // 0이 아닐 때
						if (curIdx == N) {
							tmpArr[curIdx--] = m[i][j];
							flag = 0;
						} else if (flag == 0 && tmpArr[curIdx + 1] == m[i][j]) {
							tmpArr[curIdx + 1] *= 2;
							flag = 1;
						} else {
							tmpArr[curIdx--] = m[i][j];
							flag = 0;
						}
					}
				}
				for (int k = 1; k <= N; k++) {
					m[i][k] = tmpArr[k];
				}
			}
			break;
		case 2: // 왼쪽
			for (int i = 1; i <= N; i++) {
				flag = 0;
				curIdx = 1;
				int tmpArr[] = new int[N + 1];
				for (int j = 1; j <= N; j++) {
					if (m[i][j] == 0) {
						continue;
					} else { // 0이 아닐 때
						if (curIdx == 1) {
							tmpArr[curIdx++] = m[i][j];
							flag = 0;
						} else if (flag == 0 && tmpArr[curIdx - 1] == m[i][j]) {
							tmpArr[curIdx - 1] *= 2;
							flag = 1;
						} else {
							tmpArr[curIdx++] = m[i][j];
							flag = 0;
						}
					}
				}
				for (int k = 1; k <= N; k++) {
					m[i][k] = tmpArr[k];
				}
			}
			break;
		case 3: // 위쪽
			for (int j = 1; j <= N; j++) {
				flag = 0;
				curIdx = 1;
				int tmpArr[] = new int[N + 1];
				for (int i = 1; i <= N; i++) {
					if (m[i][j] == 0) {
						continue;
					} else { // 0이 아닐 때
						if (curIdx == 1) {
							tmpArr[curIdx++] = m[i][j];
							flag = 0;
						} else if (flag == 0 && tmpArr[curIdx - 1] == m[i][j]) {
							tmpArr[curIdx - 1] *= 2;
							flag = 1;
						} else {
							tmpArr[curIdx++] = m[i][j];
							flag = 0;
						}
					}
				}
				for (int k = 1; k <= N; k++) {
					m[k][j] = tmpArr[k];
				}
			}
			break;
		case 4: // 아래쪽
			for (int j = 1; j <= N; j++) {
				flag = 0;
				curIdx = N;
				int tmpArr[] = new int[N + 1];
				for (int i = N; i >= 1; i--) {
					if (m[i][j] == 0) {
						continue;
					} else { // 0이 아닐 때
						if (curIdx == N) {
							tmpArr[curIdx--] = m[i][j];
							flag = 0;
						} else if (flag == 0 && tmpArr[curIdx + 1] == m[i][j]) {
							tmpArr[curIdx + 1] *= 2;
							flag = 1;
						} else {
							tmpArr[curIdx--] = m[i][j];
							flag = 0;
						}
					}
				}
				for (int k = 1; k <= N; k++) {
					m[k][j] = tmpArr[k];
				}
			}
			break;
		}
	}

}
