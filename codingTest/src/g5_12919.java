import java.util.Scanner;

public class g5_12919 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		StringBuilder start = new StringBuilder(sc.nextLine());
		String end = sc.nextLine();

		if (end.charAt(0) == 'A' && end.contains("B")) {
			System.out.println(0);
			return;
		}

		if (dfs(start, end)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}
	
	static int num = 0;

	private static boolean dfs(StringBuilder start, String end) {
System.out.println(num++);
		if (start.equals(end)) {
			return true;
		}

		System.out.println(start + " " + end + " " + start.length() + " " + end.length());
		System.out.println();
		
		if (start.length() >= end.length()) {
			return false;
		}
		
		dfs(start.append("A"), end);
		dfs(start.replace(start.length() - 1, start.length(), "B").reverse(), end);

		return false;

	}

}
