package util;

import model.User;

import java.io.*;

public class FileUtil {


    public static void saveObject(Object obj, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(obj);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object loadObject(String filename){
        File  f = new File(filename);
        if(!f.exists()){
            return null;
        }
        try
        {
            ObjectInputStream ois=new ObjectInputStream((new FileInputStream(f)));
            return ois.readObject();
        }
        catch (Exception e)
        {
           return null;
        }
    }

    public static void writeLog(String filePath, String message){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter(filePath,true));
            bw.write(message);
            bw.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
