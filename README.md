# AusPostTechTest
___________________________________________________________________________

## A simple Spring Application to store and retirve Suburbs and Postcodes

Deployed for your convienience

https://auspostfinal-env.eba-rqn8ti5f.ap-southeast-2.elasticbeanstalk.com/

REQUIRED FOR ALL ENDPOINTS DUE TO TIME CONSTRAINTS

username: user

password: password
__________________________________________________________________

## Endpoints

###### GET /

- Health check, should render "Hello World!"

(A health check ensures there is nothing that may interrupt the API from servicing incoming requests)

###### GET /addresses/suburbs?postcode={postcode}

http://auspostfinal-env.eba-rqn8ti5f.ap-southeast-2.elasticbeanstalk.com/addresses/suburbs?postcode=3055

- Returns suburb(s) which have the postcode used in the query

###### GET /addresses/postcodes?suburb={suburb}

http://auspostfinal-env.eba-rqn8ti5f.ap-southeast-2.elasticbeanstalk.com/addresses/postcodes?suburb=St+Kilda

- Returns the postcode of the suburb used in the query

###### POST /addresses

OPEN Postman

The URL for the POST request is 
http://auspostfinal-env.eba-rqn8ti5f.ap-southeast-2.elasticbeanstalk.com/addresses

Select Auth Tab

Choose Basic Auth Type from drop down

Fill in Username ("user") and Password ("password")

Choose Body tab

Select 'raw' from first drop down

Select 'JSON' from second drop down

Fill in body with JSON

```
{
    "postcode": 1234,
    "suburb": "Faketown"
}
```

Send query 
__________________________________________________________________

## Database Content

3073 Reservoir

3055 Brunswick South

3055 Brunswick West

3055 Moreland West

3160 Belgrave

3182 St Kilda

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

I deployed the project on Elastic Beanstalk using the deployment steps in this README. I then attempted to integrate into an AWS code pipeline (hence the yaml file) but was unable to complete this process in time.

I manually entered the data using the POST endpoint and the steps described in this README.

###### Tools/ Languages used used:

Eclipse

AWS Elastic Beanstalk

Java Spring

Postman

SQL

MySQL Workbench
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

If I had more time, more testing would be present in my application. I learned a lot about Spring Testing in the pocket of time I spent actioning the HealthCheckController tests. Unfortunately, I could not get the AddressController tests finishes in time

___________________________________________________________________

## How to deploy

Using the command ( ./mvnw clean install ) in the root folder will generate post-api-0.0.1-SNAPSHOT.jar under /target

This project uses JPA for data persistence and requires an SQL database - it is intended to be deployed within a web server environment on AWS's Elastic Beanstalk with the following directions:

On AWS Elastic Beanstalk, create a new web server environment, name is unimportant
    
Under 'Platform', set the platform to 'Java' (other default options are fine here)
    
When prompted for application code, provide the .jar file generated earlier
    
Select 'Configure More Options'
    
Under 'Software' select 'Edit'
    
Under 'Environment properties', add a new property SERVER_PORT with value 5000 and click 'Save'
    
Back on the environment configuration screen, under 'Database', select 'Edit'
    
The default settings that load should be for the default mysql engine
    
Add the username root and the password password and select 'Save' (as set in the project files src/resources/application.properties)
    
Back on the environment configuration screen, select 'Create environment'
    
Wait for environment to be created (can be 10-15+ minutes)
    
Open the created environment and select 'Configuration' on the left side of the screen
    
In the category 'Database', copy the Endpoint value including the port to clipboard
    
In the category 'Software', select 'Edit'
    
Under 'Environment properties', add the following properties:
    
        SPRING_DATASOURCE_URL with value jdbc:mysql://{endpoint value copied earlier}/ebdb

        SPRING_DATASOURCE_USERNAME with value root
        
        SPRING_DATASOURCE_PASSWORD with value password
        
        SPRING_JPA_DATABASE_PLATFORM with value org.hibernate.dialect.MySQL5Dialect
        
        SPRING_JPA_HIBERNATE_DDL_AUTO with value update
        
After all settings have been saved and configuration is complete, Elastic Beanstalk will take some time to update the environment (5-10minutes), after which your URL should be available and ready for use!
