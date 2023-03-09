import java.util.Scanner;

public class _9935 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		String bomb = sc.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<str.length(); i++) {
			
			sb.append(str.charAt(i));
			
			// sb의 끝문자 검사
			if(sb.length() >= bomb.length()) {
				// 폭발 문자열과 sb의 뒷문자열 확인
				boolean flag = true;
				for(int j=0; j<bomb.length(); j++) {
					if(sb.charAt(sb.length() - j - 1) != bomb.charAt(bomb.length() - j - 1)) {
						flag = false;
						break;
					}
				}
				// 폭발 문자열 동일! 펑
				if(flag) {
					sb.delete(sb.length() - bomb.length(), sb.length());
				}
			}
			
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
		
	}

}
