package com.farmSystem;

import java.util.Scanner;

import com.farmSystem.Service.AdminService;
import com.farmSystem.Service.LenderService;
import com.farmSystem.Service.RenterService;
import com.farmSystem.Service.Impl.AdminServiceImpl;
import com.farmSystem.Service.Impl.LenderServiceImpl;
import com.farmSystem.Service.Impl.RenterServiceImpl;
import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.exceptions.AdminNotFound;
import com.farmSystem.exceptions.LenderNotFound;
import com.farmSystem.exceptions.RenterNotFound;
import com.farmSystem.exceptions.UserNotLender;
import com.farmSystem.util.InputUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws AdminNotFound,LenderNotFound, RenterNotFound, UserNotLender {

//	AdminService adminService = new AdminServiceImpl();
//	
//	LenderService lenderService = new LenderServiceImpl();
//	
//	RenterService renterService = new RenterServiceImpl();
//	
//	try(Scanner sc = new Scanner(System.in)){
//		
//		do {
//			
//			System.out.println("Welcome to Farm System");
//			
//			int menuOption = InputUtil.acceptMenuOption(sc);
//			
//			switch(menuOption) {
//				
//			case 1:
//				UserDAO userDAO = InputUtil.acceptUserDetailsToSave(sc);
//				
//				if(userDAO.getRole().toString().equals("Admin")) {
//					adminService.addAdmin(userDAO);
//				}
//				else if(userDAO.getRole().toString().equals("Lender")) {
//				lenderService.addLender(userDAO);
//				}
//				else {
//					renterService.addRenter(userDAO);
//			}
//			break;
//		case 2:
//			EquipmentDAO equipmentDAO = InputUtil.acceptEquipmentDetailsToSave(sc);
//				
//				lenderService.addEquipment(2, equipmentDAO);
//				break;
//			default:
//				System.out.println("Invalid Option");
//		}
//		}while(InputUtil.wantToContinue(sc));
//	}
//		
//		double[] res = GeolocationService.getLocationFromIP("192.168.1.109");
//		
//		String address = ReverseGeocodingService.getAddressFromCoordinates(res[0], res[1]);
//		
//		System.out.println(address);
		
		
	}
}
