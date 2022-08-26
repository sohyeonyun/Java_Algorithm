import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 최소 스패닝 트리
 */
public class SWEA_3124 {

	static class Data {
		int no, weight;

		public Data(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			boolean[] v = new boolean[V + 1];
			int[] minEdge = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				minEdge[i] = Integer.MAX_VALUE;
			}

			ArrayList<Data>[] list = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<Data>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int no = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list[from].add(new Data(no, weight));
				list[no].add(new Data(from, weight));
			}

			// 최소 가중치
			long result = 0;

			PriorityQueue<Data> pq = new PriorityQueue<>((d1, d2) -> d1.weight - d2.weight);
			// 시작점
			minEdge[1] = 0;
			pq.add(new Data(1, minEdge[1]));
			// 트리에 포함된 정점 개수
			int cnt = 0;
			while (!pq.isEmpty()) {
				Data cur = pq.poll();
				if (v[cur.no])
					continue;

				result += cur.weight;
				v[cur.no] = true;
				cnt++;
				if (cnt == V) { // 모든 정점 선택했으면 종료
					break;
				}

				for (Data d : list[cur.no]) {
					if (v[d.no])
						continue;
					if (minEdge[d.no] > d.weight) {
						minEdge[d.no] = d.weight;
						pq.add(new Data(d.no, d.weight));
					}
				}

			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

}
