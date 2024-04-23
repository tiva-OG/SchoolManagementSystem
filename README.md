# School Management System

### Overview:

This project is a simple School Management System aimed at course management for 
streamlining administrative tasks within a school environment. It provides functionalities
for managing students, teachers, and courses.

### Features:

1. **Student Management:**
   - Add, edit, and delete student records.
   - View student details including name, level, and contact information.

2. **Teacher Management:**
   - Add, edit, and delete teacher records.
   - View teacher details including name, subject in-charge, and contact information.

3. **Course Management:**
   - Add, edit, and delete course information.
   - Assign teachers to courses.
   - View course details including course name, description, assigned teacher, and enrolled students.


### Installation:

1. Clone the repository:

   ```
   git clone https://github.com/yourusername/school-management-system.git
   ```

2. Set up the PostgreSQL database:
   - Install PostgreSQL if you haven't already.
   - Create a new database named `school_db`.
   - Execute the SQL scripts provided in the `resources` folder to create the necessary tables.

3. Open the project in your favorite Java IDE.

4. Configure the database connection:
   - Open the `Main.java` file.
   - Update the database connection URL, username, and password according to your PostgreSQL setup.

5. Compile and run the application.

### Usage:

1. Upon launching the application, you will be presented with a menu of options.
2. Use the menu to navigate through different functionalities such as managing students, teachers, and courses.
3. Follow the prompts to perform actions like adding, editing, or deleting records.
4. Use the appropriate commands to interact with the database and retrieve information.
