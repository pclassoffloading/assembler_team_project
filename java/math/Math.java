
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

  public void convertHexToInt(String i){
    //System.out.println(i);
    int n = (int) Long.parseLong(i, 16);
    System.out.println("Number = " + n);

  }


}
