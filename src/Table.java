public class Table {
  public static void main(String[] args) {
    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) System.out.print(9 - Math.max(x, y));
      System.out.println();
    }
  }
}
