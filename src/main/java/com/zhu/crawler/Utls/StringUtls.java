package com.zhu.crawler.Utls;

public class StringUtls {

    public static  double sub(String str){
        String  result =str;
        double  db =0;
        if(str.indexOf("万")>0){
            int index = str.indexOf("万");
            result = str.substring(0,index);
            db = Double.parseDouble(result) * 10000;
        }else
            db= Double.parseDouble(str);
        return db;
    }


}
