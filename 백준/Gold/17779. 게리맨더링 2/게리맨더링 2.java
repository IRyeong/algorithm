import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int population[][];
	static int area[][];
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ret_min = Integer.MAX_VALUE;
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						ret_min = Math.min(divide(x, y, d1, d2), ret_min);
					}
				}
			}
		}
		System.out.println(ret_min);
	}

	private static int divide(int x, int y, int d1, int d2) {
		int ret[] = new int[5];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int tmpI, tmpJ;
		area = new int[N + 1][N + 1];
		int a = 0, b = 0, c = 0, d = 0, e = 0;
		// 1번
		int check_cnt=0;
		tmpI = Math.min(N + 1, x + d1);
		for (int i = 1; i < tmpI; i++) {
			for (int j = 1; j <= y; j++) {
				if (j < -i + (x + y)) {
					area[i][j] = 1;
					a += population[i][j];
					check_cnt++;
				}
			}
		}
		if(check_cnt==0) {
			return Integer.MAX_VALUE;
		}
		ret[0] = a;
		// 2번
		check_cnt=0;
		tmpI = Math.min(N, x + d2);
		tmpJ = Math.min(N + 1, y + 1);
		for (int i = 1; i <= tmpI; i++) {
			for (int j = tmpJ; j <= N; j++) {
				if (j > i + (-x + y)) {
					area[i][j] = 2;
					b += population[i][j];
					check_cnt++;
				}
			}
		}
		if(check_cnt==0) {
			return Integer.MAX_VALUE;
		}
		ret[1] = b;
		// 3번
		check_cnt=0;
		tmpI = Math.min(N + 1, x + d1);
		tmpJ = Math.min(N, y + d2 - d1);
		for (int i = tmpI; i <= N; i++) {
			for (int j = 1; j < tmpJ; j++) {
				if (j < i + (-x + y - 2 * d1)) {
					area[i][j] = 3;
					c += population[i][j];
					check_cnt++;
				}
			}
		}
		if(check_cnt==0) {
			return Integer.MAX_VALUE;
		}
		ret[2] = c;
		// 4번
		check_cnt=0;
		tmpI = Math.min(N, x + d2 + 1);
		tmpJ = Math.max(0, y + d2 - d1);
		for (int i = tmpI; i <= N; i++) {
			for (int j = tmpJ; j <= N; j++) {
				if (j > -i + (x + y + 2 * d2)) {
					area[i][j] = 4;
					d += population[i][j];
					check_cnt++;
				}
			}
		}
		if(check_cnt==0) {
			return Integer.MAX_VALUE;
		}
		ret[3] = d;
		// 5번 
		check_cnt=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (area[i][j] == 0) {
					e += population[i][j];
					check_cnt++;
				}
			}
		}
		if(check_cnt==0) {
			return Integer.MAX_VALUE;
		}
		ret[4] = e;
		Arrays.sort(ret);
		return ret[4]-ret[0];
	}

}
