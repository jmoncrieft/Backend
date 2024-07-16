# Hotel Bookings API
API for the Hotel Bookings Frontend Frameworks module project.

## Usage
This API will be hosted on localhost:8080 and requires access to a Postgres database on port 5432.

### Starting the Application with Postgres
* Ensure that your postgres database is available and configured with the following options:
  * POSTGRES_USER=postgres
  * POSTGRES_PASSWORD=root
  * PORT=5432
* The DataLoader class in the data package will load a few examples of each entity (Users, Reservation, Room Type) into the database after the service starts up.

### Running the Application
* Navigate to src\main\java\io\catalyte\training for HotelApiApplication.java
* If starting in Intellij right click Application, then click run.
* After this has been done, the application may be run subsequently with the green play symbol in the top right corner. Ensure the dropdown selection is at HotelApiApplication.

### Autoloaded Users

| If you want to...              | Use this method...  | And this URI...             |
|--------------------------------|---------------------|-----------------------------|
| Login as a manager or employee | POST                | http://localhost:8080/login |

#### Manager
email: manager@hotelapi.com
password: password

#### Employee
email: employee@hotelapi.com
password: password

#### Parameters
The User Credential object has the following properties:
* **email**. String. The user's email.
* **password**. String. The user's password.

Example JSON object to use when sending a POST request to login:

````
{
    "email": "manager@hotelapi.com",
    "password": "password"
}
````

#### Response Messages

| HTTP Status Code | Reason                                                                                                         |
|------------------|----------------------------------------------------------------------------------------------------------------|
| 200              | OK - Returns a JSON Web Token (JWT). This token can then be used to access the Reservation and Room Type APIs. |
| 400              | Bad Request - Check your inputs for invalid entries                                                            |
| 403              | Forbidden - Ensure you're making a POST request                                                                |

### Reservation API

| If you want to...        | Use this method...  | And this URI...                          |
|--------------------------|---------------------|------------------------------------------|
| Create a reservation     | POST                | http://localhost:8080/reservations       |
| Read all reservations    | GET                 | http://localhost:8080/reservations       |
| Read a reservation by id | GET                 | http://localhost:8080/reservations/{id}  |
| Update a reservation     | PUT                 | http://localhost:8080/reservations/{id}  |
| Delete a reservation     | DELETE              | http://localhost:8080/reservations/{id}  |

#### Parameters
The Reservation object has the following properties:
* **id**. Long. Number. The unique identifier for a record in the database. The id is auto-generated, so
  you do not need to include it when creating a new reservation.
* **user**. String. The user's email.
* **guestEmail**. String. The guest's email.
* **RoomTypeId**. Long. The id of the room type.
* **checkInDate**. String. The guest's check in date.
* **numberOfNights**. int. The number of nights the guest will be staying.

Example JSON object to use when creating a reservation (POST):

````
{
    "user": "manager@hotelapi.com",
    "guestEmail": "guestEmail@guest.com",
    "roomTypeId": 1,
    "checkInDate": "06-01-2022",
    "numberOfNights": 3,
}
````

Example JSON object to use when updating a reservation (PUT) - must include id property:

````
{
    "id": 1,
    "user": "employee@hotelapi.com",
    "guestEmail": "bobRoss@gmail.com",
    "roomTypeId": 1,
    "checkInDate": "01-01-2020",
    "numberOfNights": 2
}
````

#### Response Messages

GET - Fetch all reservations or fetch a reservation by id

| HTTP Status Code | Reason                                                                                                                    |
|------------------|---------------------------------------------------------------------------------------------------------------------------|
| 200              | OK - If no id provided, returns all reservations. If id is provided, returns a single reservation associated with that id |
| 404              | Not Found - The id for that reservation does not exist                                                                    |
| 403              | Forbidden - Ensure you're logged in                                                                                       |

POST - Creating a reservation

| HTTP Status Code | Reason                                                                                          |
|------------------|-------------------------------------------------------------------------------------------------|
| 201              | Created - Returns created JSON object                                                           |
| 400              | Bad Request - Check your inputs for invalid entries                                             |
| 403              | Forbidden - Ensure you're logged in                                                             |
| 405              | Method Not Allowed - Ensure the URI includes the id of the reservation you're trying to update  |

PUT - Updating a reservation

| HTTP Status Code | Reason                                                                                         |
|------------------|------------------------------------------------------------------------------------------------|
| 200              | OK - Returns updated JSON object                                                               |
| 400              | Bad Request - Check your inputs for invalid entries                                            |
| 403              | Forbidden - Ensure you're logged in                                                            |
| 405              | Method Not Allowed - Ensure the URI includes the id of the reservation you're trying to update |

DELETE - Deleting a reservation

| HTTP Status Code | Reason                                                                                         |
|------------------|------------------------------------------------------------------------------------------------|
| 204              | No Content - Deletes reservation                                                               |
| 403              | Forbidden - Ensure you're logged in                                                            |

### Room Type API

| If you want to...        | Use this method...  | And this URI...                       |
|--------------------------|---------------------|---------------------------------------|
| Create a room types      | POST                | http://localhost:8080/room-types      |
| Read all room types      | GET                 | http://localhost:8080/room-types      |
| Read a room types by id  | GET                 | http://localhost:8080/room-types/{id} |
| Update a room types      | PUT                 | http://localhost:8080/room-types/{id} |

#### Parameters
The Room Type object has the following properties:
* **id**. Long. Number. The unique identifier for a record in the database. The id is auto-generated, so
  you do not need to include it when creating a new room type.
* **name**. String. The room name.
* **description**. String. The room type description.
* **rate**. BigDecimal. The cost of the room per night.
* **active**. Boolean. The status of the room: active or inactive.

Example JSON object to use when creating a room type (POST):

````
{
    "name": "King",
    "description": "Single king non-smoking",
    "rate": 129.99,
    "active": true
}
````

Example JSON object to use when updating a room type (PUT) - must include id property:

````
{
    "id": 1,
    "name": "King",
    "description": "Single king non-smoking",
    "rate": 129.99,
    "active": true
}
````

#### Response Messages

GET - Fetch all room types or fetch a room type by id

| HTTP Status Code | Reason                                                                                                                |
|------------------|-----------------------------------------------------------------------------------------------------------------------|
| 200              | OK - If no id provided, returns all room types. If id is provided, returns a single room type associated with that id |
| 404              | Not Found - The id for that room type does not exist                                                                  |
| 403              | Forbidden - Ensure you're logged in                                                                                   |

POST - Creating a room type

| HTTP Status Code | Reason                                                                                       |
|------------------|----------------------------------------------------------------------------------------------|
| 201              | Created - Returns created JSON object                                                        |
| 400              | Bad Request - Check your inputs for invalid entries                                          |
| 403              | Forbidden - Ensure you're logged in                                                          |
| 405              | Method Not Allowed - Ensure the URI includes the id of the room type you're trying to update |

PUT - Updating a room type

| HTTP Status Code | Reason                                                                                       |
|------------------|----------------------------------------------------------------------------------------------|
| 200              | OK - Returns updated JSON object                                                             |
| 400              | Bad Request - Check your inputs for invalid entries                                          |
| 403              | Forbidden - Ensure you're logged in                                                          |
| 405              | Method Not Allowed - Ensure the URI includes the id of the room type you're trying to update |
