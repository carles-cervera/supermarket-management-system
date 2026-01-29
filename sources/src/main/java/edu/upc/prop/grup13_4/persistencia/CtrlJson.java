package edu.upc.prop.grup13_4.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.upc.prop.grup13_4.distribucio.Distribucio;
import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioNotFoundException;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariNotFoundException;
import edu.upc.prop.grup13_4.exceptions.perfil.PerfilAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.perfil.PerfilNotFoundException;
import edu.upc.prop.grup13_4.inventari.Inventari;
import edu.upc.prop.grup13_4.inventari.Producte;
import edu.upc.prop.grup13_4.inventari.ProducteAdapter;
import edu.upc.prop.grup13_4.inventari.Relacio;
import edu.upc.prop.grup13_4.perfil.Perfil;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

public class CtrlJson {

    public static void main(String[] args) {
        /*CtrlJson ctrlJson = CtrlJson.getInstance();
        Inventari oi = new Inventari("dummy", "dummy");
        oi.addProducte(new Producte("pastaga"));
        oi.addProducte(new Producte("bajoca"));
        ctrlJson.saveInventari(oi, "dummy");
        Inventari inv = ctrlJson.getInventari("dummy","dummy");
        System.out.println(inv.getId());
         */

        CtrlJson ctrlJson = CtrlJson.getInstance();
        Perfil p = new Perfil("Dylan Bautista", "dummy", "asd123");
        ctrlJson.savePerfil(p);
        Perfil pc = ctrlJson.getPerfil("dummy");
        System.out.println(pc.getUsername() + " " + pc.getRealname() + " " + pc.getPassword());
        //ctrlJson.deletePerfil("dummy");

        /*Map<Producte, TreeSet<Relacio>> relacions = new HashMap<>();
        TreeSet<Relacio> rel = new TreeSet<>();
        rel.add(new Relacio(new Producte("prova1"),new Producte("prod2"), 10));
        rel.add(new Relacio(new Producte("provaa"),new Producte("prodd"), 10));
        Producte prod = new Producte("pastanaga");
        relacions.put(prod, rel);
        Gson gson = new GsonBuilder().registerTypeAdapter(Producte.class, new ProducteAdapter()).create();
        String json = gson.toJson(relacions);
        Type type = new TypeToken<Map<Producte, TreeSet<Relacio>>>() {}.getType();
        Map<Producte, TreeSet<Relacio>> relacions2 = gson.fromJson(json, type);
        System.out.println(relacions2.toString());
        System.out.println(relacions2.keySet().iterator().next());*/
    }

    private static CtrlJson instance;

