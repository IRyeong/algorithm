import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int board[][];
	static int score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		board = new int[10][10];
		score = 0;
		int N = Integer.parseInt(br.readLine());
		int t, x, y;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			toBlue(t, x, y);
			checkBlueCol();
			checkBlueCon();
			toGreen(t, x, y);
			checkGreenRow();
			checkGreenCon();
		}
		System.out.println(score);
		int num = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] != 0) {
					num++;
				}
			}
		}
		System.out.println(num);
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	// 파란색 쪽으로 떨어뜨리기
	private static void toBlue(int t, int x, int y) {
		if (t == 1) { // 1x1
			for (int j = y; j < 9; j++) {
				if (j == 8 && board[x][j + 1] == 0) {
					board[x][j + 1] = t;
					break;
				}
				if (board[x][j + 1] != 0) {
					board[x][j] = t;
					break;
				}
			}
		} else if (t == 2) { // 1x2
			for (int j = y; j < 9; j++) {
				if (j == 8 && board[x][j + 1] == 0) {
					board[x][j + 1] = t;
					board[x][j] = t;
					break;
				}
				if (board[x][j + 1] != 0) {
					board[x][j] = t;
					board[x][j - 1] = t;
					break;
				}
			}
		} else { // 2x1
			for (int j = y; j < 9; j++) {
				if (j == 8 && board[x][j + 1] == 0 && board[x + 1][j + 1] == 0) {
					board[x][j + 1] = t;
					board[x + 1][j + 1] = t;
					break;
				}
				if (board[x][j + 1] != 0 || board[x + 1][j + 1] != 0) {
					board[x][j] = t;
					board[x + 1][j] = t;
					break;
				}
			}
		}
	}

	// 초록색 쪽으로 떨어뜨리기
	private static void toGreen(int t, int x, int y) {
		if (t == 1) { // 1x1
			for (int i = x; i < 9; i++) {
				if (i == 8 && board[i + 1][y] == 0) {
					board[i + 1][y] = t;
					break;
				}
				if (board[i + 1][y] != 0) {
					board[i][y] = t;
					break;
				}
			}
		} else if (t == 2) { // 1x2
			for (int i = y; i < 9; i++) {
				if (i == 8 && board[i + 1][y] == 0 && board[i + 1][y + 1] == 0) {
					board[i + 1][y + 1] = t;
					board[i + 1][y] = t;
					break;
				}
				if (board[i + 1][y + 1] != 0 || board[i + 1][y] != 0) {
					board[i][y + 1] = t;
					board[i][y] = t;
					break;
				}
			}
		} else { // 2x1
			for (int i = y; i < 9; i++) {
				if (i == 8 && board[i][y] == 0 && board[i + 1][y] == 0) {
					board[i][y] = t;
					board[i + 1][y] = t;
					break;
				}
				if (board[i + 1][y] != 0) {
					board[i][y] = t;
					board[i - 1][y] = t;
					break;
				}
			}
		}
	}

	// 파란색 쪽에 완성된 열이 있는지 확인
	private static void checkBlueCol() {
		boolean flag = true;
		for (int j = 9; j >= 6; j--) {
			flag = true;
			for (int i = 0; i < 4; i++) {
				if (board[i][j] == 0) {
					flag = false;
				}
			}
			if (flag) { // i행이 다 찬 것을 의미
				score++;
				for (int t = j; t >= 4; t--) {
					for (int i = 0; i < 4; i++) {
						board[i][t] = board[i][t - 1];
					}
				}
				j++;
			}
		}
	}

	// 초록색 쪽에 완성된 행이 있는지 확인
	private static void checkGreenRow() {
		boolean flag = true;
		for (int i = 9; i >= 6; i--) {
			flag = true;
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0) {
					flag = false;
				}
			}
			if (flag) { // i행이 다 찬 것을 의미
				score++;
				for (int t = i; t >= 4; t--) {
					for (int j = 0; j < 4; j++) {
						board[t][j] = board[t - 1][j];
					}
				}
				i++;
			}
		}

	}

	private static void checkBlueCon() {
		int cnt = 0;
		for (int j = 4; j <= 5; j++) {
			for (int i = 0; i < 4; i++) {
				if (board[i][j] != 0) {
					cnt++;
					break;
				}
			}
		}
		if (cnt == 1) { // 오른쪽 한 줄을 없애야 하는 경우
			for (int j = 9; j >= 4; j--) {
				for (int i = 0; i < 4; i++) {
					board[i][j] = board[i][j - 1];
				}
			}
		} else if (cnt == 2) { // 오른쪽 두 줄을 없애야 하는 경우
			for (int j = 9; j >= 4; j--) {
				for (int i = 0; i < 4; i++) {
					board[i][j] = board[i][j - 1];
				}
			}
			for (int j = 9; j >= 4; j--) {
				for (int i = 0; i < 4; i++) {
					board[i][j] = board[i][j - 1];
				}
			}
		}
	}

	private static void checkGreenCon() {
		int cnt = 0;
		for (int i = 4; i <= 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					cnt++;
					break;
				}
			}
		}
		if (cnt == 1) { // 아래쪽 한 줄을 없애야 하는 경우
			for (int i = 9; i >= 4; i--) {
				for (int j = 0; j < 4; j++) {
					board[i][j] = board[i - 1][j];
				}
			}
		} else if (cnt == 2) { // 아래쪽 두 줄을 없애야 하는 경우
			for (int i = 9; i >= 4; i--) {
				for (int j = 0; j < 4; j++) {
					board[i][j] = board[i - 1][j];
				}
			}
			for (int i = 9; i >= 4; i--) {
				for (int j = 0; j < 4; j++) {
					board[i][j] = board[i - 1][j];
				}
			}
		}
	}

}
