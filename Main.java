import java.io.*;
import Nodos.*;

public class Main {
  public static void main(String argv[]) {
    /* Start the parser */
    try {
      Ada95 lexico = new Ada95(new FileReader(argv[0]));
      parser p = new parser(lexico);
      p.parse();
      if (p.msgErroresSintactico.size() > 0) {
        for(String errores : p.msgErroresSintactico){
          System.out.println(errores);
        }
      } else if (lexico.msgErroresLexico.size() > 0) {
        for(String errores : lexico.msgErroresLexico){
          System.out.println(errores);
        }
      } else {
        printTree(p.raiz, 0);
        System.out.println("Imprimiendo Tabla: ");
        System.out.println(p.tablaFinal.tabla.size());
        System.out.println(p.tablaFinal.tabla.get(0).id);
        System.out.println(p.tablaFinal.tabla.get(1).id);
        System.out.println(p.tablaFinal.tabla.get(2).id);
        /*System.out.println(p.tablaFinal.tabla.get(0).id);
        System.out.println(p.tablaFinal.tabla.get(1).id);
        System.out.println(p.tablaFinal.tabla.get(2).id);*/
        // printTabla(p.tablaFinal, 0);
        /*System.out.println("Tabla de simbolos");
        for(int i = 0; i < p.tablaSimbolos.size(); i++){
          for(int j = 0; j < p.tablaSimbolos.get(i).length; j++){
            System.out.print(p.tablaSimbolos.get(i)[j] + ",");
          }
          System.out.println();
        }*/
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void printTree(Nodo n,int nivel){

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

  public static void printTabla(TablaSimbolos tabla, int nivel){
    int tem = nivel;

    for(int i=0;i<nivel;i++){
      System.out.print("-");
    }
    for(int i = 0; i < tabla.tabla.size(); i++){
      System.out.println(tabla.tabla.get(i));
    }

    //System.out.print("")
    nivel++;
    for (int i=0; i<tabla.children.size();i++){
      //System.out.println("Hijo de: "+n.getInfo());
      //System.out.print("--");
      printTabla(tabla.children.get(i),nivel);

    }
    nivel = tem;
  }
}


