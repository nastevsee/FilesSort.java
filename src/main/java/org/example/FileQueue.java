package org.example;

import java.io.File;
import java.util.Scanner;

public class FileQueue {
    private Scanner scanner;
    private File file;
    private int intValue;
    private String strValue="";
    private boolean fileOpened,inicialized,isIncrement,isString;
    public FileQueue(String filename,boolean isIncrement,boolean isString){
        try {
            this.file = new File(filename);
            this.scanner = new Scanner(file);
            this.fileOpened = true;
            this.isIncrement = isIncrement;
            this.isString = isString;
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error open file "+filename);
            //System.out.println(e.fillInStackTrace());
        }
    }

    public boolean readNext(){
        if (this.isString){
            return readNextString();
        } else {
            return readNextInt();
        }
    }
    private boolean readNextString(){
        if (!this.fileOpened){return false;}
        String currentSrtValue=this.strValue;
        while (scanner.hasNext()) {
                this.strValue = scanner.next();
                if (isIncrement && (this.strValue.compareTo(currentSrtValue)<0) && inicialized){continue;}
                if (!isIncrement && (this.strValue.compareTo(currentSrtValue)>0) && inicialized){continue;}
                this.inicialized=true;
                return true;
        }
        return false;
    }

    private boolean readNextInt(){
        if (!this.fileOpened){return false;}
        int currentIntValue=this.intValue;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()){
                this.intValue = scanner.nextInt();
                if (isIncrement && (this.intValue<currentIntValue) && inicialized){continue;}
                if (!isIncrement && (this.intValue>currentIntValue) && inicialized){continue;}
                this.inicialized=true;
                return true;
            } else {
                scanner.next();
            }
        }
        return false;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }
}