    String currentDir = System.getProperty("user.dir");
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Producte.class, new ProducteAdapter())
            .create();

    private CtrlJson() {

    }

    public static CtrlJson getInstance() {
        if (instance == null) {
            instance = new CtrlJson();
        }
        return instance;
    }

    public boolean searchInventari(String id, String username) {
        Path inventariPath = Path.of(currentDir, "data", username, "inventaris", id+".json");
        return Files.exists(inventariPath);
    }

    public void strictSaveInventari(Inventari inv, String username) {
        if (inv == null) { throw new NullPointerException();}

        Path userPath = Path.of(currentDir, "data", username, "inventaris", inv.getId() + ".json");

        try {
            Files.createDirectories(userPath.getParent());
            Files.createFile(userPath);
        } catch (FileAlreadyExistsException e) {
            //Nothing really happens
            throw new InventariAlreadyExistsException(inv.getId());
        } catch (Exception e) {
            System.exit(1); //Fatal error, should not happen
            return;
        }

        PrintStream jsonStream;
        try {
            jsonStream = new PrintStream(userPath.toFile());
            jsonStream.println(gson.toJson(inv));
            jsonStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void saveInventari(Inventari inv, String username) {
        if (inv == null) { throw new NullPointerException();}


        Path userPath = Path.of(currentDir, "data", username, "inventaris", inv.getId() + ".json");
        try {
            Files.createDirectories(userPath.getParent());
            Files.createFile(userPath);
        } catch (FileAlreadyExistsException e) {
            //Nothing really happens
        } catch (Exception e) {
            System.exit(1); //Fatal error, should not happen
            return;
        }

        PrintStream jsonStream;
        try {
            jsonStream = new PrintStream(userPath.toFile());
            jsonStream.println(gson.toJson(inv));
            jsonStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Inventari getInventari(String id, String username) {
        Path userPath = Path.of(currentDir, "data", username, "inventaris", id + ".json");

        if (!Files.exists(userPath)) { throw new InventariNotFoundException(id); }

        Inventari inv = null;
        StringBuilder content = new StringBuilder();

        try ( InputStream inputStream = new FileInputStream(userPath.toFile());
               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)) ) {

            String line;
            while ((line = reader.readLine()) != null) { //Read line by line
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(content.toString(), Inventari.class);
    }

    public void deleteInventari(String id, String username) {
        Path userPath = Path.of(currentDir, "data", username, "inventaris", id + ".json");
        if (!Files.exists(userPath)) { throw new InventariNotFoundException(id); }
        userPath.toFile().delete();
    }

    public void saveDistribucio(Distribucio d, String username) {
        Path userPath = Path.of(currentDir, "data", username, "distribucions", d.getIdDistribucio() + ".json");
        try {
            Files.createDirectories(userPath.getParent());
            Files.createFile(userPath);
        } catch (FileAlreadyExistsException e) {
            //Nothing really happens
            throw new DistribucioAlreadyExistsException(d.getIdDistribucio());
        } catch (Exception e) {
            System.exit(1); //Fatal error, should not happen
            return;
        }

        PrintStream jsonStream;
        try {
            jsonStream = new PrintStream(userPath.toFile());
            jsonStream.println(gson.toJson(d));
            jsonStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Distribucio getDistribucio(String id, String username) {
        Path userPath = Path.of(currentDir, "data", username, "distribucions", id + ".json");

        if (!Files.exists(userPath)) { throw new DistribucioNotFoundException(id); }

        StringBuilder content = new StringBuilder();

        try ( InputStream inputStream = new FileInputStream(userPath.toFile());
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)) ) {

            String line;
            while ((line = reader.readLine()) != null) { //Read line by line
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(content.toString(), Distribucio.class);
    }

    public String[] getDistribucioIds(String username) {

        Path userPath = Path.of(currentDir, "data", username, "distribucions");

        if (!Files.exists(userPath.getParent())) { throw new PerfilNotFoundException(username); }

        List<String> distribucions = new ArrayList<>();

        try (Stream<Path> files = Files.list(userPath)) {
            files.forEach(file-> {
                if (Files.isRegularFile(file)) {
                    String fileName = file.getFileName().toString();
                    if (fileName.endsWith(".json")) {
                        distribucions.add(fileName.substring(0,fileName.length() - 5));
                    }
                }
            });
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return distribucions.toArray(new String[distribucions.size()]);
    }

    public List<String> getInventarisIds(String username) {

        Path userPath = Path.of(currentDir, "data", username, "inventaris");

        if (!Files.exists(userPath.getParent())) { throw new PerfilNotFoundException(username); }

        List<String> inventaris = new ArrayList<>();

        try (Stream<Path> files = Files.list(userPath)) {
            files.forEach(file-> {
                if (Files.isRegularFile(file)) {
                    String fileName = file.getFileName().toString();
                    if (fileName.endsWith(".json")) {
                        inventaris.add(fileName.substring(0,fileName.length() - 5));
                    }
                }
            });
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return inventaris;
    }

    public void deleteDistribucio(String id, String username) {
        Path userPath = Path.of(currentDir, "data", username, "distribucions", id + ".json");
        if (!Files.exists(userPath)) { throw new DistribucioNotFoundException(id); }
        userPath.toFile().delete();
    }

    //PERFIL

    public void strictSavePerfil(Perfil perfil) {
        if (perfil == null) { throw new NullPointerException();}

        String username = perfil.getUsername();

        Path userPath = Path.of(currentDir, "data", username, "config.json");
        try {
            Files.createDirectories(userPath.getParent());
            Files.createFile(userPath);
        } catch (FileAlreadyExistsException e) {
            //Nothing really happens
            throw new PerfilAlreadyExistsException(username);
        } catch (Exception e) {
            System.exit(1); //Fatal error, should not happen
            return;
        }

        PrintStream jsonStream;
        try {
            jsonStream = new PrintStream(userPath.toFile());
            jsonStream.println(gson.toJson(perfil));
            jsonStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void savePerfil(Perfil perfil) {
        if (perfil == null) { throw new NullPointerException();}

        String username = perfil.getUsername();

        Path userPath = Path.of(currentDir, "data", username, "config.json");
        try {
            Files.createDirectories(userPath.getParent());
            Files.createFile(userPath);
        } catch (FileAlreadyExistsException e) {
            //Nothing really happens
        } catch (Exception e) {
            System.exit(1); //Fatal error, should not happen
            return;
        }

        PrintStream jsonStream;
        try {
            jsonStream = new PrintStream(userPath.toFile());
            jsonStream.println(gson.toJson(perfil));
            jsonStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Perfil getPerfil(String username) {
        Path userPath = Path.of(currentDir, "data", username, "config.json");

        if (!Files.exists(userPath)) { throw new PerfilNotFoundException(username); }

        StringBuilder content = new StringBuilder();

        try ( InputStream inputStream = new FileInputStream(userPath.toFile());
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)) ) {

            String line;
            while ((line = reader.readLine()) != null) { //Read line by line
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(content.toString(), Perfil.class);
    }

    public void deletePerfil(String username) {
        Path userDir = Path.of(currentDir, "data", username);

        if (!Files.exists(userDir)) {
            throw new PerfilNotFoundException(username);
        }

        try {
            Files.walkFileTree(userDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("No s'ha pogut eliminar el perfil: " + username, e);
        }
    }


    public void verifyPerfil(String username) {
        Path userPath = Path.of(currentDir, "data", username, "config.json");

        if (!Files.exists(userPath)) { System.exit(1); }

        Perfil p = this.getPerfil(username);

        if (p == null) { System.exit(1); }
        if (!p.getUsername().equals(username)) { System.exit(1); }
    }

}
