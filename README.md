---

# Hibernate Layered POS System

This is a **Point of Sale (POS) system** built using **Hibernate** as the ORM framework. The project follows a layered architecture to maintain separation of concerns and improve code maintainability. This system provides basic functionality for managing sales, products, customers, and transactions.

## Features

- **CRUD operations** for managing products, customers, and orders.
- **Hibernate ORM** for database interactions with support for transaction management.
- **Layered architecture** ensuring separation of concerns:
  - **UI Layer**: User interface components.
  - **Service Layer**: Business logic.
  - **DAO Layer**: Data access objects for interacting with the database.
- **MVC pattern** to keep code organized and scalable.
- **Database support**: Works with relational databases like MySQL or PostgreSQL.

## Technologies Used

- **Java**: Core programming language.
- **Hibernate**: ORM for managing database entities.
- **MySQL/PostgreSQL**: For database operations.
- **JavaFX**: Used for the user interface.
- **Maven**: For dependency management.
- **JUnit**: For unit testing.
  
## Project Structure

- **src/main/java**: Contains all Java source code.
  - **com.example.controller**: Handles UI interactions.
  - **com.example.service**: Business logic layer.
  - **com.example.dao**: Data Access Layer (DAO) for database operations.
  - **com.example.entity**: Hibernate entities mapped to database tables.
  - **com.example.util**: Utility classes for managing Hibernate sessions and database configurations.
  
- **src/main/resources**: Contains application configuration files (e.g., `hibernate.cfg.xml`).

## Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ChamathDilshanC/Hybernate-Layered-POS.git
   cd Hybernate-Layered-POS
   ```

2. Import the project into your preferred Java IDE (e.g., IntelliJ, Eclipse).

3. Configure the database connection in the `hibernate.cfg.xml` file under `src/main/resources`. Update the database URL, username, and password.

4. Run the Maven build to resolve dependencies:
   ```bash
   mvn clean install
   ```

5. Execute the application through your IDE or via command line:
   ```bash
   mvn exec:java
   ```

## Database Schema

Ensure you have a MySQL or PostgreSQL database set up with the following schema:

- **Product**: Stores product details (ID, name, price, quantity).
- **Customer**: Stores customer information (ID, name, contact).
- **Order**: Stores order details (ID, customerID, totalAmount).
- **OrderItem**: Stores the relationship between orders and products (orderID, productID, quantity).

## Contributions

Contributions are welcome! Feel free to fork the project and submit pull requests. For major changes, please open an issue to discuss what you'd like to change.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This should be a comprehensive starting point for your project. Let me know if you'd like any further customizations!
