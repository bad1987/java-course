/**
 * ObjectRelationshipsDemo.java
 * This program demonstrates different types of relationships between objects in Java.
 */
public class ObjectRelationshipsDemo {
    public static void main(String[] args) {
        System.out.println("--- Association Example ---");
        
        // Create Student and Course objects
        StudentClass student1 = new StudentClass("Alice");
        StudentClass student2 = new StudentClass("Bob");
        
        CourseClass course1 = new CourseClass("Java Programming");
        CourseClass course2 = new CourseClass("Database Systems");
        
        // Associate students with courses
        student1.enrollInCourse(course1);
        student1.enrollInCourse(course2);
        student2.enrollInCourse(course1);
        
        // Display enrollments
        student1.displayCourses();
        student2.displayCourses();
        
        course1.displayStudents();
        course2.displayStudents();
        
        System.out.println("\n--- Aggregation Example ---");
        
        // Create Department and Employee objects
        Employee emp1 = new Employee("John", "Developer");
        Employee emp2 = new Employee("Sarah", "Designer");
        Employee emp3 = new Employee("Mike", "Manager");
        
        // Create a department and add employees
        Department dept = new Department("Engineering");
        dept.addEmployee(emp1);
        dept.addEmployee(emp2);
        dept.addEmployee(emp3);
        
        // Display department info
        dept.displayInfo();
        
        // Remove an employee
        dept.removeEmployee(emp2);
        System.out.println("\nAfter removing an employee:");
        dept.displayInfo();
        
        // The employee still exists even if removed from the department
        System.out.println("\nEmployee still exists:");
        System.out.println(emp2.getName() + " - " + emp2.getPosition());
        
        System.out.println("\n--- Composition Example ---");
        
        // Create a Car object which creates an Engine object
        CarWithEngine car = new CarWithEngine("Toyota", "Corolla");
        
        // Display car info
        car.displayInfo();
        car.start();
        car.accelerate();
        car.stop();
        
        System.out.println("\n--- Complex Relationships Example ---");
        
        // Create a university system with multiple relationships
        University university = new University("Tech University");
        
        // Create departments
        AcademicDepartment csDept = new AcademicDepartment("Computer Science");
        AcademicDepartment mathDept = new AcademicDepartment("Mathematics");
        
        // Create professors
        Professor prof1 = new Professor("Dr. Smith", csDept);
        Professor prof2 = new Professor("Dr. Johnson", mathDept);
        
        // Create courses taught by professors
        CourseWithProfessor javaCourse = new CourseWithProfessor("Java Programming", prof1);
        CourseWithProfessor databaseCourse = new CourseWithProfessor("Database Systems", prof1);
        CourseWithProfessor calculusCourse = new CourseWithProfessor("Calculus", prof2);
        
        // Add courses to departments
        csDept.addCourse(javaCourse);
        csDept.addCourse(databaseCourse);
        mathDept.addCourse(calculusCourse);
        
        // Add departments to university
        university.addDepartment(csDept);
        university.addDepartment(mathDept);
        
        // Create students
        StudentWithCourses student3 = new StudentWithCourses("Emily", "CS123");
        StudentWithCourses student4 = new StudentWithCourses("David", "MATH456");
        
        // Enroll students in courses
        student3.enrollInCourse(javaCourse);
        student3.enrollInCourse(calculusCourse);
        student4.enrollInCourse(calculusCourse);
        student4.enrollInCourse(databaseCourse);
        
        // Display university information
        university.displayInfo();
    }
}

/**
 * Classes demonstrating Association relationship.
 * Students can enroll in multiple courses, and courses can have multiple students.
 * Both can exist independently.
 */
class StudentClass {
    private String name;
    private CourseClass[] courses;
    private int courseCount;
    private static final int MAX_COURSES = 5;
    
    public StudentClass(String name) {
        this.name = name;
        this.courses = new CourseClass[MAX_COURSES];
        this.courseCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void enrollInCourse(CourseClass course) {
        if (courseCount < MAX_COURSES) {
            courses[courseCount] = course;
            courseCount++;
            course.addStudent(this);
            System.out.println(name + " enrolled in " + course.getName());
        } else {
            System.out.println("Cannot enroll in more courses. Maximum limit reached.");
        }
    }
    
    public void displayCourses() {
        System.out.println("\n" + name + " is enrolled in the following courses:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("- " + courses[i].getName());
        }
    }
}

class CourseClass {
    private String name;
    private StudentClass[] students;
    private int studentCount;
    private static final int MAX_STUDENTS = 20;
    
    public CourseClass(String name) {
        this.name = name;
        this.students = new StudentClass[MAX_STUDENTS];
        this.studentCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void addStudent(StudentClass student) {
        if (studentCount < MAX_STUDENTS) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cannot add more students. Class is full.");
        }
    }
    
    public void displayStudents() {
        System.out.println("\nStudents enrolled in " + name + ":");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("- " + students[i].getName());
        }
    }
}

/**
 * Classes demonstrating Aggregation relationship.
 * A Department has Employees, but Employees can exist without a Department.
 */
class Employee {
    private String name;
    private String position;
    
    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }
}

class Department {
    private String name;
    private Employee[] employees;
    private int employeeCount;
    private static final int MAX_EMPLOYEES = 10;
    
    public Department(String name) {
        this.name = name;
        this.employees = new Employee[MAX_EMPLOYEES];
        this.employeeCount = 0;
    }
    
