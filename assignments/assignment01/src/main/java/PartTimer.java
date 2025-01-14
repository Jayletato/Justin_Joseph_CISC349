public class PartTimer extends Employee {
  // instance variable(s)
  int requiredHours;

  // construtor declaration
  public PartTimer(String payPosition, String name, String title, int SSN, int requiredHours) {
    super(payPosition,name,title,SSN);
    this.requiredHours = requiredHours;
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