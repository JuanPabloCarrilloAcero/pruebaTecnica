Coding Challenge - README

This backend application is built using Spring Boot framework and incorporates
various components like config, controller, DTO, filter, model, repository,
service, and util to implement the specified functionalities.
Below is an overview of the features and structure of the application:

Features:

Empresa (Company) View:

    Capture information such as NIT (primary key), company name, address,
    and phone number.

Productos (Products) View:

    Capture information including product code, name, characteristics, 
    prices in multiple currencies, and associated company.

Inicio de Sesión (Login) View:

    Capture user information for login, including user and password.

Inventario (Inventory) View:

    Form to download a PDF containing inventory information.
    Utilizes AWS API to send the PDF to a desired email address.

User Management:

    Two types of users: Administrator and Externo (External).
    Administrator has access to CRUD operations for Empresa, and can register products by company.
    External users can view companies as visitors.

Database Design:

    Entity-relationship model includes entities such as Empresa,
    Productos, Categorías (Categories), Clientes (Customers),
    and Ordenes (Orders).
    
    Requirements like multiple categories per product,
    multiple orders per customer, and multiple products per order are fulfilled.

Security:

    Passwords are encrypted for authentication of Administrator users.

Deployment:

    The application is hosted on an AWS cloud server.

Project Structure:
    
    config: Contains configuration files.

    controller: Implements endpoints to handle HTTP requests.

    DTO: Data Transfer Objects for data exchange between layers.

    filter: Implementation of filters for intercepting requests.

    model: Defines entities for the database.

    repository: Contains repository interfaces for database operations.

    service: Business logic implementation.
    
    util: Utility classes and functions.

Technologies Used:
    
    Spring Boot: Provides a robust framework for building and deploying Java applications.
    
    AWS: Utilized for cloud hosting and integration with AWS API for PDF sending.

    Database: TBD (You should specify the database you used, considering requirements and compatibility with Spring Boot).

Setup Instructions:

    Clone this repository.

    Configure your database connection in application.properties.

    Build and run the application.

    Login as an Administrator with the credentials:
        - User: admin
        - Password: testAdmin

    or as an External user with the credentials:
        - User: external
        - Password: testExteral

    Access the endpoints based on the provided routes.

Deployment:
    
    The application is deployed on an AWS cloud server. Ensure proper configurations are set for deployment, including AWS credentials and server setup.

Developer:

    Juan Pablo Carrillo Acero

Feel free to reach out for any questions 
or clarifications regarding the application setup 
or functionality.






