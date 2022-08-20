import java.util.Scanner;

public class AdjListTest {
	
    static class Node{
        int to;
        Node next;
        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }
    
    static Node[] adjList;
    static int N;
    static int[] v;
    
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //정점수
        v = new int[N]; //방문체크 배열
        int E = sc.nextInt(); //간선 수
        
        adjList = new Node[N];
        
        for(int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            // 배열의 첫번째에 삽입하기 때문에 A -> C -> B 순으로 접근한다.
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }
        
        dfs(0); //임의의 정점에서 시작

    }
    
    static void dfs(int cur) {
    	
        v[cur] = 1; //시작하자 마자 방문체크 함
        System.out.print((char)(cur+ 'A') + " ");

        //현재 정점에서 연결된 나머지 정점들 큐에 삽입(인접 행렬이면 배열만큼 반복)
        for(Node temp = adjList[cur]; temp != null; temp = temp.next) {
            if(v[temp.to] == 1) { //방문한 경우 가지치기 개념으로 무시
                continue;
            }
            //인접 체크 할 필요 없음
            
            //방문한적도 없고, 연결된정점은  재귀 호출
            dfs(temp.to);
        }
    }

}