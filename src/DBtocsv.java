package dbconnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DBtocsv {
	
	
	public static void makeCSV() {
		String filePath = "C:\\work\\dbConnection\\test.csv";
		
		File file = null;
		BufferedWriter bw = null;
		String newLine = System.lineSeparator();
		
		
		
		try {
			file = new File(filePath);
			bw = new BufferedWriter(new FileWriter(file));
			String header = "phone_ID, phone_NAME, phone_ADDRESS, phone_NUMBER, phone_COMPANY, phone_GRADE, phone_PART";
			bw.write(header);	// 쓰기
			bw.
			
			

			
			
			for (List<String> row : )
			
		} catch {
			
		}
	}
	
	


	
}





//// 이중 리스트 생성
//List<List<String>> data = new ArrayList<>();
//String headLine = "phone_ID, phone_NAME, phone_ADDRESS, phone_NUMBER, phone_COMPANY, phone_GRADE, phone_PART";
//data.add(Arrays.asList(headLine));
//
//
//for (int i=0; i<addrList.size(); i++) {
//	int id = addrList.get(i).getId();
//	String name = addrList.get(i).getName();
//	String address = addrList.get(i).getAddress();
//	String number = addrList.get(i).getPhone();
//	String company = addrList.get(i).getCompany();
//	String grade = addrList.get(i).getGrade();
//	String part = addrList.get(i).getPart();
//	
//	String addrData = id + ", " + name + ", "+ address + ", " + number + ", " + company + ", " + grade + ", " + part + "\n";
//    data.add(Arrays.asList(addrData));
//
//}
//
//System.out.println(data);
//
//// CSV 파일에 쓰기
//writeCSV("example.csv", data);
//
//System.out.println("CSV 파일이 생성되었습니다.");
//}
//
//public static void writeCSV(String fileName, List<List<String>> data) {
//FileWriter writer = new FileWriter(fileName);
//for (List<String> row : data) {
//	writer.append(row);
//}