import requests
import threading
from datetime import datetime, timedelta

# Replace with actual JWTs for each test user
jwt_tokens = {
    1: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZW50ZXI1QGV4YW1wbGUuY29tIiwiaWF0IjoxNzQ3MjgzMjc1LCJleHAiOjE3NDczMTkyNzV9.-eq5-z-UfHh2EwQW-7wOhwBSN1zLXep09-Qud1FNcaM",
    2: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZW50ZXIxMUBleGFtcGxlLmNvbSIsImlhdCI6MTc0NzI4MzMxNSwiZXhwIjoxNzQ3MzE5MzE1fQ.2jGm4MLXhJzBdL3wtItTRpHmF_y2J1wU5OT-plfft2g",
    #3: "jwt_token_user_3",
    #4: "jwt_token_user_4",
    #5: "jwt_token_user_5",
}

def book_equipment(user_id, equipment_id, base_start_date):
    token = jwt_tokens[user_id]
    url = "http://localhost:8080/booking"

    # Adjust each user's booking start and end time to prevent overlaps
    start_date = base_start_date + timedelta(days=user_id)
    end_date = start_date + timedelta(days=5)

    payload = {
        "equipmentId": equipment_id,
        "startDate": start_date.strftime("%Y-%m-%dT%H:%M:%S"),
        "endDate": end_date.strftime("%Y-%m-%dT%H:%M:%S")
    }

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json"
    }

    response = requests.post(url, json=payload, headers=headers)
    print(f"User {user_id} booking status: {response.status_code}")
    print(response.text)

# Base start time for simulation
base_start_date = datetime(2026, 5, 15, 9, 0)

# Simulate concurrent bookings for 5 users on equipment ID 1
threads = []
for user_id in range(1, 6):
    t = threading.Thread(target=book_equipment, args=(user_id, 5, base_start_date))
    threads.append(t)
    t.start()

for t in threads:
    t.join()
