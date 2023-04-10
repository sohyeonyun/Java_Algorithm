import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class b1_1157 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine().toUpperCase();
		
		HashMap<Character, Integer> hash = new HashMap<>();
		
		for(int i=0; i<s.length(); i++) {
			if(hash.containsKey(s.charAt(i))) {
				hash.put(s.charAt(i), hash.get(s.charAt(i)) + 1);
			} else {
				hash.put(s.charAt(i), 1);
			}
		}
		
		int MAX = 0;
		Character answer = '?';
		for(Entry<Character, Integer> entry: hash.entrySet()) {
			if(MAX < entry.getValue()) {
				MAX = entry.getValue();
				answer = entry.getKey();
			} else if(MAX == entry.getValue()) {
				answer = '?';
			}
		}
		
		System.out.println(answer);

//		Scanner sc = new Scanner(System.in);
//		
//		String s = sc.nextLine();
//		
//		s = s.toUpperCase();
//		
//		int n = 'Z' - 'A' + 1;
//		int[] alpha = new int[n];
//		
//		for(int i=0; i<s.length(); i++) {
//			alpha[s.charAt(i) - 'A']++;
//		}
//		
//		int max = 0;
//		int max_index = -1;
//		
//		for(int i=0; i<n; i++) {
//			if(max < alpha[i]) {
//				max_index = i;
//				max = alpha[i];
//			}
//		}
//		
//		boolean same = false;
//		for(int i=0; i<n; i++) {
//			if(max == alpha[i]) {
//				if(!same) {
//					same = true;
//					continue;
//				}
//				System.out.println("?");
//				return;
//			}
//		}
//		
//		System.out.println((char)('A' + max_index));
		
	}

}
