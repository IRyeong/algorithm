import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static boolean isPrime[];

	public static void main(String[] args) throws Exception {
		// 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다
		// 100만 이하의 모든 짝수에 대해서 이 추측을 검증하는 프로그램을 작성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		// 소수를 먼저 찾아야겠지?
		isPrime = new boolean[1000001]; // 소수면 false, 아니면 true
		isPrime[1] = true;

		for (int cur = 3; cur <= 1000; cur += 2) {
			if (isPrime[cur]) {
				continue;
			}
			for (int i = cur * 2; i <= 1000000; i += cur) {
				isPrime[i] = true;
			}
		}
		boolean flag;
		// 숫자 하나씩 받아서 그 숫자가 소수로 이루어져있는지 확인!
		while (true) {
			flag = false;
			int number = Integer.parseInt(br.readLine());
			if (number == 0) {
				break;
			}
			for (int i = 3; i <= number / 2; i += 2) {
				if (!isPrime[i] && !isPrime[number - i]) {
					sb.append(number + " = " + i + " + " + (number - i) + "\n");
					flag = true;
					break;
				}
			}
			if(!flag) {
				sb.append("Goldbach's conjecture is wrong."+"\n");
			}
		}
		System.out.println(sb);
	}

}
