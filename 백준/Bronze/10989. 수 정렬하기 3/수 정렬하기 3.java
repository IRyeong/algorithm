import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[10001];
		int num;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			arr[num]++;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 10000; i++) {
			for (int j = 0; j < arr[i]; j++) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}

}
