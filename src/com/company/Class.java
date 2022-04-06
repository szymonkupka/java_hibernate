package com.company;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Class  implements   TableData{
    String nazwa;
    public int max ;
    List<Student> students_list = new ArrayList<>();

    public Class(String n, int m) {
        nazwa =n;
        max = m;
    }
    public void changeClass(String n, int m) {
        this.nazwa =n;
        this.max = m;
        System.out.println("it shoud be modified");
    }
   // public void removeStudent(String imie, String nazwisko){
   //     int i = 0;
   //     for(Student a : students_list)
   //     {
   //         if(a.nazwisko.equals(nazwisko) && a.imie.equals(imie)) {
   //             this.students_list.remove(i);
   //         }
   //         i++;
   //     }
   // }

    public boolean addStudent(Student s){

        for(Student a : students_list)
        {
            if(a.nazwisko.equals(s.nazwisko) && a.imie.equals(s.imie)) {
                System.err.println("Ten student jest juz na liscie");
                return false;
            }
        }
        if(students_list.size()< max){
            students_list.add(s);
            return true;
        }
        else
            System.out.println("Maksymalna liczba miejsc została przekroczona");
        return false;


    }
     void addPoints(Student s, double p){
        s.punkty+=p;
     }

    public Student getStudent(Student s) {

       int i = students_list.indexOf(s);
        if(i ==-1)
        {
            System.out.println("nie ma takiego studenta");
            return null;
        }else
        {
            if( students_list.get(i).punkty <= 0.0)
            {
                students_list.remove(i);
                return null;
            }
            else {
                Student output = students_list.get(i);
                students_list.remove(i);
                return output;
            }
        }



     }
    void changeCondition(Student s, StudentCondition sc){s.status = sc;}
    void removePoints(Student s, double p){
        s.punkty = s.punkty - p;
    }

    public Student search(String n){

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }

        };
        for(Student s : students_list){

            if(comparator.compare(s.nazwisko, n) == 0) {
                return s;
            }
        }
        System.out.println("nie znaleziona takiego nazwiska");
        return null;
    }
    public List<Student> searchPartial(String s){
         List<Student> wynik = new ArrayList<>();
        for(Student st : students_list){
            if(st.nazwisko.contains(s)||st.imie.contains(s))
                  wynik.add(st);
        }
        return wynik;

    }
    public int countByCondition(StudentCondition sc){
        int i = 0;
        for(Student st : students_list){
            if( sc == st.status)
                i++;

        }
             return  i;
    }
    void summary(){
        for(Student st : students_list) {
            st.print();
        }
    }

    public List<Student> sortByName() {
        List<Student> l = students_list;
        Collections.sort(l);
        return l ;
    }

    public List<Student> sortByPoints() {
        List<Student> l = students_list;
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if(s1.punkty==s2.punkty)
                    return 0;
                else if(s1.punkty > s2.punkty)
                    return 1;
                else
                    return -1;

            }
        };
        //Collections.sort(l,comparator);
        l.sort(comparator);

        return l;
    }
    public double max(){
        Comparator<Student> comparator = (s1, s2) -> {
            if(s1.punkty==s2.punkty)
                return 0;
            else if(s1.punkty > s2.punkty)
                return 1;
            else
                return -1;

            //return (int) (s1.punkty-s2.punkty);
        };
        return Collections.max(students_list,comparator).punkty;
    }


    public String [] getLabels(){
        return new String[]{"First name", "Last name", "Condition", "Birth year", "Total points"};
    }
    public List<List<Object>> getData(){
        List <List<Object>> stringData = new ArrayList<>(1);
        for(Student s : this.students_list){
            stringData.add(s.getData());
        }
        return stringData;
    }

    public List<Object> getDataForClassContainer(){
        List <Object> stringData = new ArrayList<>(1);
        stringData.add(this.nazwa);
        stringData.add(this.students_list.size());
        stringData.add(this.max);
        stringData.add(((double) students_list.size() / (double) max) *100);

        return stringData;


    }
    private boolean shouldWrite(Object o) throws Exception {
        boolean temp = true;
        java.lang.Class c = o.getClass();

        for (Field f : c.getDeclaredFields()) {

            if (f.getAnnotation(Serialize.class).shouldAdd() == "No")
                temp = false;

        }
        return temp;
    }

    public void writeToCsv(String namefile) throws Exception {
        java.lang.Class refClass = Student.class;


        FileWriter csvWriter = null;

            csvWriter = new FileWriter(namefile +".csv");

        try {
            String [] labels = this.getLabels();
            for(String label: labels){
                csvWriter.append( label );
                csvWriter.append(",");
            }
            csvWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Student student : this.students_list) {
            try {
                if(this.shouldWrite(student)) {
                    csvWriter.append(student.imie);
                    csvWriter.append(",");
                    csvWriter.append(student.nazwisko);
                    csvWriter.append(",");
                    csvWriter.append(student.status.toString());
                    csvWriter.append(",");
                    csvWriter.append(Integer.toString(student.rok_urodzenia));
                    csvWriter.append(",");
                    csvWriter.append(Double.toString(student.punkty));
                    csvWriter.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Student> readFromCsv(String namefile){
        List<Student> list = new ArrayList<>(1);
        java.lang.Class refClass = Student.class;
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(namefile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row;
        try {
            row = csvReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

            try {
                row = csvReader.readLine();
                Constructor constructor= refClass.getConstructor(String.class, String.class, StudentCondition.class, int.class, double.class);
                if (row == null) break;
                String[] data = row.split(",");
                String name = data[0];
                String second = data[1];
                StudentCondition cond;

                if(Objects.equals(data[2], "obecny")){
                    cond = StudentCondition.OBECNY;
                }
                else if(Objects.equals(data[2], "nieobecny"))
                {
                    cond = StudentCondition.NIEOBECNY;
                }
                else if(Objects.equals(data[2], "chory")){
                    cond = StudentCondition.CHORY;
                }
                else if(Objects.equals(data[2], "odrabiajacy")){
                    cond =  StudentCondition.ODRABIAJACY;
                }
                else
                    cond =  StudentCondition.NIEOBECNY;




                int birthYear = Integer.parseInt(data[3]);
                double points = Double.parseDouble(data[4]);
                list.add((Student) constructor.newInstance(name, second, cond, birthYear, points));



            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // do something with the data
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
