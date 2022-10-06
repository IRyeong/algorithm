import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			boolean visited[] = new boolean[N];
			String ret = "";
			String s = "1";
			int r = 1 % N;
			visited[r]=true;
			Queue<Pair> q = new LinkedList<>();
			q.add(new Pair(s, r));
			while (!q.isEmpty()) {
				Pair tmpP = q.poll();
				String tmpS = tmpP.s;
				int tmpR = tmpP.r;
				if (tmpS.length() > 100) {
					break;
				}
				if (tmpR == 0) {
					ret = tmpS;
					break;
				}
				if (!visited[(tmpR * 10) % N]) {
					visited[(tmpR * 10) % N] = true;
					q.add(new Pair(tmpS + "0", (tmpR * 10) % N));
				}
				if (!visited[(tmpR * 10 + 1) % N]) {
					visited[(tmpR * 10 + 1) % N] = true;
					q.add(new Pair(tmpS + "1", (tmpR * 10 + 1) % N));
				}
			}
			if (ret == "") {
				ret = "BRAK";
			}
			System.out.println(ret);
		}
	}

	private static class Pair {
		String s;
		int r;

		public Pair(String s, int r) {
			super();
			this.s = s;
			this.r = r;
		}
	}

}
