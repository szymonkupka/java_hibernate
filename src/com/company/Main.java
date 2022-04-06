package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public  class Main {
   /* public static void f(StudentCondition sc) {
        int i = 0;

        if (sc == StudentCondition.CHORY)
            System.out.println("oks");


    }*/

    public static void main(String[] args) {


       Class a1 = new Class("klasa a1", 5);
       a1.addStudent(new Student("can","Naj",StudentCondition.CHORY,1999, 61.00));
       a1.addStudent(new Student("an","Aaa1",StudentCondition.CHORY,2000, 2.00));
       a1.addStudent(new Student("ban","Kupka1",StudentCondition.OBECNY,2000, 5.00));
       a1.addStudent(new Student("fan","Nasz",StudentCondition.NIEOBECNY,2000, 1.00));
       a1.addStudent(new Student("zan","Nasz123",StudentCondition.ODRABIAJACY  ,2000, 10.00));
       //a1.removeStudent("zan","Nasz123");

       List<Student> sePoi = a1.sortByPoints();
       System.out.println("\nSORTOWANIE PO IMIENIU :");
       sePoi = a1.sortByName();
       for(Student st : sePoi)
           st.print();

       System.out.println("\nSORTOWANIE PO PUNKTACH :");
       sePoi = a1.sortByPoints();
       for(Student st : sePoi)
           st.print();

       System.out.println("\nMAX ILOSC PUNKTOW W KLASIE  : ");
       System.out.println(a1.max() +"\n");
       System.out.println("zmiana danych dla osoby z najmniejsza punktow: ");
        a1.addPoints( a1.students_list.get(0),0.7);
       System.out.println(a1.students_list.get(0).punkty);
       a1.changeCondition(a1.students_list.get(0), StudentCondition.OBECNY);
       System.out.println(a1.students_list.get(0).status +"\n");
       System.out.println("odbieramy punkty");
       a1.removePoints(a1.students_list.get(0),0.7);
       System.out.println(a1.students_list.get(0).punkty);

       System.out.println("\nuzycie getStudent i print");
       Student s = a1.getStudent(a1.students_list.get(0));
       s.print();
       System.out.println("\nuzycie serch (zmiana na najlepzego)");
       s = a1.search("Naj");
       s.print();

       System.out.println("\nuzycie searchPartial");
       List<Student> sl1 = a1.searchPartial("1");
       for(Student st : sl1)
           st.print();
       System.out.println("\nuzycie  countByCondition");
       System.out.println("chorych jest:" +  a1.countByCondition(StudentCondition.CHORY));

       System.out.println("\nuzycie summary");
       a1.summary();

        ClassContainer cc = new ClassContainer(a1);
       // cc.addClass(a1.nazwa,a1.max);
        cc.addClass("Nowa", 5);
       cc.addClass("Nowa2", 4);
      // cc.grupy.get("klasa a1").max();
       System.out.println("Puste klasy: ");
       List<Class> empty = cc.findEmpty();

       for(Class a : empty)
       {
           System.out.println( a.nazwa);
       }
       System.out.println("\nsummery i remove dla classContainer: ");

       cc.removeClass("Nowa");
       cc.summary();

       new GUI(cc);
        /*JLabel l = new JLabel();
        l.setText("dlugie teeeeeeeeeeeeeeeeext");

        JPanel p1 = new JPanel();
        p1.setBackground(Color.blue);
        p1.setBounds(0,0 ,250,250);
        //p1.setPreferredSize(new Dimension(250,250));

        JPanel p2 = new JPanel();
        p2.setBackground(Color.red);
        p2.setBounds(250,0 ,250,250);

        JPanel p3 = new JPanel();
        p3.setBackground(Color.yellow);
        p3.setBounds(0,250 ,500,250);


        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(700,700);
        f.setVisible(true);
        f.add(p1);
        f.add(p2);
        f.add(p3);*/








        // write your code here
       // Student s = new Student("sz", "k", StudentCondition.CHORY, 2000, 0);

        //if( StudentCondition.CHORY == )
        //f(s.status);
    }
}

