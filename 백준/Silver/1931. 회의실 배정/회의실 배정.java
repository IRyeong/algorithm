import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 맨 뒤에서부터 하나씩 배정하면 될거같당
		// 먼저 정렬해줘야대 기준은?
		// (start, end) 이렇게 있으면
		// end가 큰 순서,
		// start가 큰 순
		Pair time[] = new Pair[N];
		long s, e;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			time[i] = new Pair(s, e);
		}
		Arrays.sort(time, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.end == o2.end) {
					return (int) (o1.start - o2.start);
				}
				return (int) (o1.end - o2.end);
			}

		});
		int cnt = 0;
		long curTime = time[0].start;
		for (int i = 0; i < N; i++) {
			if (time[i].start >= curTime) {
				curTime = time[i].end;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static class Pair {
		long start;
		long end;

		Pair(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}

}
