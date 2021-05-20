package com.leyou.search.client;

public class Single {

    private Single(){ }

    private static  Single single = null;

    public static synchronized Single  getInstance(){
        if(single==null){
             single=new Single();
        }
        return single;
    }
}
