import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int dx[] = { -1, 0, 1, 0 }; // 위, 왼쪽, 아래, 오른쪽
	static int dy[] = { 0, -1, 0, 1 };
	static int d[][];
	static int N;
	static int minI, minJ, minD, eat, time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int matrix[][] = new int[N][N];
		int babyI = -1, babyJ = -1, babyS = 2; // 아기 상어의 위치, 아기 상어의 크기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (matrix[i][j] == 9) {
					babyI = i;
					babyJ = j;
					matrix[i][j] = 0;
				}
			}
		}
		while (true) {
			d = new int[N][N];
			minI = Integer.MAX_VALUE;
			minJ = Integer.MAX_VALUE;
			minD = Integer.MAX_VALUE;

			bfs(matrix, babyI, babyJ, babyS);

			if (minI != Integer.MAX_VALUE && minJ != Integer.MAX_VALUE) {
				eat++;
				matrix[minI][minJ] = 0;
				babyI = minI;
				babyJ = minJ;
				time += d[minI][minJ];

				if (eat == babyS) {
					babyS++;
					eat = 0;
				}
			} else {
				break;
			}
		}
		System.out.println(time);
	}

	private static void bfs(int[][] matrix, int babyI, int babyJ, int babyS) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(babyI, babyJ));
		Pair tmp;
		int tmpI, tmpJ;
		while (!q.isEmpty()) {
			tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				tmpI = tmp.x + dx[i];
				tmpJ = tmp.y + dy[i];
				if (tmpI >= 0 && tmpI < N && tmpJ >= 0 && tmpJ < N && d[tmpI][tmpJ] == 0) {
					d[tmpI][tmpJ] = d[tmp.x][tmp.y] + 1;
					if (matrix[tmpI][tmpJ] > babyS) { // 아기상어보다 큰 물고기가 있을 때
						continue;
					} else if (matrix[tmpI][tmpJ] == babyS) { // 아기상어 크기와 같은 물고기가 있을 때
						q.offer(new Pair(tmpI, tmpJ));
					} else if (matrix[tmpI][tmpJ] != 0 && matrix[tmpI][tmpJ] < babyS) { // 아기상어 크기보다 작은 물고기가 있을 때
						if (minD > d[tmpI][tmpJ]) {
							minD = d[tmpI][tmpJ];
							minI = tmpI;
							minJ = tmpJ;
						} else if (minD == d[tmpI][tmpJ]) {
							if (minI == tmpI) { // i값이 서로 같다면
								if(minJ > tmpJ) { // 더 왼쪽인 좌표로 갱신
									minI=tmpI;
									minJ =tmpJ;
								}
							} else if (minI > tmpI) { // 더 위쪽인 좌표로 갱신
								minI=tmpI;
								minJ=tmpJ;
							}
						}
						q.offer(new Pair(tmpI, tmpJ));
					} else { // 물고기가 없을 때
						q.offer(new Pair(tmpI, tmpJ));
					}
				}
			}
		}

	}

}

class Pair {
	public int x;
	public int y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
