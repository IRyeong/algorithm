import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int INF = 87654321;

	public static void main(String[] args) throws Exception {
		// n개의 도시
		// 한 도시에서 출발하고 다른 도시에 도착하는 m개의 버스
		// 모든 도시 쌍 (A,B)에 대해 도시 A에서 B로 가는데 필요한 비용의 최솟값
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int conn[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					conn[i][j] = INF;
				}
			}
		}
		int a, b, c;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			conn[a][b] = Math.min(conn[a][b], c);
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (conn[i][j] > conn[i][k] + conn[k][j]) {
						conn[i][j] = conn[i][k] + conn[k][j];
					}
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (conn[i][j] == INF) {
					conn[i][j] = 0;
				}
				sb.append(conn[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
