
public class Sifre {
	private int code;
	private String name;
	private String id;
	private String pw;
	private String email;
	private String discription;
	
	
	public Sifre(int code,String name, String id, String pw, String email, String discription) {
		super();
		this.code=code;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.discription = discription;
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

}
