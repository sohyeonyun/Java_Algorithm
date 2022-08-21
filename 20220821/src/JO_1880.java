import java.util.HashMap;
import java.util.Scanner;

/*
 * 암호풀기
 * A65 a97
 */
public class JO_1880 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		String keys = sc.nextLine();
		String input = sc.nextLine();

		HashMap<Character, Character> hash = new HashMap<>();

		for (int i = 0; i < keys.length(); i++) {
			char key = (char) (i + 'a');
			char value = keys.charAt(i);
			hash.put(key, value);
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isLowerCase(c)) {
				c = hash.get(c);
			} else if (Character.isUpperCase(c)) {
				char lower = Character.toLowerCase(c);
				c = Character.toUpperCase(hash.get(lower));
			}

			sb.append(c);
		}

		System.out.println(sb);
	}

}
