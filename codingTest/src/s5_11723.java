import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class s5_11723 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		HashSet<Integer> set = new HashSet<>();

		String cmd;
		int x;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();

			switch (cmd) {
			case "add":
				x = Integer.parseInt(st.nextToken());
				set.add(x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				set.remove(x);
				break;
			case "check":
				x = Integer.parseInt(st.nextToken());
				sb.append(set.contains(x) ? 1 : 0).append("\n");
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				if(set.contains(x)) {
					set.remove(x);
				} else {
					set.add(x);
				}
				break;
			case "all":
				set.clear();
				for(int i=1; i<=20; i++) {
					set.add(i);
				}
				break;
			case "empty":
				set.clear();
				break;
			default:
				break;
			}
		}
		
		System.out.println(sb);

	}

}
