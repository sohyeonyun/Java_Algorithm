import java.util.Scanner;

public class g5_12919 {
	
	static String start;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		start = sc.nextLine();
		String end = sc.nextLine();

		if (dfs(end)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}
	
	private static boolean dfs(String end) {
		if (start.equals(end)) {
			return true;
		}
		
		if(end.length() <= 1) {
			return false;
		}
		
		// A 떼기
		if(end.charAt(end.length() - 1) == 'A') {
			if(dfs(end.substring(0, end.length()-1))) {
				return true;
			}
		}
		
		// B 떼기
		if(end.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder(end);
			if(dfs(sb.reverse().delete(sb.length() - 1, sb.length()).toString())) {
				return true;
			}
		}
		
		return false;

	}

}
