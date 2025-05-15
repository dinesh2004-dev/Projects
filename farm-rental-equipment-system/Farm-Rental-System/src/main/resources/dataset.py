import bcrypt
import random
from faker import Faker
from datetime import datetime

fake = Faker()

roles = ['Admin', 'Lender', 'Renter']
equipment_categories = ['Harvester', 'Plow', 'Seeder', 'Sprayer', 'Tractor']
equipment_conditions = ['Average', 'Good', 'NeedsRepair', 'New']
equipment_availability = ['Available', 'Booked', 'Unavailable']

# Base passwords
passwords = {
    'Admin': bcrypt.hashpw(b"adminpass", bcrypt.gensalt()).decode(),
    'Lender': bcrypt.hashpw(b"lenderpass", bcrypt.gensalt()).decode(),
    'Renter': bcrypt.hashpw(b"renterpass", bcrypt.gensalt()).decode()
}

# Generate user_table entries
users = []
for i in range(1, 11):
    role = roles[i % 3]
    password = passwords[role]
    full_name = fake.name()
    email = f"{role.lower()}{i}@example.com"
    address = fake.address().replace('\n', ', ')
    mobile = f"99999900{str(i).zfill(2)}"
    lat, lng = round(fake.latitude(), 6), round(fake.longitude(), 6)

    users.append(
        f"( {i}, '{address}', CURRENT_TIMESTAMP, '{email}', '{full_name}', {lat}, {lng}, '{password}', '{mobile}', '{role}' )"
    )

# Print user_table INSERT
print("-- user_table INSERT")
print("INSERT INTO user_table (id, address, created_at, email_id, full_name, latitude, longitude, password, mobile, role) VALUES")
print(",\n".join(users) + ";")

# Generate equipment records only for Lenders
lender_ids = [user_id for user_id in range(1, 11) if roles[user_id % 3] == 'Lender']
equipment = []
for eid in range(1, 11):
    category = random.choice(equipment_categories)
    condition = random.choice(equipment_conditions)
    availability = random.choice(equipment_availability)
    owner_id = random.choice(lender_ids)
    name = f"{fake.company()} {category}"
    description = fake.sentence(nb_words=6)
    location = fake.city()
    rate = round(random.uniform(300, 2500), 2)

    equipment.append(
        f"( {eid}, '{availability}', '{category}', '{condition}', CURRENT_TIMESTAMP, '{description}', '{location}', '{name}', {rate}, {owner_id} )"
    )

# Print Equipment INSERT
print("\n-- Equipment INSERT")
print("INSERT INTO Equipment (id, availability, category, condition, create_date, description, location, name, rental_rate, owner_id) VALUES")
print(",\n".join(equipment) + ";")
