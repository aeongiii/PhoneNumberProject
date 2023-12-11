package dbconnection;

import java.util.ArrayList;
import java.util.Scanner;

public class AddrApp {

	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		Scanner scan = new Scanner(System.in);
		
		String prompt1 = """
				==================================================
				=						 =
				=		1. 전화번호 추가하기			 =
				=		2. 전화번호 수정하기			 =
				=		3. 전화번호 삭제하기			 =
				=		4. 전체 목록 조회하기		 =
				=		5. 전화번호 조회하기			 =
				=		0. 프로그램 종료			 =
				=						 =
				==================================================
				
				""";
		
		String prompt2 = """
				==================================================
				=						 =
				=		1. 결과 내 재검색			 =
				=		2. 전체 검색		 	 =
				=		0. 처음으로 돌아가기			 =
				=						 =
				==================================================
				
				""";
		
		while (true) {
			System.out.println(prompt1);
			System.out.println("명령어를 입력해주세요. : ");
			String cmd = scan.nextLine();
			
			if (cmd.equals("0")) {		// exit
				break;
			} else if (cmd.equals("1")) {			// add
				System.out.println("=============== 주소록 등록 ===============");
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.println("주소 : ");
				String address = scan.nextLine();
				System.out.println("전화번호 : ");
				String phone = scan.nextLine();
				
				db.insertAddress(name, address, phone);
				
				System.out.println("주소록 등록 완료");
				System.out.println("----------------------------------------");
			}
			
			
			else if (cmd.equals("2")) {		// update
				System.out.println("=============== 주소록 수정 ===============");
				System.out.print("id : ");
				int id = scan.nextInt();
				scan.nextLine();
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.println("주소 : ");
				String address = scan.nextLine();
				System.out.println("전화번호 : ");
				String phone = scan.nextLine();
				
				db.updateAddress(id, name, address, phone);
				
				System.out.println("주소록 수정 완료");
				System.out.println("----------------------------------------");
			}
			
			
			else if (cmd.equals("3")) {		// delete
				System.out.println("=============== 주소록 삭제 ===============");
				System.out.print("id : ");
				int id = scan.nextInt();
				scan.nextLine();
				
				db.deleteAddress(id);
				
				System.out.println("주소록 삭제 완료");
				System.out.println("----------------------------------------");
			}
			
			else if (cmd.equals("4")) {			// show
				ArrayList<Addr> addrList = db.getAddress();
				
				// 모바일에서 출력
//				MobileView mv = new MovileView();
//				mv.printAddr(addrList);
				
				// 웹에서 출력
				WebView mv2 = new WebView();
				mv2.printAddr(addrList);
//				System.out.println(addrList);
			}
			
			else if (cmd.equals("5")) {		// search
				ArrayList<Addr> addrList = new ArrayList<>();
				ArrayList<String> searchList = new ArrayList<>();
				
				do {
					addrList.clear();	// 초기화해줘야함
					System.out.println("=============== 전화번호 조회 ===============");
					System.out.print("검색할 내용 : ");
					String searchData = scan.nextLine();
					searchList.add(searchData);
					
					addrList = db.searchAddress(addrList, searchList);
					
					WebView mv2 = new WebView();	// 웹에서 출력
					mv2.printAddr(addrList);
					
					System.out.println("주소록 조회 완료");
					System.out.println(prompt2);
					int answer2 = scan.nextInt();
					scan.nextLine();
				
					if (answer2 == 0) {	// 처음으로
						System.out.println("처음으로 돌아갑니다.");
						break; 
					} else if (answer2 == 2) {	// 전체검색
						searchList.clear();
					}
				} while (true);	// 결과 내 재검색
			}
		}
	}
}
