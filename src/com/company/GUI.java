package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class GUI implements  ActionListener {
    JPanel panel;
    JButton addButton;
    JButton removeButton;
    JButton modifyButton;
    JButton sortButton;
    JButton serializeButton;
    JButton saveButton;
    JButton show_hide_StudentsButton;
    Boolean isClassesTableShowed;
    JFrame frame;
    JTable table;
    ClassContainer classes;
    String nameOfDisplaiedGropu;


    public GUI (ClassContainer classes){

        this.classes = classes;
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        sortButton = new JButton("Sort");
        modifyButton = new JButton("Modify");
        serializeButton = new JButton("Serialize");
        saveButton = new JButton("Save");
        show_hide_StudentsButton = new JButton("Show students in group");

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        sortButton.addActionListener(this);
        modifyButton.addActionListener(this);
        serializeButton.addActionListener(this);
        saveButton.addActionListener(this);
        show_hide_StudentsButton.addActionListener(this);


        panel = new JPanel();
        //panel.setLayout( new GridBagLayout());

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(modifyButton);
        panel.add(sortButton);
        panel.add(serializeButton);
        panel.add(saveButton);
        panel.add(show_hide_StudentsButton);

        table = new JTable(new TableTemplate(classes));
        table.setPreferredSize(new Dimension(450,63));
        table.setFillsViewportHeight(true);

        //table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );


        JScrollPane tableScroll = new JScrollPane(table);
        panel.add(tableScroll);
        isClassesTableShowed = true;


        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setVisible(true);
        frame.add(panel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

           if (e.getSource() == show_hide_StudentsButton ) {

               if (table.getSelectedRow() != -1 && isClassesTableShowed) {
                   isClassesTableShowed = false;
                   show_hide_StudentsButton.setText("Show classes");
                   nameOfDisplaiedGropu = ((String) table.getValueAt(table.getSelectedRow(),0));
                   table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));

               }
               else if( !isClassesTableShowed){
                   isClassesTableShowed = true;
                   show_hide_StudentsButton.setText("Show students in group");
                   table.setModel(new TableTemplate(classes));
               }
           }
        if (e.getSource() == serializeButton){
            try {
            String filename =(classes.grupy.get(nameOfDisplaiedGropu).nazwa + ".bin");
           // table.getModel()
           // table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));

            TableTemplate obeject = new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu));


                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(obeject);

                out.close();
                file.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "cos poszlo nie tak/ moze robisz serializacje na liscie klas zamiest na danej klasie","erroe", JOptionPane.ERROR_MESSAGE);
            }


        }
        if (e.getSource() == saveButton ){
            try {
                java.lang.Class<Class> reflectionClass = Class.class;
                String nameGroup = classes.grupy.get(nameOfDisplaiedGropu).nazwa;

                    classes.grupy.get(nameOfDisplaiedGropu).writeToCsv(nameGroup);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "nie robimy tej operacji na liscie klas","erroe", JOptionPane.ERROR_MESSAGE);
                }
           /*     List<Student> stList = Class.readFromCsv(nameGroup + ".csv");
                System.out.println("after reading:");
                for (Student s : stList) {
                    s.print();*/
                }





            if (e.getSource() == addButton) {
                ///
                if(show_hide_StudentsButton.getText().toString().equals("Show students in group") ){
                    JTextField ClassName = new JTextField();
                    JTextField Max = new JTextField();
                    JPanel addingPanel = new JPanel(new GridLayout(2, 2, 2, 2));


                    addingPanel.add(new JLabel("Class Name"));
                    addingPanel.add(ClassName);
                    addingPanel.add(new JLabel("Class Max Occupancy"));
                    addingPanel.add(Max);
                    JOptionPane.showMessageDialog(
                            frame, addingPanel, "Add Class", JOptionPane.QUESTION_MESSAGE);
                    //Arrays.asList(ClassName.getText(), Max.getText());
                    classes.addClass((String) ClassName.getText(), Integer.parseInt((String) Max.getText()));
                    table.setModel(new TableTemplate(classes));
                    //table = new JTable(new TableTemplate(classes));
                    //table.revalidate();
                }
                else{

                    JTextField FirstName = new JTextField();
                    JTextField LastName = new JTextField();
                    JTextField BirthYear = new JTextField();
                    JTextField TotalPoints = new JTextField();
                    JComboBox Condition =  new JComboBox(StudentCondition.values());
                    JPanel addingPanel = new JPanel(new GridLayout(5, 2, 2, 2));


                    addingPanel.add(new JLabel("Students first Name"));
                    addingPanel.add(FirstName);
                    addingPanel.add(new JLabel("Students last name"));
                    addingPanel.add(LastName);
                    addingPanel.add(new JLabel("Students birth date"));
                    addingPanel.add(BirthYear);
                    addingPanel.add(new JLabel("Students total points"));
                    addingPanel.add(TotalPoints);
                    addingPanel.add(new JLabel("Students condition"));
                    addingPanel.add(Condition);

                    JOptionPane.showMessageDialog(
                            frame, addingPanel, "Add Studetn", JOptionPane.QUESTION_MESSAGE);
                    //Arrays.asList(ClassName.getText(), Max.getText());
                    classes.grupy.get(nameOfDisplaiedGropu)
                            .addStudent(new Student(
                                            (String) FirstName.getText(),
                                            (String) LastName.getText(),
                                            (StudentCondition) Condition.getSelectedItem(),
                                            Integer.parseInt((String) BirthYear.getText()),
                                            Double.parseDouble((String) TotalPoints.getText())));

                    table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));


                }



            }

          if (e.getSource() == removeButton) {
            // int selectedRow = table.getSelectedRow();
              int x = table.getSelectedRow();
              if(show_hide_StudentsButton.getText().toString().equals("Show students in group") ) {
                  if (table.getSelectedRow() != -1) {
                      classes.removeClass((String) table.getValueAt(table.getSelectedRow(), 0));

                      table.setModel(new TableTemplate(classes));
                  }
              }
                  else{

                      if (table.getSelectedRow() != -1){

                          classes.grupy.get(nameOfDisplaiedGropu).students_list.remove(x);
                         // System.out.println(nameOfDisplaiedGropu + " taaaaaaaak " + x);
                          table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));

                      }
                  }

          }


          if (e.getSource() == modifyButton) {

              int x = table.getSelectedRow();

                if (table.getSelectedRow() != -1 && show_hide_StudentsButton.getText().toString().equals("Show students in group")) {

                    JTextField ClassName = new JTextField((String) table.getValueAt(table.getSelectedRow(), 0));
                    JTextField Max = new JTextField(Integer.toString((Integer) table.getValueAt(table.getSelectedRow(), 2)));
                    JPanel addingPanel = new JPanel(new GridLayout(2, 2, 2, 2));


                    addingPanel.add(new JLabel("Class Name"));
                    addingPanel.add(ClassName);
                    addingPanel.add(new JLabel("Class Max Occupancy"));
                    addingPanel.add(Max);
                    JOptionPane.showMessageDialog(
                            frame, addingPanel, "Modify Class", JOptionPane.QUESTION_MESSAGE);
                    //Arrays.asList(ClassName.getText(), Max.getText());
                    // classes.addClass((String)ClassName.getText(),Integer.parseInt((String)Max.getText()));
                   // classes.grupy.get((String) (table.getValueAt(table.getSelectedRow(), 0))).max = Integer.parseInt((String) Max.getText());
                   // classes.grupy.get((String) (table.getValueAt(table.getSelectedRow(), 0))).nazwa = (String) ClassName.getText();
                    //classes.grupy.get((String) (table.getValueAt(table.getSelectedRow(), 0))).changeClass((String) ClassName.getText(),Integer.parseInt((String) Max.getText()));
                   // classes.modifyClass((String) (table.getValueAt(table.getSelectedRow(), 0)),(String) ClassName.getText(),Integer.parseInt((String) Max.getText()));


                    Class temp = classes.grupy.get((String) table.getValueAt(table.getSelectedRow(), 0));
                    temp.max = Integer.parseInt((String) Max.getText());
                    temp.nazwa = (String) ClassName.getText();
                    classes.removeClass((String) table.getValueAt(table.getSelectedRow(), 0));
                    classes.grupy.put(temp.nazwa,temp);


                    table.setModel(new TableTemplate(classes));


                }
                else{
                    if (table.getSelectedRow() != -1){
                        JTextField FirstName = new JTextField((String) table.getValueAt(table.getSelectedRow(), 0));
                        JTextField LastName = new JTextField((String) table.getValueAt(table.getSelectedRow(), 1));
                        JTextField BirthYear = new JTextField(Integer.toString((Integer) table.getValueAt(table.getSelectedRow(), 3)));
                        JTextField TotalPoints = new JTextField(Double.toString((Double) table.getValueAt(table.getSelectedRow(), 4)));
                        JComboBox Condition =  new JComboBox(StudentCondition.values());
                        Condition.setSelectedItem((StudentCondition) table.getValueAt(table.getSelectedRow(), 2));
                        JPanel addingPanel = new JPanel(new GridLayout(5, 2, 2, 2));


                        addingPanel.add(new JLabel("Students first Name"));
                        addingPanel.add(FirstName);
                        addingPanel.add(new JLabel("Students last name"));
                        addingPanel.add(LastName);
                        addingPanel.add(new JLabel("Students birth date"));
                        addingPanel.add(BirthYear);
                        addingPanel.add(new JLabel("Students total points"));
                        addingPanel.add(TotalPoints);
                        addingPanel.add(new JLabel("Students condition"));
                        addingPanel.add(Condition);

                        JOptionPane.showMessageDialog(
                                frame, addingPanel, "Modify Studetn", JOptionPane.QUESTION_MESSAGE);
                        //Arrays.asList(ClassName.getText(), Max.getText());


                        classes.grupy.get(nameOfDisplaiedGropu)
                                .addStudent(new Student(
                                        (String) FirstName.getText(),
                                        (String) LastName.getText(),
                                        (StudentCondition) Condition.getSelectedItem(),
                                        Integer.parseInt((String) BirthYear.getText()),
                                        Double.parseDouble((String) TotalPoints.getText())));
                        classes.grupy.get(nameOfDisplaiedGropu).students_list.remove(x);

                        table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));
                    }

              }



          }


         if (e.getSource() == sortButton && show_hide_StudentsButton.getText().toString().equals("Show classes")) {

             classes.grupy.get(nameOfDisplaiedGropu).sortByName();
             table.setModel(new TableTemplate(classes.grupy.get(nameOfDisplaiedGropu)));
        }

    }

}
