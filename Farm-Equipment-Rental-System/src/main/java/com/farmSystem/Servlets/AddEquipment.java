package com.farmSystem.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

/**
 * Servlet implementation class AddEquipment
 */
@WebServlet("/AddEquipment")
public class AddEquipment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEquipment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
		
		response.setCharacterEncoding("UTF-8");
		
		try {
			
			String name = request.getParameter("name").trim();
			
			String category = request.getParameter("category").trim();
			
			String location = request.getParameter("location").trim();
			
			String description = request.getParameter("description").trim();
			
			String condition = request.getParameter("condition").trim();
			
			String rentalRate = request.getParameter("rentalRate").trim();
			
			String availability = request.getParameter("availability").trim();
			
			if(name == null ||  category == null || location == null || description == null || condition == null 
					|| rentalRate == null || availability == null) {
				
				sendErrorResponse(response, "Missing Requried Fields");

				return;
			}
			
			JSONObject jsonResponse = new JSONObject();

			jsonResponse.put("status", "sucess");

			jsonResponse.put("message", "Equipmet Added Sucefully");

			PrintWriter out = response.getWriter();

			out.print(jsonResponse.toString());

			out.flush();
		}
		catch(Exception e){
			
			sendErrorResponse(response,"Adding Equipmet Failed: "+e.getMessage());
			
		}
	}
	public void sendErrorResponse(HttpServletResponse response, String message) throws IOException {

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		JSONObject jsonResponse = new JSONObject();

		jsonResponse.put("status", "error");

		jsonResponse.put("message", message);

		PrintWriter out = response.getWriter();

		out.print(jsonResponse.toString());

		out.flush();
	}

}
