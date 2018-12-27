import java.util.Random;
import java.util.Scanner;

public class BaseballMain {
	public static void main(String[] args) {
		
		int nBall        = 0 ; // 볼 
		int nStrike      = 0 ;  //스트라이크
		int nRepeat      = 10 ;  // 시도
		int nArrRandom[] = new int[10] ; // 랜덤수  0~9
		int nArrOut[]    = new int[3] ; // 뽑고 저장
		int nArrInput[]  = new int[ 3] ; // 입력수
		String  sMsg;
		
		int nTemp        = 0 ;  //
		int nRan         = 0 ;  //
		int i , k , j ;
		
		Random ran = new Random();
		for( i = 0 ; i < 10 ; i++)  // 0 ~ 9 입력
			nArrRandom[i]= i;
		
		
		for( k = 0 ; k < 3 ; k++ )
		{			
			nTemp = ran.nextInt(3-k);			
			nRan = nArrRandom[nTemp];  // 10개중 k번째 추첨		
			nArrOut[k] = nArrRandom[nRan];   // 결과 저장
		
			for ( i = 0 ; i < 3-1 ; i++)
			{
				if( i >= nRan)
					nArrRandom[i] = nArrRandom[i+1];	// 뽑은것 뒤부터 한칸씩 앞으로 민다			
			}
		}
	
//		for( i = 0 ; i < 3 ; i++)
//			System.out.printf(nArrOut[i]+ " ");
		
		System.out.println("게임을 시작합니다 (Y / N) : ");
				
		Scanner scan    = new Scanner(System.in);
		String  sStart  = scan.nextLine();
		
		if ( ( sStart.equals("N") )  ||  ( sStart.equals("n") ) )
			return;
		
		for( j = 0 ; j < nRepeat ; j++)
		{
		
		System.out.printf("총 10회 시도 중 " + j + " 번째 시도. 3개의 정수를 입력하세요  : ");
		sMsg = scan.nextLine();
		String sParse[] = sMsg.split(" ");
		
		nStrike = 0;
		for( i = 0 ; i < 3 ; i++)  // parse + 스트라이크 체크
		{
			nArrInput[i] = Integer.parseInt(sParse[i]);
			if( nArrInput[i] == nArrOut[i] )
				nStrike++;			
		}
		nBall = 0;
		for( i = 0 ; i < 3 ; i++ )  // 같은지 비교
		{
			for( k = 0 ; k < 3 ; k++ )
			{
				if ( nArrInput[i] == nArrOut[k] )
					nBall++;
			}			
		}
		System.out.println("볼 :" + nBall + "\t스트라이크 " + nStrike );
		if ( nBall == 3 && nStrike == 3)
		{
			System.out.println("삼진아웃" );			
			break;
		}
		}
		if ( nBall != 3 || nStrike != 3)
		System.out.println("10회 모두 소진. game over!!!" );
				
	}

}
