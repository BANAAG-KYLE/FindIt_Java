<div align="center">
  
  <img src="https://github.com/BANAAG-KYLE/FindIt/blob/main/Images/lost_and_found_logo.png" alt="FINDIT Logo" width="400"/>
  
  <h1>FINDIT: Lost and Found Management System</h1>
  
  <p><em>Lost Today, Found Tomorrow.</em></p>

  [![Java Version](https://img.shields.io/badge/java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
</div>

# Table of Contents
I. [Project Overview](#project-overview)

II. [Java Concepts](#java-concepts)

III. [SDG Integration](#sdg-integration)

IV. [Installation and Usage](#installation-and-usage)

# Project Overview

## About FINDIT
FINDIT is a comprehensive Lost and Found Management System developed in Java, designed to revolutionize how institutions handle lost and found items. Built with modern Java programming techniques, FINDIT offers a secure, efficient, and user-friendly console-based platform for reporting, tracking, and claiming lost items.


# Java Concepts

### 1. **Object-Oriented Programming**
Object-Oriented Programming (OOP) is the cornerstone of FINDIT's design, enabling a modular, scalable, and reusable codebase:

- **Inheritance**: 
  - Abstract `BaseItem` class serves as a base for `Item` class
  - Provides a common structure for all item-related operations
  - Allows for future expansion of item types

- **Encapsulation**: 
  - Private fields in `Item` and `Claimant` classes
  - Controlled access through getter and setter methods
  - Protects internal data from unauthorized modifications

- **Interfaces**: 
  - `ItemStatus` and `Categorizable` interfaces define contract-based programming
  - Enables flexible implementation of item-related behaviors
  - Supports future extensibility

- **Polymorphism**: 
  - Abstract `getItemDetails()` method in `BaseItem`
  - Allows different implementations for various item types
  - Demonstrates runtime polymorphic behavior

---

## Libraries and Dependencies
```
java.time.LocalDate          # Date handling
java.util.Random             # ID generation
java.security.MessageDigest  # Secure hashing
java.util.stream             # Data processing
java.util.Scanner            # User input
```

# SDG Integration

## SDG 11: Sustainable Cities and Communities
The system promotes resource efficiency by:
- Facilitating item recovery and reuse
- Reducing waste through effective lost and found management
- Encouraging community collaboration
- Minimizing unnecessary product replacement

## SDG 16: Peace, Justice and Strong Institutions
FINDIT supports institutional integrity through:
- Transparent item tracking
- Fair and accountable claim processes
- Secure data management
- Equal access to item recovery services

# Installation and Usage

## Prerequisites
- Java 17 or higher
- Command-line interface/terminal

## Installation Steps
1. Ensure Java is installed
2. Clone the repository
3. Compile the Java files
4. Run the application

```bash
# Compile
javac *.java

# Run
java LostAndFoundSystem
```

## User Guide

### Main Menu Options
1. Report a lost item
2. Verify item ownership
3. Claim item
4. View all items
5. Admin settings
6. Exit

### Admin Access
- Default admin password: `admin123`

---

<div align="center">
  <p>Developed with ❤️ for Object Oriented Programming</p>
  <p>© 2024 FindIt Java Edition. All rights reserved.</p>
</div>
