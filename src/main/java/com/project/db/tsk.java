package com.project.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tsk {
    private IntegerProperty taskid ;
    private StringProperty titel;
    private  StringProperty ddtask;
    private  StringProperty tid;
    tsk(Integer taskid,String titel,String ddtask,String tid){
        this.taskid=new SimpleIntegerProperty(taskid);
        this.titel=new SimpleStringProperty(titel);
        this.ddtask=new SimpleStringProperty(ddtask);
        this.tid=new SimpleStringProperty(tid);
    }
    public IntegerProperty gettaskid(){
        return taskid;
    }

    public StringProperty gettitel(){
        return titel;
    }

    public StringProperty getddtask(){
        return ddtask;
    }

    public StringProperty gettid(){
        return tid;
    }
}
