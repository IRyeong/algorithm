import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] table; // 진입 차수 저장 테이블
	static ArrayList<Node>[] tree; // 연결 상태 파악하기 위함
	static int[] ret; // 각 부품별로 몇 개 필요한지 결과 출력
	static boolean[] isPart; // 부품이면 true, 완성품이면 false

	private static class Node {
		int num; // 부품 번호
		int cnt; // 필요한 부품 개수

		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 전체 부품 개수
		M = Integer.parseInt(br.readLine()); //
		table = new int[N + 1];
		tree = new ArrayList[N + 1];
		ret = new int[N + 1];
		isPart = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<>();
			isPart[i] = true;
		}
		int X, Y, K;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 중간 부품이나 완제품 X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요하다
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			tree[X].add(new Node(Y, K)); // Y -> X (위상정렬을 역으로 생각)
			table[Y]++;
			isPart[X] = false;
		}
		topologySort();
		for (int i = 1; i <= N; i++) {
			if (isPart[i]) {
				sb.append(i + " " + ret[i] + "\n");
			}
		}
		System.out.println(sb);
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>(); // 진입 차수가 0인 노드를 넣을 큐
		q.add(N);
		ret[N] = 1;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			int tmpC = ret[tmp];
			for (Node tmpT : tree[tmp]) {
				int tmpN = tmpT.num;
				table[tmpN]--;
				ret[tmpN] += (tmpC * tmpT.cnt);
				if (table[tmpN] == 0) {
					q.add(tmpN);
				}
			}
		}
	}

}
