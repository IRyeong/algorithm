import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Pair {
		int x;
		int y;
		int cnt;

		public Pair(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int lab[][], tmpLab[][];
	static boolean labVisited[][];
	static int N, M, size, ret, noneVirusCnt, infectedCnt;
	static LinkedList<Pair> virus;
	static Queue<Pair> q;
	static boolean visited[], retFlag;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new LinkedList<>();
		lab = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) {
					virus.add(new Pair(i, j, 0));
				} else if (lab[i][j] == 0) {
					noneVirusCnt++;
				}
			}
		}
		size = virus.size();
		// 입력받은 바이러스들 중 M개 뽑기 -> 그 뽑은걸로 bfs 풀기
		visited = new boolean[size];
		ret = Integer.MAX_VALUE;
		go(0, 0);
		if(ret == Integer.MAX_VALUE) {
			ret = -1;
		}
		System.out.println(ret);
	}

	private static void go(int start, int cnt) {
		if (cnt == M) {
			q = new LinkedList<>();
			tmpLab = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmpLab[i][j] = lab[i][j];
				}
			}
			labVisited = new boolean[N][N];
			for (int i = 0; i < size; i++) {
				if (visited[i]) {
					q.add(virus.get(i));
					labVisited[virus.get(i).x][virus.get(i).y] = true;
				}
			}
			infectedCnt = 0;
			bfs(q);
		}

		for (int i = start; i < size; i++) {
			visited[i] = true;
			go(i + 1, cnt + 1);
			visited[i] = false;
		}
	}

	private static void bfs(Queue<Pair> q) {
		int maxCnt = -1;
		a: while (!q.isEmpty()) {
			Pair tmpP = q.poll();
			int tmpX = tmpP.x;
			int tmpY = tmpP.y;
			int tmpCnt = tmpP.cnt;
			if (ret <= tmpCnt) {
				break a;
			}
			if (infectedCnt >= noneVirusCnt) {
				boolean flag = true;
				for (int m = 0; m < N; m++) {
					for (int n = 0; n < N; n++) {
						if (!(tmpLab[m][n] == 2 || tmpLab[m][n] == 1)) {
							flag = false;
						}
					}
				}
				if (flag == true) {
					if (maxCnt == -1) {
						maxCnt = 0;
					}
					ret = Math.min(ret, maxCnt);
					break a;
				}
			}
			for (int i = 0; i < 4; i++) {
				int newX = tmpX + dx[i];
				int newY = tmpY + dy[i];
				if (newX < 0 || newY < 0 || newX >= N || newY >= N) {
					continue;
				} else if ((tmpLab[newX][newY] == 0 || tmpLab[newX][newY] == 2) && !labVisited[newX][newY]) {
					labVisited[newX][newY] = true;
					tmpLab[newX][newY] = 2;
					infectedCnt++;
					q.add(new Pair(newX, newY, tmpCnt + 1));
					maxCnt = Math.max(maxCnt, tmpCnt + 1);
				}
			}
		}
//		if (retFlag == false) {
//			ret = -1;
//		}

	}

}
