import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 0! = 1
		// 1! = 1
		// 2! = 2
		// 5! = 120 (2가 1번, 5가 1번)
		// 6! = 720
		// 그러면 5가 곱해진 횟수를 구하는게 관건!
		// 25면 25/25=1, 25/5= 5
		// 75이면 75/25=3, 75/5=25
		// 80이면 80/25=3, 80/5=16
		int divider = 5;
		int count = 0;
		while (N >= divider) {
			count += N / divider;
			divider *= 5;
		}
		System.out.println(count);
	}
}
