# AusPostTechTest

We want you to implement:

•An API that allows mobile clients to retrieve the suburb information 
by postcode.

•An API that allows mobile clients to retrieve a postcode given a 
suburb name

•A secured API to add new suburb and postcode combinations (you'll 
have to work out how this should work)

•Some form of persistence
__________________________________________________________________

REQUIRED FOR ALL ENDPOINTS DUE TO TIME CONSTRAINTS

username: user

password: password


__________________________________________________________________

##Endpoints

GET /addresses/suburbs?postcode={postcode}

- Returns suburb(s) which have the postcode used in the query

GET /addresses/postcodes?suburb={suburb}

- Returns the postcode of the suburb used in the query

POST /addresses
__________________________________________________________________

## Approach

(For more detailed information, please see attached design doc)

I initialised a Spring Application with several dependencies and used Eclipse as my IDE.

First, I created a HealthCheckController to render "Hello World!" on successful app launch (endpoint: "/")

I then built a repository layer which extends Java Persistance API.

I added a few items to the DB in Postman. Then, using MySQL Workbench, I generated SQL queries which fetched...
- POSTCODE by SUBURB
- SUBURB(S) by POSTCODE

I added these queries to the repository layer and then built out my service and controller layer for these requests. They successfully rendered in both the Browser & Postman

I then worked to make the POST/addresses endpoint require authentication. As I was new to security and auth in Spring, and that the recent update had deprecated some common security config, I struggled to find a solution that I would be comfortable with. As such, I have some copied and pasted code in my SecurityConfig, with the link to it's origin commented out in the file. I then tried to execute a workaround so that some end points remained public. Testing in Postman and the Browser gave me evidence that my solution met the MVP brief that the POST endpoint should be se secure.

I then used some remaining time to work on unit testing and integration testing for my Health Check Controller. I had mentioned to Kalyani that JUnit testing was not something I had a lot of experience in, so I was sure to show some hearty attempts.
__________________________________________________________________

## If I had more time...

###### Structuring Data

It took me a too much time to determine the path of least reisistance in building this project. I was at first building a one to many schema (one postcode to many suburbs). I then settled on a one to one framework easily executed by an SQL query.

###### Security

My username ("user") and password ("password") for the project are accessible in the source code (and now in the README.md!!!). This is of course a huge no-no in the production world. I was acutely aware of this, having spent time in past workplaces using env files for APIKeys. Ideally, these secrets would be stored in a centralised and MFA secure DevOps platform.

It's also unfortunate that my workaround was unsuccessful. This means all endpoints require auth rather than just the one.

###### Types/ Generics

I kept it simple by having Postcodes as Integers and Suburbs as Strings. In production, I would expect to see very little generics used.

###### Try/ Catch & Exceptions/ Error Handling

There is very limited error handling within my application. Though I have used tags like @Valid to ensure payloads are correct, a line of inquiry for myself would be to understand exactly what Spring annotations cover so that in the future, exceptions/ errors etc. are built appropriately for anticipated errors/ edge cases etc.

###### Testing

If I had more time, more testing would be present in my application. I learned a lot about Spring Testing in the pocket of time I spent actioning the HealthCheckController tests.
