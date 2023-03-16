import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int N, numLen, operLen;
	public static long maxRet;
	public static char oper[];
	public static long num[];
	public static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxRet = Long.MIN_VALUE;
		operLen = N / 2;
		numLen = N - operLen;
		oper = new char[operLen];
		num = new long[numLen];
		visited = new boolean[numLen];
		int i1 = 0, i2 = 0;
		String input = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 != 0) { // 연산자
				oper[i1++] = input.charAt(i);
			} else { // 숫자
				num[i2++] = input.charAt(i) - '0';
			}
		}
		if (N == 1) {
			maxRet = num[0];
		}
		// N개 중 2개 쌍 1개에서 N/2개 뽑기
		for (int r = 1; r <= N / 2; r++) {
			find(r, 0, 0);
		}
		System.out.println(maxRet);
	}

	private static void find(int r, int start, int cnt) {
		// N개 중 2개쌍 i개 뽑기
		if (cnt == r) {
			// visited 돼 있는 애들부터 계산했을 때 값 구하기
			maxRet = Math.max(maxRet, calculate());
			return;
		}
		for (int i = start; i < numLen - 1; i++) {
			visited[i] = true;
			visited[i + 1] = true;
			find(r, i + 2, cnt + 1);
			visited[i] = false;
			visited[i + 1] = false;
			find(r, i + 1, cnt);
		}
	}

	// visited 돼있는 애들부터 계산했을 때 값 구하기
	private static long calculate() {
		long total = 0;
		long ret[] = new long[numLen];
		for (int i = 0; i < numLen; i++) {
			ret[i] = num[i];
		}
		for (int i = 0; i < numLen; i++) {
			if (visited[i]) {
//				System.out.print(num[i] + " ");
				if (oper[i] == '+') {
					ret[i] = ret[i] + ret[i + 1];
					ret[i + 1] = Long.MIN_VALUE;
				} else if (oper[i] == '*') {
					ret[i] = ret[i] * ret[i + 1];
					ret[i + 1] = Long.MIN_VALUE;
				} else { // -
					ret[i] = ret[i] - ret[i + 1];
					ret[i + 1] = Long.MIN_VALUE;
				}
				i++;
			}
		}
//		System.out.println();
		total = ret[0];
		for (int i = 1; i < numLen; i++) {
			if (ret[i] == Long.MIN_VALUE) {
				continue;
			}
			if (oper[i - 1] == '+') {
				total += ret[i];
			} else if (oper[i - 1] == '*') {
				total *= ret[i];
			} else { // -
				total -= ret[i];
			}
		}
		return total;
	}

}
