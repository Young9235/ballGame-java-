
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class BallGameService {
   
   Scanner scan = new Scanner(System.in);
   
   int user_arr[] = new int[3];
   int ran_arr[] = new int[3];
   
   HashMap<String, LoginVo> map = null;
   String player = null;
   String key = null;
   int strik = 0;
   int ball = 0;
   int count = 1;
   int scoreVo = 0;
   int []score = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
   LoginVo vo = null;
   
   final static String fileName = "C:\\inyoung\\java_workspace\\BallGame\\login.txt";
   
   public BallGameService(String key, String player, int scoreVo, HashMap<String, LoginVo> map, LoginVo vo) {
	   this.key = key;
	   this.player = player;
	   this.scoreVo = scoreVo;
	   this.map = map;
	   this.vo = vo;
   }
   
   public void outFile(HashMap<String, LoginVo> map) {
		// 쓰기
		File outFile = new File(fileName);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outFile));
			for (String key : map.keySet()) {
					
				bw.write(key + "," + map.get(key));
				bw.newLine();
			}

			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 읽기
//	public void inFile() {
//		File inFile = new File(fileName);
//		BufferedReader br = null;
//		String line;
//		try {
//			br = new BufferedReader(new FileReader(inFile));
//			while ((line = br.readLine()) != null) {
//				System.out.println("----" + line);
//				vo = new LoginVo();	
//				key = line.substring(0, line.indexOf(","));
//				String pl = line.substring(line.indexOf(",")+1, line.length());
//				vo.setPlayer(pl.substring(0, pl.indexOf(",")));
//				vo.setScore(Integer.parseInt(pl.substring(pl.indexOf(",")+1, pl.length())));
//				scoreVo = vo.getScore();
//				System.out.println(scoreVo);
//			}
//			map.put(key, vo);
//			System.out.println("읽어오기 map : " + map);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

//	public void fileCheck() {
//		File file = new File(fileName);
//		if (file.isFile()) { // 파일이 존재 하는지 안하는지 체크
//			inFile();
//		}
//
//	}
   
   
   
   public boolean score(int scoreVo, HashMap<String, LoginVo> map, String key) {
      boolean flag = true;
      strik = 0;
      ball = 0;
      System.out.println("현재 스코어 : " + scoreVo);
      	/* 스트라이크와 볼 수 측정하기 */
         System.out.print("1~9사이의 3개의 정수를 입력하세요 : ");
         
         for(int i=0; i<ran_arr.length; i++) {
              user_arr[i] = scan.nextInt();
              if(user_arr[i] == ran_arr[i]) {
                 strik++;
              }
           }
            for(int i=0; i<ran_arr.length; i++) {
               for(int j=0; j<user_arr.length; j++) {
                  if(user_arr[i] == ran_arr[j]) {
                     ball++;
                  }
               }
            }
            
            System.out.println(count+"번째 결과 -> 스트라이크 :" + strik +"s" + "\t볼 :" + ball+"b");
            
            /* 점수 해당 플레이어(key)의 (value)값에 점수 배당 */
            for(int i=0; i<10; i++) {
            	if(count == i+1) {
            		if(strik == 3 && ball == 3) {
            			System.out.println("다 맞추었습니다!"+score[i]+"점");
            			scoreVo += score[i];
            			vo.setScore(scoreVo);
            			map.put(key, vo);
            			flag = playerScore(map);
            			
            		}
            	}
            }
            if(count == 10) {
            	System.out.println("10회 모두 소진... Gameover!!");
            	flag = false;
        		
            }
            //vo.setPlayer(player);
            //System.out.println(map.get(key));
            
            return flag;
            
      }
   
   public boolean playerScore(HashMap<String, LoginVo> map) {

		//System.out.println((key + "님의 점수 결과 : " + map.get(key)));
		//System.out.println("id : "+key+ ", player : " + player + ", score : " + scoreVo);
		System.out.println(vo.getPlayer()+"님의 최종 스코어 :" + vo.getScore());
		outFile(map);

	   return false;
   }
   
   
   public void ballGame() {
	   	boolean flag = true;
         /* 랜덤 숫자 중복안되게 짜는 로직 */   
         for(int i=0; i<ran_arr.length; i++) {
            ran_arr[i] = (int)(Math.random()*9)+1;
            for(int j=0; j<i; j++) {
               if(ran_arr[i] == ran_arr[j]) {
                  i--;
                  break;
               }  
            }   
         }
         for(int i=0; i<ran_arr.length; i++) {
            //System.out.print(ran_arr[i] +",");
         }
         
        System.out.println("\n환영합니다~!" + key + "님!"); 
        System.out.println("\n---- 숫자야구 게임 시작합니다.(총 10번의 기회) ----"); 
	    //fileCheck(); 
	    //System.out.println("초반 맵의 정보 : " + map);
	    do {
	    	flag = score(scoreVo, map, key);
	    	count++; 
	    }while(flag);
      
   }
}