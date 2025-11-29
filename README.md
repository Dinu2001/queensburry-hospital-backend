http://localhost:8090/api/v1/lab-test/

http://localhost:8090/api/v1/lab-test/save
{
    "labId": "LT10002",
    "testName": "CBC (Complete Blood Count)",
    "description": "Checks overall health and detects disorders.",
    "test_amount": 1200.0,
    "preparationInstructions": "No special preparation required."
  }


http://localhost:8090/api/v1/lab-test/update/LT84287
{
    "testName": "CBC (Complete Blood Count)",
    "description": "Checks overall health and detects disorders.",
    "test_amount": 1500.0,
    "preparationInstructions": "No special preparation required."
  }

http://localhost:8090/api/v1/user/save
{
  "userId": "",
  "firstName": "Dr. Malith",
  "lastName": "Fernando",
  "email": "malith.fernando@hospital.com",
  "role": "DOCTOR",
  "status": "DEACTIVE",
  "password": "EncryptedPassword456!",
  "createdAt": "2025-02-01T09:20:10"
}

http://localhost:8090/api/v1/user/

http://localhost:8090/api/v1/patient/

http://localhost:8090/api/v1/doctor/save
{
  "firstName": "Kumara",
  "lastName": "Jayasinghe",
  "email": "kumara.j@gmail.com",
  "password": "Doctor@789",
  "registrationNumber": "DOC1003",
  "specification": "Orthopedics",
  "phoneNumber": "0779876543",
  "availableDates": [
    "2025-12-05",
    "2025-12-07",
    "2025-12-09"
  ]
}
