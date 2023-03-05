import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int cur = 666;
		int tmp = cur;
		while (true) {
			tmp = cur;
			while (tmp != 0) {
				if (tmp % 1000 == 666) {
					cnt++;
					break;
				} else {
					tmp /= 10;
				}
			}
			if(cnt==N) {
				break;
			}
			cur++;
		}
		System.out.println(cur);
	}

}
