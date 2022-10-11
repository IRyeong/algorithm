import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int tLen, pLen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		KMP(T, P);
	}

	private static void KMP(char[] t, char[] p) {
		tLen = t.length;
		pLen = p.length;
		int table[] = make_table(p);
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0, j = 0; i < tLen; i++) {
			while (j > 0 && t[i] != p[j]) {
				j = table[j - 1];
			}
			if (t[i] == p[j]) {
				if (j == pLen - 1) {
					cnt++;
					list.add(i - j + 1);
					j = table[j];
				} else {
					j++;
				}
			}
		}
		System.out.println(cnt);
		for (Integer i : list) {
			System.out.print(i + " ");
		}
	}

	// 부분 일치 테이블 만들기
	private static int[] make_table(char[] p) {
		int table[] = new int[pLen];
		for (int i = 1, j = 0; i < pLen; i++) {
			while (j > 0 && p[i] != p[j]) {
				j = table[j - 1];
			}
			if (p[i] == p[j]) {
				table[i] = ++j;
			} else {
				table[i] = 0;
			}
		}
		return table;
	}

}
