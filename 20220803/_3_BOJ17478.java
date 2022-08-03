import java.util.Scanner;

/*
 * 17478 재귀함수가 뭔가요?
 */

public class _3_BOJ17478 {

	static String s1 = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static String s2 = "\"재귀함수가 뭔가요?\"";
	static String s3 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static String s4 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static String s5 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static String s6 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static String s7 = "라고 답변하였지.";

	public static void recursive(int cur, int n, String head) {
		if (cur >= n) {
			System.out.println(head + s2);
			System.out.println(head + s6);
			System.out.println(head + s7);
			return;
		}
		System.out.println(head + s2);

		System.out.println(head + s3);
		System.out.println(head + s4);
		System.out.println(head + s5);

		recursive(++cur, n, head + "____");

		System.out.println(head + s7);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		System.out.println(s1);
		recursive(0, n, "");

	}
}