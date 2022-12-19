import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, total, max_total;
	static int[] time;
	static ArrayList<Integer>[] tree;
	static int[] degree;
	static int[] result;

	public static void main(String[] args) throws Exception {
		// 1번부터 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		time = new int[N + 1];
		tree = new ArrayList[N + 1];
		result = new int[N+1];
		degree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		int tmp, num;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			result[i] = time[i];
			tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				num = Integer.parseInt(st.nextToken());
				tree[num].add(i);
				degree[i]++;
			}
		}
		topologySort();
		int ret = 0;
		for(int a : result) {
			ret = Math.max(ret, a);
		}
		System.out.println(ret);
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>(); // 진입 차수가 0인 노드를 넣을 큐
		for(int i=1;i<=N;i++) {
			if(degree[i]==0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int tmp = q.poll();
			for (int tmpN : tree[tmp]) {
				degree[tmpN]--;
				result[tmpN] = Math.max(result[tmpN], result[tmp]+time[tmpN]);
				if (degree[tmpN] == 0) {
					q.add(tmpN);
				}
			}
		}
	}

}
