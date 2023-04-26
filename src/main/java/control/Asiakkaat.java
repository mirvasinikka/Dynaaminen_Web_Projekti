package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Myynti;
import model.dao.Dao;


@WebServlet("/asiakkaat/*")
public class Asiakkaat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Asiakkaat() {
        System.out.println("Asiakkaat.Asiakkaat()");
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Asiakkaat.doGet()");
		Dao dao = new Dao();
		ArrayList<Myynti> asiakkaat = dao.getAllItems();
		ArrayList<Myynti> suodatettuAsiakkaat = new ArrayList<Myynti>();
		String haku = request.getParameter("haku");
		Gson JSON = new Gson();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (haku == null) {
			out.println(JSON.toJson(asiakkaat));
		} else {
			for (Myynti item : asiakkaat) {
				if (item.getEtunimi().toLowerCase().contains(haku)) {
					suodatettuAsiakkaat.add(item);
				}
				else if (item.getSukunimi().toLowerCase().contains(haku)) {
					suodatettuAsiakkaat.add(item);
				} else if (item.getPuhelin().toLowerCase().contains(haku)) {
					suodatettuAsiakkaat.add(item);
				} else if (item.getSposti().toLowerCase().contains(haku)) {
					suodatettuAsiakkaat.add(item);
				}

			}
			out.println(JSON.toJson(suodatettuAsiakkaat));
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Asiakkaat.doPost()");

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Asiakkaat.doPut()");
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Asiakkaat.doDelete()");
	}

}
