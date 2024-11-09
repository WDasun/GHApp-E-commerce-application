# GHApp-E-commerce-application
Welcome to Glimmer Heaven, an e-commerce platform designed to provide a smooth and secure shopping experience. This application allows customers to browse products, place orders, and manage accounts with ease.
## Features
* Customer registration and login
* Product browsing with search and filter options
* Session Card / DB Cart
* Wish-list
* Customer reviews and ratings
* Checkout
* Password Recovery
* Admin dashboard
    - Employee registration
    - Manageengine system user
    - Privilege management for Roles 
    - Shop Manage
## Technologies Used
* Frontend: HTML, CSS, Bootstrap 5, JavaScript
* Backend: Java (Servlets, Hibernate)
* Database: MySQL
* Report Generation: Tibco Jaspersoft Studio

## Design Patterns Used
### 1. MVC 
    * Servlets act as controllers, processing client requests and directing responses. Hibernate manages the data model, and the view layer is constructed with HTML, CSS, JavaScript, and Bootstrap.
### 2. DAO
    * DAOs are used to perform CRUD operations on entities like customers, products, orders, and reviews, utilizing Hibernate to manage database access.
### 3. Singleton
    * Used in database connection management.
