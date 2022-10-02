import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

public class _5 {

	static String[][] arr;
	static HashMap<String, ArrayList<Point>> map;
	
	public static void main(String[] args) {
		String[] commands = { "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
				"UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
				"UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4",
				"UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4" };

		arr = new String[50][50];
		map = new HashMap<>();

		int r, c, r2, c2;
		String value, value1, value2;
		ArrayList<Point> list, list2;
		
		ArrayList<String> ans = new ArrayList<>();
		for (String command : commands) {
			String[] tmp = command.split(" ");
			String com = tmp[0];

			switch (com) {
			case "UPDATE":
				if (tmp.length == 4) {
					r = Integer.parseInt(tmp[1]) - 1;
					c = Integer.parseInt(tmp[2]) - 1;
					 value = tmp[3];

					arr[r][c] = value;
					list = map.containsKey(value) ? map.get(value) : new ArrayList<>();
					list.add(new Point(r, c));
					map.put(value, list);

				} else if (tmp.length == 3) {
					 value1 = tmp[1];
					 value2 = tmp[2];

					list = map.get(value1);
					list2 = map.containsKey(value2) ? map.get(value2) : new ArrayList<>();
					if (list != null) {
						for (Point p : list) {
							arr[p.r][p.c] = value2;
							list2.add(p);
						}
					}
					map.remove(value1);
					map.put(value2, list2);
				}
				break;
			case "MERGE":
				r = Integer.parseInt(tmp[1]) - 1;
				c = Integer.parseInt(tmp[2]) - 1;
				r2 = Integer.parseInt(tmp[3]) - 1;
				c2 = Integer.parseInt(tmp[4]) - 1;
				
				// value1 -> value2
				if(!map.containsKey(arr[r][c]) && map.containsKey(arr[r2][c2])) {
					merge(r, c, r2, c2, arr[r2][c2], arr[r][c]);
				} else {
					merge(r2, c2, r, c, arr[r][c], arr[r2][c2]);
				}
				
				break;
			case "UNMERGE":
				r = Integer.parseInt(tmp[1]) - 1;
				c = Integer.parseInt(tmp[2]) - 1;
				value = arr[r][c];
				
				list = map.containsKey(value) ? map.get(value) : new ArrayList<>();
				unMerge(r, c, value);
				break;
			case "PRINT":
				r = Integer.parseInt(tmp[1]) - 1;
				c = Integer.parseInt(tmp[2]) - 1;
				ans.add(arr[r][c] == null ? "EMPTY" : arr[r][c]);
				break;

			}

			
			// 출력
			System.out.println(command);
//			for (int i = 0; i < 50; i++) {
//				for (int j = 0; j < 50; j++) {
//					if (arr[i][j] != null) {
//						System.out.println(i + " " + j + " " + arr[i][j]);
//					}
//				}
//			}
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
			
			for (Entry<String, ArrayList<Point>> entrySet : map.entrySet()) {
				System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
			}
			System.out.println("==========");
		}
		
		// 정답 출력
		String[] answer = new String[ans.size()];
		for(int i=0; i<ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		System.out.println(Arrays.toString(answer));
	}

	
	private static void unMerge(int r, int c, String value) {
		ArrayList<Point> addList = new ArrayList<>();
		addList.add(new Point(r, c));
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		arr[r][c] = value;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nr = p.r + dx[i];
				int nc = p.c + dy[i];
				if(nr < 0 || nr >= 50 || nc < 0 || nc >= 50 || arr[nr][nc] == null || !arr[nr][nc].equals(value)) {
					continue;
				}
				arr[nr][nc] = null;
				q.add(new Point(nr, nc));
				addList.add(new Point(r, c));
			}
		}
		
		if(map.containsKey(value)) {
			ArrayList<Point> l = new ArrayList<>();
			for(Point p: map.get(value)) {
				for(Point addP: addList) {
					if(!(p.r == addP.r && p.c == addP.c)) {
						l.add(p);
					}
				}
			}
			map.put(value, l);
		}
	}


	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	private static void merge(int r, int c, int r2, int c2, String v1, String v2) {
		ArrayList<Point> addList = map.containsKey(v1) ? map.get(v1) : new ArrayList<>();
		addList.add(new Point(r, c));
		addList.add(new Point(r2, c2));
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		arr[r][c] = v1;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nr = p.r + dx[i];
				int nc = p.c + dy[i];
				if(nr < 0 || nr >= 50 || nc < 0 || nc >= 50 || arr[nr][nc] == null || !arr[nr][nc].equals(v2)) {
					continue;
				}
				arr[nr][nc] = v1;
				q.add(new Point(nr, nc));
				addList.add(new Point(r, c));
			}
		}
		
		if(map.containsKey(v2)) {
			ArrayList<Point> l = new ArrayList<>();
			for(Point p: map.get(v2)) {
				for(Point addP: addList) {
					if(!(p.r == addP.r && p.c == addP.c)) {
						l.add(p);
					}
				}
			}
			map.put(v2, l);
		}
		
		
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
		
	}

}
