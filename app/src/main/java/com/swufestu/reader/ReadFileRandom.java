package com.swufestu.reader;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadFileRandom {
    private InputStream dataInputStream=null;
    private String filePath=null;
    private static  final String TAG="ReadRandomFile";
    public ReadFileRandom(String path){
        this.filePath=path;
        try {
            dataInputStream=new DataInputStream(new FileInputStream(filePath));
        }catch (FileNotFoundException e){
            e.getStackTrace();
        }
    }
    public void openNewStream(){
        shut();
        try {
            dataInputStream=new DataInputStream(new FileInputStream(filePath));
        }catch (FileNotFoundException e){
        }
    }

    public byte[]readBytes(int len){
        byte[] bytes = new byte[len];
        try {
            dataInputStream=new DataInputStream(new FileInputStream(filePath));
            dataInputStream.read(bytes);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return bytes;
    }

    private void shut() {
        if(dataInputStream!=null){
            try {
                dataInputStream.close();
            }catch (IOException e){

            }
        }
    }
}
