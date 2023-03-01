import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			String s = br.readLine();
			int len = s.length();
			int count = 0;
			int score = 0;
			for (int i = 0; i < len; i++) {
				if(s.charAt(i)=='O') {
					count++;
					score += count;
				} else {
					count = 0;
				}
			}
			System.out.println(score);
		}
	}

}
