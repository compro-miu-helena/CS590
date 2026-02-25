# SpringMongoDemo - Students

This application stores `Student` documents in MongoDB and runs the required queries at startup.

## Data Model

- `Student`
  - `name`
  - `phoneNumber`
  - `email`
  - `address`
- `Address`
  - `street`
  - `city`
  - `zip`

MongoDB collection used: `students` in database `testdb`.

## What happens on startup

`Application` does the following:

1. Deletes existing records from `students`
2. Inserts 5 students
3. Runs and prints:
   - all students
   - students with a certain name
   - one student with a certain phone number
   - students from a certain city

## Run the application

1. Start MongoDB locally on port `27017`.
2. From `springmongodemo` folder run:

```powershell
.\mvnw spring-boot:run
```

## Repository query methods

- `findAll()`
- `findByName(String name)`
- `findByPhoneNumber(String phoneNumber)`
- `findByAddressCity(String city)`

## Check data in MongoDB Compass

1. Open MongoDB Compass.
2. Connect using:
   - Host: `localhost`
   - Port: `27017`
3. Open database `testdb`.
4. Open collection `students`.
5. Click **Documents** to view all inserted students.
6. Use the filter bar for the required queries:
   - All students:
     ```json
     {}
     ```
   - Students by name (`Alice Johnson`):
     ```json
     { "name": "Alice Johnson" }
     ```
   - Student by phone number (`555-1004`):
     ```json
     { "phoneNumber": "555-1004" }
     ```
   - Students by city (`Chicago`):
     ```json
     { "address.city": "Chicago" }
     ```

If Compass shows 5 documents in `students`, the insert step worked correctly.
