
public class BitTest2 {

	public static void main(String[] args) {
		// 비트 마스킹
		boolean[] v = new boolean[32];
		v[4] = true;	// 마스킹
		v[0] = true;	// 마스킹
		
		int flag = 0;
//		00000000 00000000 00000001 000?0000	=> 1 => |
//	|	00000000 00000000 00000000 00000001	=> 1 => |
//	|	00000000 00000000 00000000 00010001	=> 1 => |
//		00000000 00000000 00000001 00010001	=> 1 => |
		
		// 비트 마스킹
		flag = flag | (1 << 0);
		flag = flag | (1 << 4); // flag | 16;	// 왼쪽으로 네 칸 민거
		
//		System.out.println(flag);
		
		// 자리값 확인 &
//		00000000 00000000 000?0000 00000001 => 17
//	&	00000000 00000000 00000000 00010000 => 16
//	=	00000000 00000000 00000000 00000000 => 16

		boolean sw = v[4];
		sw = (flag & (1 << 4)) != 0 ? true : false;
		System.out.println(flag & (1<<4));
		System.out.println(sw);
//		sw = flag & (1 << 4);
		
	}

}
