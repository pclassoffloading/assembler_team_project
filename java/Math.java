
import java.lang.*;
import java.math.BigInteger;

public class Math {
   public Math() {}

   public String convertIntToHex(int i){
      String testy = Integer.toHexString(i);
      return testy;
   }

   public int convertHexToInt(String i){
      int n = (int) Long.parseLong(i, 16);
      return n;
   }

   public String addHextoHex(String val1, String val2){
      int test = convertHexToInt(val1) + convertHexToInt(val2);
      return convertIntToHex(test);
   }

   public String subHextoHex(String val1, String val2){
      int test = convertHexToInt(val1) - convertHexToInt(val2);
      return convertIntToHex(test);
   }

   static String hexToBin(String s) {
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("00000000" + testy).substring(testy.length());
      return formatty;
   }

   static String hexToBin12(String s){
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("000000000000" + testy).substring(testy.length());
      return formatty;
   }

   static String hexToBin_Addr(String s) {
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("00000000000000000000" + testy).substring(testy.length());
      return formatty;
   }

   public int toInt(String s) {
      int numInDec = Integer.parseInt(s);
      return numInDec;
   }

   static String binToHex(String s) {
      int numInDec = Integer.parseInt(s, 2);
      String hexString = Integer.toHexString(numInDec);
      return hexString;
   }

}
