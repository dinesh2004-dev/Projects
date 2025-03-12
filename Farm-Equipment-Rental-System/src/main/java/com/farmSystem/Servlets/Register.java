package com.farmSystem.Servlets;

import java.io.IOException;

import java.io.PrintWriter;

import org.json.JSONObject;

import com.farmSystem.Service.AdminService;
import com.farmSystem.Service.LenderService;
import com.farmSystem.Service.RenterService;
import com.farmSystem.Service.Impl.AdminServiceImpl;
import com.farmSystem.Service.Impl.LenderServiceImpl;
import com.farmSystem.Service.Impl.RenterServiceImpl;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.enums.Role;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			String fullName = request.getParameter("fullName").trim();

			String password = request.getParameter("password").trim();

			String emailId = request.getParameter("email").trim();

			String mobileNo = request.getParameter("mobileNo").trim();

			String role = request.getParameter("role").trim();

			String address = request.getParameter("address").trim();

			if (fullName == null || password == null || emailId == null || mobileNo == null || role == null
					|| address == null) {

				sendErrorResponse(response, "Missing Requried Fields");

				return;
			}

			UserDAO userDAO = new UserDAO(fullName, password, emailId, mobileNo, Role.valueOf(role), address);

			if (role.equals("Admin")) {

				AdminService adminService = new AdminServiceImpl();

				adminService.addAdmin(userDAO);
			} else if (role.equals("Lender")) {
				LenderService lenderService = new LenderServiceImpl();

				lenderService.addLender(userDAO);
			} else {
				RenterService renterService = new RenterServiceImpl();

				renterService.addRenter(userDAO);
			}

			JSONObject jsonResponse = new JSONObject();

			jsonResponse.put("status", "sucess");

			jsonResponse.put("message", "user registered sucessfully");

			PrintWriter out = response.getWriter();

			out.print(jsonResponse.toString());

			out.flush();

		} catch (Exception e) {

			sendErrorResponse(response, "Registration Failed :" + e.getMessage());
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
