package com.company;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Serialize{
    String shouldAdd() default "Yes";
}
public class Student implements Comparable<Student>, Serializable {

    @Serialize
    public String imie;
    @Serialize
    public String nazwisko;
    @Serialize
    public transient StudentCondition status;
    @Serialize
    public int rok_urodzenia;
    @Serialize
    public double punkty;

    public  Student(String i, String n, StudentCondition s, int r, double p){
         imie = i;
         nazwisko = n;
         status = s;
         rok_urodzenia = r;
         punkty = p;
    }
    public void print(){
        System.out.println("Informacje o studencie:");
        System.out.println("Imie: "+ this.imie);
        System.out.println("Nazwisko: "+ this.nazwisko);
        System.out.println("Status: "+ this.status);
        System.out.println("Rok urodzenia: "+ this.rok_urodzenia);
        System.out.println("Punkty: "+ this.punkty);
    }

    @Override
    public int compareTo(Student s) {

      return this.imie.compareTo(s.imie);
    }

    public List<Object> getData()
    {
        List <Object> stringData = new ArrayList<>(1);
        stringData.add(this.imie);
        stringData.add(this.nazwisko);
        stringData.add(this.status);
        stringData.add(this.rok_urodzenia);
        stringData.add(this.punkty);
        return stringData;
    }
}
