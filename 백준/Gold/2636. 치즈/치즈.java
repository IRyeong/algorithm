import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int cheese[][];
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, 0));
		int time = 0, last_cnt = 0; // 걸린 시간과 한시간 전 남아있는 치즈 조각 수
		// 매 시간마다 돌기 
		while (true) {
			Queue<Pair> tmpQ = new LinkedList<>();
			boolean flag = false;
			int cnt = 0;
			// 한 번 돌았을 때 다음 녹을 치즈가 없을 때까지 돌기 
			while (!q.isEmpty()) {
				Pair tmpP = q.poll();
				int tmpX, tmpY, newX, newY;
				tmpX = tmpP.x;
				tmpY = tmpP.y;
				for (int d = 0; d < 4; d++) {
					newX = tmpX + dx[d];
					newY = tmpY + dy[d];
					if (newX >= 0 && newX < N && newY >= 0 && newY < M && !visited[newX][newY]) {
						if (cheese[newX][newY] == 1) {
							flag = true;
							visited[newX][newY] = true;
							cheese[newX][newY] = 'c';
							cnt++;
							tmpQ.add(new Pair(newX, newY));
						} else if(cheese[newX][newY] == 0) {
							visited[newX][newY] = true;
							q.add(new Pair(newX, newY));
						}
					}
				}
			}
			if(!flag) {
				System.out.println(time+"\n"+last_cnt);
				return;
			}
			last_cnt = cnt;
			time++;
			q =tmpQ;
		}
	}

	private static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
