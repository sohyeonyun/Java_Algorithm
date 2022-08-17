
/*
 * n 개의 동전들 중에 가짜 동전이 하나 포함되어 있다. 가짜 동전은 진짜 동전에 비해 아주 조금 가볍다.
 * 진짜 동전들의 무게가 동일하다고 할 때 양팔 저울을 이용해서 가짜 동전을 찾아보자.
 * 양팔 저울을 최소로 사용해서 가짜 동전을 찾는 방법은 무엇인가?
 * 예) 24(진짜 23, 가짜 1)개가 있다면?
 */
public class FindFakeCoin {

	public static void main(String[] args) {

		int n = 24;
		int[] coins = new int[n];
		for(int i=0; i<n; i++) {
			coins[i] = 1;
		}
		coins[14] = 0; // 가짜 동전
		
		
		
	}

}
