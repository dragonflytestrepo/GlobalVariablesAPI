#!/bin/bash

# GlobalVariablesAPI Test Script
# Make sure the application is running on http://localhost:8080

BASE_URL="http://localhost:8080"
AUTH="admin:password"

echo "🧪 Testing GlobalVariablesAPI"
echo "================================"

# Test 1: POST - Store global variables
echo "1️⃣ Testing POST /api/global-vars"
echo "Storing variables for ID '1'..."
curl -X POST "$BASE_URL/api/global-vars" \
  -H "Content-Type: application/json" \
  -u "$AUTH" \
  -d '{
    "GlobalVariables": [
      {
        "id": "1",
        "BASE_URL": "https://your-app.com",
        "USERNAME": "autovendorC01",
        "PASSWORD": "dragonfly2",
        "Transaction_ID": "CNY202507091610081",
        "ENVIRONMENT": "staging",
        "Withdrawl_ID": "W123432",
        "Withdrawl": "W123",
        "ID": "1234"
      }
    ]
  }' | jq '.'

echo -e "\n"

# Test 2: POST - Store another set of variables
echo "2️⃣ Testing POST /api/global-vars (second set)"
echo "Storing variables for ID '2'..."
curl -X POST "$BASE_URL/api/global-vars" \
  -H "Content-Type: application/json" \
  -u "$AUTH" \
  -d '{
    "GlobalVariables": [
      {
        "id": "2",
        "ENVIRONMENT": "production",
        "API_KEY": "abc123",
        "DEBUG_MODE": "false"
      }
    ]
  }' | jq '.'

echo -e "\n"

# Test 3: GET - Retrieve variables by ID
echo "3️⃣ Testing GET /api/global-vars/1"
echo "Retrieving variables for ID '1'..."
curl -X GET "$BASE_URL/api/global-vars/1" \
  -u "$AUTH" | jq '.'

echo -e "\n"

# Test 4: GET - Retrieve variables by ID (second set)
echo "4️⃣ Testing GET /api/global-vars/2"
echo "Retrieving variables for ID '2'..."
curl -X GET "$BASE_URL/api/global-vars/2" \
  -u "$AUTH" | jq '.'

echo -e "\n"

# Test 5: GET - Retrieve all variables
echo "5️⃣ Testing GET /api/global-vars"
echo "Retrieving all variables..."
curl -X GET "$BASE_URL/api/global-vars" \
  -u "$AUTH" | jq '.'

echo -e "\n"

# Test 6: POST - Update existing variables
echo "6️⃣ Testing POST /api/global-vars (update existing)"
echo "Updating variables for ID '1'..."
curl -X POST "$BASE_URL/api/global-vars" \
  -H "Content-Type: application/json" \
  -u "$AUTH" \
  -d '{
    "GlobalVariables": [
      {
        "id": "1",
        "BASE_URL": "https://updated-app.com",
        "USERNAME": "autovendorC01",
        "PASSWORD": "dragonfly2",
        "NEW_VARIABLE": "new_value",
        "ENVIRONMENT": "production"
      }
    ]
  }' | jq '.'

echo -e "\n"

# Test 7: GET - Verify updated variables
echo "7️⃣ Testing GET /api/global-vars/1 (after update)"
echo "Retrieving updated variables for ID '1'..."
curl -X GET "$BASE_URL/api/global-vars/1" \
  -u "$AUTH" | jq '.'

echo -e "\n"
echo "✅ API testing completed!" 