# LibraryManagement (Java + Swing + MySQL + NetBeans)

**Author:** Stacy  
**Admission:** 189257

---

## ✨ Features
- **Login system** connected to MySQL (`users` table).  
- **Library management** with CRUD operations:  
  - View books in a JTable.  
  - Add new books.  
  - Delete selected books.  
- **OOP principles covered**:
  - **Encapsulation**: private fields + getters/setters (`User`, `LibraryItem`).  
  - **Abstraction**: `LibraryItem` is abstract with abstract method `getInfo()`.  
  - **Inheritance**: `Book`, `Magazine` extend `LibraryItem`.  
  - **Polymorphism**: `getInfo()` overridden in subclasses.  
- **Objects communicating**: GUI ↔ `DatabaseConnection` ↔ `LibraryItem` subclasses.  
- Runs in **NetBeans** with **MySQL (XAMPP)** backend.  

---

## ⚙️ Setup Instructions

### 1. Database (using XAMPP)
1. Start **Apache** and **MySQL** in XAMPP Control Panel.  
2. Open phpMyAdmin: http://localhost/phpmyadmin  
3. Create database `librarydb`.  
4. Import `librarydb.sql` from the project folder.

Default login:
```
Username: admin
Password: 1234
```

---

### 2. NetBeans
1. Open **NetBeans** → Create a new **Java Application** project → name it `LibraryManagement`.  
2. Copy `src/librarymanagement` into your project's `src/`.  
3. Add MySQL JDBC driver (`mysql-connector-j-<version>.jar`) to Project Libraries.

---

### 3. Run
1. Run `librarymanagement.LoginFrame`.  
2. Login with admin/1234.  
3. Use Dashboard to Refresh/Add/Delete books.

---

## 📂 Project Structure
```
LibraryManagement/
 ├─ src/librarymanagement/
 │    ├─ LibraryItem.java
 │    ├─ Book.java
 │    ├─ Magazine.java
 │    ├─ LibraryManager.java
 │    ├─ User.java
 │    ├─ DatabaseConnection.java
 │    ├─ LoginFrame.java
 │    ├─ MainDashboard.java
 │    └─ AddBookDialog.java
 ├─ librarydb.sql
 ├─ lib/
 └─ README.md
```

---

## Notes
- Java 8+ recommended.
- If your MySQL uses different user/password, update `DatabaseConnection.java`.
- This project is structured to mirror a NetBeans-ready setup similar to the FleetManagement project you received earlier.
