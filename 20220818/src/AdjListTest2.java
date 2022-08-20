import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AdjListTest2 {
   
    static int N;
    static int[] v;
    // 인접리스트 배열
    static ArrayList<Integer> [] list = null; // from 정점 정보를 관리
    
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //정점수
        v = new int[N]; //방문체크 배열
        
        list = new ArrayList[N];
        
        for(int i = 0; i < N; i++) { // 배열 안에 리스트 만들어주기!!
        	list[i] = new ArrayList<Integer>();
        }
        
        int E = sc.nextInt(); //간선 수
        for(int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            // 양방향
            list[from].add(to);
            list[to].add(from);
        }
//        dfs(0); //임의의 정점에서 시작
        bfs(0);

    }
    
    static void bfs(int start) {
    	// 큐 생성
    	Queue<Integer> q = new LinkedList<>();
    	// 큐에 시작 정정 입력 및 그래프면 방문 체크
    	q.offer(start);
    	v[start] = 1;
    	// 큐가 빌 때까지 계속적인 업무
    	while(!q.isEmpty()) {
	    	// 큐에서 하나 빼기 
    		int cur = q.poll();
	    	// 그 대상을 실행
    		System.out.print((char) (cur + 'A'));
	    	// 현재 것과 연결된 나머지 처리
    		for(Integer idx : list[cur]) {
    			// 이미 처리된 것 배제
    			if(v[idx] == 1) {
    				continue;
    			}
    			// 큐에 삽입 및 방문 처리
    			q.offer(idx);
    			v[idx] = 1;
    		}
    	}
    	System.out.println();
    }
    
    static void dfs(int cur) {
        v[cur] = 1; //시작하자 마자 방문체크 함
        System.out.print((char)(cur+ 'A') + " ");

        //현재 정점에서 연결된 나머지 정점들 큐에 삽입(인접 행렬이면 배열만큼 반복)
//        for(int i = 0, size = list[cur].size(); i < size; i++) {
        for(Integer idx: list[cur]) {
        	if(v[idx] == 1) { // 이미 방문처리 되어 있으면
        		continue;
        	}
        	// 연결 여부 판단 필요 없음
        	dfs(idx);
        }
        
    }

}