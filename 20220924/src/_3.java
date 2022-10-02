import java.util.Arrays;

public class _3 {

	static int[][] users;
	static int[] emotions;
	static int N;
	static int[] ratios;
	static int[] input = { 10, 20, 30, 40 };
	static int MAX_service = 0;
	static int MAX_sales = 0;

	public static void main(String[] args) {
		int[][] u = { { 40, 10000 }, { 25, 10000 } };
		int[] e = { 7000, 9000 };

		users = u;
		emotions = e;

		N = emotions.length;
		ratios = new int[N];

		perm(0);

		System.out.println("!!!" + MAX_service + " " + MAX_sales);
	}

	public static void perm(int cnt) {
		if (cnt == N) {
			System.out.println(Arrays.toString(ratios));

			int service = 0;
			int sales = 0;

			for (int[] user : users) {
				int ratio = user[0];
				int money = user[1];
				
				double sum = 0;
				for (int i = 0; i < N; i++) {
					if(ratios[i] >= ratio) {
						sum += emotions[i] * (1 - (double) ratios[i] / 100);
					}
				}
				
				if(sum >= money) { // 서비스 가입
					service++;
				} else  { // 판매
					sales += sum;
				}
				
			}
			System.out.println(service + " " + sales);
			
			if(MAX_service < service) {
				MAX_service = service;
				MAX_sales = sales;
			} else if(MAX_service == service && MAX_sales < sales) {
				MAX_sales = sales;
			}
			System.out.println("===" + MAX_service + " " + MAX_sales);
			return;
		}

		for (int i = 0; i < 4; i++) {
			ratios[cnt] = input[i];
			perm(cnt + 1);
		}

	}

}
