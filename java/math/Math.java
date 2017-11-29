
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
   public String addHextoHex(String val1, String val2){
      //System.out.println(i);
      int test = convertHexToInt(val1) + convertHexToInt(val2);
      return convertIntToHex(test);
      //System.out.println(test);
   }
   
   public String subHextoHex(String val1, String val2){
    
      int test = convertHexToInt(val1) - convertHexToInt(val2);
      return convertIntToHex(test);
   }
   
   public static int hex_to_decimal(String s)
   {
      String digits = "0123456789ABCDEF";
      s = s.toUpperCase();
      int val = 0;
      for (int i = 0; i < s.length(); i++)
      {
         char c = s.charAt(i);
         int d = digits.indexOf(c);
         val = 16*val + d;
      }
      return val;
   }


}//endMath
