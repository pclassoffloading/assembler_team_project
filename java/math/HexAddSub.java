
public class HexAddSub {

    /**
     * This method convert the value from String type to long type .
     * @param number is the number that we want to convert it from string to long .
     * @return finalNumber which is the number but on the long type .
     */
    private long String2long(String number) {
        long finalNumber = 0;
        int length = number.length();
        while (length > 0) {
            finalNumber = finalNumber + (number.charAt(length - 1) - 48) * (int) Math.pow(10, number.length() - length);
            length--;
        }
        return finalNumber;
    }
    /**
     * This method convert the number from the Binary to the Decimal.
     * @param binNumber the number in long type in the Binary system .
     * @return the number in the Decimal system .
     */
    public long BinToDec(long binNumber) //B2D
    {

        String bin = binNumber + "";
        int length = bin.length();
        int total = 0;
        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            int dec = 0;
            switch (bin.charAt(i)) {
                case '1':
                    dec = (int) Math.pow(2, j);
                    break;
                case '0':
                    dec = 0;
                    break;
                default:
                    System.out.println("Invalid value! ");
                    i = 0;
                    break;
            }

            total += dec;
        }

        return total;
    }
    
    /**
     * This method convert the number from the Hexadecimal to the Decimal.
     * @param hexNumber the number in String type in the Hexadecimal system .
     * @return the number in the Decimal system .
     */
    public long HexToDec(String hexNumber) //H2D
    {
        String hex = hexNumber.toLowerCase();
        int length = hex.length();
        long total = 0;

        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            int dec = 0;
            switch (hex.charAt(i)) {
                case '1':
                    dec = (int) Math.pow(16, j);
                    break;
                case '2':
                    dec = (int) Math.pow(16, j) * 2;
                    break;
                case '3':
                    dec = (int) Math.pow(16, j) * 3;
                    break;
                case '4':
                    dec = (int) Math.pow(16, j) * 4;
                    break;
                case '5':
                    dec = (int) Math.pow(16, j) * 5;
                    break;
                case '6':
                    dec = (int) Math.pow(16, j) * 6;
                    break;
                case '7':
                    dec = (int) Math.pow(16, j) * 7;
                    break;
                case '8':
                    dec = (int) Math.pow(16, j) * 8;
                    break;
                case '9':
                    dec = (int) Math.pow(16, j) * 9;
                    break;
                case 'a':
                    dec = (int) Math.pow(16, j) * 10;
                    break;
                case 'b':
                    dec = (int) Math.pow(16, j) * 11;
                    break;
                case 'c':
                    dec = (int) Math.pow(16, j) * 12;
                    break;
                case 'd':
                    dec = (int) Math.pow(16, j) * 13;
                    break;
                case 'e':
                    dec = (int) Math.pow(16, j) * 14;
                    break;
                case 'f':
                    dec = (int) Math.pow(16, j) * 15;
                    break;
                case '0':
                    dec = 0;
                    break;
                default:
                    System.out.println("Invalid value! ");
                    i = 0;

                    return 0;
            }
            total += dec;
        }

        return total;
    }
      
    
    /**
     * This method convert the number from the Hexadecimal to the Binary.
     * @param hexNumber the number in String type in the Hexadecimal system .
     * @return the number in the Binary system .
     */
    public long HexToBin(String hexNumber) //H2B
    {
        return DecToBin(HexToDec(hexNumber));
    }

    
    /**
     * This method convert the number from the Binary to the Hexadecimal.
     * @param binNumber the number in String type in the Binary system .
     * @return the number in the Hexadecimal system .
     */
    public String BinToHex(String binNumber) //B2H
    {
        return DecToHex(BinToDec(binNumber));
    }

    /**
     * This method convert the number from the Binary to the Hexadecimal.
     * @param binNumber the number in long type in the Binary system .
     * @return the number in the Hexadecimal system .
     */
    public String BinToHex(long binNumber) //B2H
    {
        return DecToHex(BinToDec(binNumber));
    }

    

       /**
     * This method gather the two Binary numbers .
     * @param binNumber1 The first Binary number .
     * @param binNumber2 The second Binary number .
     * @return The summation of them .
     */
    public long Bin_Add(long binNumber1, long binNumber2) {
        long finalBin = DecToBin(BinToDec(binNumber1) + BinToDec(binNumber2));
        return finalBin;
    }

    
    /**
     * This method gather the two Hexadecimal numbers .
     * @param hexNumber1 The first Hexadecimal number .
     * @param hexNumber2 The second Hexadecimal number .
     * @return The summation of them .
     */
    public String Hex_Add(String hexNumber1, String hexNumber2) {
        String finalHex = DecToHex(HexToDec(hexNumber1) + HexToDec(hexNumber2));
        return finalHex;
    }

    
    /**
     * This method Holds subtraction of two Binary numbers .
     * @param binNumber1 The first Binary number .
     * @param binNumber2 The second Binary number .
     * @return The subtraction of them .
     */
    public long Bin_Sub(long binNumber1, long binNumber2) {
        long finalBin = DecToBin(BinToDec(binNumber1) - BinToDec(binNumber2));
        return finalBin;
    }

   
    /**
     * This method Holds subtraction of two Hexadecimal numbers
     * @param hexNumber1 The first Hexadecimal number .
     * @param hexNumber2 The second Hexadecimal number .
     * @return The subtraction of them .
     */
    public String Hex_Sub(String hexNumber1, String hexNumber2) {
        String finalHex = DecToHex(HexToDec(hexNumber1) - HexToDec(hexNumber2));
        return finalHex;
    }
    
    
    