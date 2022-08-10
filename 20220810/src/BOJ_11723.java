import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BOJ_11723 {

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 연산 횟수
		int n = Integer.parseInt(br.readLine());
		// 공집합 (비트 마스킹)
		int set = 0;

		for (int i = 0; i < n; i++) {
			// 연산, 숫자 입력받기
			String[] input = br.readLine().split(" ");
			String command = input[0];
			int x = 0;
			if (!command.equals("all") && !command.equals("empty")) {
				x = Integer.parseInt(input[1]);
			}

			// 연산 수행
			switch (command) {
			case "add":
				set = set | (1 << x);
				break;
			case "remove":
				if (((set >> x) & 1) == 1) {
					set = set ^ (1 << x);
				}
				break;
			case "check":
				boolean res = (set & (1 << x)) != 0;
				bw.write(res ? 1 + "\n": 0 + "\n");
				break;
			case "toggle":
				set = set ^ (1 << x);
				break;
			case "all":
				set = set | (-1);
				break;
			case "empty":
				set = set & 0;
				break;

			}
//			System.out.println(command + " " + x + ">> " + set);
		}
		bw.flush();
	}

}
