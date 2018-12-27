
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
		// ����
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
	
	// �б�
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
//			System.out.println("�о���� map : " + map);
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
//		if (file.isFile()) { // ������ ���� �ϴ��� ���ϴ��� üũ
//			inFile();
//		}
//
//	}
   
   
   
   public boolean score(int scoreVo, HashMap<String, LoginVo> map, String key) {
      boolean flag = true;
      strik = 0;
      ball = 0;
      System.out.println("���� ���ھ� : " + scoreVo);
      	/* ��Ʈ����ũ�� �� �� �����ϱ� */
         System.out.print("1~9������ 3���� ������ �Է��ϼ��� : ");
         
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
            
            System.out.println(count+"��° ��� -> ��Ʈ����ũ :" + strik +"s" + "\t�� :" + ball+"b");
            
            /* ���� �ش� �÷��̾�(key)�� (value)���� ���� ��� */
            for(int i=0; i<10; i++) {
            	if(count == i+1) {
            		if(strik == 3 && ball == 3) {
            			System.out.println("�� ���߾����ϴ�!"+score[i]+"��");
            			scoreVo += score[i];
            			vo.setScore(scoreVo);
            			map.put(key, vo);
            			flag = playerScore(map);
            			
            		}
            	}
            }
            if(count == 10) {
            	System.out.println("10ȸ ��� ����... Gameover!!");
            	flag = false;
        		
            }
            //vo.setPlayer(player);
            //System.out.println(map.get(key));
            
            return flag;
            
      }
   
   public boolean playerScore(HashMap<String, LoginVo> map) {

		//System.out.println((key + "���� ���� ��� : " + map.get(key)));
		//System.out.println("id : "+key+ ", player : " + player + ", score : " + scoreVo);
		System.out.println(vo.getPlayer()+"���� ���� ���ھ� :" + vo.getScore());
		outFile(map);

	   return false;
   }
   
   
   public void ballGame() {
	   	boolean flag = true;
         /* ���� ���� �ߺ��ȵǰ� ¥�� ���� */   
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
         
        System.out.println("\nȯ���մϴ�~!" + key + "��!"); 
        System.out.println("\n---- ���ھ߱� ���� �����մϴ�.(�� 10���� ��ȸ) ----"); 
	    //fileCheck(); 
	    //System.out.println("�ʹ� ���� ���� : " + map);
	    do {
	    	flag = score(scoreVo, map, key);
	    	count++; 
	    }while(flag);
      
   }
}