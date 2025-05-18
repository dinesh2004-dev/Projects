# 🚜 Farm Equipment Rental System - Backend (Spring Boot)

This is a complete **Spring Boot backend system** for a Farm Equipment Rental platform that connects **Lenders** (equipment owners) with **Renters** (equipment seekers). It includes robust features for user management, equipment listings, advanced filtering, bookings, payments, and more.

---

## 🔑 Key Features

- ✅ **Role-based Registration & Login**
  - Supports three roles: **Admin**, **Lender**, and **Renter**
  - JWT-based authentication and authorization

- 📍 **Google Maps API Integration**
  - Fetch latitude and longitude for equipment address

- 🔎 **Advanced Search Filters**
  - Search by:
    - Equipment type
    - Price range
    - Availability
    - Distance radius from location

- 📅 **Equipment Booking System**
  - Book equipment based on real-time availability

- 💳 **Payment Integration with Razorpay**
  - Secure payment gateway integration for transactions

- 📧 **Email Notification Service**
  - Confirmation emails for bookings and updates

---

## 🛠 Tech Stack

- **Backend**: Spring Boot
- **Database**: MySQL
- **Security**: JWT Authentication
- **Payment Gateway**: Razorpay
- **Geolocation API**: Google Maps API
- **Mail Service**: JavaMailSender (SMTP)
- **Testing**: Postman

---

## 🚀 Upcoming Features

1. 📆 Google Calendar API integration to show available dates
2. 💬 Chat functionality between renter and lender
3. 🚚 Equipment Delivery & Pickup using Shiprocket API
4. ⭐ Review and Rating system for equipment and lenders
5. 📊 Analytics Dashboard:
   - **Lenders**: View revenue, equipment utilization, rental history
   - **Renters**: View expenses and booking history
   - **Admins**: Monitor platform activity, trends, and disputes

---

## 📦 Getting Started

### ✅ Prerequisites

- Java 11 or higher
- Maven
- MySQL
- Google Maps API Key
- Razorpay API Keys
- Email SMTP credentials

---

### 🔧 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/dinesh2004-dev/Projects/new/main/farm-rental-equipment-system
   cd farm-equipment-backend
