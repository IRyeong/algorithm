import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long arr[] = new long[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		long min = 0;
		long max = arr[K - 1] + 1;
		long mid = (min + max) / 2;
		long cnt = 0;
		while (min < max) {
			cnt = 0;
			mid = (min + max) / 2;
			for (int i = 0; i < K; i++) {
				cnt += arr[i] / mid;
			}
			if (cnt < N) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}
		System.out.println(min - 1);
	}

}
