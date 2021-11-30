# Patient Management System (UHN Assignment)

> This project is an assignment for a Technical Specialist position at University Health Network.

## Built With

- Major languages : Java 8
- Frameworks : Spring Boot (2.6.0)
- Technologies used : RESTful APIs, JUnit, Maven

## Getting Started
**Please download the zip file and run the server using IDE or the command below.**
```
java -jar YOUR_PATH/UHN_Assignment-main/patient-0.0.1-SNAPSHOT.jar
```

### Usage
**Please call the these endpoints below using Postman**

- *URL to get a list of all patients (GET)*
```
http://localhost:8080/api/patient/list
```
- *URL to search for a patient by first name (POST)*
```
http://localhost:8080/api/patient/list?searchString=Jewon7
```
- *URL to get a specific patient (GET)*
```
http://localhost:8080/api/patient/1
```
- *URL to add a new patient (POST)*
```
http://localhost:8080/api/patient/add/new?firstName=Jewon8&lastName=Moon8&dob=1918-03-30
```

## Authors

👤 **Jewon Moon**

- GitHub: [@jewon1988](https://github.com/jewon1988)
- LinkedIn: [jewon-moon](https://linkedin.com/in/jewon-moon)
- e-mail: jewon1988@gmail.com

