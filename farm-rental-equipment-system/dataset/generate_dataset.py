import bcrypt
import random
from faker import Faker
from datetime import datetime

fake = Faker('en_IN')

# ✅ 40+ Indian cities with latitude & longitude
indian_locations = [
    ("Mumbai", 19.0760, 72.8777), ("Delhi", 28.6139, 77.2090), ("Bengaluru", 12.9716, 77.5946),
    ("Kolkata", 22.5726, 88.3639), ("Chennai", 13.0827, 80.2707), ("Hyderabad", 17.3850, 78.4867),
    ("Ahmedabad", 23.0225, 72.5714), ("Pune", 18.5204, 73.8567), ("Jaipur", 26.9124, 75.7873),
    ("Lucknow", 26.8467, 80.9462), ("Kanpur", 26.4499, 80.3319), ("Nagpur", 21.1458, 79.0882),
    ("Indore", 22.7196, 75.8577), ("Thane", 19.2183, 72.9781), ("Bhopal", 23.2599, 77.4126),
    ("Visakhapatnam", 17.6868, 83.2185), ("Patna", 25.5941, 85.1376), ("Vadodara", 22.3072, 73.1812),
    ("Ghaziabad", 28.6692, 77.4538), ("Ludhiana", 30.9000, 75.8573), ("Agra", 27.1767, 78.0081),
    ("Nashik", 19.9975, 73.7898), ("Faridabad", 28.4089, 77.3178), ("Meerut", 28.9845, 77.7064),
    ("Rajkot", 22.3039, 70.8022), ("Kalyan", 19.2403, 73.1305), ("Vasai-Virar", 19.3919, 72.8397),
    ("Varanasi", 25.3176, 82.9739), ("Srinagar", 34.0837, 74.7973), ("Aurangabad", 19.8762, 75.3433),
    ("Dhanbad", 23.7957, 86.4304), ("Amritsar", 31.6340, 74.8723), ("Navi Mumbai", 19.0330, 73.0297),
    ("Allahabad", 25.4358, 81.8463), ("Ranchi", 23.3441, 85.3096), ("Howrah", 22.5958, 88.2636),
    ("Coimbatore", 11.0168, 76.9558), ("Jabalpur", 23.1815, 79.9864), ("Gwalior", 26.2183, 78.1828),
    ("Vijayawada", 16.5062, 80.6480), ("Madurai", 9.9252, 78.1198)
]

roles = ['Admin', 'Lender', 'Renter']
equipment_categories = ['Harvester', 'Plow', 'Seeder', 'Sprayer', 'Tractor']
equipment_conditions = ['Average', 'Good', 'NeedsRepair', 'New']
equipment_availability = ['Available', 'Booked', 'Unavailable']

# Pre-hash passwords
passwords = {
    'Admin': bcrypt.hashpw(b"adminpass", bcrypt.gensalt()).decode(),
    'Lender': bcrypt.hashpw(b"lenderpass", bcrypt.gensalt()).decode(),
    'Renter': bcrypt.hashpw(b"renterpass", bcrypt.gensalt()).decode()
}

# Descriptions based on the equipment category
equipment_descriptions = {
    'Harvester': "High-efficiency harvester for large-scale crop harvesting.",
    'Plow': "Heavy-duty plow ideal for deep soil tilling and land preparation.",
    'Seeder': "Efficient seed planter for large farms and plantations.",
    'Sprayer': "Advanced agricultural sprayer for pesticides and fertilizers.",
    'Tractor': "Versatile tractor for farming, construction, and roadwork."
}

# Generate user_table entries
users = []
lender_ids = []
email_set = set()  # To store and ensure unique email
phone_set = set()  # To store and ensure unique phone numbers

for i in range(1, 51):
    role = roles[i % 3]
    password = passwords[role]
    full_name = fake.name()
    
    # Generate unique email and phone number
    email = f"{role.lower()}{i}@example.com"
    while email in email_set:  # Ensure unique email
        email = f"{role.lower()}{i}@example.com"
    email_set.add(email)

    mobile = f"99999900{str(i).zfill(2)}"  # Phone number is always 10 digits
    while mobile in phone_set:  # Ensure unique mobile number
        mobile = f"99999900{str(i).zfill(2)}"
    phone_set.add(mobile)

    address = fake.address().replace('\n', ', ')
    city, lat, lon = random.choice(indian_locations)

    if role == "Lender":
        lender_ids.append(i)

    users.append(
    f"( {i}, '{address}', CURRENT_TIMESTAMP, '{email}', '{full_name}', {lat}, {lon}, '{password}', '{mobile}', '{role}' ) -- password: {role.lower()}pass"
)


# Print user_table INSERT
print("-- user_table INSERT")
print("INSERT INTO user_table (id, address, created_at, email_id, full_name, latitude, longitude, password, mobile, role) VALUES")
print(",\n".join(users) + ";")

# Generate equipment entries
equipment = []
for eid in range(1, 51):
    category = random.choice(equipment_categories)
    condition = random.choice(equipment_conditions)
    availability = random.choice(equipment_availability)
    name = f"{fake.company()} {category}"
    description = equipment_descriptions[category]  # Relevant description based on category
    city, lat, lon = random.choice(indian_locations)
    rate = round(random.uniform(300, 2500), 2)
    location = city
    owner_id = random.choice(lender_ids)

    equipment.append(
        f"( {eid}, '{availability}', '{category}', '{condition}', CURRENT_TIMESTAMP, '{description}', '{location}', '{name}', {rate}, {owner_id} )"
    )

# Print Equipment INSERT
print("\n-- Equipment INSERT")
print("INSERT INTO Equipment (id, availability, category, condition, create_date, description, location, name, rental_rate, owner_id) VALUES")
print(",\n".join(equipment) + ";")
