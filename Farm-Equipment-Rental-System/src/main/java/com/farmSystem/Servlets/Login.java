package com.farmSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import com.farmSystem.Service.UserService;
import com.farmSystem.Service.Impl.UserServiceImpl;
import com.farmSystem.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		PrintWriter out = response.getWriter();
		
		try {
			
			String emailId = request.getParameter("Email").trim();
			
			String password = request.getParameter("password").trim();
			
			if(emailId == null || password == null) {
				
				sendErrorResponse(response,"Missing Requried fields");
				
				return;
			}
			
			UserService userService = new UserServiceImpl();
			
			User user = userService.findUser(emailId, password);
			
			if(user != null) {
				
				int userId = user.getId();
				
				HttpSession httpSession = request.getSession(true);
				
				httpSession.setAttribute("userId", userId);
				
				httpSession.setAttribute("user", user);
				
				httpSession.setAttribute("emailId", emailId);
				
				JSONObject jsonResponse = new JSONObject();
				
				jsonResponse.put("status","sucess");
				jsonResponse.put("message","User Login Sucess");
				jsonResponse.put("userId", userId);
				jsonResponse.put("email",emailId);
				jsonResponse.put("sessionId", httpSession.getId());
				
				
				
				out.print(jsonResponse.toString());
				
				out.flush();
				
				System.out.println("User logged in with ID: " + userId);
			}
			else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.println("{\"success\": false, \"message\": \"Invalid username or password\"}");
			}
			
			
			
		}
		catch(Exception e) {
			
			sendErrorResponse(response,"Logging Failed: "+e.getMessage());
		}
	}
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	        HttpSession httpSession = request.getSession(false);
	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        
	        if (httpSession != null && httpSession.getAttribute("userId") != null) {
	            Integer userId = (Integer) httpSession.getAttribute("userId");
	            String username = (String) httpSession.getAttribute("username");
	            
	            out.println("{\"success\": true, \"message\": \"User already logged in\", " +
	                       "\"userId\": " + userId + ", \"username\": \"" + username + "\"}");
	        } else {
	            out.println("{\"success\": false, \"message\": \"No active session found\"}");
	        }
	    }
	
	public void sendErrorResponse(HttpServletResponse response,String message) throws IOException {
		
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("status","error");
		
		jsonObject.put("message",message);
		
		PrintWriter out = response.getWriter();
		
		out.print(jsonObject.toString());
		
		out.flush();
	}

}
