import java.util.*;
import java.io.*;

public class g3_12757 {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> keyList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			map.put(key, value);
			keyList.add(key);
		}
		
		// binary search 위헤 정렬
		Collections.sort(keyList);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) { // cmd 1 추가
				int value = Integer.parseInt(st.nextToken());
				map.put(key, value);
				
				keyList.add(key);
				Collections.sort(keyList);
			} else if(cmd == 2) { // cmd 2 변경
				int value = Integer.parseInt(st.nextToken());
				
				// 해당 key에서 있는지
				if(map.containsKey(key)) {
					map.replace(key, value);
					continue;
				}
				// 양쪽 K거리만큼의 key에 대해 있는지
				int rightIdx = Collections.binarySearch(keyList, key);
				
				// key와 양쪽에서 가장 가까운 인덱스
				rightIdx = -(rightIdx + 1);
				int leftIdx = rightIdx - 1;
				
				// 양쪽 가장자리
				if(leftIdx < 0) {
					if(keyList.get(rightIdx) - key < K) {
						map.replace(keyList.get(rightIdx), value);
					}
					continue;
				} else if(rightIdx >= keyList.size()) {
					if(keyList.get(leftIdx) - key < K) {
						map.replace(keyList.get(leftIdx), value);
					}
					continue;
				}
				
				int leftKey = keyList.get(leftIdx);
				int rightKey = keyList.get(rightIdx);
				
				// 양쪽 중 K 거리 안이고 더 가까운 거?
				if(rightKey - key < leftKey - key) { // 오른쪽 키가 더 가깝다
					if(rightKey - key < K) {
						map.replace(rightKey, value);
					} 
				} else if(rightKey - key > leftKey - key) { // 왼쪽 키가 더 가깝다
					if(leftKey - key < K) {
						map.replace(leftKey, value);
					}
				} 
				
				
			} else { // cmd 3
				// 해당 key에서 있는지
				if(map.containsKey(key)) {
					System.out.println(map.get(key));
					continue;
				}
				// 양쪽 K거리만큼의 key에 대해 있는지
				int rightIdx = Collections.binarySearch(keyList, key);
				
				// key와 양쪽에서 가장 가까운 인덱스
				rightIdx = -(rightIdx + 1);
				int leftIdx = rightIdx - 1;
				
				// 양쪽 가장자리
				if(leftIdx < 0) {
					if(keyList.get(rightIdx) - key < K) {
						System.out.println(map.get(keyList.get(rightIdx)));
					} else {
						System.out.println(-1);
					}
					continue;
				} else if(rightIdx >= keyList.size()) {
					if(key - keyList.get(leftIdx) < K) {
						System.out.println(map.get(keyList.get(leftIdx)));
					} else {
						System.out.println(-1);
					}
					continue;
				}
				
				int leftKey = keyList.get(leftIdx);
				int rightKey = keyList.get(rightIdx);
				// 양쪽 중 K 거리 안이고 더 가까운 거?
				if(rightKey - key < key - leftKey) { // 오른쪽 키가 더 가깝다
					if(rightKey - key < K) {
						System.out.println(map.get(rightKey));
					} 
				} else if(rightKey - key > key - leftKey) { // 왼쪽 키가 더 가깝다
					if(key - leftKey < K) {
						System.out.println(map.get(leftKey));
					}
				} else if(rightKey - key == key - leftKey) { // key가 두개 이상
					System.out.println("?");
				} else { // key 없음
					System.out.println(-1);
				}
				
			}
		}
		
		
	}

}
