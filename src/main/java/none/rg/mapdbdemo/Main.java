package none.rg.mapdbdemo;

import org.mapdb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import com.google.common.io.*;
import com.google.common.base.*;

public class Main {

    void run() {
        DB db = DBMaker.newFileDB(new File("testdb/db"))
            .cacheDisable()
            .closeOnJvmShutdown().make();
        ConcurrentMap<Integer, byte[]> map = db.getHashMap("files");
        loadFiles(db, map, "testdata");
        db.close();
    }
    
    void loadFiles(DB db, Map<Integer, byte[]> map, String dirName) {
        int i = 0;
        for (File file : new File(dirName).listFiles()) {
            String name = file.getName();
            int num = Integer.parseInt(name.replaceFirst("\\..*", ""));
            try {
                byte[] content = Files.toByteArray(file);
                map.put(num, content);
                i++;
                if (i % 50 == 0) {
                    db.commit();
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

