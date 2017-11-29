
import java.lang.*;
import java.math.BigInteger;

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
  public String addHextoHex(String val1, String val2){
    //System.out.println(i);
    int test = convertHexToInt(val1) + convertHexToInt(val2);

    return convertIntToHex(test);

  }
  public String subHextoHex(String val1, String val2){
    int test = convertHexToInt(val1) - convertHexToInt(val2);
    return convertIntToHex(test);
  }

  static String hexToBin(String s) {
    return new BigInteger(s, 16).toString(2);
  }

  // static String binToHex(String s) {
  //   return new BigInteger(s, 16).toString(2);
  // }

}
