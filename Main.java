import java.io.*;

public class Main {
  static public void main(String argv[]) {
    /* Start the parser */
    try {
      Ada95 lexico = new Ada95(new FileReader(argv[0]));
      parser p = new parser(lexico);
      p.parse();
      if (p.msgErrores.size() > 0) {
        for(String errores : p.msgErrores){
          System.out.println(errores);
        }
      } else if (lexico.msgErrores.size() > 0) {
        for(String errores : lexico.msgErrores){
          System.out.println(errores);
        }
      } else {
        printTree(p.raiz,0);
        
        System.out.println("Tabla de simbolos");
        for(int i = 0; i < p.tablaSimbolos.size(); i++){
          for(int j = 0; j < p.tablaSimbolos.get(i).length; j++){
            System.out.print(p.tablaSimbolos.get(i)[j] + ",");
          }
          System.out.println();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  static public void printTree(Nodo n,int nivel){

    int tem = nivel;

    for(int i=0;i<nivel;i++){
      System.out.print("-");
    }
    System.out.println(n.getInfo());

    //System.out.print("")
    nivel++;
    for (int i=0; i<n.hijos.size();i++){
      //System.out.println("Hijo de: "+n.getInfo());
      //System.out.print("--");
      printTree(n.hijos.get(i),nivel);

    }
    nivel = tem;
    //System.out.print("--");

  }
}


