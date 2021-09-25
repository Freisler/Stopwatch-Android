package com.freisler_stopky.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

public class LastLaps {
    private final Context context;
    private final String filename;
    private final FileOperations fileOperations;
    private ArrayList<String> lapsArray;

    public LastLaps(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        fileOperations = new FileOperations();
    }

    public ArrayList<String> getLapsArray() {
        String data = fileOperations.readFromFile(context, filename);
        String[] laps = data.split("\n");

        lapsArray = new ArrayList<>();
        Collections.addAll(lapsArray, laps);

        cutArray(lapsArray);
        //Log.d("STOPWATCH", String.valueOf(lapsArray));

        cutAndOverwriteFile();

        return lapsArray;
    }

    private void cutArray(ArrayList<String> array) {
        Collections.reverse(array);
        int arraySize = array.size();
        if(arraySize > 20)
            array.subList(20, arraySize).clear();
    }

    private void cutAndOverwriteFile() {
        ArrayList<?> cutArrayOverwrite = (ArrayList<?>)lapsArray.clone();
        Collections.reverse(cutArrayOverwrite);

        StringBuilder sb = new StringBuilder();
        for(Object s : cutArrayOverwrite) {
            sb.append(s);
            sb.append("\n");
        }
        fileOperations.writeToFile(context, filename, sb.toString(), false);
    }
}
