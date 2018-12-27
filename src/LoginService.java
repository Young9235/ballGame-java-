
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class LoginService {

	final static String fileName = "C:\\inyoung\\java_workspace\\BallGame\\login.txt";

	LoginVo vo = null;
	Scanner scan = new Scanner(System.in);

	HashMap<String, LoginVo> map = new HashMap<String, LoginVo>();
	
	int score = 0;
	String id = null;
	String player = null;
	
	Iterator<String> it = null;

	public void outFile(HashMap<String, LoginVo> map) {
		// 쓰기
		File outFile = new File(fileName);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outFile));
			for (String id : map.keySet()) {
				bw.write(id + "," + map.get(id));
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
	public void inFile(HashMap<String, LoginVo> map) {
		File inFile = new File(fileName);
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new FileReader(inFile));
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
	
				vo = new LoginVo();
				id = line.substring(0, line.indexOf(","));

				String pl = line.substring(line.indexOf(",")+1, line.length());
				vo.setPlayer(pl.substring(0, pl.indexOf(",")));
				vo.setScore(Integer.parseInt(pl.substring(pl.indexOf(",")+1, pl.length())));
				map.put(id, vo);		
				//System.out.println(player);
				//System.out.println(score);
				//System.out.println(map);
				

//				if (map.containsKey(id)) {
//					System.out.println("find key [" + id + "]");
//				} else {
//					System.out.println("not find key [" + id + "]");
//				}
//				if (map.containsValue(vo)) {
//					System.out.println("find value [" + vo + "]");
//				} else {
//					System.out.println("not find value [" + vo + "]");
//				}
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void fileCheck() {
		File file = new File(fileName);
		if (file.isFile()) { // 파일이 존재 하는지 안하는지 체크
			inFile(map);
		}

	}
	
	public void menu() {
		System.out.println("2.정보를 입력합니다.");
		System.out.println("id를 입력하세요 : ");
		id = scan.nextLine();
		System.out.println("이름을 입력하세요 : ");
		player = scan.nextLine();
	}
	
	
	public boolean insert(HashMap<String, LoginVo> map) {

		boolean flag = true;
		menu();
		it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(key.matches(id)) {
				System.out.println("아이디 중복입니다. 다시 입력하세요");
				menu();
			}
		}
		//System.out.println("-----"+key);
		//System.out.println("key : " + key + ", value : " + map.get(key));

		
		if(it != null) {
			vo = new LoginVo();
			vo.setPlayer(player);
			vo.setScore(score);
			map.put(id, vo);
			outFile(map);
			System.out.println("저장되었습니다.");
			flag = false;
		}
		return flag;
	}
	
	public boolean insert2(HashMap<String, LoginVo> map) {
		boolean flag = true;
		
		String key2 = null;
		
		System.out.println("id를 입력하세요.");
		String loginId = scan.nextLine();
		it = map.keySet().iterator();
		//System.out.println(map.keySet());
		
		if(map.keySet().contains(loginId)) {
			while(it.hasNext()) {
				String key = it.next();
				if(key.matches(loginId)) {
					System.out.println("접속되었습니다.");
					//System.out.println("id : " + key + ", value : " + map.get(key));
					key2 = key;
					vo = map.get(key);
				}
			}
//			System.out.println("플레이어 : " + vo.getPlayer());
//			System.out.println("점수 : " + vo.getScore());
//			System.out.println("id : " + key2);
			/* 숫자야구게임 접속 */
			BallGameService game = new BallGameService(key2, vo.getPlayer(), vo.getScore(), map, vo);
			game.ballGame();
			flag = false;
		}
		
		else {
			System.out.println("회원정보가 없습니다. 회원가입 하시겠습니까? Y/N");
			String answer = scan.nextLine();
			if(answer.equals("y")) {
				flag = insert(map);
			}else {
				flag = false;
			}
		}
		return flag;
	}
	
	

	public void login() {
		boolean flag = true;
		fileCheck();
		System.out.println("<숫자야구게임>");
		System.out.println("1.로그인   2.회원가입 ");
		String answer = scan.nextLine();
		if(answer.equals("1")) {
			do {
				flag = insert2(map);
			}while(flag);
		}	
		else if(answer.equals("2")) {
			do {
				flag = insert(map);	
			}while (flag);
		}

	}
}
