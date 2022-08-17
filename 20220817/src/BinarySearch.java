import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {

		int num = 3;
		int[] arr = {1, 0, 3, 2, 10, -5, -2, 6, 2, 8};
		
		Arrays.sort(arr);
		
		// 반복문
		System.out.println(binarySearch1(arr, num));
		// 재귀
		System.out.println(binarySearch2(arr, num, 0, arr.length - 1));
		// API
		System.out.println(Arrays.binarySearch(arr, num));
	}
	
	static int binarySearch1(int[] arr, int num) {
		int left = 0;
		int right = arr.length - 1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			if(arr[mid] == num) return mid;
			else if(arr[mid] > num) right = mid - 1;
			else left = mid + 1;
		}
		
		return -1;
	}
	
	static int binarySearch2(int[] arr, int num, int left, int right) {
		if(left > right) {
			return -1;
		}
		
		int mid = (left + right) / 2;
		if(arr[mid] == num) return mid;
		else if(arr[mid] < num) return binarySearch2(arr, num, mid + 1, right);
		else return binarySearch2(arr, num, left, mid - 1);
	}
	
}
