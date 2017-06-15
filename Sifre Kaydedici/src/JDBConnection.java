import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JDBConnection {//Frame class main kýsmýnda baðlantý baþlatýlýyor
	
	static String tempID=null;
	static String tempPass=null;
	static String tempTable=null;
	public JDBConnection(){
		getConnection();
	}
	public static Connection getConnection(){//Database ilk baðlantýyý almak için
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:sifrekaydedici.db");	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static ArrayList<Sifre> getSifreler(){
		ArrayList<Sifre> sifreler=new ArrayList<Sifre>();
		Connection conn=getConnection();
		String querry="select * from "+tempTable;
		
		Statement st;
		ResultSet myRs;
		
		try {
			st=conn.createStatement();
			myRs=st.executeQuery(querry);
			Sifre yeniSifre;
			while(myRs.next()){
				yeniSifre=new Sifre(myRs.getInt("sifre_pk"),myRs.getString("sifre_name"),myRs.getString("sifre_id"),
						myRs.getString("sifre_pw"),myRs.getString("sifre_email"),myRs.getString("sifre_dis"));
				sifreler.add(yeniSifre);
			}
			myRs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sifreler;
		
	}
	public static  void tabloyaAktar(){
		ArrayList<Sifre> list=getSifreler();
		DefaultTableModel model=(DefaultTableModel) AnaPanel.table.getModel();
		Object[] row=new Object[6];
		for(int i=0;i<list.size();i++){
			row[0]=list.get(i).getCode();
			row[1]=list.get(i).getName();
			row[2]=list.get(i).getId();
			row[3]=list.get(i).getPw();
			row[4]=list.get(i).getEmail();
			row[5]=list.get(i).getDiscription();
			model.addRow(row);
		}
	}
	public static boolean findAccount(String userId,String userPass){
		Connection con=getConnection();
		String query="SELECT * FROM profiles";
		Statement mySt;
		ResultSet rs;
		
		int state=0;
		try {
			
			mySt=con.createStatement();
			rs=mySt.executeQuery(query);
			while(rs.next()){
				tempID=rs.getString("profile_id");
				tempPass=rs.getString("profile_pass");
				tempTable=rs.getString("profile_table");
				if(tempID.equalsIgnoreCase(userId) && tempPass.equalsIgnoreCase(userPass)){
					state=1;
					break;
				}
				else{
					state=0;
				}
			}
			rs.close();
			mySt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(state==1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void executeSQLQuery(String query,String message){
		Connection con=getConnection();
		Statement st;
		
		try {
			st=con.createStatement();
			if(st.executeUpdate(query)==1){
				
				DefaultTableModel model=(DefaultTableModel)AnaPanel.table.getModel();
				model.setRowCount(0);
				tabloyaAktar();
				
				JOptionPane.showMessageDialog(null,message+"baþarýlý!");
			}
			else{
				JOptionPane.showMessageDialog(null,message+"islemi baþarýsýz!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void CreateTable(String query){
		Connection con=getConnection();
		Statement st;
		
		try {
			st=con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void executeSQL(String query){
		Connection con=getConnection();
		Statement st;
		
		try {
			st=con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
