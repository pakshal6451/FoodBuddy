# FoodBuddy
FoodBuddy is a Web-Application that is an all-in-one program for food tracking, expense management, and recipe ideas based on your grocery inventory. This project allows users to email other users when they run out of a shopping item. At the moment, the project only permits one person to join a single group. If the user want to join another group, he must first quit current one. Receipe Recommendation is a unique feature since we propose what you can cook based on your grocery inventory as well as the steps to make it. On the Spending Tracker, you can examine your individual spending in the group as well as the total spending of the group. FoodBuddy combines all of these functions under one roof. 

## Requirements:
 React js, Java & Maven 
 
# Dependencies -
Below dependencies are already mentioned in the code and if we have the required software then if you run the commands the dependencies will be auto installed.
## Frontend

    "@emotion/react": "^11.10.6"
    "@emotion/styled": "^11.10.6"
    "@mui/icons-material": "^5.11.11"
    "@mui/material": "^5.11.13"
    "@testing-library/jest-dom": "^5.16.5"
    "@testing-library/react": "^13.4.0"
    "@testing-library/user-event": "^13.5.0"
    "axios": "^1.3.3"
    "bootstrap": "^5.2.3"
    "http-proxy-middleware": "^2.0.6"
    "react": "^18.2.0"
    "react-bootstrap-validation": "^0.1.11"
    "react-datepicker": "^4.10.0"
    "react-dom": "^18.2.0"
    "react-redux": "^8.0.5"
    "react-router-bootstrap": "^0.26.2"
    "react-scripts": "5.0.1"
    "redux": "^4.2.1"
    "cors": "^2.8.5"
    "web-vitals": "^2.1.4"

  ## Backend

    spring-boot-starter-data-jpa
    spring-boot-starter-security
    spring-boot-starter-web
    mssql-jdbc
    mysql-connector-j
    spring-boot-starter-test
    spring-security-test
    lombok
    gson
    spring-boot-starter-mail
    javax.mail
    junit

# How to Build and Run -
## Frontend

### Manual Steps:
Open a terminal window and navigate to the project root directory.
   Run the command to install the required dependencies:
```npm i``` 
    Run the command to start the development server:
```npm start```
    
### Via Pipeline:
Go to the Gitlab repo and run the pipeline on main branch.
Run the build pipeline.

## Backend
### Manual:
Open the project in your preferred IDE.  Build the code using below command:
  ```mvn clean install```
     
#### Via Pipeline:
Once the Pipeline is triggered for the frontend and all steps are completed. 

## Deployment:
Once the pipeline we triggered in build step then follow the below steps:
Login to the VM using below command:
```ssh csci5308vm14@csci5308vm14.research.cs.dal.ca```

#### Kill any task if its already running on 3000 & 8080 ports using below commands:
```sudo kill -9 $(sudo lsof -t -i:8080)```
```sudo kill -9 $(sudo lsof -t -i:3000)```
#### Open in one instance of a terminal:
   Navigate to the frontend build directory using below command:
```cd /home/csci5308vm14/foodbuddy/frontend```
    Run the Node server using below command:
```serve -s build```
 #### Open in another instance of a terminal:
  Navigate to the backend build directory using below command:
```/home/csci5308vm14/foodbuddy/backend``` 
    Run the spring boot application using below command:
```java -jar foodBuddy-0.0.1-SNAPSHOT.jar```

## Use Cases:
## Modules Integrated-
1) Registration Module:
A new user can register with his information in this module. After enrolled, the user can access the application. 

2) Login Module:
The user can log in to this module using the credentials he registered with. We perform a rigorous authentication to determine whether or not the password is correct. 

3) Group Management Module:
Once a user logs in to this module. He has two options: form a group or join an existing one.
When a user starts a group, a new group code is generated for him to share with other members.
If he joins the group, he will require the group code. We only allow one group per user in this application.Hence, if someone is already a member of one group, he must quit to join another. 

4) Inventory Management Module:
Since the user is a member of the group, he can add his groceries and other items in this module, and those items will be saved in the database. Users can save information about purchased things, including name, price, expiration date, and quantity. One comprehensive feature we offer is the ability for customers to email one another when they run out of specific items.The item may be edited or even deleted by the user. 

5) Expense Tracker Module:
The user can track his own expenses as well as the expenses of the group in this module. 

6) Receipe Management Module:
This module provides the user with recipe suggestions depending on the inventory items. Together with the recipe suggestion, we also give the user with the instructions to make that recipe. 

## Document List & Location:
Location : group14/FoodBuddy_DevRole/Documents

#### Files:

##### API Documentation.docx
##### Contribution_statement.xlsx
##### DevRole.docx
##### Final_Presentation_Group14.pptx
##### Food_Buddy_Group_18.pptx
##### GROUP-14-MID-TERM REVIEW .pptx
##### SmellsDetails.xlsx



    

     
  
 

