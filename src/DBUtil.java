package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class DBUtil {

	// ============ JDBC 관련 변수 ===================
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	// 데이터베이스 연결 정보
	String url = "jdbc:mysql://localhost:3306/phone_book?serverTimezone=UTC";
	String user = "root";	// MySQL 사용자 이름
	String pass = "1234";	// MySQL 비밀번호
	
	// ============================================
	
	StringBuilder sb = new StringBuilder();
	Scanner scan = new Scanner(System.in);
	
	
	
	// 생성자
	public DBUtil() {
		conn = getConnection();
		
	}

	public Connection getConnection() {
		Connection conn = null;
		
		try {
				// 1. 드라이버 세팅
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// 2. Connection 획득
				conn = DriverManager.getConnection(url, user, pass);
				System.out.println("DB Connection 성공");
				
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
		}
		return conn;
	}
	
	public void insertAddress(String name, String address, String phone, String company, String grade, String part) {
		// =================== DB 작업 ==================
		// 3. Statement 생성
		try {
			stmt = conn.createStatement();
			
			// 4. SQL 처리하고 결과 ResultSet에 받아오기
			String sql = "INSERT INTO phone (phone_NAME, phone_ADDRESS, phone_NUMBER, phone_COMPANY, phone_GRADE, phone_PART) VALUES ('" + name + "', '" + address + "', '" + phone + "', '" + company + "', '" + grade + "', '" + part + "')";
			
			// 조회 결과가 없는 경우에는 ResultSet으로 받아올게 없기 때문에 
			// sql만 반영해주는 executeUpdate(sql) 사용
			stmt.executeUpdate(sql);
			System.out.println("등록완료");
			
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace(); 	// 예외 상세 정보 출력
		}
		// ==============================================
	}
	
	public ArrayList<Addr> getAddress() {
		// 담아야하니까
		ArrayList<Addr> AddrList = new ArrayList<>();
		
		try {
//			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from phone;\r\n" + "";
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("============== 주소록 목록 ===============");
			while (rs.next()) {
				int id = rs.getInt("phone_ID");
				String name = rs.getString("phone_NAME");
				String address = rs.getString("phone_ADDRESS");
				String phone = rs.getString("phone_NUMBER");
				String company = rs.getString("phone_COMPANY");
				String grade = rs.getString("phone_GRADE");
				String part = rs.getString("phone_PART");
				
				Addr a1 = new Addr(id, name, address, phone, company, grade, part);
				AddrList.add(a1);
			}
		} catch (Exception e) {
			System.out.println("list DB 작업 중 문제 발생!");
			e.printStackTrace();
		}
		return AddrList;
	}
		
	public void updateAddress() {
		// ================== DB 작업 ======================
		try {
			
			System.out.print("id : ");
			int id = scan.nextInt();
			scan.nextLine();
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
			
			stmt = conn.createStatement();
			String sql = "UPDATE phone SET phone_name = '" + name + "', phone_ADDRESS = '" + address + "', phone_NUMBER = '" + phone + "', phone_COMPANY = '" + company + "', phone_GRADE = '" + grade + "', phone_PART = '" + part + "' WHERE phone_ID = " + id;
		
			stmt.executeUpdate(sql);
			System.out.println("주소록 수정 완료");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// =================================================
	}
	
	public void updateAddress(int id) {
		// ================== DB 작업 ======================
		try {
			System.out.println("[ 입력하신 정보로 수정됩니다. ]");
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
			
			stmt = conn.createStatement();
			String sql = "UPDATE phone SET phone_name = '" + name + "', phone_ADDRESS = '" + address + "', phone_NUMBER = '" + phone + "', phone_COMPANY = '" + company + "', phone_GRADE = '" + grade + "', phone_PART = '" + part + "' WHERE phone_ID = " + id;
		
			stmt.executeUpdate(sql);
			System.out.println("주소록 수정 완료");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// =================================================
	}
	
	public void deleteAddress() {
		// =================================================
		try {
			
			System.out.println("삭제할 id를 공백으로 구분하여 모두 입력해주세요. (ex. 1 2 3)");
			String num = scan.nextLine();
			String[] deletenum = num.split(" ");		// 삭제할 id를 배열에 담음
			
			stmt = conn.createStatement();
			sb.delete(0, sb.length());
			sb.append("DELETE FROM phone WHERE ");
			
			for (int i=0; i<deletenum.length; i++) {
				sb.append("phone_ID = " + deletenum[i]);
				if (i != deletenum.length -1) {
					sb.append(" OR ");
				} else {
					sb.append(";");
				}
			}
			String sql = sb.toString();
			stmt.executeUpdate(sql);
			System.out.println("주소록 삭제 완료");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// =================================================
	}
	
	
	public void deleteAddress(int id) {
		// =================================================
		try {
			stmt = conn.createStatement();
			String sql = ("DELETE FROM phone WHERE phone_ID = " + id + ";");
			stmt.executeUpdate(sql);
			System.out.println("주소록 삭제 완료");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// =================================================
	}
	
		
	public ArrayList<Addr> searchAddress(ArrayList<Addr> addrList, ArrayList<String> searchList ) {
		// =================================================
		
		try {
			stmt = conn.createStatement();
			sb.delete(0, sb.length());
			sb.append("select * from phone where ");
			for (int i=0; i<searchList.size(); i++) {
				sb.append("(phone_NAME LIKE '%" + searchList.get(i) + "%' " + "OR phone_ADDRESS LIKE '%" + searchList.get(i) + "%' " + "OR phone_NUMBER LIKE '%" + searchList.get(i) + "%' " + "OR phone_COMPANY LIKE '%" + searchList.get(i) + "%' "  + "OR phone_GRADE LIKE '%" + searchList.get(i) + "%' " + "OR phone_PART LIKE '%" + searchList.get(i) + "%')");
				if (i != searchList.size()-1) {
					sb.append(" AND ");
				} else {
					sb.append(";");
				}
			}

			String sql = sb.toString();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("phone_ID");
				String name = rs.getString("phone_NAME");
				String address = rs.getString("phone_ADDRESS");
				String phone = rs.getString("phone_NUMBER");
				String company = rs.getString("phone_COMPANY");
				String grade = rs.getString("phone_GRADE");
				String part = rs.getString("phone_PART");
				
				Addr a1 = new Addr(id, name, address, phone, company, grade, part);
				addrList.add(a1);
			}
			
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		
		return addrList;
		// =================================================
	}
	

	
	
}













