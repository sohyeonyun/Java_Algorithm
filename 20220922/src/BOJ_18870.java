import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_18870 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// key: 인풋 숫자, value: 해당 숫자가 나타나는 인덱스
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> list = new ArrayList<>();
			if(map.containsKey(key)) {
				list = map.get(key);
				list.add(i);
			} else {
				list.add(i);
			}
			map.put(key, list);
		}
	
		// map의 key 리스트를 생성해 내림차순 정렬
		ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
		keyList.sort((x1, x2) -> -Integer.compare(x1, x2));
		
//		for(Integer key : keyList) {
//			System.out.println(key + " " + map.get(key));
//		}
//		1000 [0, 2, 4]
//		999 [1, 3, 5]		

		// 정답 배열
		int[] answer = new int[n];

		int keySize = keyList.size();
		for (int i = 0; i < keySize; i++) {
			int key = keyList.get(i);
			for(Integer idx: map.get(key)) {
				answer[idx] = keySize - i - 1;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}

}
