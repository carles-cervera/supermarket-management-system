//MAIN CLASS

package edu.upc.prop.grup13_4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.inventari.Producte;
import edu.upc.prop.grup13_4.inventari.Relacio;

import java.io.File;
import java.util.TreeSet;
import java.util.Vector;

class Test {
  public TreeSet<Relacio> treeSet;

  Test() {
    treeSet = new TreeSet<>();
  }
}

public class Main {
  public static void main(String[] args) {
    Gson gson = new Gson();
    //Get the project's current direcotry, independently of the system we're using.
    String currentDir = System.getProperty("user.dir");

    //File currentDirFile = new File(currentDir);
    //File jsonFile = new File(currentDirFile, "grup13_4.json");

    Test test = new Test();
    test.treeSet.add(new Relacio(new Producte("pastanaga"), new Producte("carbassot"), 10));
    test.treeSet.add(new Relacio(new Producte("arros"), new Producte("cafe"), 5));
    String json = gson.toJson(test);

    Test newtest = gson.fromJson(json, Test.class);

    System.out.println(json);



  }
}
