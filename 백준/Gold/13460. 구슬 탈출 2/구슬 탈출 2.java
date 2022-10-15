import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ret = Integer.MAX_VALUE;
	static Node red, blue;
	static char matrix[][];

	static class Node {
		int x;
		int y;
		int flag; // 1 : 왼, 2 : 오, 3 : 위, 4 : 아래, 0 : default
		int count; // 몇 번 돌렸는지

		public Node(int x, int y, int flag, int count) {
			super();
			this.x = x;
			this.y = y;
			this.flag = flag;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new char[N][M];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = str.charAt(j);
				if (matrix[i][j] == 'R') {
					red = new Node(i, j, 0, 1);
					matrix[i][j] = '.';
				} else if (matrix[i][j] == 'B') {
					blue = new Node(i, j, 0, 1);
					matrix[i][j] = '.';
				}
			}
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(red);
		q.offer(blue);
		a: while (!q.isEmpty()) {
			Node redNode = q.poll();
			Node blueNode = q.poll();
			Node backupRed = redNode;
			Node backupBlue = blueNode;

			if (redNode.count == 11) {
				ret = -1;
				continue;
			}
			if (redNode.flag == 0) { // 위, 아래, 오른쪽, 왼쪽 가능
				for (int i = 1; i <= 4; i++) {
					redNode = backupRed;
					blueNode = backupBlue;
					redNode = move(i, redNode, blueNode);
					blueNode = move(i, blueNode, redNode);
					if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					}
					redNode = move(i, redNode, blueNode);
					if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					} else if (blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					}
					redNode.count++;
					blueNode.count++;
					q.offer(redNode);
					q.offer(blueNode);
				}
			} else if (redNode.flag == 1 || redNode.flag == 2) { // 위, 아래 가능
				for (int i = 3; i <= 4; i++) {
					redNode = backupRed;
					blueNode = backupBlue;
					redNode = move(i, redNode, blueNode);
					blueNode = move(i, blueNode, redNode);
					if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					}
					redNode = move(i, redNode, blueNode);
					if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					} else if (blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					}
					redNode.count++;
					blueNode.count++;
					q.offer(redNode);
					q.offer(blueNode);
				}
			} else if (redNode.flag == 3 || redNode.flag == 4) { // 오른쪽, 왼쪽 가능
				for (int i = 1; i <= 2; i++) {
					redNode = backupRed;
					blueNode = backupBlue;
					redNode = move(i, redNode, blueNode);
					blueNode = move(i, blueNode, redNode);
					if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1 && blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					} else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					}
					redNode = move(i, redNode, blueNode);
					if(redNode.x==-1 && blueNode.x==-1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					}
					else if (redNode.x == -1) {
						if (ret == -1 || ret == Integer.MAX_VALUE) {
							ret = blueNode.count;
						} else {
							ret = Math.min(ret, blueNode.count);
						}
						break a;
					} else if (blueNode.x == -1) {
						if (ret == Integer.MAX_VALUE) {
							ret = -1;
						}
						redNode = backupRed;
						blueNode = backupBlue;
						continue;
					}
					redNode.count++;
					blueNode.count++;
					q.offer(redNode);
					q.offer(blueNode);
				}
			}
		}
		System.out.println(ret);
	}

	private static Node move(int dir, Node tmpNode, Node otherNode) {
		Node retNode;
		int tx, ty, ox = -1, oy = -1;
		tx = tmpNode.x;
		ty = tmpNode.y;
		if (otherNode.x != -1) {
			ox = otherNode.x;
			oy = otherNode.y;
		}
		if (dir == 1) { // 왼쪽으로
			int i;
			for (i = ty - 1; i >= 0; i--) {
				if (otherNode.x != -1 && tx == ox && i == oy) {
					break;
				} else if (matrix[tx][i] == '.') { // 왼쪽으로 갈 수 있는 경우
					continue;
				} else if (matrix[tx][i] == 'O') { // 구멍이 있어서 끝나는 경우
					retNode = new Node(-1, -1, -1, tmpNode.count);
					return retNode;
				} else { // 왼쪽으로 못 가는 경우
					break;
				}
			}
			ty = i + 1;
			retNode = new Node(tx, ty, dir, tmpNode.count);
			return retNode;
		} else if (dir == 2) { // 오른쪽으로
			int i;
			for (i = ty + 1; i < M; i++) {
				if (otherNode.x != -1 && tx == ox && i == oy) {
					break;
				} else if (matrix[tx][i] == '.') { // 오른쪽으로 갈 수 있는 경우
					continue;
				} else if (matrix[tx][i] == 'O') { // 구멍이 있어서 끝나는 경우
					retNode = new Node(-1, -1, -1, tmpNode.count);
					return retNode;
				} else { // 오른쪽으로 못 가는 경우
					break;
				}
			}
			ty = i - 1;
			retNode = new Node(tx, ty, dir, tmpNode.count);
			return retNode;
		} else if (dir == 3) { // 위쪽으로
			int i;
			for (i = tx - 1; i >= 0; i--) {
				if (otherNode.x != -1 && i == ox && ty == oy) {
					break;
				} else if (matrix[i][ty] == '.') { // 위쪽으로 갈 수 있는 경우
					continue;
				} else if (matrix[i][ty] == 'O') { // 구멍이 있어서 끝나는 경우
					retNode = new Node(-1, -1, -1, tmpNode.count);
					return retNode;
				} else { // 위쪽으로 못 가는 경우
					break;
				}
			}
			tx = i + 1;
			retNode = new Node(tx, ty, dir, tmpNode.count);
			return retNode;
		} else if (dir == 4) { // 아래쪽으로
			int i;
			for (i = tx + 1; i < N; i++) {
				if (otherNode.x != -1 && i == ox && ty == oy) {
					break;
				} else if (matrix[i][ty] == '.') { // 아래쪽으로 갈 수 있는 경우
					continue;
				} else if (matrix[i][ty] == 'O') { // 구멍이 있어서 끝나는 경우
					retNode = new Node(-1, -1, -1, tmpNode.count);
					return retNode;
				} else { // 아래쪽으로 못 가는 경우
					break;
				}
			}
			tx = i - 1;
			retNode = new Node(tx, ty, dir, tmpNode.count);
			return retNode;
		}
		return null;
	}

}
