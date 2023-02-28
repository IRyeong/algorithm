import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int w, h;
	static int map[][];
	static boolean check[][];
	static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		w = -1;
		h = -1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				break;
			}
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int count = 0;
			check = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!check[i][j] && map[i][j] == 1) {
						count++;
						bfs(i, j);
					}
				}
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i, j));
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int curI = p.x;
			int curJ = p.y;
			for (int d = 0; d < 8; d++) {
				int newI = curI + dx[d];
				int newJ = curJ + dy[d];
				if (newI >= 0 && newI < h && newJ >= 0 && newJ < w) {
					if (!check[newI][newJ] && map[newI][newJ] == 1) {
						check[newI][newJ] = true;
						q.add(new Pair(newI, newJ));
					}
				}
			}
		}
	}

	private static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
