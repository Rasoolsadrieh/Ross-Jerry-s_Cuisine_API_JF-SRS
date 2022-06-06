# **Project 1:** My Restaurant Application - **_DUE: 06/03/22_**

## Description

My Restaurant app was developed for a small restaurant that is looking to help streamline the ordering process for customers to help reduce costs. Customers can enjoy an seemless means to order directly with an account of their own at the restaurant in question, where Admins are capable of adding and updating the menu items. This RESTful application leverages a tomcat server to handle incoming requests to thoroughly tested services that must be persisted using Hibernate and Azure SQL services. This project includes a frontend that will be developed using ReactJS to handle user requests.

## ERD

![](img/p1_erd.png)

# User Stories

## As A: Admin

-   [ ] Add items to the menu
-   [ ] Update items to the menu
-   [ ] Delete items to the menu

## As A: Customer

-   [ ] View all items on the menu without needing to Register or Login
-   [ ] Register/Update/Delete an account
-   [ ] Add/Update/Delete a credit card to be saved to my account
-   [ ] Make an order for a specific menu item
-   [ ] Add a comment to the order to request a change, if it is substitutable
-   [ ] Favorite an order
-   [ ] View past orders by date
-   [ ] Pay off your balance with your credit card

## Optional User Stories for Customer:

-   [ ] Quickly reorder all orders on a specific date
-   [ ] Pay for a friends order
-   [ ] Filter menu by protein

## Required Challenge:

In this project, you're presented with the challenge to learn and implement the use of Hibernate an ORM for java. Below are resources to help with understanding how to leverage hibernate in your project. Please talk with your partner and research as soon as possible. Make sure to ask questions and leverage any tool you can.

-   [What is an ORM and Why You Should Use It](https://blog.bitsrc.io/what-is-an-orm-and-why-you-should-use-it-b2b6f75f5e2a)

-   [Hibernate Documentation](https://hibernate.org/orm/documentation/5.4/)

-   [JPA and Hibernate](https://www.baeldung.com/jpql-hql-criteria-query)

-   [Tutorialspoint Hibernate](https://www.tutorialspoint.com/hibernate/index.htm)

-   [Hibernate Session Factory](https://www.java2novice.com/hibernate/session-factory/)

## Optional Challenges:

-   [ ] Utilization of JWT for user authentication
-   [ ] Session-based caching to minimize calls to the database for already retrieved data
-   [ ] Implementation of Redux Toolkit on the frontend

## Tech Stack

-   [ ] Java 8
-   [ ] JUnit
-   [ ] Mockito
-   [ ] Apache Maven
-   [ ] Hibernate
-   [ ] Jackson library (for JSON marshalling/unmarshalling)
-   [ ] Java EE Servlet API (v4.0+)
-   [ ] Azure SQL
-   [ ] ReactJS Frontend
-   [ ] Git SCM (on GitHub)
-   [ ] Azure WebApps (Potentially)

## Functional Requirements

-   [ ] CRUD operations are supported for one or more domain objects via the web application's exposed endpoints
-   [ ] JDBC logic is abstracted away by hibernate
-   [ ] Programmatic persistence of entities (basic CRUD support) using hibernate

## Non-Functional Requirements

-   [ ] Daily Commits to Github
-   [ ] Branching strategies implemented
-   [ ] 80% line coverage of all service layer classes
-   [ ] Mocking for unit testing
-   [ ] **_RECOMMENDATION!_** Use Trello or some kanban board to keep track of target goals. You can include your trainer on these.

## Init Instructions

-   Create another new repostory within this organization for your API with the `nameOfRestaurant_API_teamMemberInitials`
-   Create another new repostory within this organization for your Frontend with the `nameOfRestaurant_FE_teamMemberInitials`
-   A README.md file in your repo describing the goal of this wedding planner, how to use it(the url endpoint calls), technologies used (with versions), ERD, how to initialize it, features and contributers. Optional: Include potential future goals of the project

## Presentation

-   Finalized version of the web application must be pushed to personal repository within this organization by the presentation date (June 3rd, 2022)
-   10 minute live demonstration of the web application; demonstration will be performed using your ReactJS FrontEnd to query your API's endpoints
