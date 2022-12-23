import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int money[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int total=0;
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
			total += money[i];
		}
		int M = Integer.parseInt(br.readLine());
		Arrays.sort(money);
		// 모두 배분 불가능한 경우 
		int maxRet = -1;
		if(total > M) {
			int cur, ret=0;
			for(int x=1;x<=N;x++) {
				cur = 0;
				for(int i=0;i<N-x;i++) {
					cur += money[i];
				}
				if(cur < M) {
					ret = (M-cur)/x;
				}
				maxRet = Math.max(maxRet, ret);
			}
			System.out.println(maxRet);
		}else { // 모두 배분 가능한 경우 
			System.out.println(money[N-1]);
		}
	}

}
