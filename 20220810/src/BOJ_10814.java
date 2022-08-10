import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Person {
	int num;
	int age;
	String name;

	public Person(int num, int age, String name) {
		this.num = num;
		this.age = age;
		this.name = name;
	}
}

public class BOJ_10814 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		List<Person> list = new ArrayList<>();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Person(i, Integer.parseInt(st.nextToken()), st.nextToken()));
		}

		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if (o1.age != o2.age)
					return o1.age - o2.age;
				return o1.num - o2.num;
			}
		});

		for (int i = 0; i < list.size(); i++) {
			bw.write(list.get(i).age + " " + list.get(i).name + "\n");
		}
		bw.flush();

	}


}
