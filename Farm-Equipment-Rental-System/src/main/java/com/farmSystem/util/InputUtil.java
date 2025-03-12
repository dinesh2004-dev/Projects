package com.farmSystem.util;

import java.util.Scanner;

import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.enums.Availability;
import com.farmSystem.enums.Category;
import com.farmSystem.enums.Condition;
import com.farmSystem.enums.Role;

public class InputUtil {
	
	public static int acceptMenuOption(Scanner sc) {
		
		System.out.println("press 1 to add User:");
		
		System.out.println("Press 2 to add Equipment:");
		
		int menuOption = sc.nextInt();
		
		if(menuOption == 1 || menuOption == 2) {
			
			return menuOption;
		}
		else {
			
			return acceptMenuOption(sc);
		}
	}
	
	public static boolean wantToContinue(Scanner sc) {
		System.out.println("Press Y to continue and N to exit.");
		char choice = sc.next().toUpperCase().charAt(0);
		return 'Y' == choice;
	}
	
	public static UserDAO acceptUserDetailsToSave(Scanner sc) {
		
		System.out.println("Enter Full name:");
		
		String name = sc.next();
		
		sc.nextLine();
		
		System.out.println("Enter Password:");
		
		String password = sc.next();
		
		System.out.println("Enter EmailId:");
		
		String Email = sc.next();
		
		System.out.println("Enter Mobile Number:");
		
		String mobile = sc.next();
		
		System.out.println("Enter Role(Admin,Lender,Renter):");
		
		String role = sc.next();
		
		System.out.println("Enter Address:");
		
		String address = sc.next();
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.setAddress(address);
		
		userDAO.setEmailId(Email);
		
		userDAO.setFullName(name);
		
		userDAO.setPassword(password);
		
		userDAO.setPhoneNumber(address);
		
		userDAO.setRole(Role.valueOf(role));
		
		return userDAO;
	}
	
	public static EquipmentDAO acceptEquipmentDetailsToSave(Scanner sc) {
		
		System.out.println("Enter Equipment Name:");
		
		String name = sc.next();
		
		System.out.println("Enter Category (Tractor, Plow, Sprayer, Harvester, Seeder):");
		
		String category = sc.next();
		
		System.out.println("Enter Description Of Equipment:");
		
		String description = sc.next();
		
		sc.nextLine();
		
		System.out.println("Enter Condition (New,Good, Average, NeedsRepair):");
		
		String condition = sc.next();
		
		System.out.println("Enter Rental Rate : ");
		
		double rentaRate = sc.nextDouble();
		
		System.out.println("Enter Availability of Equipment (Available, Booked, Unavailable):");
		
		String availability =  sc.next();
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		equipmentDAO.setAvailability(Availability.valueOf(availability));
		
		equipmentDAO.setCategory(Category.valueOf(category));
		
		equipmentDAO.setCondition(Condition.valueOf(condition));
		
		equipmentDAO.setDescription(description);
		
		equipmentDAO.setName(name);
		
		equipmentDAO.setRentalRate(rentaRate);
		
		return equipmentDAO;
	}

}
