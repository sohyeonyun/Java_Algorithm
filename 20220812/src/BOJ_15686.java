import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 치킨 배달
 */
class Pos {
	int x;
	int y;
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15686 {
	
	static int n, m;
	static List<Pos> chicken, house;
	static boolean[] select;
	static int cityMin = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n: 배열 크기, m: 남길 치킨집의 최대 개수
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) house.add(new Pos(i, j));
				if(a == 2) chicken.add(new Pos(i, j));
			}
		}
		
		select = new boolean[chicken.size()];
		comb(0, 0);
		
		System.out.println(cityMin);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == m) {
			int sum = 0; // 이 경우에서 도시의 모든 치킨 거리의 합
			for(int i=0; i<house.size(); i++) {
				int minL = Integer.MAX_VALUE;	// 각 집마다 치킨 거리를 각각 구함.
				for(int j=0; j<chicken.size(); j++) {
					if(select[j]) {
						Pos h = house.get(i);
						Pos c = chicken.get(j);
						int len = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
						minL = Math.min(minL, len);
					}
				}
				sum += minL;
			}
			// 각 경우에서 도시의 최소 치킨 거리 갱신
			cityMin = Math.min(cityMin, sum);
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			select[i] = true;
			comb(cnt + 1, i + 1);
			select[i] = false;
		}
	}
	

}
