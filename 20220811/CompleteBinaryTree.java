import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 완전 이진 트리
public class CompleteBinaryTree {
	private char[] nodes;
	private int lastIndex; // 마지막 노드의 인덱스
	private final int SIZE;

	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new char[size + 1];
	}

	public boolean add(char e) {
		if (lastIndex == SIZE) { // 오버플로우
			return false;
		}
		lastIndex++;
		nodes[lastIndex] = e;
		return true;
	}

	public void bfs2() {
		Queue<Integer> q = new LinkedList<>(); // 위치 정보를 큐에 삽입한다.
		q.offer(1); // 처음 시작 위치 삽입
		while (!q.isEmpty()) { // 큐가 빌때 까지 반복

			int size = q.size(); // 큐의 사이즈 체크해서 사이즈 만큼만 반복
			for (int i = 0; i < size; i++) {

				int cur = q.poll(); // 큐이 첫번째 위치의 객체를 반환
				// 할일 구현
				System.out.print(nodes[cur]);

				// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
				if (cur * 2 <= lastIndex) { // 나의 위치에서 자식으로 연결된 왼쪽 자식의 위치가 존재하는지 여부
					q.offer(cur * 2);
				}
				if (cur * 2 + 1 <= lastIndex) { // 나의 위치에서 자식으로 연결된 오른쪽 자식의 위치가 존재하는지 여부
					q.offer(cur * 2 + 1);
				}
				System.out.println();
			}
			System.out.println("===================");
		}
	}

	public void bfs() {
		Queue<Integer> q = new LinkedList<>(); // 위치 정보를 큐에 삽입한다.
		q.offer(1); // 처음 시작 위치 삽입
		while (!q.isEmpty()) { // 큐가 빌때 까지 반복
			int cur = q.poll(); // 큐이 첫번째 위치의 객체를 반환
			// 할일 구현
			System.out.print(nodes[cur]);

			// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
			if (cur * 2 <= lastIndex) { // 나의 위치에서 자식으로 연결된 왼쪽 자식의 위치가 존재하는지 여부
				q.offer(cur * 2);
			}
			if (cur * 2 + 1 <= lastIndex) { // 나의 위치에서 자식으로 연결된 오른쪽 자식의 위치가 존재하는지 여부
				q.offer(cur * 2 + 1);
			}
			System.out.println();
		}
	}

	public void dfsByPreOrder(int cur) {
		// 할일 구현
		System.out.print(nodes[cur] + " ");

		// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
		if (cur * 2 <= lastIndex) { // 나의 위치에서 자식으로 연결된 왼쪽 자식의 위치가 존재하는지 여부
			dfsByPreOrder(cur * 2);
		}
		if (cur * 2 + 1 <= lastIndex) { // 나의 위치에서 자식으로 연결된 오른쪽 자식의 위치가 존재하는지 여부
			dfsByPreOrder(cur * 2 + 1);
		}
	}

	public void dfsByInOrder(int cur) {
		if (cur > lastIndex) {
			return;
		}
		// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
		dfsByInOrder(cur * 2);

		// 할일 구현
		System.out.print(nodes[cur] + " ");

		dfsByInOrder(cur * 2 + 1);
	}

	// public void dfsByInOrder(int cur) {
	//
	// //현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
	// if(cur *2 <= lastIndex) { //나의 위치에서 자식으로 연결된 왼쪽 자식의 위치가 존재하는지 여부
	// dfsByInOrder(cur *2);
	// }
	//
	// //할일 구현
	// System.out.print( nodes[cur] + " ");
	//
	// if(cur *2+1 <= lastIndex) { //나의 위치에서 자식으로 연결된 오른쪽 자식의 위치가 존재하는지 여부
	// dfsByInOrder(cur *2 + 1);
	// }
	// }
	public void dfsByPostOrder(int cur) {
		if (cur > lastIndex) {
			return;
		}
		// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
		dfsByPostOrder(cur * 2);

		dfsByPostOrder(cur * 2 + 1);

		// 할일 구현
		System.out.print(nodes[cur] + " ");

	}

	public void dfs() {
		Stack<Integer> s = new Stack<>(); // 위치 정보를 큐에 삽입한다.
		s.push(1); // 처음 시작 위치 삽입
		while (!s.isEmpty()) { // 큐가 빌때 까지 반복
			int cur = s.pop(); // 큐이 첫번째 위치의 객체를 반환
			// 할일 구현
			System.out.print(nodes[cur] + " ");

			// 현재 위치에서 연결된 나머지 일들을 다시 큐에 삽입
			if (cur * 2 <= lastIndex) { // 나의 위치에서 자식으로 연결된 왼쪽 자식의 위치가 존재하는지 여부
				s.push(cur * 2);
			}
			if (cur * 2 + 1 <= lastIndex) { // 나의 위치에서 자식으로 연결된 오른쪽 자식의 위치가 존재하는지 여부
				s.push(cur * 2 + 1);
			}
		}
		System.out.println();
	}
}