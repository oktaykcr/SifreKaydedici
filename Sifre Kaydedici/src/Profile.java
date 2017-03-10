import javax.swing.JOptionPane;


public class Profile {
	private String name;
	private String id;
	private String password;
	private String tableName;
	
	
	public Profile(String name,String id,String pass){
		this.name=name;
		this.tableName=name;
		this.id=id;
		this.password=pass;
		sendUser();
	}
	
	public void sendUser(){//oluþturulan kullanýcýlarý profiles tablosuna yolluyor
		String sql="INSERT INTO profiles"+"(profile_id,profile_pass,profile_table)"+"VALUES('"+getId()+"','"+getPassword()+"','"+getTableName()+"')";
		JDBConnection.executeSQL(sql);
		JOptionPane.showMessageDialog(null,"Kayýt Olma Baþarýlý!");
	}
	public static  void createProfilesTable(){//proifles tablosu yoksa oluþturacak varsa bir þey yapmayacak
		String sql="CREATE TABLE IF NOT EXISTS profiles" 
				+"(profile_pk    INTEGER       PRIMARY KEY AUTOINCREMENT,"
				+"profile_id  VARCHAR (20),"
				+"profile_pass    VARCHAR (20),"
				+"profile_table    VARCHAR (20))";
		JDBConnection.CreateTable(sql);
				
	}
	public String getTableName(){
		return this.tableName;
	}
	public String getId(){
		return this.id;
	}
	public String getPassword(){
		return this.password;
	}
	public void setId(String id){
		this.id=id;
	}
	public void setPassword(String pass){
		this.password=pass;
	}
	public void setTableName(String tableName){
		this.tableName=tableName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
