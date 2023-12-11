package dbconnection;

public class Addr {
	private int id;
	private String name;
	private String address;
	private String phone;
	
	public Addr(int id, String name, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "\n================== 주소록 출력 ====================" + "\n번호 : " + id + "\n이름 : " + name + "\n주소 : " + address + "\n번호 : " + phone;
	}
	
	


}
