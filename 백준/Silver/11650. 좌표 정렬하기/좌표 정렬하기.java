import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pair input[] = new Pair[N];
		StringTokenizer st;
		int x, y;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			input[i] = new Pair(x, y);
		}
		Arrays.sort(input, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.x == o2.x) {
					return o1.y - o2.y;
				} else {
					return o1.x - o2.x;
				}
			}

		});
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			sb.append(input[i].x + " " + input[i].y + "\n");
		}
		System.out.println(sb);
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
