import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static class Horse {
		int direction; // 1 : 오른쪽, 2 : 왼쪽, 3 : 위쪽, 4 : 아래쪽
		int x;
		int y;

		public Horse(int x, int y, int direction) {
			super();
			this.direction = direction;
			this.x = x;
			this.y = y;
		}
	}

	private static class Info {
		int color; // 0 : 흰색, 1 : 빨간색, 2 : 파란색
		String order; // 말을 둔 순서대로 저장 : 맨 밑부터 맨 위까지

		public Info(int color, String order) {
			super();
			this.color = color;
			this.order = order;
		}
	}

	static int N, K, size, tmpChar;
	static Horse h[]; // 말들 정보 저장
	static Info board[][]; // 체스판 정보 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new Info[N + 1][N + 1];
		h = new Horse[K];
		for (int i = 1; i <= N; i++) { // 체스판 색깔 정보 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = new Info(Integer.parseInt(st.nextToken()), "");
			}
		}
		int hx, hy, hdir;
		for (int i = 0; i < K; i++) { // 각 말의 행, 열, 이동 방향 정보 입력
			st = new StringTokenizer(br.readLine());
			hx = Integer.parseInt(st.nextToken());
			hy = Integer.parseInt(st.nextToken());
			hdir = Integer.parseInt(st.nextToken());
			h[i] = new Horse(hx, hy, hdir);
			board[hx][hy].order += i;
		}
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				if (board[i][j].order.length() == 0) {
//					System.out.print("X ");
//				} else {
//					System.out.print(board[i][j].order + " ");
//				}
//			}
//			System.out.println();
//		}
		int turn = 0;
		int afterX, afterY, curX, curY, moveDir;
		int boardColor;
		String tmpOrder, remOrder, s;
		StringBuffer sb;
		a : while (turn++ < 1000) {
			for (int i = 0; i < K; i++) { // 한 턴에 순서대로 모든 말이 움직임
				curX = h[i].x; // 현재 좌표
				curY = h[i].y;
				afterX = curX; // 움직인 뒤의 좌표
				afterY = curY;
				moveDir = h[i].direction; // 현재 방향
				if (moveDir == 1) { // 1 : 오른쪽, 2 : 왼쪽, 3 : 위쪽, 4 : 아래쪽
					afterY += 1;
				} else if (moveDir == 2) {
					afterY -= 1;
				} else if (moveDir == 3) {
					afterX -= 1;
				} else {
					afterX += 1;
				}
				if (afterX <= 0 || afterX > N || afterY <= 0 || afterY > N) {
					if (afterX <= 0) {
						afterX = 1;
					} else if (afterX > N) {
						afterX = N;
					}
					if (afterY <= 0) {
						afterY = 1;
					} else if (afterY > N) {
						afterY = N;
					}
					boardColor = 2;
				} else {
					boardColor = board[afterX][afterY].color; // 움직인 뒤의 좌표에 있는 색깔
				}
				switch (boardColor) {
				case 0: // 흰색
					tmpOrder = board[curX][curY].order; // 현재 좌표에 있는 말들 순서
					remOrder = tmpOrder.substring(tmpOrder.indexOf((char) (i + '0'))); // 움직였을 때 after 좌표로 올길 말들
					if (afterX != curX || afterY != curY) {
						board[afterX][afterY].order += remOrder; // 움직인 뒤의 좌표에 있는 말들 순서 갱신
						board[curX][curY].order = tmpOrder.substring(0, tmpOrder.indexOf(i + '0')); // 현재 좌표에 있는
					}else {
						board[afterX][afterY].order = remOrder;
					}
					size = remOrder.length();
					for (int p = 0; p < size; p++) {
						tmpChar = remOrder.charAt(p) - 48;
						h[tmpChar].x = afterX;
						h[tmpChar].y = afterY;
					}
					if(board[afterX][afterY].order.length()>=4) {
						break a;
					}
					break;
				case 1: // 빨간색
					tmpOrder = board[curX][curY].order;
					remOrder = tmpOrder.substring(tmpOrder.indexOf((char) (i + '0')));
					sb = new StringBuffer(remOrder);
					if (afterX != curX || afterY != curY) {
						board[afterX][afterY].order += sb.reverse().toString();
						board[curX][curY].order = tmpOrder.substring(0, tmpOrder.indexOf(i + '0'));
					} else {
						board[afterX][afterY].order = sb.reverse().toString();
					}
					size = remOrder.length();
					for (int p = 0; p < size; p++) {
						tmpChar = remOrder.charAt(p) - 48;
						h[tmpChar].x = afterX;
						h[tmpChar].y = afterY;
					}
					if(board[afterX][afterY].order.length()>=4) {
						break a;
					}
					break;
				case 2: // 파란색
					if (moveDir == 1) { // 1 : 오른쪽, 2 : 왼쪽, 3 : 위쪽, 4 : 아래쪽
						moveDir = 2;
						h[i].direction = 2;
					} else if (moveDir == 2) {
						moveDir = 1;
						h[i].direction = 1;
					} else if (moveDir == 3) {
						moveDir = 4;
						h[i].direction = 4;
					} else {
						moveDir = 3;
						h[i].direction = 3;
					}
					if (moveDir == 1) { // 1 : 오른쪽, 2 : 왼쪽, 3 : 위쪽, 4 : 아래쪽
						afterY = curY + 1;
					} else if (moveDir == 2) {
						afterY = curY - 1;
					} else if (moveDir == 3) {
						afterX = curX - 1;
					} else {
						afterX = curX + 1;
					}
					if (afterX <= 0 || afterX > N || afterY <= 0 || afterY > N) {
						if (afterX <= 0) {
							afterX = 1;
						} else if (afterX > N) {
							afterX = N;
						}
						if (afterY <= 0) {
							afterY = 1;
						} else if (afterY > N) {
							afterY = N;
						}
						boardColor = 2;
					} else {
						boardColor = board[afterX][afterY].color; // 움직인 뒤의 좌표에 있는 색깔
					}
					switch (boardColor) {
					case 0: // 흰색
						tmpOrder = board[curX][curY].order; // 현재 좌표에 있는 말들 순서
						remOrder = tmpOrder.substring(tmpOrder.indexOf((char) (i + '0'))); // 움직였을 때 after 좌표로 올길 말들
						if (afterX != curX || afterY != curY) {
							board[afterX][afterY].order += remOrder; // 움직인 뒤의 좌표에 있는 말들 순서 갱신
							board[curX][curY].order = tmpOrder.substring(0, tmpOrder.indexOf(i + '0')); // 현재 좌표에 있는
						}else {
							board[afterX][afterY].order = remOrder;
						}
						size = remOrder.length();
						for (int p = 0; p < size; p++) {
							tmpChar = remOrder.charAt(p) - 48;
							h[tmpChar].x = afterX;
							h[tmpChar].y = afterY;
						}
						if(board[afterX][afterY].order.length()>=4) {
							break a;
						}
						break;
					case 1: // 빨간색
						tmpOrder = board[curX][curY].order;
						remOrder = tmpOrder.substring(tmpOrder.indexOf((char) (i + '0')));
						sb = new StringBuffer(remOrder);
						if (afterX != curX || afterY != curY) {
							board[afterX][afterY].order += sb.reverse().toString();
							board[curX][curY].order = tmpOrder.substring(0, tmpOrder.indexOf(i + '0'));
						} else {
							board[afterX][afterY].order = sb.reverse().toString();
						}
						size = remOrder.length();
						for (int p = 0; p < size; p++) {
							tmpChar = remOrder.charAt(p) - 48;
							h[tmpChar].x = afterX;
							h[tmpChar].y = afterY;
						}
						if(board[afterX][afterY].order.length()>=4) {
							break a;
						}
						break;
					case 2: // 파란색
						h[i].x = curX;
						h[i].y = curY;
						if(board[curX][curY].order.length()>=4) {
							break a;
						}
						break;
					}
					break;
				}
			}
		}
		if (turn == 1001) {
			System.out.println(-1);
		} else {
			System.out.println(turn);
		}
	}

}