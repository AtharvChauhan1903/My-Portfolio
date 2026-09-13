 class Expense {

  private String description;
  private int amount;
  private String category;

  public Expense(String description, int amount, String category) {
    this.description = description;
    this.amount = amount;
    this.category = category;
  }


  public String getDescription() {
    return description;
  }

  public double getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }


  public void displayExpense() {
    System.out.println("- " + description + ": Rs. " + amount + " [" + category + "]");
  }
}
