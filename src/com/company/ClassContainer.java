package com.company;

import java.util.*;

public class ClassContainer implements TableData {

    Map<String, Class > grupy ;//= new HashMap<>();;

    public ClassContainer(Class c)
    {
        this.grupy = new HashMap<>();
        this.grupy.put(c.nazwa, c);

    }
    void addClass(String n , double max){
         grupy.put(n,new Class(n, (int) max ));
        System.out.printf("new calass added");

    }
    void modifyClass(String old_n, String new_n , int new_max){
        grupy.get(old_n).changeClass(new_n, new_max);
        System.out.println("lets modify");
    }

    void removeClass(String n){
        if( grupy.remove(n) ==null)         //lub try catch
            System.out.println("nie ma klasy o tej nazwie");
        else
            System.out.println("usunieto klase: " + n);

    }
    public List<Class> findEmpty() {

        List<Class> l = new ArrayList<>();
        for(Class c : grupy.values())
        {
            if(c.students_list.size()==0) //.
            {
                l.add(c);
            }
        }
        return l;


    }
    void summary(){
        for(Class c :this.grupy.values())
        {
            double zapelnienie = ((double) c.students_list.size() / (double) c.max) *100;
            System.out.println("klasa " + c.nazwa + "jest zapelniona w " + zapelnienie + "% " );
        }

    }
    public String [] getLabels(){
        return new String[]{"Class name", "Number of students", "Max num of students", "Occupancy [%]"};
    }

    public List<List<Object>> getData(){

        List <List<Object>> stringData = new ArrayList<List<Object>>(1);

        for(Class c: grupy.values()){
            stringData.add(c.getDataForClassContainer());
        }


        return stringData;
    }


}
