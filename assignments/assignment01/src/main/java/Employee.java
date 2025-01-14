// I used Geeks for Geeks as a reference 
// https://www.geeksforgeeks.org/classes-objects-java/

public class Employee {

  // instance variables
  String payPosition; // This is mostly redundant but I do not want to rewrite everything;
  String name; // This would probably have been better implemented as a separate First Name and Last Name;
  String title;
  int SSN;
  int requiredHours;

  // constructor declaration
  public Employee(String payPosition, String name, String title,
      int SSN) {
    this.payPosition = payPosition;
    this.name = name;
    this.title = title;
    this.SSN = SSN;
  }

  // getters
  public String getPosition() {
    return payPosition;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }

  public int getSSN() {
    return SSN;
  }

  public int getRequiredHours() {
    return requiredHours;
  }

  // setters
  public void setPosition(String payPosition) {
    this.payPosition = payPosition;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void getTitle(String title) {
    this.title = title;
  }

  public void getSSN(int SSN) {
    this.SSN = SSN;
  }

  public void setRequiredHours(int requiredHours) {
    this.requiredHours = requiredHours;
  }
}