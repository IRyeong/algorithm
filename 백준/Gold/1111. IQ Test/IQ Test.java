import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Pair {
		int i;
		int j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numbers[] = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		int a, b, c;
		Set<Integer> ret = new HashSet<Integer>();
		boolean check = true;
		if (N == 1) {
			System.out.println("A");
		} else if (N == 2) {
			if (numbers[0] == numbers[1]) {
				System.out.println(numbers[0]);
			} else {
				System.out.println("A");
			}
		} else { // N >= 3
			a = numbers[0];
			b = numbers[1];
			c = numbers[2];
			if (a == b) {
				check = true;
				for (int k = 2; k < N; k++) {
					if (numbers[k] != b) { // 답 없음
						check=false;
						break;
					}
				}
				if(check) { // 답 하나 
					System.out.println(a);
				} else { // 답 없음 
					System.out.println("B");
				}
			} else {
				double n = (b - c) / (a - b);
				if ((n * 10) % 10 == 0) { // n이 정수일 때만 가능
					double m = b - a * n;
					if ((m * 10) % 10 == 0) { // n이 정수일 때만 가능
						check = true;
						for (int k = 1; k < N; k++) {
							a = numbers[k - 1];
							b = numbers[k];
							if (!check) {
								break;
							}
							if (a * n + m != b) {
								check = false;
							}
						}
						if (check) {
							ret.add((int) (numbers[N - 1] * n + m));
						}
					}

				}
				int retSize = ret.size();
				if (retSize == 1) {
					Iterator<Integer> iter = ret.iterator();
					while (iter.hasNext()) {
						System.out.println(iter.next());
					}
				} else if (retSize == 0) {
					System.out.println("B");
				} else {
					System.out.println("A");
				}
			}
		}
	}
}
