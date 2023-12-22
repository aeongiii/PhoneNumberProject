package dbconnection;

import java.util.ArrayList;

public class WebView {

	public void printAddr(ArrayList<Addr> addrList) {
		System.out.println("웹 브라우저에 출력");
		System.out.println("================ 주소록 목록 ==================");
		
		for(int i=0; i<addrList.size(); i++) {
			// 출력
			System.out.println("번호 : " + addrList.get(i).getId());
			System.out.println("이름 : " + addrList.get(i).getName());
			System.out.println("주소 : " + addrList.get(i).getAddress());
			System.out.println("번호 : " + addrList.get(i).getPhone());
			System.out.println("회사 : " + addrList.get(i).getCompany());
			System.out.println("직급 : " + addrList.get(i).getGrade());
			System.out.println("부서 : " + addrList.get(i).getPart());
			System.out.println("--------------------------------------------");
		}
	}
}