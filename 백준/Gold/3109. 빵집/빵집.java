import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int count;
	static int R, C, curR, curC;
	static int dx[] = { -1, 0, 1 };
	static int dy[] = { 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char matrix[][] = new char[R][C];
		String s;
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = s.charAt(j);
			}
		}
		for(int i=0;i<R;i++) {
			if(pipeCount(matrix, i,0)) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static boolean pipeCount(char[][] matrix, int tmpR, int tmpC) {
		if (tmpC == C - 1) {
			return true;
		}
		
		for (int j = 0; j < 3; j++) {
			curR = tmpR + dx[j];
			curC = tmpC + dy[j];
			if (curR >= 0 && curR < R && curC >= 0 && curC < C && matrix[curR][curC] == '.') {
				matrix[tmpR][tmpC] = '!';
				if(pipeCount(matrix, curR, curC)) {
					return true;
				}
			}
		}
		return false;
	}

}
