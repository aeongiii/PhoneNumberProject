package dbconnection;

public class Addr {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String company;
	private String grade;
	private String part;
	
	
	public Addr(int id, String name, String address, String phone, String company, String grade, String part) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.company = company;
		this.grade = grade;
		this.part = part;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getPart() {
		return part;
	}



	public void setPart(String part) {
		this.part = part;
	}



	@Override
	public String toString() {
		return "\n================== 주소록 출력 ====================" + "\n번호 : " + id + "\n이름 : " + name + "\n주소 : " + address + "\n번호 : " + phone;
	}
	
	


}

//231212 10:32 편집
