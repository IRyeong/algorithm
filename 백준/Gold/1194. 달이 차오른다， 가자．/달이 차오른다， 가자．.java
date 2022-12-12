import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, minCnt;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean visited[][][];
	static char maze[][];
	static Status minsik;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visited = new boolean[50][50][1<<6];
		// 입력 받기
		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j);
				if (maze[i][j] == '0') {
					minsik = new Status(0, 0, i, j);
					maze[i][j] = '.';
				}
			}
		}
		minCnt = Integer.MAX_VALUE;
		bfs();
		if (minCnt == Integer.MAX_VALUE) {
			minCnt = -1;
		}
		System.out.println(minCnt);
	}

	// 미로 탈출
	private static void bfs() {
		Queue<Status> q = new LinkedList<>();
		q.add(minsik);
		Status tmpS;
		int tmpKey, tmpCnt, tmpX, tmpY, newX, newY, tmpChar;
		while (!q.isEmpty()) {
			tmpS = q.poll();
			tmpKey = tmpS.key;
			tmpCnt = tmpS.cnt;
			tmpX = tmpS.x;
			tmpY = tmpS.y;
			if (tmpCnt >= minCnt) {
				continue;
			}
			for (int d = 0; d < 4; d++) {
				newX = tmpX + dx[d];
				newY = tmpY + dy[d];
				if (newX >= 0 && newX < N && newY >= 0 && newY < M && maze[newX][newY] != '#' && !visited[newX][newY][tmpKey]) {
					tmpChar = maze[newX][newY];
					if (tmpChar == '.') {// 빈 칸인 경우
						visited[newX][newY][tmpKey]=true;
						q.add(new Status(tmpKey, tmpCnt + 1, newX, newY));
					} else if (tmpChar >= 'a' && tmpChar <= 'f') {// 열쇠가 있는 경우
						visited[newX][newY][tmpKey | (1 << (tmpChar - 'a'))]=true;
						q.add(new Status(tmpKey | (1 << (tmpChar - 'a')), tmpCnt + 1, newX, newY));
					} else if (tmpChar >= 'A' && tmpChar <= 'F') {// 문이 있는 경우
						if ((tmpKey & (1 << (tmpChar - 'A'))) != 0) { // 키가 있으면
							visited[newX][newY][tmpKey]=true;
							q.add(new Status(tmpKey, tmpCnt + 1, newX, newY));
						} else { // 키가 없으면 되돌아감
							visited[newX][newY][tmpKey]=true;
							continue;
						}
					} else if (tmpChar == '1') {// 출구가 있는 경우
						minCnt = Math.min(minCnt, tmpCnt + 1);
						break;
					}
				}
			}
		}
	}

	private static class Status {
		int key; // 비트마스크
		int cnt; // 이동 횟수
		int x;
		int y;
		public Status(int key, int cnt, int x, int y) {
			super();
			this.key = key;
			this.cnt = cnt;
			this.x = x;
			this.y = y;
		}

	}

}
