// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {
  public static boolean exitStatus = false;

  public static ArrayList<Employee> staff = new ArrayList<Employee>();
  // staff.add(employee0);

  public static void main(String[] args) {
    // Test staff
    staff.add(new FullTimer("Fulltimer", "Bob Ross", "Professor", 123456789, 78));
    staff.add(new PartTimer("PartTimer", "Pete Zahut", "Tutor", 123456789, 49));
    staff.add(new PartTimer("PartTimer", "Leon S. Kennedy", "Rookie", 123456789, 69));
    // add PartTime Jill SuccessCoordinator 123984567 20


    System.out.println("Welcome! Type 'help' for a list of commands and 'exit' to quit.");
    while (!exitStatus) {
      home();
    }
  }

  public static void home() {
    System.out.println("-----------------------------------------------------------");
    String currentInput = ReadInput();
    String[] currentInputArray = currentInput.split(" ");
    System.out.println("currentinputarray[0]: " + currentInputArray[0] + "\n");

    // help menu and also documentation
    if (currentInput.equals("help")) {
      System.out.println("Commands: \n");
      System.out.println("help: shows commands.\n");
      System.out.println("exit: exits the program.\n");
      System.out.println("view: shows all employees by full name.\n");
      System.out.println(
          "details <integer>: Select an employee from the system by their index.\n");
      System.out.println(
          "remove <integer>: Delete an employee from the system by their index.\n");
      System.out.println(
          "add <employee_pay_position>: Use to add an employee of position PartTime/FullTime/Contractor to the system. ALL details for the employee must be provided in the subsequent command prompt line in the order of: Pay Position (str), Full Name (str), Job Title (str), SSN (int), and Required Hours (int) (only necessary for Part Time employees).\n");
      System.out.println(
          "edit <integer>: Edit an employee's details by their index. Type in the next line what parameter should be modified.\n");
    }
    // vvv Implement commands here vvv

    if (currentInputArray[0].equals("add")) {
      addEmployee(currentInputArray);
    }

    if (currentInputArray[0].equals("exit")) {
      System.out.println(currentInput);
      exitProgram();
    }

    if (currentInputArray[0].equals("view")) {
      View();
    }

    if (currentInputArray[0].equals("details")) {
      Details(currentInputArray);
      System.out.println();
    }

    if (currentInputArray[0].equals("remove")) {
      removeEmployee(currentInputArray);
    }

    if (currentInputArray[0].equals("edit")){
      editEmployee(currentInputArray);
    }
  }

  public static void Details(String[] inputArray) {
    // Edge case
    if (inputArray.length <= 1) {
      System.out.println("Integer parameter needed, try again");
      return;
    }
    
    // returns the details of the employee at the index specified
    int inputInteger = 0;
    // HERE OFFICER HERE
    try {
      inputInteger = Integer.parseInt(inputArray[1]);
    } catch (NumberFormatException nfe) {
      System.out.println("Please enter a valid integer. \n");
      return;
    }

    // Handles the case where the input is out of bounds
    if (inputInteger >= staff.size() || inputInteger < 0) {
      System.out.println("Invalid integer (out of bounds)");
      return;
    }

    // insert code to show employee information
    System.out.println("Name: " + staff.get(inputInteger).getName());
    System.out.println("Position : " + staff.get(inputInteger).getPosition());
    System.out.println("Title: " + staff.get(inputInteger).getTitle());
    System.out.println("SSN: " + staff.get(inputInteger).getSSN());
    System.out.println("Required hours: " + staff.get(inputInteger).getRequiredHours());
  }

  public static void View() {
    // returns the names of all employees
    for (int i = 0; i < staff.size(); i++) {
      System.out.print("[" + i + "]: " + staff.get(i).getName() + " \n");
    }
    System.out.println();
  }

  public static void addEmployee(String[] inputArray) {
    // adds an employee to the system. Requires the employee's pay position, full
    // name, job title, SSN, and required working hours IN ORDER.
    String payPosition;
    String name;
    String title;
    int SSN;
    int requiredHours = 0;

    // Checks if the input size is valid
    if (inputArray.length > 6) {
      System.out.println("Too many arguments. Please try again.");
      return;}
    
    // Sets the name
    name = inputArray[2];

    // Sets the title
    title = inputArray[3];

    // Sets the SSN
    try {
      SSN = Integer.parseInt(inputArray[4]);
    } catch (NumberFormatException nfe) {
      System.out.println("Invalid integer for SSN.");
      return;
    }

    // Sets the requiredHours
    try {
      requiredHours = Integer.parseInt(inputArray[5]);
    } catch (NumberFormatException nfe) {
      System.out.println("Invalid integer for required hours.");
      return;
    } catch (ArrayIndexOutOfBoundsException bounds) {
      System.out.print("Required hours not provided.");
      if (inputArray[1].equals("FullTime")) {
        System.out.println("Required hours defaulted to 45.");
        requiredHours = 45;
      }
    }

    // Adds the employee to the system
    if (inputArray[1].equals("FullTime") || inputArray[1].equals("FullTimer")) {
      payPosition = "FullTimer";
      staff.add(new FullTimer(payPosition, name, title, SSN, requiredHours));
    }
    if (inputArray[1].equals("PartTime") || inputArray[1].equals("PartTimer")) {
      payPosition = "PartTimer";
      staff.add(new PartTimer(payPosition, name, title, SSN, requiredHours));
    }
    if (inputArray[1].equals("Contractor")) {
      payPosition = "Contractor";
      staff.add(new Contractor(payPosition, name, title, SSN, requiredHours));
    }
  }

  public static void editEmployee(String[] inputArray) {
    // Changes a detail of an employee. Requires the employee's index, the parameter to be changed (position, name, title, SSN, and required hours), and the new value.


    // Edge case
    if (inputArray.length < 4) {
      System.out.println("Parameters needed, try again");
      return;
    }
    if (inputArray.length > 4) {
      System.out.println("Too many arguments. Please try again.");
      return;
    }

    // Gets the employee index from input string and handles out of bounds error
    int employeeIndex;
    employeeIndex = stringToIndex(inputArray[1]);
    if (employeeIndex < 0 || employeeIndex > (staff.size() - 1)) {
      System.out.println("Employee index out of bounds");
      return;
    }

    String parameterToChange = inputArray[2].toLowerCase();
    String newParameter = inputArray[3].toLowerCase();

    System.out.println(staff.get(employeeIndex).getName());

    if (parameterToChange.equals("name")) {
      staff.get(employeeIndex).setName(newParameter);
    }
  
    // Changing an employee's pay position will create  an altered duplicate instance of them and delete the original
    else if (parameterToChange.equals("position")) {
      if (newParameter.equals("fulltime") || newParameter.equals("fulltimer")) {
        staff.add(new FullTimer("FullTimer", staff.get(employeeIndex).getName(), staff.get(employeeIndex).getTitle(), staff.get(employeeIndex).getSSN(), staff.get(employeeIndex).getRequiredHours()));
      }
    
      if (newParameter.equals("parttime") || newParameter.equals("fulltimer")) {
        staff.add(new PartTimer("PartTimer", staff.get(employeeIndex).getName(), staff.get(employeeIndex).getTitle(), staff.get(employeeIndex).getSSN(), staff.get(employeeIndex).getRequiredHours()) );
      }

      if (newParameter.equals("contractor")) {
        staff.add(new Contractor("Contractor", staff.get(employeeIndex).getName(), staff.get(employeeIndex).getTitle(),staff.get(employeeIndex).getSSN(), staff.get(employeeIndex).getRequiredHours()) );
      }

      staff.remove(employeeIndex);
    } // This one is troublesome


    else if (parameterToChange.equals("title")) {
      staff.get(employeeIndex).setTitle(newParameter);
    }

    else if (parameterToChange.equals("ssn")) {
      int SSN = stringToIndex(inputArray[3]);
      if (SSN > 999999999 || SSN < 100000000) {
        System.out.println("Invalid SSN entered.");
        return;
      }
      staff.get(employeeIndex).setSSN(SSN);
    }

    else if (parameterToChange.equals("requiredhours")) {
      int reqHours = stringToIndex(newParameter);
      if (reqHours < 0) {
        System.out.println("Invalid required hours entered.");
        return;
      }
      staff.get(employeeIndex).setRequiredHours(reqHours);
    }

  return;
    
    
  }
  
  public static void removeEmployee(String[] inputArray){
    //deletes an employee from the system by index
    int inputInteger;

    // Edge case
    if (inputArray.length <= 1) {
      System.out.println("Integer parameter needed, try again");
      return;
    }
    
    try {
      inputInteger = Integer.parseInt(inputArray[1]);
    } catch (NumberFormatException nfe) {
      System.out.println("Invalid integer.");
      return;
    }
    if (inputInteger >= staff.size() || inputInteger < 0) {
      System.out.println("Invalid integer (out of bounds)");
      return;
    }
    staff.remove(inputInteger);
  }

  public static Integer stringToIndex(String currentInput) {
    // Takes the input string and tries to convert the string at index 1 into an integer. If possible, returns true and if not, returns false. 
    Integer index = -1;
    try {
      index = Integer.parseInt(currentInput);
    } catch (NumberFormatException nfe) {
      System.out.println("invalid integer provided. Please try again.");
    }
    return index;
  }

  public static String ReadInput() {

    InputStreamReader inputChars = new InputStreamReader(System.in);
    BufferedReader bufferedChars = new BufferedReader(inputChars);

    String input = "";

    while (input.equals("")) {
      try {
        input = bufferedChars.readLine();
        return input;
      } catch (IOException exception) {
        System.out.println("Error reading input.");
      }
    }
    return input;
  }

  public static void exitProgram() {
    exitStatus = true;
  }
}