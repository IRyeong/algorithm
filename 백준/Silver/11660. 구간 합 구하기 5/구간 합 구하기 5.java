import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int input[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int sum[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i >= 2 && j >= 2) {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + input[i][j];
				} else if (i >= 2) {
					sum[i][j] = sum[i - 1][j] + input[i][j];
				} else {
					sum[i][j] = sum[i][j - 1] + input[i][j];
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append(sum[x2][y2] - (sum[x2][y1 - 1] + sum[x1 - 1][y2] - sum[x1 - 1][y1 - 1]) + "\n");
		}

//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= N; j++) {
//				System.out.print(sum[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(sb);
	}

}
