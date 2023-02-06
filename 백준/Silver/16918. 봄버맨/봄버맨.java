import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, N;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		char[][] matrix;
		matrix = new char[R][C];
		String s;
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = s.charAt(j);
			}
		}
		if (N == 1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
		} else {
			switch (N % 4) {
			case 0: // 전체 다 색칠돼잇음!
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						System.out.print('O');
					}
					System.out.println();
				}
				break;
			case 1: // 처음 입력 받은 격자랑 같음!
				char[][] ret = fill(matrix);
				char[][] ret2 = fill(ret);
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						System.out.print(ret2[i][j]);
					}
					System.out.println();
				}
				break;
			case 2:
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						System.out.print('O');
					}
					System.out.println();
				}
				break;
			case 3: // 처음 입력 받은 격자에서 폭탄 터지고 남은 격자 채운거랑 같음!
				// 폭탄 터지는 부분 다 0으로 채워넣기
				char[][] ret3 = fill(matrix);
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						System.out.print(ret3[i][j]);
					}
					System.out.println();
				}
				break;
			}
		}
	}

	private static char[][] fill(char[][] matrix) {
		char[][] ret = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (matrix[i][j] == 'O') {
					ret[i][j] = 'O';
					for (int d = 0; d < 4; d++) {
						int newI = i + dx[d];
						int newJ = j + dy[d];
						if (newI >= 0 && newI < R && newJ >= 0 && newJ < C) {
							ret[newI][newJ] = 'O';
						}
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (ret[i][j] == 'O') {
					ret[i][j] = '.';
				} else {
					ret[i][j] = 'O';
				}
			}
		}
		return ret;
	}

}
