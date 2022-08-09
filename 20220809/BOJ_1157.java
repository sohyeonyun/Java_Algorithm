import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class BOJ_1157 {

	public static void main(String[] args) {

		// 문자열 입력 받고 대문자로 고쳐줌. 
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine().toUpperCase();
		
		// hashMap 자료구조 사용 
		HashMap<Character, Integer> hash = new HashMap<>();
		
		// 문자 하나씩 개수와 함께 해쉬에 넣어준다. 
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(hash.containsKey(c)) hash.put(c, hash.get(c) + 1);
			else hash.put(c, 1);
		}
		
		// 해쉬의 키와 값을 하나씩 보며 최대 개수일 때 구한다. 
		int MAX = 0;
		Character answer = '?';
		for(Entry<Character, Integer> entry : hash.entrySet()) {
			if(MAX < entry.getValue()) {
				MAX = entry.getValue();
				answer = entry.getKey();
			} else if(MAX == entry.getValue()) {
				answer = '?';
			}
		}
		
		System.out.println(answer);
	}

}
