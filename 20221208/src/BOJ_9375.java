import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 패션왕 신해빈 
 */
public class BOJ_9375 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			
			while(N-- > 0) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				if(map.containsKey(type)) {
					map.put(type, map.get(type) + 1);
				} else {
					map.put(type, 1);
				}
			}
			
			int ans = 1;
			for(String key : map.keySet()) {
				ans *= (map.get(key) + 1);  // 경우의수 + 1 (안입을 경우)
			}
			System.out.println(ans - 1); // 아무것도 안입을 경우 빼
		}
		
	}

}
