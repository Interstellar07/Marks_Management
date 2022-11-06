package com.zaidisam.to_do;

public class ToDoModel {
   public String totalques,totalstud,exmtype;

    public ToDoModel()
    {

    }

    public ToDoModel(String totalques,String totalstud,String exmtype) {
        this.totalques = totalques;
        this.totalstud = totalstud;
        this.exmtype = exmtype;
    }

    public String getTotalques() {
        return totalques;
    }

    public void setTotalques(String totalques) {
        this.totalques = totalques;
    }

    public String getTotalstud() {
        return totalstud;
    }

    public void setTotalstud(String totalstud) {
        this.totalstud = totalstud;
    }

    public String getExmtype() {
        return exmtype;
    }

    public void setExmtype(String exmtype) {
        this.exmtype = exmtype;
    }
}
