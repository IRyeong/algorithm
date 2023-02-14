import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// MCN 구하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		long times[] = new long[16];
		times[0] = 1;
		for (int i = 1; i < 16; i++) {
			times[i] = times[i - 1] * i;
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visited = new boolean[M];
			long ret = 1;
			int range = Math.max(M - N, N);
			int divider = Math.min(M - N, N);

			for (int i = M; i > range; i--) {
				ret *= i;
			}
			ret /= times[divider];
			sb.append(ret + "\n");
		}
		System.out.print(sb);
	}
}
