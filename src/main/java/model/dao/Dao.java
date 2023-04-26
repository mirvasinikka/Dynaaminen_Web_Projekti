package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Myynti;

public class Dao {
	
	private Connection con=null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep=null; 
	private String sql;
	private String db ="Myynti.sqlite";
	
	private Connection yhdista(){		
    	Connection con = null;    	
    	String path = System.getProperty("catalina.base");
		path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); // Eclipsessa
		//System.out.println(path); //Tästä näet mihin kansioon laitat tietokanta-tiedostosi
		// path += "/webapps/"; //Tuotannossa. Laita tietokanta webapps-kansioon    	
		String url = "jdbc:sqlite:"+path+db;    	
    	try {	       
    		Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection(url);	
	        //System.out.println("Yhteys avattu.");
	     }catch (Exception e){	
	    	 System.out.println("Yhteyden avaus ep�onnistui.");
	        e.printStackTrace();	         
	     }
	     return con;
	}
	
	private void sulje() {		
		if (stmtPrep != null) {
			try {
				stmtPrep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
				System.out.println("Yhteys suljettu.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Myynti> getAllItems() {
		ArrayList<Myynti> asiakkaat = new ArrayList<Myynti>();
		sql = "SELECT * FROM asiakkaat ORDER BY id DESC"; 
		try {
			con = yhdista();
			if (con != null) { // jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);
				rs = stmtPrep.executeQuery();
				if (rs != null) { // jos kysely onnistui
					while (rs.next()) {
						Myynti myynti = new Myynti();
						myynti.setId(rs.getInt(1));
						myynti.setEtunimi(rs.getString(2));
						myynti.setSukunimi(rs.getString(3));
						myynti.setPuhelin(rs.getString(4));
						myynti.setSposti(rs.getString(5));
						asiakkaat.add(myynti);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sulje();
		}
		return asiakkaat;
	}

}
