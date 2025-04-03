package com.farmSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.Repository.RenterRepository;
import com.farmSystem.Repository.Impl.EquipmentRepositoryImpl;
import com.farmSystem.Repository.Impl.RenterRepositoryImpl;
import com.farmSystem.Service.BookingsService;
import com.farmSystem.Service.Impl.BookingsServiceImpl;
import com.farmSystem.dao.BookingDAO;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;
import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		
		response.setCharacterEncoding("UTF-8");
		
		try {
			
			String equipmentId = request.getParameter("Emp_id").trim();
			
			String startDate = request.getParameter("startdate").trim();
			
			String endDate = request.getParameter("enddate").trim();
			
			String bookingStatus = request.getParameter("booking_status").trim();
			
			String paymentStatus = request.getParameter("payment_status").trim();
			
			String totalCost = request.getParameter("total_cost").trim();
			
			if(equipmentId == null || startDate == null || endDate == null
					|| bookingStatus == null || paymentStatus == null || totalCost == null) {
				sendErrorResponse(response, "Missing Requried Fields");
				return;
			}
			
			RenterRepository renterRepository = new RenterRepositoryImpl();
			
			EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
			
			HttpSession httpSession = request.getSession(false);
			
			User renter = renterRepository.findRenter((int)httpSession.getAttribute("userId"));
			
			Equipment equipment = equipmentRepository.findEquipment(Integer.parseInt(equipmentId));
			
			BookingsService bookingService = new BookingsServiceImpl();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate start_date = LocalDate.parse(startDate,dtf);
			
			LocalDate end_date = LocalDate.parse(endDate,dtf);
			
			LocalDateTime start_Date = start_date.atStartOfDay();
			
			LocalDateTime end_Date = start_date.atStartOfDay();
			
			BookingDAO bookingDAO = new BookingDAO(equipment,renter,start_Date,
					end_Date,BookingStatus.valueOf(bookingStatus),PaymentStatus.valueOf(paymentStatus),
					Double.parseDouble(totalCost));
			
			bookingService.bookEquipment(bookingDAO);
			
			out.print("{\"status\":success,\"message\":Equipment booked}");
			
			
			
			
			
			
		}
		catch(Exception e) {
			
			sendErrorResponse(response,"Logging Failed: "+e.getMessage());
			
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
