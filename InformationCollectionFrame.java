/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendancesystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author F.C
 */
public class InformationCollectionFrame extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form InformationCollectionFrame
     */
    String select;
    String st = "No";
    String stCc = "No";

    public InformationCollectionFrame() {
        initComponents();
        setTitle("ADD NEW INFORMATION");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ButtonGroup group;
        group = new ButtonGroup();
        group.add(deptJRadioButton1);
        group.add(courseJRadioButton);
        group.add(studentJRadioButton);
        group.add(teacherJRadioButton);

        visiableMethod();
        fillComboboxDepartment();

        backJButton.addActionListener(this);
        okJButton.addActionListener(this);
        deptJRadioButton1.addActionListener(this);
        courseJRadioButton.addActionListener(this);
        studentJRadioButton.addActionListener(this);
        teacherJRadioButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backJButton) {
            setVisible(false);
            AuthorHandlingFrame aHF = new AuthorHandlingFrame();

        }
        if (e.getSource() == deptJRadioButton1) {
            visiableMethod();
            newDeptNameJTextField.setVisible(true);
            newDeptTitleJLabel.setVisible(true);
            okJButton.setVisible(true);

            select = "department";

        }
        if (e.getSource() == courseJRadioButton) {
            visiableMethod();
            courseCodeJLabel.setVisible(true);
            courseCodeJTextField.setVisible(true);
            courseTitleJLabel.setVisible(true);
            courseTitleJTextField.setVisible(true);
            creditJLabel.setVisible(true);
            creditJTextField.setVisible(true);
            deptNameJLabel.setVisible(true);
            deptNameJComboBox.setVisible(true);
            okJButton.setVisible(true);

            select = "course";
            fillComboboxDepartment();

        }
        if (e.getSource() == studentJRadioButton) {
            visiableMethod();
            studentDeptJLabel1.setVisible(true);
            studentDeptJComboBox.setVisible(true);
            studentIdJLabel1.setVisible(true);
            studentIdJTextField1.setVisible(true);
            studentNameJLabel1.setVisible(true);
            studentNameJTextField1.setVisible(true);
            okJButton.setVisible(true);

            select = "student";
            fillComboboxDepartment();

        }
        if (e.getSource() == teacherJRadioButton) {
            visiableMethod();
            teacherDeptJLabel.setVisible(true);
            teacherDeptJComboBox.setVisible(true);
            teacherIdJLabel.setVisible(true);
            teacherIdJTextField.setVisible(true);
            teacherNameJLabel.setVisible(true);
            teacherNameJTextField.setVisible(true);
            okJButton.setVisible(true);

            select = "teacher";
            fillComboboxDepartment();

        }
        if (e.getSource() == okJButton) {
            if (select.equals("student")) {
                checkStudentMethod();
                if (st.equals("No")) {
                    studentInformationMethod();
                } else {

                    JOptionPane.showMessageDialog(rootPane, "This is already Added...");
                    st = "No";
                }

            } else if (select.equals("department")) {
                checkDepartmentMethod();
                if (st.equals("No")) {
                    departmentInformationMethod();
                    fillComboboxDepartment();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "This is already Added...");
                    fillComboboxDepartment();
                    st = "No";
                }

            } else if (select.equals("course")) {
                checkCourseCodeMethod();
                if (st.equals("No")) {
                    courseInformationMethod();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "This is already Added...");
                    st = "No";

                }

            } else {
                checkTeacherMethod();
                if (st.equals("No")) {
                    teacherInformationMethod();
                } else {

                    JOptionPane.showMessageDialog(rootPane, "This is already Added...");
                    st = "No";
                }

            }
        }
    }

    private void checkCourseCodeMethod() {
        String cC = courseCodeJTextField.getText();
        String upCC = cC.toUpperCase();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `course`";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {

                String cId = obj.rs.getString("C_code");
                if (upCC.equals(cId)) {
                    st = "yes";
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void checkStudentMethod() {
        String id = studentIdJTextField1.getText();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student`";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {

                String sId = obj.rs.getString("S_id");
                if (id.equals(sId)) {
                    st = "yes";
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void checkTeacherMethod() {
        String id = teacherIdJTextField.getText();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher`";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {

                String tId = obj.rs.getString("T_id");
                if (id.equals(tId)) {
                    st = "yes";
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void checkDepartmentMethod() {
        String nm = newDeptNameJTextField.getText().toUpperCase();
        String name;
        ConnectorClass obj = new ConnectorClass();
        String query = "slect * from department";
        try {
            while (obj.rs.next()) {

                name = obj.rs.getString(1);
                if (nm.equals(name)) {
                    st = "yes";

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void departmentInformationMethod() {

        String newdept = newDeptNameJTextField.getText().toUpperCase();

        if (newdept.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up All Accurately.....");
        } else {
            JOptionPane.showMessageDialog(rootPane, "YOUR DATA IS SAVED..");

            ConnectorClass obj = new ConnectorClass();
            String query = "INSERT INTO department " + "VALUES ('" + newdept + "')";
            try {

                obj.stmt.executeUpdate(query);
                obj.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void courseInformationMethod() {

        String newCCode = courseCodeJTextField.getText().toUpperCase();
        String newCTitle = courseTitleJTextField.getText();
        String newCredit = creditJTextField.getText();
        String courseDName = deptNameJComboBox.getSelectedItem().toString();

        boolean check1 = isInteger(newCredit);

        if ((check1 == true) && (!newCredit.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Credit is not an Float..");
        } else if (newCCode.equals("") || newCTitle.equals("") || newCredit.equals("") || (courseDName.equals("")) || (courseDName.equals("Select An Item"))) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up All Accurately.....");
        } else {
            JOptionPane.showMessageDialog(rootPane, "YOUR DATA IS SAVED..");

            ConnectorClass obj = new ConnectorClass();
            String query = "INSERT INTO course " + "VALUES ('" + newCCode + "', '" + newCTitle + "', '" + courseDName + "' , '" + newCredit + "')";
            try {

                obj.stmt.executeUpdate(query);
                obj.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            deptNameJComboBox.removeAllItems();
            courseCodeJTextField.setText("");
            courseTitleJTextField.setText("");
            creditJTextField.setText("");
            fillComboboxDepartment();
        }

    }

    private static boolean isInteger(String nm) {
        try {
            Integer.parseInt(nm);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private void studentInformationMethod() {

        String sDept = studentDeptJComboBox.getSelectedItem().toString();
        String sId = studentIdJTextField1.getText();
        String sName = studentNameJTextField1.getText();

        boolean check = isLong(sId);

        if ((check == false) && (!sId.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Id is not an Integer..");
        } else if (sDept.equals("") || sId.equals("") || sName.equals("") || (sDept.equals("Select one"))) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up All Accurately.....");
        } else {
            String sUser = "NEUB$";
            String sPass = sId;

            ConnectorClass obj = new ConnectorClass();
            String query = "INSERT INTO student " + "VALUES ('" + sName + "', '" + sId + "', '" + sDept + "' , '" + sUser + "' , '" + sPass + "')";
            try {

                obj.stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "YOUR DATA IS SAVED....\nYour User Name is : " + sUser + "\nYour Password is : " + sPass);
                obj.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            studentIdJTextField1.setText("");
            studentNameJTextField1.setText("");
            studentDeptJComboBox.removeAllItems();
            fillComboboxDepartment();
        }
    }

    private String randomMethod() {
        Random rn = new Random();
        long sln = 4 + (rn.nextInt(100) + 1);

        String slnRan = "" + sln;
        return slnRan;
    }

    private void teacherInformationMethod() {

        String tDept = teacherDeptJComboBox.getSelectedItem().toString();
        String tId = teacherIdJTextField.getText();
        String tName = teacherNameJTextField.getText();

        boolean check = isLong(tId);

        if ((check == false) && (!tId.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Id is not an Long..");
        } else if (tDept.equals("") || tId.equals("") || tName.equals("") || (tDept.equals("Select one"))) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up All Accurately.....");
        } else {

            String tUser = tDept + "#";
            String ran = randomMethod();
            String tPass = "NEUB" + ran;

            String ch = "yes";
            while (ch.equals("yes")) {
                ch = checkMethod(tPass);
            }

            if (ch.equals("no")) {
                ConnectorClass obj = new ConnectorClass();

                String query = "INSERT INTO teacher " + "VALUES ('" + tId + "', '" + tName + "', '" + tDept + "' , '" + tUser + "' , '" + tPass + "')";
                try {

                    obj.stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(rootPane, "YOUR DATA IS SAVED....\nYour User Name is : " + tUser + "\nYour Password is : " + tPass);
                    obj.con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                teacherIdJTextField.setText("");
                teacherNameJTextField.setText("");
                teacherDeptJComboBox.removeAllItems();
                fillComboboxDepartment();
            }

        }

    }

    private String checkMethod(String tPass) {
        int j = 0;
        int len1 = 0;
        String[] ch1 = new String[100];
        ConnectorClass obj = new ConnectorClass();

        String query1 = "SELECT * FROM `teacher`";

        String c = "no";

        try {

            obj.rs = obj.stmt.executeQuery(query1);

            while (obj.rs.next()) {

                String chPass = obj.rs.getString("T_pass");
                ch1[j] = chPass;
                j++;
            }
            len1 = j;
        } catch (SQLException ex) {
            return c;
        }
        for (int k = 0; k <= len1; k++) {
            if (tPass.equals(ch1[k])) {
                c = "yes";
                break;
            }
        }
        return c;

    }

    private static boolean isLong(String nm) {
        try {
            Long.parseLong(nm);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private void visiableMethod() {

        courseCodeJLabel.setVisible(false);
        courseTitleJLabel.setVisible(false);
        courseTitleJTextField.setVisible(false);
        creditJLabel.setVisible(false);
        creditJTextField.setVisible(false);
        deptNameJLabel.setVisible(false);
        deptNameJComboBox.setVisible(false);
        courseCodeJTextField.setVisible(false);
        newDeptNameJTextField.setVisible(false);
        newDeptTitleJLabel.setVisible(false);
        studentDeptJLabel1.setVisible(false);
        studentDeptJComboBox.setVisible(false);
        studentIdJLabel1.setVisible(false);
        studentIdJTextField1.setVisible(false);
        studentNameJLabel1.setVisible(false);
        studentNameJTextField1.setVisible(false);
        teacherDeptJLabel.setVisible(false);
        teacherDeptJComboBox.setVisible(false);
        teacherIdJLabel.setVisible(false);
        teacherIdJTextField.setVisible(false);
        teacherNameJLabel.setVisible(false);
        teacherNameJTextField.setVisible(false);
        okJButton.setVisible(false);
    }

    private void fillComboboxDepartment() {
        studentDeptJComboBox.removeActionListener(this);
        teacherDeptJComboBox.removeActionListener(this);
        deptNameJComboBox.removeActionListener(this);
        ConnectorClass obj = new ConnectorClass();

        String query = "Select * from department";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            studentDeptJComboBox.removeAllItems();
            teacherDeptJComboBox.removeAllItems();
            deptNameJComboBox.removeAllItems();

            studentDeptJComboBox.addItem("Select one");
            teacherDeptJComboBox.addItem("Select one");
            deptNameJComboBox.addItem("Select one");

            while (obj.rs.next()) {
                String dept = obj.rs.getString("Dept_name");

                studentDeptJComboBox.addItem(dept);
                teacherDeptJComboBox.addItem(dept);
                deptNameJComboBox.addItem(dept);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentDeptJComboBox.addActionListener(this);
        teacherDeptJComboBox.addActionListener(this);
        deptNameJComboBox.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        backJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        deptJRadioButton1 = new javax.swing.JRadioButton();
        courseJRadioButton = new javax.swing.JRadioButton();
        teacherJRadioButton = new javax.swing.JRadioButton();
        studentJRadioButton = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        okJButton = new javax.swing.JButton();
        courseCodeJLabel = new javax.swing.JLabel();
        courseTitleJLabel = new javax.swing.JLabel();
        deptNameJLabel = new javax.swing.JLabel();
        creditJLabel = new javax.swing.JLabel();
        teacherIdJLabel = new javax.swing.JLabel();
        teacherIdJTextField = new javax.swing.JTextField();
        teacherNameJLabel = new javax.swing.JLabel();
        teacherDeptJLabel = new javax.swing.JLabel();
        studentIdJLabel1 = new javax.swing.JLabel();
        studentIdJTextField1 = new javax.swing.JTextField();
        studentNameJLabel1 = new javax.swing.JLabel();
        studentDeptJLabel1 = new javax.swing.JLabel();
        newDeptTitleJLabel = new javax.swing.JLabel();
        creditJTextField = new javax.swing.JTextField();
        teacherNameJTextField = new javax.swing.JTextField();
        newDeptNameJTextField = new javax.swing.JTextField();
        studentNameJTextField1 = new javax.swing.JTextField();
        courseTitleJTextField = new javax.swing.JTextField();
        courseCodeJTextField = new javax.swing.JTextField();
        deptNameJComboBox = new javax.swing.JComboBox();
        studentDeptJComboBox = new javax.swing.JComboBox();
        teacherDeptJComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backJButton.setText("BACK");
        jPanel2.add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, 90, 35));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("ADD NEW INFORMATION");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 200, 50));

        deptJRadioButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        deptJRadioButton1.setForeground(new java.awt.Color(0, 153, 153));
        deptJRadioButton1.setText("Department ");
        deptJRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptJRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(deptJRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 127, -1));

        courseJRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        courseJRadioButton.setForeground(new java.awt.Color(0, 153, 153));
        courseJRadioButton.setText("Course");
        jPanel2.add(courseJRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 125, -1));

        teacherJRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherJRadioButton.setForeground(new java.awt.Color(0, 153, 153));
        teacherJRadioButton.setText("Teacher");
        jPanel2.add(teacherJRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 127, -1));

        studentJRadioButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentJRadioButton.setForeground(new java.awt.Color(0, 153, 153));
        studentJRadioButton.setText("Student");
        jPanel2.add(studentJRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 127, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        okJButton.setBackground(new java.awt.Color(0, 204, 204));
        okJButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        okJButton.setForeground(new java.awt.Color(0, 153, 153));
        okJButton.setText("OK");
        jPanel1.add(okJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 60, 30));

        courseCodeJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        courseCodeJLabel.setText("Course Code :");
        jPanel1.add(courseCodeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, 30));

        courseTitleJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        courseTitleJLabel.setText("Course Title :");
        jPanel1.add(courseTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 200, 30));

        deptNameJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        deptNameJLabel.setText("Department Name :");
        jPanel1.add(deptNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 260, 30));

        creditJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        creditJLabel.setText("Credit  :");
        jPanel1.add(creditJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, 30));

        teacherIdJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        teacherIdJLabel.setText("Teacher Id :");
        jPanel1.add(teacherIdJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 30));

        teacherIdJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jPanel1.add(teacherIdJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 190, 30));

        teacherNameJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        teacherNameJLabel.setText("Teacher Name :");
        jPanel1.add(teacherNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 300, 30));

        teacherDeptJLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        teacherDeptJLabel.setText("Dept  :");
        jPanel1.add(teacherDeptJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 300, 30));

        studentIdJLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        studentIdJLabel1.setText("Student Id :");
        jPanel1.add(studentIdJLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 305, 30));

        studentIdJTextField1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jPanel1.add(studentIdJTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 190, 30));

        studentNameJLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        studentNameJLabel1.setText("Student Name :");
        jPanel1.add(studentNameJLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 305, 30));

        studentDeptJLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        studentDeptJLabel1.setText("Dept  :");
        jPanel1.add(studentDeptJLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 305, 30));

        newDeptTitleJLabel.setText("Enter The New  Department Name :");
        jPanel1.add(newDeptTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 305, 30));

        creditJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        creditJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditJTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(creditJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 190, 30));

        teacherNameJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        teacherNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherNameJTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(teacherNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 190, 30));

        newDeptNameJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jPanel1.add(newDeptNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 190, 30));

        studentNameJTextField1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jPanel1.add(studentNameJTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 190, 30));

        courseTitleJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        courseTitleJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseTitleJTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(courseTitleJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 194, 30));

        courseCodeJTextField.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        courseCodeJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseCodeJTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(courseCodeJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 194, 30));

        deptNameJComboBox.setMaximumRowCount(1000);
        jPanel1.add(deptNameJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 194, 30));

        studentDeptJComboBox.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        studentDeptJComboBox.setMaximumRowCount(1000);
        studentDeptJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDeptJComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(studentDeptJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 194, 30));

        teacherDeptJComboBox.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        teacherDeptJComboBox.setMaximumRowCount(1000);
        teacherDeptJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherDeptJComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(teacherDeptJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 194, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 557, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deptJRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptJRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deptJRadioButton1ActionPerformed

    private void teacherDeptJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherDeptJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherDeptJComboBoxActionPerformed

    private void studentDeptJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDeptJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentDeptJComboBoxActionPerformed

    private void courseCodeJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseCodeJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseCodeJTextFieldActionPerformed

    private void courseTitleJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseTitleJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseTitleJTextFieldActionPerformed

    private void teacherNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherNameJTextFieldActionPerformed

    private void creditJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditJTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InformationCollectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformationCollectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformationCollectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformationCollectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformationCollectionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel courseCodeJLabel;
    private javax.swing.JTextField courseCodeJTextField;
    private javax.swing.JRadioButton courseJRadioButton;
    private javax.swing.JLabel courseTitleJLabel;
    private javax.swing.JTextField courseTitleJTextField;
    private javax.swing.JLabel creditJLabel;
    private javax.swing.JTextField creditJTextField;
    private javax.swing.JRadioButton deptJRadioButton1;
    private javax.swing.JComboBox deptNameJComboBox;
    private javax.swing.JLabel deptNameJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField newDeptNameJTextField;
    private javax.swing.JLabel newDeptTitleJLabel;
    private javax.swing.JButton okJButton;
    private javax.swing.JComboBox studentDeptJComboBox;
    private javax.swing.JLabel studentDeptJLabel1;
    private javax.swing.JLabel studentIdJLabel1;
    private javax.swing.JTextField studentIdJTextField1;
    private javax.swing.JRadioButton studentJRadioButton;
    private javax.swing.JLabel studentNameJLabel1;
    private javax.swing.JTextField studentNameJTextField1;
    private javax.swing.JComboBox teacherDeptJComboBox;
    private javax.swing.JLabel teacherDeptJLabel;
    private javax.swing.JLabel teacherIdJLabel;
    private javax.swing.JTextField teacherIdJTextField;
    private javax.swing.JRadioButton teacherJRadioButton;
    private javax.swing.JLabel teacherNameJLabel;
    private javax.swing.JTextField teacherNameJTextField;
    // End of variables declaration//GEN-END:variables

}
