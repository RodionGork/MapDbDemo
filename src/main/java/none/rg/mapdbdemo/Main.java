package none.rg.mapdbdemo;

import org.mapdb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import com.google.common.io.*;
import com.google.common.base.*;

public class Main {

    void run() {
        DB db = DBMaker.newFileDB(new File("testdb/db")).closeOnJvmShutdown().make();
        ConcurrentMap<Integer, String> map = db.getHashMap("files");
        loadFiles(db, map, "testdata");
        db.commit();
    }
    
    void loadFiles(DB db, Map<Integer, String> map, String dirName) {
        int i = 0;
        for (File file : new File(dirName).listFiles()) {
            String name = file.getName();
            int num = Integer.parseInt(name.replaceFirst("\\..*", ""));
            try {
                String content = Files.toString(file, Charsets.UTF_8);
                map.put(num, content);
                db.commit();
                i++;
                if (i % 10 == 0) {
                    System.out.println(i);
                }
            } catch (IOException e) {
                System.err.println("File loading error: " + name);
            }
        }
    }
    
    public static void main(String... args) {
        new Main().run();
    }
    
    

}

