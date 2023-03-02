import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// (x,y) 
	// 직사각 (0,0) - (w,h)
	// 직사각형의 경계선까지 가는 거리의 최솟값 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int minX = Math.min(x, w-x);
		int minY = Math.min(y, h-y);
		System.out.println(Math.min(minX, minY));
	}

}
