package dbconnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class DBtocsv {
	
	
	public void makeCSV(ArrayList<Addr> addrList) {
		String filePath = "C:/works/dbConnection/test.csv";
		
		File file = null;
		BufferedWriter bw = null;
		
		
		try {
			file = new File(filePath);
			bw = new BufferedWriter(new FileWriter(file));
			String header = "phone_ID, phone_NAME, phone_ADDRESS, phone_NUMBER, phone_COMPANY, phone_GRADE, phone_PART";
			bw.write(header + "\n");	// 쓰기
			
			for (int i=0; i<addrList.size(); i++) {
				int id = addrList.get(i).getId();
				String name = addrList.get(i).getName();
				String address = addrList.get(i).getAddress();
				String number = addrList.get(i).getPhone();
				String company = addrList.get(i).getCompany();
				String grade = addrList.get(i).getGrade();
				String part = addrList.get(i).getPart();
				
				String addrData = id + ", " + name + ", "+ address + ", " + number + ", " + company + ", " + grade + ", " + part;
			    bw.write(addrData + "\n");
			}
			
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

//231212 10:32 편집