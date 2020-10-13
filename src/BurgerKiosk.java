import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class BurgerKiosk {
  public static void main(String[] args) {
    BurgerMenu menu = new BurgerMenu()
      .addBurger(new Burger("Chicken burger", "Chicken", "White bread", 1.4)
        .addExtra("Mayonnaise", .1)
        .addExtra("Tomato", .3)
        .addExtra("Lettuce", .1)
        .addExtra("Cheese", .2))
      .addBurger(new Burger("Old Fashioned Cheeseburger", "Beef", "White bread", 1.4)
        .addExtra("Bacon", .8)
        .addExtra("Cucumber", .3)
        .addExtra("Onion", .4)
        .addExtra("Bacon dressing", .2))
      .addBurger(new Burger("New York Burger", "Pork", "Grain bread", 1.4)
        .addExtra("Pineaplle", .4)
        .addExtra("Tomato", .3)
        .addExtra("Cucumber", .3)
        .addExtra("Onion", .4));
    System.out.println(menu);

    BurgerOrder order = new BurgerOrder();

    Scanner reader = new Scanner(System.in);

    System.out.println("\033[1mEnter product numbers (0 to finish):\033[m");

    while (true) {
      int burgerId = reader.nextInt();
      if (burgerId < 1) break;
      if (burgerId > menu.getBurgers().size()) System.out.println("Index out of bounds.");
      else {
        Burger burger = menu.getBurger(burgerId - 1);
        order.addBurger(burger);
        System.out.println(burger.name + " added to order.");
      }
    }

    System.out.println("\033[1mOrder:\033[m");
    System.out.println(order);
  }
}

class BurgerOrder {
  private double sum = 0;
  private HashMap<String, Integer> burgersCount = new HashMap<String, Integer>();
  private HashMap<String, Double> burgersPrice = new HashMap<String, Double>();

  public BurgerOrder addBurger(Burger burger) {
    sum += burger.getPrice();
    String name = burger.name;
    if (burgersCount.containsKey(name)) burgersCount.put(name, burgersCount.get(name) + 1);
    else {
      burgersCount.put(name, 1);
      burgersPrice.put(name, burger.getPrice());
    }
    return this;
  }

  public String toString() {
    String result = "";
    for (java.util.Map.Entry<String, Integer> entry : burgersCount.entrySet()) {
      if (!result.isEmpty()) result += '\n';
      result += String.format("%3dx %2.2f$ %s", entry.getValue(), burgersPrice.get(entry.getKey()), entry.getKey());
    }
    result += String.format("\n\033[1mSum: %2.2f$\033[m", sum);
    return result;
  }
}

class BurgerMenu {
  private ArrayList<Burger> burgers = new ArrayList<Burger>();

  public BurgerMenu addBurger(Burger burger) {
    burgers.add(burger);
    return this;
  }

  public Burger getBurger(int i) {
    return burgers.get(i);
  }

  public ArrayList<Burger> getBurgers() {
    return burgers;
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < burgers.size(); i++) {
      if (!result.isEmpty()) result += '\n';
      Burger burger = burgers.get(i);
      result += String.format("%d. %.2f$ %s", i + 1, burger.getPrice(), burger);
    }
    return result;
  }
}

class Burger {
  public final String name;
  public final String meat;
  public final String bread;
  private double price;
  public final int extrasLimit = 4;
  private ArrayList<String> extras = new ArrayList<String>();

  public Burger(String name, String meat, double price) {
    this.name = name;
    this.meat = meat;
    this.bread = "teraleib";
    this.price = price;
  }

  public Burger(String name, String meat, String bread, double price) {
    this.name = name;
    this.meat = meat;
    this.bread = bread;
    this.price = price;
  }

  public Burger addExtra(String name, double price) {
    if (extras.size() <= extrasLimit) {
      extras.add(name);
      this.price += price;
    }
    return this;
  }

  public double composeBurger() {
    return price;
  }

  public double getPrice() {
    return price;
  }

  public String toString() {
    return String.format("%s (%s, %s, %s)", name, meat, bread, String.join(", ", extras));
  }
}
