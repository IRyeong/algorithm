import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 가계부 프로그램
	// 1. 생후 p일에 수입, 지출 내용 추가. 수입은 양수, 지출은 음수
	// 2. 생후 p일부터 q일까지 잔고가 변화한 값 구하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(st.nextToken()); // 살아온 날
		int Q = Integer.parseInt(st.nextToken()); // 쿼리 개수
		long money[] = new long[N + 1];
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());
			int p, q, x;
			if (input == 1) {
				p = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				money[p] += x;
			} else {
				p = Integer.parseInt(st.nextToken());
				q = Integer.parseInt(st.nextToken());
				long total = 0;
				for (int k = p; k <= q; k++) {
					total += money[k];
				}
				sb.append(total + "\n");
			}
		}
		System.out.println(sb);
	}

}
