# Online Bookshop System 📚🛒

Welcome to the Online Bookshop System! This system is designed to provide a seamless experience for users to explore, search, and order books online. Whether you are a customer looking for your favorite author's latest release or an administrator managing publishers and stock levels, this system has you covered.

## Table of Contents
1. [Business Requirements](#business-requirements)
2. [Entities and Relationships](#entities-and-relationships)
3. [Minimum Viable Product (MVP) Features](#minimum-viable-product-mvp-features)
4. [Getting Started](#getting-started)
5. [API Documentation](#api-documentation)

## Business Requirements 📋
### Search Books 🔍
   - **1. Author-based Book Search:** Users can search for books based on the author's name.
   - **2. Title-based Book Search:** Users can find books containing specific words in their title.
   - **3. Genre-based Book Search:** Users can explore books based on their genre.
   - **4. Publisher-based Book Search:** Users can find all books published by a specific publisher.
   - **5. Critical Quantity Book Search:** Users can identify books with stock quantities below a critical threshold.

### Order Management 🛍️
   - **6. Place an Order:** Users can place orders for selected books.
   - **7. View Order History:** Users can access their order history, including order date, book titles, and quantities.

### Admin Features 👩‍💼
   - **8. Automatic Stock Quantity Update:** The system automatically updates stock quantity after an order is placed.
   - **9. Admin Publisher Monitoring:** Administrators can view all publishers operating in a specific city.

### User Account Management 👤
   - **10. User Account Deletion:** Users can delete their accounts from the system.

## Entities and Relationships 🤝

### Entities 🏛️

- **Publisher:** Represents entities responsible for publishing books.
- **Author:** Represents creative minds behind the books.
- **Book:** Central entity encapsulating essential book details.
- **Address:** Captures geographical details associated with users.
- **User:** Enables personalized experiences for customers.
- **Order:** Facilitates the order process with user details and ordered books.

### Relationships 🌐

- **Address - User (One-to-One)**
   - An address is associated with only one user.
   - A user has only one address.

- **Publisher-Book (One-to-Many)**
   - A publisher can publish multiple books.
   - A book is published by only one publisher.

- **Author - Book (Many-to-Many)**
   - An author can write multiple books.
   - A book can have multiple authors.

- **User - Order (One-to-Many)**
   - A user can have multiple orders.
   - Each order belongs to only one user.

- **Book - Order (Many-to-Many)**
   - A book can be ordered multiple times.
   - An order can contain multiple books.


## Minimum Viable Product (MVP) Features 💼
1. **User Book Search Endpoint:** REST endpoint for users to search books by author.
2. **Title-based Book Search Endpoint:** REST endpoint for users to find books containing specific words in their title.
3. **Genre-based Book Search Endpoint:** REST endpoint for users to find books by genre.
4. **Publisher-based Book Search Endpoint:** REST endpoint for users to find all books published by a specific publisher.
5. **Order Placement Endpoint:** REST endpoint for users to place orders for books.

## Getting Started 🚀
Follow these steps to get the Online Bookshop System up and running locally:

### Prerequisites 🛠️
- Java Development Kit (JDK) installed on your machine.
- Maven installed on your machine.
- Git installed on your machine.

### Database Configuration 🗃️
Make sure to configure your database settings in the `application.properties` file before running the application.

## API Documentation 📖
The API documentation can be accessed at http://localhost:8080/bookshop once the application is running.
