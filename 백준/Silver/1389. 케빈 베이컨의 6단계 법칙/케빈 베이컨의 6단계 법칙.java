import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int dist[][];
	static int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][N + 1];
		int a, b;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					dist[i][j] = 0;
				} else {
					dist[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			dist[a][b] = 1;
			dist[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		int minRet = 87654321;
		int minPerson = 0;

		for (int i = 1; i <= N; i++) {
			int total = 0;
			for (int j = 1; j <= N; j++) {
				total += dist[i][j];
			}
			if (minRet > total) {
				minRet = total;
				minPerson = i;
			}
		}
		System.out.println(minPerson);
	}

}
