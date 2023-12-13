import java.io.PrintWriter;

/**
 * Converting Braille to ASCII program.
 * @author Hyeon Kim
 * @version 0.0.9
 */

public class BrailleASCII {
  public static void main(String[] args) throws Exception{
    if(args.length != 2){
      throw new Exception("Invalid number of inputs!");
    } // if
    /* Static initialization of the methods */
    BrailleASCIITables brailleAsciiTbl = new BrailleASCIITables();
    PrintWriter pen = new PrintWriter(System.out, true);
    String option = args[0];
    String bits = args[1];
    option = option.toLowerCase();
    switch (option) {
      case "ascii":
        pen.println(brailleAsciiTbl.stringToASCII(bits));
        break;
      case "braille":
        pen.println(brailleAsciiTbl.stringToBraille(bits));
        break;
      case "unicode":
        pen.println(brailleAsciiTbl.stringToUniCode(bits));
        break;
      default:
        throw new IllegalArgumentException("Invalid option " + option);
    } // switch/case
  } // main(String[])
} // BrailleASCII
