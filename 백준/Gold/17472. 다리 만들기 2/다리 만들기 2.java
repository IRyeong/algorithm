import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, index = 0, result;
	static int map[][];
	static int conn[][];
	static int tmp[][];
	static Queue<Pair> island;
	static ArrayList<Pair> islandNum[];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean flag = true;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		island = new LinkedList<>();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					island.add(new Pair(i, j));
				}
			}
		}
		// 섬 구역 판단하여 나누기
		islandNum = new ArrayList[7];
		for (int i = 0; i <= 6; i++) {
			islandNum[i] = new ArrayList<Pair>();
		}
		divideIsland();
		// 섬 간 최소거리 구하기
		conn = new int[index + 1][index + 1];
		findBridge();
		tmp = new int[index][index];
		for(int i=0;i<index;i++) {
			for(int j=0;j<index;j++) {
				tmp[i][j] = conn[i+1][j+1];
			}
		}
		// 최소신장트리 찾기
		prim();
		if(!flag) {
			result = -1;
		}
		System.out.println(result);
	}

	// 섬 구역 판단하여 나누기
	private static void divideIsland() {
		Queue<Pair> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][M];
		index = 0;
		while (!island.isEmpty()) {
			Pair tmpP2 = island.poll();
			if (!visited[tmpP2.x][tmpP2.y]) {
				q.add(tmpP2);
				index++;
				while (!q.isEmpty()) {
					Pair tmpP = q.poll();
					int tmpX = tmpP.x;
					int tmpY = tmpP.y;
					map[tmpX][tmpY] = index;
					islandNum[index].add(new Pair(tmpX, tmpY));
					for (int d = 0; d < 4; d++) {
						int newX = tmpX + dx[d];
						int newY = tmpY + dy[d];
						if (newX >= 0 && newX < N && newY >= 0 && newY < M && !visited[newX][newY]) {
							visited[newX][newY] = true;
							if (map[newX][newY] == 1) {
								map[newX][newY] = index;
								islandNum[index].add(new Pair(newX, newY));
								q.add(new Pair(newX, newY));
							}
						}
					}
				}
			}
		}
	}

	// 섬 간 최소거리 구하기
	private static void findBridge() {
		// 각 섬에 대하여
		for (int k = 1; k <= index; k++) {
			int size = islandNum[k].size();
			// 섬의 구역들에 대해
			for (int i = 0; i < size; i++) {
				int tmpX = islandNum[k].get(i).x;
				int tmpY = islandNum[k].get(i).y;
				// 사방으로 직진으로 움직이는데
				for (int d = 0; d < 4; d++) {
					int len = 0;
					int newX = tmpX;
					int newY = tmpY;
					while (true) {
						newX += dx[d];
						newY += dy[d];
						// 영역 벗어나면 다음 방향으로 바꿈
						if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
							break;
						}
						// 자기 섬을 만나면 다음 방향으로 바꿈
						if (map[newX][newY] == k) {
							break;
						}
						// 다른 섬을 만난 경우 길이를 최소로 갱신하여 conn에 넣어줌
						if (map[newX][newY] != 0) {
							if (len > 1) {
								if (conn[k][map[newX][newY]] == 0) {
									conn[k][map[newX][newY]] = len;
								} else {
									conn[k][map[newX][newY]] = Math.min(conn[k][map[newX][newY]], len);
								}
							}
							break;
						}
						if (map[newX][newY] == 0) {
							len++;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	// 최소 신장트리 찾기
	private static void prim() {
		int minEdge[] = new int[index];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		boolean visited[] = new boolean[index];
		result = 0;
		int cnt = 0;
		minEdge[0] = 0;
		while (true) {
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int i = 0; i < index; i++) {
				if (!visited[i] && minEdge[i] < min) {
					idx = i;
					min = minEdge[i];
				}
			}
			visited[idx] = true;
			result += min;
			cnt++;
			if(flag && min==Integer.MAX_VALUE) {
				flag=false;
			}
			if (cnt == index) {
				break;
			}
			for(int i=0;i<index;i++) {
				if(!visited[i] && tmp[idx][i]>0 && minEdge[i]>tmp[idx][i]) {
					minEdge[i] = tmp[idx][i];
				}
			}
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
