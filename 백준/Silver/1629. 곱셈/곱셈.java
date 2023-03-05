import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		// B보다 크거나 같은 가장 작은 2의 거듭제곱...
		long tmp = B;
		int cnt = 0;
		while (tmp > 1) {
			tmp /= 2;
			cnt++;
		}
		long arr[] = new long[cnt + 1];
		A %= C;
		arr[0] = A;
		for (int j = 1; j <= cnt; j++) {
			arr[j] = (arr[j - 1] * arr[j - 1]) % C;
		}
		long ret = 1L;
		for (int i = 0; i <= cnt; i++) {
			if ((B & (1 << i)) != 0) {
				ret = (ret * arr[i]) % C;
			}
		}
		System.out.println(ret);
	}

}
