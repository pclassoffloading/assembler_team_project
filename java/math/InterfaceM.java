
import java.lang.*;
public class InterfaceM {
  public static void main(String [] args) {
    Math test = new Math();

    String testy = test.convertIntToHex(17099800);
    test.convertHexToInt(testy);
    test.addHextoHex("104ec18", "104ec10");
   }
}
