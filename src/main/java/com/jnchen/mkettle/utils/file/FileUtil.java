package com.jnchen.mkettle.utils.file;

import java.io.*;

/**
 * Created by caojingchen on 2017/12/29.
 */
public class FileUtil {
    private static final String FILE_BASE_PATH = "./uploads/";

    public static boolean save(InputStream inputStream,String key,String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(FILE_BASE_PATH+key+"/"+name));
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=inputStream.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fos.close();
        inputStream.close();
        return true;
    }

    public static String getFilePath(String key,String name){
        return FILE_BASE_PATH+key+"/"+name;
    }
}
