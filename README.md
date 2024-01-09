# Online Bookshop System

Welcome to the Online Bookshop System! This system is designed to provide a seamless experience for users to explore, search, and order books online. Whether you are a customer looking for your favorite author's latest release or an administrator managing publishers and stock levels, this system has you covered.

## Table of Contents
1. [Business Requirements](#business-requirements)
2. [Entities and Relationships](#entities-and-relationships)
3. [Minimum Viable Product (MVP) Features](#minimum-viable-product-mvp-features)
4. [Getting Started](#getting-started)
5. [API Documentation](#api-documentation)

## Business Requirements
### 1. Search Books
   - **Author-based Book Search:** Users can search for books based on the author's name.
   - **Title-based Book Search:** Users can find books containing specific words in their title.
   - **Genre-based Book Search:** Users can explore books based on their genre.
   - **Publisher-based Book Search:** Users can find all books published by a specific publisher.
   - **Critical Quantity Book Search:** Users can identify books with stock quantities below a critical threshold.

### 2. Order Management
   - **Place an Order:** Users can place orders for selected books.
   - **View Order History:** Users can access their order history, including order date, book titles, and quantities.

### 3. Admin Features
   - **Automatic Stock Quantity Update:** The system automatically updates stock quantity after an order is placed.
   - **Admin Publisher Monitoring:** Administrators can view all publishers operating in a specific city.

### 4. User Account Management
   - **User Account Deletion:** Users can delete their accounts from the system.

## Entities and Relationships
- **Publisher:** Represents entities responsible for publishing books.
- **Author:** Represents creative minds behind the books.
- **Book:** Central entity encapsulating essential book details.
- **Address:** Captures geographical details associated with users.
- **User:** Enables personalized experiences for customers.
- **Order:** Facilitates the order process with user details and ordered books.

## Minimum Viable Product (MVP) Features
1. **User Book Search Endpoint:** REST endpoint for users to search books by author.
2. **Title-based Book Search Endpoint:** REST endpoint for users to find books containing specific words in their title.
3. **Genre-based Book Search Endpoint:** REST endpoint for users to find books by genre.
4. **Publisher-based Book Search Endpoint:** REST endpoint for users to find all books published by a specific publisher.
5. **Order Placement Endpoint:** REST endpoint for users to place orders for books.

## Getting Started
Follow these steps to get the Online Bookshop System up and running locally:

### Prerequisites
- Java Development Kit (JDK) installed on your machine.
- Maven installed on your machine.
- Git installed on your machine.

### Database Configuration
Make sure to configure your database settings in the application.properties file before running the application.

## API Documentation
The API documentation can be accessed at http://localhost:8080/bookshop once the application is running.
