import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] bingo;
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bingo = new int[5][5];
		check = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 이제 사회자가 숫자 불러요~
		int number;
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				number = Integer.parseInt(st.nextToken());
				// 숫자가 있는지 확인하고 있으면 체크
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 5; l++) {
						if (bingo[k][l] == number) {
							check[k][l] = true;
						}
					}
				}
				// 빙고가 됐는지 확인
				if (checkBingo() >= 3) {
					System.out.println(i * 5 + j+1);
					return;
				}
			}
		}
	}

	private static int checkBingo() {
		int ret = 0;
		// 가로 빙고 확인
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!check[i][j]) {
					break;
				}
				if (j == 4) {
					ret++;
				}
			}
		}
		// 세로 빙고 확인
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!check[j][i]) {
					break;
				}
				if (j == 4) {
					ret++;
				}
			}
		}
		// 대각선 빙고 확인
		for (int i = 0; i < 5; i++) {
			if (!check[i][i]) {
				break;
			}
			if (i == 4) {
				ret++;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (!check[i][4 - i]) {
				break;
			}
			if (i == 4) {
				ret++;
			}
		}
		return ret;
	}
}
