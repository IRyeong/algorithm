import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static HashMap<Character, Integer> map = new HashMap<>();
	static int ALen, WLen, SLen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			String A = br.readLine(); // 사용되는 모든 알파벳을 배치 순서대로 나열
			String W = br.readLine(); // 원문
			String S = br.readLine(); // 시저 암호화된 문자열
			// A를 map에 일단 저장
			ALen = A.length();
			for (int i = 0; i < ALen; i++) {
				map.put(A.charAt(i), i);
			}
//			System.out.println(map.toString());
			// shift(i)를 하나씩 늘려나가면서 S 복호화
			SLen = S.length(); // text
			WLen = W.length(); // pattern
			Queue<Integer> ans = new LinkedList<>();
			for (int i = 0; i < ALen; i++) {
				StringBuilder decrypt = new StringBuilder();
				for (int j = 0; j < WLen; j++) {
					decrypt.append(A.charAt((map.get(W.charAt(j)) + i) % ALen));
				}
				// 복호화된 문자열에 W가 한 개만 있는지 확인하고 한 개만 있으면 Queue에 저장
//				System.out.println("KMP(" + S + ", " + decrypt + ") : " + KMP(S, decrypt));
				if (KMP(S, decrypt) == 1) {
					ans.add(i);
				}
			}
			int ansCnt = ans.size();
			if (ansCnt == 0) {
				sb.append("no solution" + "\n");
			} else if (ansCnt == 1) {
				sb.append("unique: " + ans.poll() + "\n");
			} else {
				sb.append("ambiguous:");
				for (int i = 0; i < ansCnt; i++) {
					sb.append(" " + ans.poll());
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	// KMP 알고리즘 적용하기
	private static int KMP(String text, StringBuilder pattern) {
		int table[] = make_table(pattern);
		int cnt = 0; // T에 P가 몇 번 나타나는지 저장
		for (int i = 0, j = 0; i < SLen; i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				if (j == WLen - 1) {
					cnt++;
					j = table[j];
				} else {
					j++;
				}
			}
		}
		return cnt;
	}

	// 부분 일치 테이블 만들기
	private static int[] make_table(StringBuilder pattern) {
		int table[] = new int[WLen];
		for (int i = 1, j = 0; i < WLen; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				table[i] = ++j;
			} else {
				table[i] = 0;
			}
		}
		return table;
	}
}
