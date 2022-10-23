import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(String str1, String str2) {
        double up = 0; // 교집합 수
		double down = 0; // 합집합 수
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		int len1 = str1.length();
		int len2 = str2.length();
		for (int i = 0; i < len1 - 1; i++) {
			if (isEng(str1.charAt(i)) && isEng(str1.charAt(i + 1))) {
				list1.add("" + toSmall(str1.charAt(i)) + toSmall(str1.charAt(i + 1)));
			}
		}
		for (int i = 0; i < len2 - 1; i++) {
			if (isEng(str2.charAt(i)) && isEng(str2.charAt(i + 1))) {
				list2.add("" + toSmall(str2.charAt(i)) + toSmall(str2.charAt(i + 1)));
			}
		}
		Collections.sort(list1);
		Collections.sort(list2);
		len1 = list1.size();
		len2 = list2.size();
		if (len1 == 0 && len2 == 0) {
			up = 1;
			down = 1;
		}
		if (len2 > 0) {
			String tmp = list2.remove(0);

			int i=0, comp;
			while(i<len1) {
				comp = list1.get(i).compareTo(tmp);
				if (comp == 0) {
					up++;
					if (list2.size() == 0) {
						break;
					}
					tmp = list2.remove(0);
					i++;
					continue;
				} else if (comp < 0) { // s1이 tmp보다 사전 순서가 앞설 때
					i++;
					continue;
				} else { // tmp가 s1보다 사전 순서가 앞설 때
					if (list2.size() == 0) {
						break;
					}
					tmp = list2.remove(0);
				}
			}
			down = len1 + len2 - up;
		}
		return (int) ((up / down) * 65536);
    }
    private static char toSmall(char c) {
		if(c >= 'A' && c <= 'Z') {
			c -= ('A'-'a');
		}
		return c;
	}

	private static boolean isEng(char c) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			return true;
		}
		return false;
	}
}