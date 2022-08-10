
public class BitTest1 {

	public static void main(String[] args) {
//		비트(bit = binary digit) 연산자 => 정수(int)
//		0(false), 1(true)
//		| : or
//		& : and
//		~ : not => 단항(1항)
//		^ : xor
//		<<, >>, >>>: shift
		
		int num = 11;	// 0000 1011
		num = ~num;		// 1111 0100	- (1더하고 부호 바꿈)
		
		System.out.println(Integer.toBinaryString(num) + "\t" + num);
		
		int a = 5;		// 0000 0101
		int b = 8;		// 0000 1000
					
		int c = a | b; 	// 0000 1101
		c = a & b;		// 0000 0000
		c = a ^ b;		// 0000 1101
		c = a ^ a;		// 0
		c = 0;
		System.out.println(c);
		
		int z = 3 + (~4 + 1); // 3 - 4
		System.out.println(z);
		
		z = 7;		// 00000111
//		z = z << 1;	// 00001110		* 2
//		z = z << 2;	// 00011100		* 2 * 2
//		z = z << 3;	// 00111000		* 2 * 2 * 2
//		z = z << 5;	// 00000000 00000000 00000000 11100000		* 2 * 2 * 2 * 2 * 2
//		z = z << 29;// 11100000	 	음수됨
		z = z << (65 % 32);	
		
//		z = z * 1024;	// 컴파일러
//		z = z << 10;	// 컴파일러
		
//		z = 7;		// 00000111
//		z = z >> 1;	// 00000011 (산술 쉬프트)	/2
//		z = z >> 2;	// 00000001 (산술 쉬프트)	/2 /2
		z = -7;		// 11111001
//		z = z >> 1;	// 11111111 11111111 11111111 11111100 오른쪽 쉬프트 시 부호가 같은 비트가 들어옴.
//		System.out.println(Integer.toBinaryString(z) + "\t" + z);
//		System.out.println(-7 / 2);	// 음수는 나누기 효과는 있지만 같은 값은 아님.
		
		z = z >>> 1;	// 논리 쉬프트 (왼쪽에서 부호와 상관없이 0이 들어옴)
		System.out.println(Integer.toBinaryString(z) + "\t" + z);
		
		
		
	}

}
