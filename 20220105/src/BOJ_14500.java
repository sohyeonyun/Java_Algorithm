import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 테트로미노 
 */
public class BOJ_14500 {
   
   static int N, M;
   static int[][] map;
   static int MAX = 0;

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][M];
      
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<M; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      game();
      
      System.out.println(MAX);
   }

   private static void game() {
      
      // 1. ㅜ 제외한 모양 네가지
      //       -> 길이가 3인 dfs
      
      // dfs 위한 방문체크 
      v = new boolean[N][M];
      
      // 각 자리마다 dfs
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
            v[i][j] = true;
            dfs(i, j, 0, map[i][j]); // 길이 3인 dfs
            v[i][j] = false;
         }
      }
      
      // 2. ㅜ 모양 -> ㅜ ㅏ ㅓ ㅗ  
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
        	 wu(i, j, i, j + 1, i, j + 2, i + 1, j + 1);
        	 wu(i, j, i + 1, j, i + 2, j, i + 1, j + 1);
        	 wu(i, j, i + 1, j - 1, i + 1, j, i + 2, j);
        	 wu(i, j, i - 1, j + 1, i, j + 1, i, j + 2);
         }
      }
   }
   
   private static void wu(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3) {
      int sum = map[x][y];
      // 범위 안이면
      if(isValid(x1, y1) && isValid(x2, y2) && isValid(x3, y3)) {
         sum += map[x1][y1] + map[x2][y2] + map[x3][y3];
         MAX = Math.max(MAX, sum);
      }
   }

   static int[] dx = {1, -1, 0, 0};
   static int[] dy = {0, 0, 1, -1};
   static boolean[][] v;

   private static void dfs(int x, int y, int cnt, int sum) {
      
      if(cnt == 3) {
         MAX = Math.max(MAX, sum);
         return;
      }

      // 4방위
      for(int d=0; d<4; d++) {
         int nx = x + dx[d];
         int ny = y + dy[d];
         // 범위 체크
         if(!isValid(nx, ny)) {
            continue;
         }
         // 방문 체크
         if(v[nx][ny]) {
            continue;
         }
         v[nx][ny] = true;
         dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
         v[nx][ny] = false;
      }
      
   }

   // 맵 범위 벗어나는가
   private static boolean isValid(int i, int j) {
      if(i < 0 || i >= N || j < 0 || j >= M) {
         return false;
      }
      return true;
   }

}