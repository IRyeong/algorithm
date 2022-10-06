import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		ArrayList<Pair> arr;
		boolean conn[][];
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			conn = new boolean[N + 2][N + 2];
			// 리스트에 좌표 입력 받기 
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				arr.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			// 각 좌표들이 연결되어 있는지 확인하기(맨해튼 거리가 1000보다 작으면 연결되어 있다고 할 수 있음) 
			for (int i = 0; i < N + 2; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					if (Manhattan(arr.get(i), arr.get(j)) <= 1000) {
						conn[i][j] = true;
						conn[j][i] = true;
					}
				}
			}
			// 플로이드 와샬 
			for(int k=0;k<N+2;k++) {
				for(int i=0;i<N+2;i++) {
					for(int j=0;j<N+2;j++) {
						if(conn[i][k] && conn[k][j]) {
							conn[i][j]=true;
						}
					}
				}
			}
			if(conn[0][N+1]) {
				sb.append("happy"+"\n");
			}else {
				sb.append("sad"+"\n");
			}
		}
		System.out.println(sb);
	}

	private static int Manhattan(Pair p1, Pair p2) {
		return Math.abs(p1.x-p2.x)+Math.abs(p1.y-p2.y);
	}

	private static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
