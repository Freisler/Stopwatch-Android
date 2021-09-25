package com.freisler_stopky.Model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOperations {
    public void writeToFile(Context context, String fileName, String data, boolean append) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        try{
            File outFile = new File(context.getExternalFilesDir(null), fileName);
            FileOutputStream out = new FileOutputStream(outFile, append);
            out.write(data.getBytes());
            out.flush();
            out.close();
            Log.d("STOPWATCH", "Writing to file " + fileName + " complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(null), fileName);
        StringBuilder data = new StringBuilder();
        if(!file.exists()) {
            Log.d("STOPWATCH", "File " + fileName + " NOT found.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader (fis);
                BufferedReader br = new BufferedReader (isr);
                String line;
                while ((line = br.readLine()) != null) {
                    data.append(line);
                    data.append('\n');
                }
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("STOPWATCH", "Reading from file " + fileName + " complete.");
        return data.toString();
    }
}
