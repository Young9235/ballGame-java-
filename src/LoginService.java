
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
		// ����
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
	
	// �б�
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
		if (file.isFile()) { // ������ ���� �ϴ��� ���ϴ��� üũ
			inFile(map);
		}

	}
	
	public void menu() {
		System.out.println("2.������ �Է��մϴ�.");
		System.out.println("id�� �Է��ϼ��� : ");
		id = scan.nextLine();
		System.out.println("�̸��� �Է��ϼ��� : ");
		player = scan.nextLine();
	}
	
	
	public boolean insert(HashMap<String, LoginVo> map) {

		boolean flag = true;
		menu();
		it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(key.matches(id)) {
				System.out.println("���̵� �ߺ��Դϴ�. �ٽ� �Է��ϼ���");
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
			System.out.println("����Ǿ����ϴ�.");
			flag = false;
		}
		return flag;
	}
	
	public boolean insert2(HashMap<String, LoginVo> map) {
		boolean flag = true;
		
		String key2 = null;
		
		System.out.println("id�� �Է��ϼ���.");
		String loginId = scan.nextLine();
		it = map.keySet().iterator();
		//System.out.println(map.keySet());
		
		if(map.keySet().contains(loginId)) {
			while(it.hasNext()) {
				String key = it.next();
				if(key.matches(loginId)) {
					System.out.println("���ӵǾ����ϴ�.");
					//System.out.println("id : " + key + ", value : " + map.get(key));
					key2 = key;
					vo = map.get(key);
				}
			}
//			System.out.println("�÷��̾� : " + vo.getPlayer());
//			System.out.println("���� : " + vo.getScore());
//			System.out.println("id : " + key2);
			/* ���ھ߱����� ���� */
			BallGameService game = new BallGameService(key2, vo.getPlayer(), vo.getScore(), map, vo);
			game.ballGame();
			flag = false;
		}
		
		else {
			System.out.println("ȸ�������� �����ϴ�. ȸ������ �Ͻðڽ��ϱ�? Y/N");
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
		System.out.println("<���ھ߱�����>");
		System.out.println("1.�α���   2.ȸ������ ");
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
