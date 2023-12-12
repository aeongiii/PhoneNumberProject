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
				=		4. 전화번호 검색하기			 =
				=		5. 전체 목록 조회하기		 =
				=						 =
				=		9. csv 파일 생성		 =
				=		0. 프로그램 종료			 =
				=						 =
				==================================================
				
				""";
		
		String prompt2 = """
				==================================================
				=						 =
				=		1. 결과 내 재검색		 	 =
				=		2. 전체 검색		 	 =
				=
				=		3. 수정		 	 =
				=		4. 선택삭제		 	 =
				=		5. 모두삭제		 	 =
				=						 =
				=		0. 처음으로 돌아가기			 =
				=						 =
				==================================================
				
				""";
		
		String prompt3 = """
				================= 정렬기준을 선택해주세요 ==============
				=						 =
				=		1. id 정렬 			 =
				=		2. 이름 정렬 		 	 =
				=		3. 주소 정렬 		 	 =
				=		4. 전화번호 정렬 		 	 =
				=		5. 그룹별 정렬 		 	 =
				=						 =
				=		0. 처음으로 돌아가기			 =
				=						 =
				==================================================
				""";
		
		while (true) {
			System.out.println(prompt1);
			System.out.println("명령어를 입력해주세요. : ");
			String cmd = scan.nextLine();
			
			if (cmd.equals("0")) {		// exit
				System.out.println("이용해주셔서 감사합니다.");
				break;
			} else if (cmd.equals("1")) {			// add
				System.out.println("=============== 주소록 등록 ===============");
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.println("주소 : ");
				String address = scan.nextLine();
				System.out.println("전화번호 : ");
				String phone = scan.nextLine();
				System.out.println("회사 : ");
				String company = scan.nextLine();
				System.out.println("직급 : ");
				String grade = scan.nextLine();
				System.out.println("부서 : ");
				String part = scan.nextLine();
				
				db.insertAddress(name, address, phone, company, grade, part);
				
				System.out.println("주소록 등록 완료");
				System.out.println("----------------------------------------");
			}
			
			
			else if (cmd.equals("2")) {		// update
				System.out.println("=============== 주소록 수정 ===============");
				db.updateAddress();
				System.out.println("----------------------------------------");
			}
			
			
			else if (cmd.equals("3")) {		// delete
				System.out.println("=============== 주소록 삭제 ===============");
				db.deleteAddress();
				System.out.println("----------------------------------------");
			}
			
			else if (cmd.equals("4")) {		// search
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
					
					int searchNum = addrList.size();
					
					System.out.printf("조회된 주소록은 총 %d개입니다.\n", searchNum);
					System.out.println(prompt2);
					int answer2 = scan.nextInt();
					scan.nextLine();
				
					if (answer2 == 0) {	// 처음으로
						System.out.println("처음으로 돌아갑니다.");
						break; 
						
					} else if (answer2 == 2) {	// 전체검색
						searchList.clear();
						
					} else if (answer2 == 3) {	// 수정
						if (searchNum == 1) {	// 검색결과가 1개뿐이면 바로 수정
							db.updateAddress(addrList.get(0).getId());
						} else {				// 검색결과가 여러개면 id 입력값 받아서 하나만 수정
							db.updateAddress();
						}
						
					} else if (answer2 == 4) {	// 삭제
						
						if (searchNum == 1) {	// 검색결과가 한개뿐이면 바로삭제
							db.deleteAddress(addrList.get(0).getId());
							break;
						} else {				// 검색결과가 여러개면 id 입력값 받아서 삭제
							db.deleteAddress();	
							System.out.println("처음으로 돌아갑니다.");
							break;
						}
					}
				} while (true);	// 결과 내 재검색
			}
			
			else if (cmd.equals("5")) {			// show
				ArrayList<Addr> addrList = db.getAddress();
	
				// 모바일에서 출력
//				MobileView mv = new MovileView();
//				mv.printAddr(addrList);
				
				// 웹에서 출력
				WebView mv2 = new WebView();
				mv2.printAddr(addrList);
//				System.out.println(addrList);
				int searchNum = addrList.size();
				
				System.out.printf("조회된 주소록은 총 %d개입니다.\n", searchNum);
				
			}
			
			
			else if (cmd.equals("9")) {			// csv 파일 생성
				
//				ArrayList<Addr> addrList = db.getAddress();
//				DBtocsv filemaker = new DBtocsv();
//				filemaker.makeCSV(addrList);
				
				
				
				
				
				
				
			}
			
			
			else {
				System.out.println("다시 입력하세요.");
			}
			
			
			
			
			
		}
	}
}
