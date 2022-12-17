import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static final int INF = 987_654_321; // (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 크기만 하면 됨
	static int n, m, t; // 교차로, 도로, 목적지 후보의 개수
	static int s, g, h; // 출발지, g-h 교차로를 반드시 지남
	static List<Node>[] list;
	static int[] dist;
	static List<Integer> goal; // 목적지 후보
	static boolean[] check;

	private static class Node implements Comparable<Node> {
		int end, distance;

		public Node(int end, int weight) {
			this.end = end;
			this.distance = weight;
		}

		@Override
		public int compareTo(Node o) {
			return distance - o.distance;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력 받기
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			// 최단 거리 배열, 인접 리스트 초기화
			list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<>();
			}
			// 인접 리스트에 그래프 정보 추가
			int a, b, d, passed = 0;
			for (int k = 0; k < m; k++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				list[a].add(new Node(b, d));
				list[b].add(new Node(a, d));
				if ((a == g && b == h) || (a == h && b == g)) {
					passed = d;
				}
			}
			// 목적지 리스트 입력받음
			int x;
			goal = new LinkedList<>();
			while (t-- > 0) {
				x = Integer.parseInt(br.readLine());
				goal.add(x);
			}
			// 다익스트라 알고리즘
			List<Integer> ans = new ArrayList<>();
			for (Integer gl : goal) {
				long tmp1 = dijkstra(s, g) + passed + dijkstra(h, gl);
				long tmp2 = dijkstra(s, h) + passed + dijkstra(g, gl);
				long sum = dijkstra(s, gl);
				if (Math.min(tmp1, tmp2) == sum) {
					ans.add(gl);
				}
			}
			Collections.sort(ans);
			for (Integer as : ans) {
				sb.append(as + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int dijkstra(int start, int end) {
		dist = new int[n + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		check = new boolean[n+1];
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));
		while (!q.isEmpty()) {
			Node tmpN = q.poll();
			int tmpE = tmpN.end;
			int tmpD = tmpN.distance;
			if (check[tmpE] == true) { // 해당 끝점을 이미 고려한 경우 다음 Node로 넘어감
				continue;
			}
			check[tmpE] = true;
			for (Node node : list[tmpE]) {
				// 특정 Node의 끝점까지의 거리가 현재 끝점까지의 거리에 특정 Node의 거리를 더한 것보다 큰 경우
				if (dist[node.end] > dist[tmpE] + node.distance) {
					dist[node.end] = dist[tmpE] + node.distance; // 더 작은 것으로 갱신
					q.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
		return dist[end];
	}
	

}
