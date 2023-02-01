package org.example;

import java.util.ArrayList;

public class FilesList extends ArrayList {
    Integer e=0;
    private int foundetInt;
    private String foundetStr="";
    public boolean add(FileQueue fq){
        if (fq.readNext()){
            return super.add(fq);
        } else {
            return false;
        }
    }

    public boolean searchMinMaxStr(boolean isMinimal){
        FileQueue cfq;
        boolean inicialized=false;
        boolean condition;
        String currentStr;
        int foundetIntIndex=0;
        for (int i=0;i<this.size();i++){
            cfq = (FileQueue)this.get(i);
            currentStr = cfq.getStrValue();
            condition = ((isMinimal && currentStr.compareTo(this.foundetStr)<0)||(!isMinimal && currentStr.compareTo(this.foundetStr)>0));
            if (condition||!inicialized){
                inicialized=true;
                this.foundetStr=currentStr;
                foundetIntIndex=i;
            }
        }
        if (inicialized){
            cfq = (FileQueue)this.get(foundetIntIndex);
            if (!cfq.readNext()){
                this.remove(foundetIntIndex);
            }
        }
        return inicialized;
    }

    public boolean searchMinMaxInt(boolean isMinimal){
        FileQueue cfq;
        boolean inicialized=false;
        boolean condition;
        int currentInt;
        int foundetIntIndex=0;
        for (int i=0;i<this.size();i++){
            cfq = (FileQueue)this.get(i);
            currentInt = cfq.getIntValue();
            condition = ((isMinimal && currentInt<this.foundetInt)||(!isMinimal && currentInt>this.foundetInt));
            if (condition||!inicialized){
                inicialized=true;
                this.foundetInt=currentInt;
                foundetIntIndex=i;
            }
        }
        if (inicialized){
            cfq = (FileQueue)this.get(foundetIntIndex);
            if (!cfq.readNext()){
                this.remove(foundetIntIndex);
            }
        }
        return inicialized;
    }

    public int getFoundetInt() {
        return foundetInt;
    }

    public String getFoundetStr() { return foundetStr; }
}
