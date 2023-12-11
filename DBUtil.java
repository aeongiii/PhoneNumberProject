package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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
	
	public void insertAddress(String name, String address, String phone) {
		// =================== DB 작업 ==================
		// 3. Statement 생성
		try {
			stmt = conn.createStatement();
			
			// 4. SQL 처리하고 결과 ResultSet에 받아오기
			String sql = "INSERT INTO phone (phone_NAME, phone_ADDRESS, phone_NUMBER) VALUES ('" + name + "', '" + address + "', '" + phone + "')";
			
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
				
				Addr a1 = new Addr(id, name, address, phone);
				AddrList.add(a1);
			}
		} catch (Exception e) {
			System.out.println("list DB 작업 중 문제 발생!");
			e.printStackTrace();
		}
		return AddrList;
	}
		
	public void updateAddress(int id, String name, String address, String phone) {
		// ================== DB 작업 ======================
		try {
			stmt = conn.createStatement();
			
			String sql = "UPDATE phone SET phone_name = '" + name + "', phone_ADDRESS = '" + address + "', phone_NUMBER = '" + phone + "' WHERE phone_ID = " + id;
		
			stmt.executeUpdate(sql);
			System.out.println("주소록 수정 완료");
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
			
			String sql = "DELETE FROM phone WHERE phone_ID = " + id;
			
			stmt.executeUpdate(sql);
			System.out.println("주소록 삭제 완료");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// =================================================
	}
	
	public ArrayList<Addr> searchAddress(String searchData) {
		// =================================================
		// 담아야하니까
		ArrayList<Addr> AddrList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			
			String sql = "select * from phone where phone_NAME LIKE '%" + searchData + "%'"
					+ "	OR phone_ADDRESS LIKE '%" + searchData + "%'"
					+ " OR phone_NUMBER LIKE '%" + searchData + "%'";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("phone_ID");
				String name = rs.getString("phone_NAME");
				String address = rs.getString("phone_ADDRESS");
				String phone = rs.getString("phone_NUMBER");
				
				Addr a1 = new Addr(id, name, address, phone);
				AddrList.add(a1);
			}
			
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		
		return AddrList;
		// =================================================
	}
	
	
	public ArrayList<Addr> searchAddress(String searchData, ArrayList<Addr> addrList) {
		// =================================================
		// 담아야하니까
		
		try {
			stmt = conn.createStatement();
			
			String sql = "select * from phone where phone_NAME LIKE '%" + searchData + "%'"
					+ "	OR phone_ADDRESS LIKE '%" + searchData + "%'"
					+ " OR phone_NUMBER LIKE '%" + searchData + "%'";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("phone_ID");
				String name = rs.getString("phone_NAME");
				String address = rs.getString("phone_ADDRESS");
				String phone = rs.getString("phone_NUMBER");
				
				Addr a1 = new Addr(id, name, address, phone);
				addrList.add(a1);
			}
			
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		
		return addrList;
		// =================================================
	}
	
	public ArrayList<Addr> searchAddress(ArrayList<Addr> addrList, ArrayList<String> searchList ) {
		// =================================================
		
		try {
			stmt = conn.createStatement();
			sb.delete(0, sb.length());
			sb.append("select * from phone where ");
			for (int i=0; i<searchList.size(); i++) {
				sb.append("(phone_NAME LIKE '%" + searchList.get(i) + "%' " + "OR phone_ADDRESS LIKE '%" + searchList.get(i) + "%' " + "OR phone_NUMBER LIKE '%" + searchList.get(i) + "%')");
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
				
				Addr a1 = new Addr(id, name, address, phone);
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













