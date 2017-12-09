package src.assembly_guts;
import src.math.*;
import src.tables.*;
public class PassOne{//String label, mnemonic, symbol;
  Source_line source_lines[];SYMTAB symtab;OPTAB optable;
  boolean isFour;String LOCCTR = "0";

  public PassOne(OPTAB optable, Source_line source_lines[], SYMTAB symtab, String fileName){
    this.optable = optable;this.source_lines = source_lines;
    this.symtab = symtab;
  }

  public Source_line[] Pass1(){
    return provide_source_lines(construct_map_addresses());
  }

  public Source_line[] provide_source_lines(Source_line[] source_lines){
    return source_lines;
  }

  public Source_line[] construct_map_addresses(){
    int count = 0;
    for (Source_line item : this.source_lines) {//System.out.println(item.mnemonic);//if mnemonic is START then we are at the start of the program and need to set LOCCTR
      item.sourceline_number = count++;

      try{
        if(item.label != null){this.symtab.createSymItem(item.label, this.LOCCTR);};
        if(item.mnemonic.equals("START")){this.LOCCTR = item.symbol;item.set_address(this.LOCCTR);}

        else{//format is of LABEL MNEMONIC SYMBOL//If the mnumonic is within OPTAB we can simply determine how many addresses the line uses by the FormatN//i.e Format 1 is 1, Format 2 is 2, Format 3 is 3, and Format 4 is 4 bytes
          item.set_address(this.LOCCTR);
          //if operation is in i.e WORD RESW BYTE RESBYTE
          if(item.mnemonic.equals("WORD")){this.LOCCTR = ConversionCalculator.addHextoHex("3", this.LOCCTR);                                                      }
          if(item.mnemonic.equals("RESW")){this.LOCCTR = ConversionCalculator.addHextoHex(ConversionCalculator.convertIntToHex(ConversionCalculator.toInt(item.symbol) * 3), this.LOCCTR);  }
          if(item.mnemonic.equals("BYTE")){byteCondition(item.symbol.charAt(0), item.symbol);                                                        }
          if(item.mnemonic.equals("RESB")){this.LOCCTR = ConversionCalculator.addHextoHex(ConversionCalculator.convertIntToHex(ConversionCalculator.toInt(item.symbol) * 1), this.LOCCTR);  }

          boolean exist; DataItem temp;
          temp = this.optable.find(item.mnemonic);

          switch (temp.formatN.charAt(0)) {

            case '1':if(does_mnemonic_exist(item, item.mnemonic)){this.LOCCTR = ConversionCalculator.addHextoHex("1", this.LOCCTR);}break;

            case '2':if(does_mnemonic_exist(item, item.mnemonic)){this.LOCCTR = ConversionCalculator.addHextoHex("2", this.LOCCTR);}break;

            case '3':this.LOCCTR = ConversionCalculator.addHextoHex(check_if_four(item.mnemonic.substring(1)), this.LOCCTR);break;

            default:item.set_address(this.LOCCTR);break;
          }
        }
      }catch (Exception e) {};
    }
    return source_lines;
  }

  public String check_if_four(String mnemonic){
    if(mnemonic.charAt(0) == '+'){return "4";}
    else                         {return "3";}
  }

  public String byteCondition(char i, String stringy){
    switch (i){
      case 'C':return Integer.toString(stringy.split("\\'")[1].length());
      case 'X':return "1";
    } return "N";
  }

  public boolean does_mnemonic_exist(Source_line item, String mnemonic){
    if(this.optable.find(mnemonic) != null){return true;} else{return false;}
  }

  public SYMTAB provide_symtable(){
    return this.symtab;
  }
}
