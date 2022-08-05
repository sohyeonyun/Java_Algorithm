package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Person {
	int num;
	int candy;

	Person(int num, int candy) {
		this.num = num;
		this.candy = candy;
	}
}

public class EBook_queue {

	public static void main(String[] args) {

		Queue<Person> q = new LinkedList<>();

		int cnt = 1; // 몇번째 사람
		int n = 20; // 마이쮸 개수
		Person person;

		q.offer(new Person(cnt, 0)); // (1번째 사람, 0개받음)
		while (true) {

			// 맨 앞 사람은 자신이 받았던 개수 + 1개를 받는다.
			person = q.poll();
			person.candy += 1;
			n -= person.candy;
			if (n <= 0) { // 남은 마이쮸 없으면 멈춤.
				System.out.println(person.num);
				break;
			}
			// 다시 줄 선다.
			q.offer(person);
			// 새로운 사람이 줄을 선다.
			cnt++;
			q.offer(new Person(cnt, 0));

		}

	}

}
