import java.util.Scanner;

public class NQueenTest {
    static int N;
    static int[] cols; //열의 위치만 사용, 배열의 index를 행의 위치로 사용함
    static int res = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cols = new int[N+1]; //1행부터 사용 0행을 버림
        setQueen(1); //0행은 버림
        System.out.println(res);
        
    }
    static void setQueen(int rowNo) { //지정된 행에 하나의 퀸만 놓아보기(재귀)
        //가지 치기
        if(!isPromming(rowNo-1)) { //유망도 체크(직전까지 상황이 퀸을 놓을 수 있는지 파악)
            return;
        }
        //종료조건(현재 행이 전체 행을 넘어가면 종료함
        if(rowNo > N) { //모든 퀸을 놓아 보았음, 위에 가지를 치기 했기 때문에 정상적인 판단됨
            res++; // 정답으로 판단되는 경우
            return;
        }
        //실행및 재귀 호출
        
        for(int i = 1; i <= N; i++) { //현재 행에 모든 열값을 놓아 봄
            int temp = cols[rowNo]; //원상복구를 위해서 값 저장
            cols[rowNo] = i;
            setQueen(rowNo + 1); //다음 행으로 이동해서 같은 일 해봄
            cols[rowNo] = temp;     //원상복구함
        }
        
    }
    private static boolean isPromming(int rowNo) {
        for(int i = 1; i < rowNo; i++) { // 내 직전행까지 비교 판단해 봄
            if(cols[i] == cols[rowNo]) { //같은 열에 있음을 판단.
                return false;
            }
            if(rowNo - i == Math.abs(cols[rowNo] - cols[i])) { //대각선에 놓여진 경우
                return false;
            }
        }
        return true; //모든 행을 검사했는데 리턴되지 않았으면 현재행까지 가능함
    }
}