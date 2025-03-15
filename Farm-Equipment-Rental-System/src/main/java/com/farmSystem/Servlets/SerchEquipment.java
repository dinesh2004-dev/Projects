package com.farmSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;

import com.farmSystem.Service.EquipmentService;
import com.farmSystem.Service.Impl.EquipmentServiceImpl;
import com.farmSystem.TypeAdapter.LocalDateTimeAdapter;
import com.farmSystem.entity.Equipment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SerchEquipment
 */
@WebServlet("/SerchEquipment")
public class SerchEquipment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SerchEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		try {
			String category = request.getParameter("category");

			String location = request.getParameter("location");

			String minRate = request.getParameter("minRate");

			String maxRate = request.getParameter("maxRate");
			
			String sortField = request.getParameter("sortField");
			
			String sortOrder = request.getParameter("sortOrder");
			
			String pageNumber = request.getParameter("pageNumber").trim();

			String pageSize = request.getParameter("pageSize").trim();

			Double minRate1 = (minRate != null && !minRate.isEmpty()) ? Double.parseDouble(minRate) : null;
			Double maxRate1 = (maxRate != null && !maxRate.isEmpty()) ? Double.parseDouble(maxRate) : null;

			if (pageSize == null || pageNumber == null || pageSize.isEmpty() || pageNumber.isEmpty()) {

				sendErrorResponse(response, "Requried parameter missing");
			}
			int pageNumber1 = Integer.parseInt(pageNumber);

			int pageSize1 = Integer.parseInt(pageSize);

			EquipmentService equipmentService = new EquipmentServiceImpl();
			
			

			List<Equipment> equipments = equipmentService.searchEquipment(category, location, minRate1, maxRate1,
					sortField,sortOrder,pageNumber1, pageSize1);

			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

			String jsonEquipment = gson.toJson(equipments);

			out.print(jsonEquipment);

			out.flush();

		} catch (Exception e) {

			sendErrorResponse(response, "serching Failed: " + e.getMessage());

		}
	}

	public void sendErrorResponse(HttpServletResponse response, String message) throws IOException {

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("status", "error");

		jsonObject.put("message", message);

		PrintWriter out = response.getWriter();

		out.print(jsonObject.toString());

		out.flush();
	}

}
