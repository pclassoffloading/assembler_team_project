package src;
import java.*;
import java.lang.*;
import java.math.BigInteger;

public interface Math {

   public static String convertIntToHex(int i){
      String testy = Integer.toHexString(i);
      return testy;
   }

   public static int convertHexToInt(String i){
      int n = (int) Long.parseLong(i, 16);
      return n;
   }

   public static String addHextoHex(String val1, String val2){
      int test = convertHexToInt(val1) + convertHexToInt(val2);
      return convertIntToHex(test);
   }

   public static String subHextoHex(String val1, String val2){
      int test = convertHexToInt(val1) - convertHexToInt(val2);
      return convertIntToHex(test);
   }

   public static String hexToBin(String s) {
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("00000000" + testy).substring(testy.length());
      return formatty;
   }

   public static String hexToBin12(String s){
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("000000000000" + testy).substring(testy.length());
      return formatty;
   }

   public static String hexToBin_Addr(String s) {
      String testy = new BigInteger(s, 16).toString(2);
      String formatty = ("00000000000000000000" + testy).substring(testy.length());
      return formatty;
   }

   public static int toInt(String s) {
      int numInDec = Integer.parseInt(s);
      return numInDec;
   }

   public static String binToHex(String s) {
      int numInDec = Integer.parseInt(s, 2);
      String hexString = Integer.toHexString(numInDec);
      return hexString;
   }

}
