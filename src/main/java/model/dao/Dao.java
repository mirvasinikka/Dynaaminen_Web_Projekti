package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Myynti;

public class Dao {
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep = null;
	private String sql = "";
	private String db = "Myynti.sqlite";

	private Connection yhdista() {
		Connection con = null;
		String path = System.getProperty("catalina.base");
		path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); // Eclipsessa
		//System.out.println(path); //Tästä näet mihin kansioon laitat tietokanta-tiedostosi
		// path += "/webapps/"; //Tuotannossa. Laita tietokanta webapps-kansioon
		String url = "jdbc:sqlite:" + path + db;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			System.out.println("Yhteys avattu.");
		} catch (Exception e) {
			System.out.println("Yhteyden avaus epäonnistui.");
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
		sql = "SELECT * FROM asiakkaat ORDER BY asiakas_id DESC"; //Suurin id tulee ensimmäisenä
		try {
			con = yhdista();
			if (con != null) { // jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);
				rs = stmtPrep.executeQuery();
				if (rs != null) { // jos kysely onnistui
					while (rs.next()) {
						Myynti asiakas = new Myynti();
						asiakas.setId(rs.getInt(1));
						asiakas.setEtunimi(rs.getString(2));
						asiakas.setSukunimi(rs.getString(3));
						asiakas.setPuhelin(rs.getString(4));
						asiakas.setSposti(rs.getString(5)); //tässä oli arvo 4
						asiakkaat.add(asiakas);						
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
	
	public ArrayList<Myynti> getAllItems(String searchStr) { //Metodeja voi kuormittaa, kunhan parametreiss䠥roja
		ArrayList<Myynti> asiakkaat = new ArrayList<Myynti>();
		sql = "SELECT * FROM asiakkaat WHERE etunimi LIKE ? or sukunimi LIKE ? or puhelin LIKE ? or sposti LIKE ? ORDER BY asiakas_id DESC";
		try {
			con = yhdista();
			if (con != null) { // jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);
				stmtPrep.setString(1, "%" + searchStr + "%");
				stmtPrep.setString(2, "%" + searchStr + "%");
				stmtPrep.setString(3, "%" + searchStr + "%");
				stmtPrep.setString(4, "%" + searchStr + "%");
				rs = stmtPrep.executeQuery();
				if (rs != null) { // jos kysely onnistui
					while (rs.next()) {
						Myynti asiakas = new Myynti();
						asiakas.setId(rs.getInt(1));
						asiakas.setEtunimi(rs.getString(2));
						asiakas.setSukunimi(rs.getString(3));
						asiakas.setPuhelin(rs.getString(4));
						asiakas.setSposti(rs.getString(5)); //tässä oli arvo 4
						asiakkaat.add(asiakas);		
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
	
	public boolean addItem(Myynti asiakas) {
		boolean paluuArvo = true;
		sql = "INSERT INTO asiakkaat(etunimi, sukunimi, puhelin, sposti)VALUES(?,?,?,?)";
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1, asiakas.getEtunimi());
			stmtPrep.setString(2, asiakas.getSukunimi());
			stmtPrep.setString(3, asiakas.getPuhelin());
			stmtPrep.setString(4, asiakas.getSposti());
			stmtPrep.executeUpdate();		
		} catch (Exception e) {
			paluuArvo=false;
			e.printStackTrace();
		} finally {
			sulje();
		}
		return paluuArvo;
	}
	
	public boolean removeItem(int id) { 
		boolean paluuArvo = true;
		sql = "DELETE FROM asiakkaat WHERE asiakas_id=?";
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, id);
			stmtPrep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			paluuArvo = false;
		} finally {
			sulje();
		}
		return paluuArvo;
	}
}
