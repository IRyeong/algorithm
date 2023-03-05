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
//		System.out.println("cnt : " + cnt);
		// A를 2의 i승 만큼 곱한 숫자를 C로 나눈 나머지
		long arr[] = new long[cnt + 1];
		// A를 C로 나눈 나머지로 갱신
		A %= C;
		// A를 2의 0승인 1개만큼 곱한 숫자를 C로 나눈 나머지는 그대로 A
		arr[0] = A;
		//
		for (int j = 1; j <= cnt; j++) {
			arr[j] = (arr[j - 1] * arr[j - 1]) % C;
		}
//		for (int i = 0; i <= cnt; i++) {
//			System.out.print(arr[i]+" ");
//		}
//		System.out.println();
		long ret = 1L;
		for (int i = 0; i <= cnt; i++) {
			if ((B & (1 << i)) != 0) {
//				System.out.println("i : " + i);
				ret = (ret * arr[i]) % C;
			}
		}
		System.out.println(ret);
	}

}
