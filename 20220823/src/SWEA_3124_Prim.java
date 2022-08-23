import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3124_Prim {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 간선 정보 저장할 배열
			int[][] map = new int[V + 1][V + 1];
			
			// 간선 정보 입력받기
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[a][b] = c;
				map[b][a] = c;
			}
			
			// 최소 비용
			long result = 0;
			
			// 거리, 방문 배열
			boolean [] v = new boolean[V + 1];
			int [] dist = new int[V + 1];
			for(int i = 1; i <= V; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			// 시작점
			int start = 1;
			dist[start] = 0;
			
			// 모든 정점마다 시행
			for(int i = 1; i <= V; i++) {
				// dist에서 최솟값 찾기
				int min = Integer.MAX_VALUE;
				int minIdx = 0;
				for(int j = 1; j <= V; j++) {
					if(v[j]) continue;
					if(min > dist[j]) {
						min = dist[j];
						minIdx = j;
					}
				}
				// 스패닝 트리에 추가
				result += min;
				v[minIdx] = true;
				// dist 업데이트
				for(int j = 1; j <= V; j++) {
					if(v[j]) continue;
					if(map[minIdx][j] == 0) continue;
					if(dist[j] > map[minIdx][j]) {
						dist[j] = map[minIdx][j];
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(result).append('\n');
			
		}
		System.out.print(sb);
	}


}
