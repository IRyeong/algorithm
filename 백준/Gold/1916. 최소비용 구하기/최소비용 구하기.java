import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static final int INF = 987_654_321; // (간선 가중치의 최댓값) * (정점 개수 - 1) 보다 크기만 하면 됨
	static int N, M; // 교차로, 도로
	static List<Node>[] list;
	static int[] dist;
	static boolean[] check;

	private static class Node implements Comparable<Node> {
		int end, cost;

		public Node(int end, int weight) {
			this.end = end;
			this.cost = weight;
		}

		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		int a, b, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, c));
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // 출발지
		int e = Integer.parseInt(st.nextToken()); // 도착지
		System.out.println(dijkstra(s, e));
	}

	private static int dijkstra(int start, int end) {
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		check = new boolean[N + 1];
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));
		while (!q.isEmpty()) {
			Node tmpN = q.poll();
			int tmpE = tmpN.end;
			if (check[tmpE] == true) { // 해당 끝점을 이미 고려한 경우 다음 Node로 넘어감
				continue;
			}
			check[tmpE] = true;
			for (Node node : list[tmpE]) {
				// 특정 Node의 끝점까지의 거리가 현재 끝점까지의 거리에 특정 Node의 거리를 더한 것보다 큰 경우
				if (dist[node.end] > dist[tmpE] + node.cost) {
					dist[node.end] = dist[tmpE] + node.cost; // 더 작은 것으로 갱신
					q.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
		return dist[end];
	}

}
