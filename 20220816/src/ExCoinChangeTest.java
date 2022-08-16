import java.util.Scanner;

public class ExCoinChangeTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int targetMoney = sc.nextInt();
		int[] units = { 500, 100, 50, 10, 5, 1 };

		int[] counts = { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };

		int totalMoney = 0;
		for (int i = 0; i < 6; i++) {
			totalMoney += units[i] * counts[i];
		} // 모든 코인으로 만들 수 있는 최대 금액

		int remainMoney = totalMoney - targetMoney; // 나머지 잔돈
		// 잔돈의 최소 금액으로 결제
		int sum = 0;
		int maxCnt = 0;
		int availCnt = 0;
		for (int i = 0; i < 6; i++) { // 가장 큰 화폐 단위부터 많이 사용하도록
			maxCnt = remainMoney / units[i]; // 해당 단위로 가장 많이 사용할 수 있는 갯수
			// 실제 사용한 코인 갯수
			availCnt = maxCnt <= counts[i] ? maxCnt : counts[i];
			counts[i] -= availCnt; // 사용하고 남은 갯수
			remainMoney -= availCnt * units[i]; // 실제 남은 돈 구하기
			sum += counts[i]; // 실제 지급하려고 사용한 동전의 갯수
		}
		System.out.println(sum); // 음료수갑을 지불하기 위해 사용한 동전갯수

		for (int i = 0; i < counts.length; i++) {
			System.out.print(counts[i] + " ");
		}
		System.out.println();
	}

}