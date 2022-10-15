import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int INF = 987654321;
	static int N, M, Q;
	static Pair teasing[]; // 멍멍이가 원숭이를 괴롭히는 시간
	static int fw[][][]; // 0 : 플로이드 워셜을 이용해 구한 꼭짓점부터 꼭짓점까지 가는데 걸리는 시간, 1 : 놀리는 최대 시간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		fw = new int[N + 1][N + 1][2];
		// 각 거리 초기값 최대로 해주기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 같은 지점 간의 거리 0으로 설정해주기
				if (i == j) {
					fw[i][j][0] = 0;
					continue;
				}
				fw[i][j][0] = INF;
			}
		}
		teasing = new Pair[N + 1];
		teasing[0] = new Pair(0, 0);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			teasing[i] = new Pair(i, Integer.parseInt(st.nextToken()));
		}
		int a, b, d;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			fw[a][b][0] = d;
			fw[b][a][0] = d;
			fw[a][b][1] = Math.max(teasing[a].time, teasing[b].time);
			fw[b][a][1] = Math.max(teasing[a].time, teasing[b].time);
		}
		// 놀리는 시간(가중치) 기준으로 오름차순 정렬
		Arrays.sort(teasing);
		// 플로이드 워셜로 거리 모두 구해두기
		for (Pair p : teasing) {
			if (p.num == 0) { // 0인 마을은 없으므로 패스
				continue;
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int tmpMax = max(fw[i][p.num][1], fw[p.num][j][1], p.time);
					if (fw[i][j][0] + fw[i][j][1] > fw[i][p.num][0] + fw[p.num][j][0] + tmpMax) {
						fw[i][j][0] = fw[i][p.num][0] + fw[p.num][j][0];
						fw[i][j][1] = tmpMax;
					}
				}
			}
		}
		// 질문 입력 받기
		int S, T;
		for (int test_case = 0; test_case < Q; test_case++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			if (fw[S][T][0] == INF) {
				sb.append(-1 + "\n");
				continue;
			}
			sb.append((fw[S][T][0] + fw[S][T][1]) + "\n");
		}
		// 출력
		System.out.println(sb);
	}

	private static class Pair implements Comparable<Pair> {
		int num; // 마을 번호
		int time; // 놀리는 시간

		public Pair(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Pair o) {
			return this.time - o.time; // 놀리는 시간 기준 오름차순
		}
	}

	// 세 수 중 가장 큰 수 리턴
	private static int max(int a, int b, int c) {
		int ret;
		ret = Math.max(a, b);
		ret = Math.max(ret, c);
		return ret;
	}

}
