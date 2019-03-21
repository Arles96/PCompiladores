import java.io.*;

public class Main {
  static public void main(String argv[]) {
    /* Start the parser */
    try {
      parser p = new parser(new Ada95(new FileReader(argv[0])));
      p.parse();
      if (p.msgErrores.size() > 0) {
        for(String errores : p.msgErrores){
          System.out.println(errores);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}


