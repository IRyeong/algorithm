import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 집 N개, 집 좌표 x1, x2, ..., xN
	// C개의 공유기를 N개의 집에 적당히 설치, 두 공유기 사이의 거리를 최대로 하고..
	// 1 2 4 8 9 에서 3개를 ㅓ떻게 ㅎ는데 1 4 9 최대 사이 거리가 x라고 하면 모두 거리가 x 이상

	public static void main(String[] args) throws Exception {

		// 최소 거리에 따라 설치할 수 있는 공유기의 개수가 달라진다... 대박!!
		// 최소 거리를 하나씩 늘려가면서 설치할 수 있는 공유기 개수랑 입력이랑 같으면 최소 거리 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int home[] = new int[N];
		for (int i = 0; i < N; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(home);
		int lo = 1;
		int hi = home[N - 1] - home[0] + 1;
		while (lo < hi) {
			int mid = (hi + lo) / 2;
			int cnt = 1;
			int curIdx = 0;
			for (int j = 1; j < N; j++) {
				if (home[j] - home[curIdx] >= mid) {
					curIdx = j;
					cnt++;
				}
				if (cnt > M) {
					break;
				}
			}
			if (cnt >= M) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		System.out.println(lo - 1);
	}

}
