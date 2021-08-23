# user-test-app
An exercise to produce an API which calls test APIs 
and returns people who are listed as either living in London,
or whose current coordinates are within 50 miles of London.

### The service:
- fetches London users from the /city/London/users API
- fetches all-users from the /user API
- performs fetches in parallel, to minimise delay
- filters the all-users list, using the Haversine formula to identify if they are located within 50 miles of London.
- Returns a combined list of London users with the filtered list of all users.

# Configuration
The Target location can be configured in the application.properties file.
- target.latitude and target.longitude are preset to the City of London
- target.tolerance.miles is preset to 50 miles.
- allusers.url is configured to the test url. This may need to changed to a production environment.
- cityusers.url is preset to fetch London users from the test url. This may need to changed to a production environment.

# Build and Test:
> mvn clean package

# Run:
> java -jar ./target/user-test-app-0.0.1-SNAPSHOT.jar


# Swagger documents at:
http://localhost:8080/swagger-ui.html
