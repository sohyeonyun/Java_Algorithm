import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BOJ_20291 {

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();

		HashMap<String, Integer> hashmap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			// 파일을 이름과 확장자로 자름
			String type = new String(sc.nextLine().split("\\.")[1]);
			// 확장자 개수 센다
			if (!hashmap.containsKey(type)) {
				hashmap.put(type, 1);
			} else {
				hashmap.put(type, hashmap.get(type) + 1);
			}
		}

		// 확장자 이름 정렬
		List<String> keySet = new ArrayList<>(hashmap.keySet());
		Collections.sort(keySet); // 오름차순
//		keySet.sort(Collections.reverseOrder()); // 내림차순
		for (String key : keySet) {
			System.out.println(key + " " + hashmap.get(key));
		}
	}

}