    public void addEmployee(Employee employee) {
        if (employeeCount < MAX_EMPLOYEES) {
            employees[employeeCount] = employee;
            employeeCount++;
            System.out.println(employee.getName() + " added to " + name + " department");
        } else {
            System.out.println("Cannot add more employees. Maximum limit reached.");
        }
    }
    
    public void removeEmployee(Employee employee) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i] == employee) {
                // Shift all elements after this one to fill the gap
                for (int j = i; j < employeeCount - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[employeeCount - 1] = null;
                employeeCount--;
                System.out.println(employee.getName() + " removed from " + name + " department");
                return;
            }
        }
        System.out.println("Employee not found in department.");
    }
    
    public void displayInfo() {
        System.out.println("Department: " + name);
        System.out.println("Number of employees: " + employeeCount);
        System.out.println("Employees:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println("- " + employees[i].getName() + " (" + employees[i].getPosition() + ")");
        }
    }
}

/**
 * Classes demonstrating Composition relationship.
 * A Car has an Engine, and the Engine cannot exist without the Car.
 */
class Engine {
    private boolean running;
    private int horsePower;
    
    public Engine(int horsePower) {
        this.horsePower = horsePower;
        this.running = false;
    }
    
    public void start() {
        running = true;
        System.out.println("Engine started. Vroom!");
    }
    
    public void stop() {
        running = false;
        System.out.println("Engine stopped.");
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public int getHorsePower() {
        return horsePower;
    }
}

class CarWithEngine {
    private String make;
    private String model;
    private Engine engine;  // Composition: Car "owns" the Engine
    
    public CarWithEngine(String make, String model) {
        this.make = make;
        this.model = model;
        // Engine is created when Car is created
        this.engine = new Engine(150);
    }
    
    public void start() {
        System.out.println("Starting the car...");
        engine.start();
    }
    
    public void stop() {
        System.out.println("Stopping the car...");
        engine.stop();
    }
    
    public void accelerate() {
        if (engine.isRunning()) {
            System.out.println("Car is accelerating with " + engine.getHorsePower() + " horsepower!");
        } else {
            System.out.println("Cannot accelerate. Engine is not running.");
        }
    }
    
    public void displayInfo() {
        System.out.println("Car: " + make + " " + model);
        System.out.println("Engine: " + engine.getHorsePower() + " HP");
    }
}

/**
 * Classes demonstrating complex relationships in a university system.
 */
class University {
    private String name;
    private AcademicDepartment[] departments;
    private int departmentCount;
    private static final int MAX_DEPARTMENTS = 10;
    
    public University(String name) {
        this.name = name;
        this.departments = new AcademicDepartment[MAX_DEPARTMENTS];
        this.departmentCount = 0;
    }
    
    public void addDepartment(AcademicDepartment department) {
        if (departmentCount < MAX_DEPARTMENTS) {
            departments[departmentCount] = department;
            departmentCount++;
        } else {
            System.out.println("Cannot add more departments. Maximum limit reached.");
        }
    }
    
    public void displayInfo() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("Number of departments: " + departmentCount);
        
        for (int i = 0; i < departmentCount; i++) {
            departments[i].displayInfo();
        }
    }
}

class AcademicDepartment {
    private String name;
    private CourseWithProfessor[] courses;
    private int courseCount;
    private static final int MAX_COURSES = 20;
    
    public AcademicDepartment(String name) {
        this.name = name;
        this.courses = new CourseWithProfessor[MAX_COURSES];
        this.courseCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void addCourse(CourseWithProfessor course) {
        if (courseCount < MAX_COURSES) {
            courses[courseCount] = course;
            courseCount++;
        } else {
            System.out.println("Cannot add more courses. Maximum limit reached.");
        }
    }
    
    public void displayInfo() {
        System.out.println("\nDepartment: " + name);
        System.out.println("Courses offered:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("- " + courses[i].getName() + " (Taught by: " + 
                              courses[i].getProfessor().getName() + ")");
            courses[i].displayStudents();
        }
    }
}

class Professor {
    private String name;
    private AcademicDepartment department;  // Association
    
    public Professor(String name, AcademicDepartment department) {
        this.name = name;
        this.department = department;
    }
    
    public String getName() {
        return name;
    }
    
    public AcademicDepartment getDepartment() {
        return department;
    }
}

class CourseWithProfessor {
    private String name;
    private Professor professor;  // Association
    private StudentWithCourses[] students;  // Association
    private int studentCount;
    private static final int MAX_STUDENTS = 30;
    
    public CourseWithProfessor(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
        this.students = new StudentWithCourses[MAX_STUDENTS];
        this.studentCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void addStudent(StudentWithCourses student) {
        if (studentCount < MAX_STUDENTS) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cannot add more students. Class is full.");
        }
    }
    
    public void displayStudents() {
        if (studentCount > 0) {
            System.out.println("   Students enrolled: " + studentCount);
            for (int i = 0; i < studentCount; i++) {
                System.out.println("   * " + students[i].getName() + " (ID: " + 
                                  students[i].getStudentId() + ")");
            }
        } else {
            System.out.println("   No students enrolled yet.");
        }
    }
}

class StudentWithCourses {
    private String name;
    private String studentId;
    private CourseWithProfessor[] courses;  // Association
    private int courseCount;
    private static final int MAX_COURSES = 6;
    
    public StudentWithCourses(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.courses = new CourseWithProfessor[MAX_COURSES];
        this.courseCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void enrollInCourse(CourseWithProfessor course) {
        if (courseCount < MAX_COURSES) {
            courses[courseCount] = course;
            courseCount++;
            course.addStudent(this);
        } else {
            System.out.println("Cannot enroll in more courses. Maximum limit reached.");
        }
    }
}
