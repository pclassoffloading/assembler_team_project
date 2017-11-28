
import java.lang.*;
public class Math {
  public Math() {

   }
  public String convertIntToHex(int i){
    System.out.println("Number = " + i);
    System.out.println("Hex = " + Integer.toHexString(i));
    String testy = Integer.toHexString(i);
    return testy;
  }

  public int convertHexToInt(String i){
    //System.out.println(i);
    int n = (int) Long.parseLong(i, 16);
    System.out.println("Number = " + n);
    return n;
  }
  public void addHextoHex(String val1, String val2){
    //System.out.println(i);
    int test = convertHexToInt(val1) + convertHexToInt(val2);
    convertIntToHex(test);
    //System.out.println(test);
  }


}
