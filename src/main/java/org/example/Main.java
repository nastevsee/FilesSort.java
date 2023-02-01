package org.example;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args){
        boolean isIncrement=true;
        boolean isString=false;
        boolean inFiles=false;
        FileWriter fw=null;
        String outFileName="";
        FilesList fl = new FilesList();
        for (int i=0;i<args.length;i++){
            if (inFiles){
                FileQueue fq = new FileQueue(args[i],isIncrement,isString);
                fl.add(fq);
            } else{
                String curArg=args[i];
                switch (curArg){
                    case "-a":
                        isIncrement=true;
                        break;
                    case "-d":
                        isIncrement=false;
                        break;
                    case "-i":
                        isString=false;
                        break;
                    case "-s":
                        isString=true;
                        break;
                    default:
                        // Имя выходного файлв
                        outFileName=curArg;
                        inFiles=true;
                }
            }
         }
        try {
            fw=new FileWriter(outFileName,false);
        } catch (Exception e){
            System.out.println("Open output file error");
            System.out.println(e.fillInStackTrace());
            return;
        }

        if (isString){
            while (fl.searchMinMaxStr(isIncrement)){
                try{
                    fw.write(fl.getFoundetStr());
                    fw.write("\n");
                } catch (Exception e){
                    System.out.println("Write out file error");
                    System.out.println(e.fillInStackTrace());
                }
            }
        } else {
            while (fl.searchMinMaxInt(isIncrement)){
                try {
                    fw.write(Integer.toString(fl.getFoundetInt()));
                    fw.write("\n");
                } catch (Exception e){
                    System.out.println("Write out file error");
                    System.out.println(e.fillInStackTrace());
                }
            }
        }
        try{
            fw.close();
        } catch (Exception e) {
            System.out.println("Close out file error");
            System.out.println(e.fillInStackTrace());
            return;
        }
    }
}
