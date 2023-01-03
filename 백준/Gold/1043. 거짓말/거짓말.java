import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int parents[];
	static boolean truth[];
	static int input[][];
	static int inputNum[];

	static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (truth[a]) {
			return a;
		}
		if (parents[a] == a) {
			return a;
		}
		return find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 총 사람 수
		int M = Integer.parseInt(st.nextToken()); // 총 파티 수
		parents = new int[N];
		input = new int[51][51];
		inputNum = new int[51];
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		truth = new boolean[N];
		for (int i = 0; i < num; i++) {
			truth[Integer.parseInt(st.nextToken()) - 1] = true;
		}
		make();
		for (int i = 0; i < M; i++) { // 파티 수 만큼 입력 받기
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken()); // 파티원 수
			inputNum[i] = num;
			for (int j = 0; j < num; j++) { // 파티원 입력 받기
				int a = Integer.parseInt(st.nextToken()) - 1;
				input[i][j] = a;
				union(a, input[i][0]);
				union(input[i][0], a);
			}
		}
		for (int i = 0; i < N; i++) { // 사람들 진실 아는지 체크하기
			if (truth[find(i)]) {
				truth[i] = true;
			}
		}
		int ret = M;
		for (int i = 0; i < M; i++) {
			int n = inputNum[i];
			boolean check = false;
			for (int j = 0; j < n; j++) {
				if (truth[input[i][j]]) {
					check = true;
					ret--;
					break;
				}
			}
		}
		System.out.println(ret);
	}

}
