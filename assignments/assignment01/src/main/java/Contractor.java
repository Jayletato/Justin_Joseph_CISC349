public class Contractor extends Employee {
  // instance variable(s)
  int requiredHours = 0;

  // construtor declaration
  public Contractor(String payPosition, String name, String title, int SSN, int requiredHours) {
    super(payPosition, name, title, SSN);
  }

  // getter
  @Override
  public int getRequiredHours() {
    return requiredHours;
  }

  // setter
  @Override
  public void setRequiredHours(int requiredHours) {
    this.requiredHours = requiredHours;
  }
}