<div align="center">
  
  <img src="https://github.com/BANAAG-KYLE/FindIt/blob/main/Images/lost_and_found_logo.png" alt="FINDIT Logo" width="400"/>
  
  <h1>FINDIT: Lost and Found Management System</h1>
  
  <p><em>Lost Today, Found Tomorrow.</em></p>

  [![Java Version](https://img.shields.io/badge/java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
</div>

## Project Overview

FINDIT is a Lost and Found System designed to simplify the process of reporting, tracking, and recovering lost items. Built using Java programming techniques, the system provides a user-friendly platform that helps individuals and organizations efficiently manage lost belongings. By creating a streamlined, secure, and accessible solution, FINDIT transforms the traditional lost and found experience. The system enables easy item reporting, ownership verification, and item recovery, while supporting sustainability by reducing unnecessary item replacements and promoting community cooperation. More than just a technical tool, FINDIT represents an innovative approach to solving a common everyday challenge, making lost item management simple, transparent, and effective.

## Object-Oriented Programming Concepts

The system demonstrates the core principles of Object-Oriented Programming through thoughtful design and implementation:

### 1. Abstraction

Abstraction is a fundamental OOP concept that focuses on hiding complex implementation details while exposing only the essential features of an object. In the FINDIT system, abstraction is prominently demonstrated through the `BaseItem` abstract class and various manager classes.

The `BaseItem` abstract class is a prime example of abstraction. It defines a blueprint for items with common properties like `itemId`, `description`, `location`, and `foundDate`, while declaring an abstract method `getItemDetails()`. This abstract class provides a simplified, generalized view of an item, concealing the intricate details of how specific item types might be implemented.

For instance, the abstract method `getItemDetails()` requires subclasses to provide their own implementation. In the `Item` class, this method returns a formatted string with all relevant item information, effectively hiding the complex string formatting logic from the users of the class. This allows different types of items to have their own unique representations while maintaining a consistent interface.

Another example of abstraction is seen in the `ItemManager` and `ClaimantManager` classes. These classes abstract away the complexities of data storage, retrieval, and management. Methods like `generateItemId()`, `findUnclaimedItemById()`, and `calculateSimilarity()` provide high-level operations without exposing the underlying data structures and algorithms.

### 2. Inheritance

The system showcases inheritance through the `BaseItem` abstract class and `Item` class. The `BaseItem` class defines a foundational structure for items, providing common attributes like `itemId`, `description`, `location`, and `foundDate`. The `Item` class extends this base class, inheriting these core properties while adding specific functionality such as category and claim status.

### 3. Encapsulation

Encapsulation is implemented through carefully controlled access to class attributes. In the `Item` and `Claimant` classes, private fields are protected from direct external modification. Access is provided through carefully designed getter and setter methods.

### 4. Polymorphism

Polymorphism is evident in the abstract `getItemDetails()` method of the `BaseItem` class. Each item type can provide its unique implementation of this method, showcasing how different classes can implement the same method signature with unique behaviors.

## Libraries and Technology Stack

The system leverages several Java libraries and features to enhance functionality:
- `java.time.LocalDate` for precise date handling
- `java.util.Random` for generating unique identifiers
- `java.security.MessageDigest` for secure password hashing
- `java.util.stream` for efficient data processing
- `java.util.Scanner` for user interaction

## Sustainable Development Goals Integration

### SDG 11: Sustainable Cities and Communities
<div align="center"> <img src="https://github.com/BANAAG-KYLE/FindIt/blob/main/Images/SDG-11_banner.png" alt="SDG 11 Banner" width="800"/> </div>

FINDIT contributes to sustainable urban management by facilitating item recovery and reuse. The system reduces waste by providing an efficient mechanism for reuniting lost items with their owners, thereby minimizing unnecessary product replacements and promoting a circular economy approach.

### SDG 16: Peace, Justice and Strong Institutions
<div align="center"> <img src="https://github.com/BANAAG-KYLE/FindIt/blob/main/Images/SGD-16_banner.png" alt="SDG 16 Banner" width="800"/> </div>
The application supports institutional integrity through its transparent and accountable item tracking and claim processes. By implementing secure data management and providing equal access to item recovery services, FINDIT embodies principles of fairness and institutional accountability.

## Installation and Usage

### Prerequisites
- Java 17 or higher
- Command-line interface or terminal

### Quick Start
1. Ensure Java is installed on your system
2. Clone the repository
3. Compile the Java files
4. Run the application

```bash
# Compile
javac *.java

# Run
java LostAndFoundSystem
```

### User Guide

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_MENU.png" alt="FINDIT Logo" width="400"/>

## 1. Report a Lost Item

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_REPORT.png" alt="FINDIT Logo" width="400"/>

When you've found a lost item, use this option to officially log it in the system:
- Enter a detailed item description (minimum 3 words)
- Specify the location where the item was found
- Input the exact date of finding the item (YYYY-MM-DD format)
- Select a category: Electronics, Clothing, Accessories, or Others
- The system will generate a unique Item ID for tracking

## 2. Verify Item Ownership

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_VERIFY_TABLE.png" alt="FINDIT Logo" width="400"/>

This feature helps you claim your lost item securely:
- Search for your item using a descriptive search term
- Browse through matching unclaimed items displayed in a table
  

- Select the item by its ID
- Provide your full name and contact information
- Describe the item in detail to prove ownership
  
  <img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_FAILED.png" alt="FINDIT Logo" width="400"/>
  
- The system checks description similarity (60% match required)
  
  <img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_VERIFIED.png" alt="FINDIT Logo" width="400"/>
  
- If verified, you'll receive a unique claim code

## 3. Claim an Item

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_CLAIM.png" alt="FINDIT Logo" width="400"/>

Use this option when you have a claim code:
- Enter the claim code generated during ownership verification
- If the code is valid, the item will be marked as claimed
- You'll receive confirmation of successful item claim

## 4. View All Items

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_VIEW_ALL.png" alt="FINDIT Logo" width="400"/>

Quickly see all currently unclaimed items:
- Displays a table of items with their ID, location, found date, and category
- Helps you browse recently found items

## 5. Admin Settings

<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_ADMIN_MENU.png" alt="FINDIT Logo" width="400"/>

Secure administrative access with features:
- Requires admin password for entry
  
<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_ADMIN_SEARCH.png" alt="FINDIT Logo" width="400"/>

- Search claimed items by ID
- View all claimed items with details
  
<img src="https://github.com/BANAAG-KYLE/FindIt_Java/blob/main/Images/OOP_ADMIN_ARCHIVE.png" alt="FINDIT Logo" width="400"/>

- Archive old claims (items unclaimed after 30 days)

## 6. Exit System
Safely close the Lost and Found application

### Admin Access
- Default admin password: `admin123`
- Provides advanced management and tracking capabilities

---

Developed with passion for Object-Oriented Programming and sustainable technological solutions.

© 2024 FindIt Java Edition. All rights reserved.
