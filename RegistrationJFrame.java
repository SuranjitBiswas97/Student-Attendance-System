/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendancesystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author F.C
 */
public class RegistrationJFrame extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form RegistrationJFrame
     */
    String select = "";

    public RegistrationJFrame() {
        initComponents();
        setTitle("Registration");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        visiableMethod();

        ButtonGroup group;
        group = new ButtonGroup();
        group.add(teacherResistationJRadioButton);
        group.add(studentResistationJRadioButton);

        teacherResistationJRadioButton.addActionListener(this);
        studentResistationJRadioButton.addActionListener(this);

        teacherDeptmentJComboBox.addActionListener(this);
        studentDeptmentJComboBox.addActionListener(this);

        okJButton.addActionListener(this);
        backJButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backJButton) {
            setVisible(false);
            AuthorHandlingFrame aHF = new AuthorHandlingFrame();
        }
        if (e.getSource() == studentResistationJRadioButton) {
            visiableMethod();
            studentResistationMethod();
        }
        if (e.getSource() == teacherResistationJRadioButton) {
            visiableMethod();
            teacherResistationMethod();

        }
        if (e.getSource() == studentDeptmentJComboBox) {
            String check = studentDeptmentJComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Department..");
            } else {
                studentIdJComboBox.setVisible(true);
                fillStudentId();
                studentIdJComboBox.addActionListener(this);
            }

        }
        if (e.getSource() == studentIdJComboBox) {
            String check = studentIdJComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Id..");
            } else {
                fillStudentName();
                fillComboboxStudentCourseCode();
            }

        }
        if (e.getSource() == studentCourseCodeJComboBox) {
            String check = studentCourseCodeJComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Course Code..");
            } else {
                fillStudentCourseTitle();
                fillComboboxCourseTeacher();
            }

        }
        //teacher
        if (e.getSource() == teacherDeptmentJComboBox) {
            String check = teacherDeptmentJComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Course Code..");
            } else {
                teacherIdJComboBox.setVisible(true);
                fillTeacherIdJComboBoxMethod();
                teacherIdJComboBox.addActionListener(this);
            }

        }
        if (e.getSource() == teacherIdJComboBox) {
            String check = teacherIdJComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Course Code..");
            } else {
                fillTeacherNameFieldMethod();
                fillTeacherCourseCodeJCombobox();
                teacherCourseCodeComboBox.addActionListener(this);
            }

        }
        if (e.getSource() == teacherCourseCodeComboBox) {
            String check = teacherCourseCodeComboBox.getSelectedItem().toString();
            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select A Course Code..");
            } else {
                fillTeacherCourseFieldMethod();
            }

        }

        //ok
        if (e.getSource() == okJButton) {
            if (select.equals("student")) {
                studentInformatinTakingMethod();
            }
            if (select.equals("teacher")) {
                teacherInformatinTakingMethod();
            }
        }
    }

    private void studentResistationMethod() {

        select = "student";

        studentResistrationFromTitleJLabel.setVisible(true);

        studentCourseTeacherJLabel.setVisible(true);
        studentCourseTitleJLabel.setVisible(true);
        studentCourseCodeJLabel.setVisible(true);
        studentDeptmentJLabel.setVisible(true);
        studentIdJLabel.setVisible(true);
        studentResistrationFromTitleJLabel.setVisible(true);
        studentSeasionJLabel.setVisible(true);
        studentSectionJLabel.setVisible(true);
        studentYearJLabel.setVisible(true);
        studentNameTitleJLabel.setVisible(true);
        studentDeptmentJComboBox.setVisible(true);

        setNullTextAll();

        fillDepartmentCombobox();
        studentDeptmentJComboBox.addActionListener(this);
    }

    private void teacherResistationMethod() {
        select = "teacher";

        teacherResistrationFromTitleJLabel.setVisible(true);

        teacherDeptmentJLabel.setVisible(true);
        teacherIdJTitleLabel.setVisible(true);
        teacherNameTitleJLabel.setVisible(true);
        teacherCourseCodeJLabel.setVisible(true);
        teacherCourseTitleJLabel.setVisible(true);
        teacherSeasionJLabel.setVisible(true);
        teacherYearJLabel.setVisible(true);
        teacherSectionJLabel.setVisible(true);
        teacherDeptmentJComboBox.setVisible(true);

        setNullTextAll();

        fillDepartmentCombobox();
        teacherDeptmentJComboBox.addActionListener(this);
    }

    //department
    private void fillDepartmentCombobox() {
        ConnectorClass obj = new ConnectorClass();

        studentDeptmentJComboBox.removeActionListener(this);
        teacherDeptmentJComboBox.removeActionListener(this);

        String query = "Select * from department";
        teacherDeptmentJComboBox.removeAllItems();
        studentDeptmentJComboBox.removeAllItems();

        studentDeptmentJComboBox.addItem("Select one");
        teacherDeptmentJComboBox.addItem("Select one");

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String dept = obj.rs.getString("Dept_name");
                studentDeptmentJComboBox.addItem(dept);
                teacherDeptmentJComboBox.addItem(dept);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //student
    private void fillStudentId() {
        studentIdJComboBox.removeActionListener(this);
        String sDept = studentDeptmentJComboBox.getSelectedItem().toString();
        studentIdJComboBox.removeAllItems();
        studentIdJComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student` WHERE Dept_name = '" + sDept + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String sId = obj.rs.getString("S_id");
                studentIdJComboBox.addItem(sId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillStudentName() {
        studentNameFieldJLabel.setVisible(true);
        String stId = studentIdJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student` WHERE S_id = '" + stId + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String nm = obj.rs.getString("S_name");
                studentNameFieldJLabel.setText(nm);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //new
    private void fillComboboxStudentCourseCode() {
        studentCourseCodeJComboBox.setVisible(true);
        studentCourseCodeJComboBox.removeActionListener(this);

        String[] cC = new String[100];
        int i = 0;
        int len;

        String sDept = studentDeptmentJComboBox.getSelectedItem().toString();
        String sId = studentIdJComboBox.getSelectedItem().toString();
        studentCourseCodeJComboBox.removeAllItems();
        studentCourseCodeJComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `course` WHERE dept_name = '" + sDept + "'";

        String query1 = "SELECT * FROM `student_registration` WHERE S_id = '" + sId + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);
            while (obj.rs.next()) {
                cC[i] = obj.rs.getString("C_code");
                studentCourseCodeJComboBox.addItem(cC[i]);
                i++;

            }

            len = i;

            obj.rs = obj.stmt.executeQuery(query1);
            while (obj.rs.next()) {

                String cCode = obj.rs.getString("C_code");
                
                for (int j = 0; j < len; j++) {
                    if (cCode.equals(cC[j])) {
                        studentCourseCodeJComboBox.removeItem(cCode);
                    } else {
                        continue;
                    }
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentCourseJLabel.setVisible(true);
        studentCourseCodeJComboBox.addActionListener(this);
    }

    private void fillStudentCourseTitle() {
        studentSeaasionJComboBox.setVisible(true);
        studentYearJTextField.setVisible(true);
        studentSectionJComboBox.setVisible(true);
        String cCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `course` WHERE C_code = '" + cCode + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String nm = obj.rs.getString("C_title");
                studentCourseJLabel.setText(nm);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxCourseTeacher() {
        studentCourseTeacherJComboBox.setVisible(true);
        studentCourseTeacherJComboBox.removeActionListener(this);
        String sDept = studentDeptmentJComboBox.getSelectedItem().toString();
        String cCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        studentCourseTeacherJComboBox.removeAllItems();
        studentCourseTeacherJComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher_registration` WHERE Dept = '" + sDept + "' and C_code = '" + cCode + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String nm = obj.rs.getString("T_name");
                studentCourseTeacherJComboBox.addItem(nm);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        okJButton.setVisible(true);
    }

    //teacher
    private void fillTeacherIdJComboBoxMethod() {
        teacherIdJComboBox.removeActionListener(this);
        String sDept = teacherDeptmentJComboBox.getSelectedItem().toString();
        teacherIdJComboBox.removeAllItems();
        teacherIdJComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher` WHERE 	dept_name = '" + sDept + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String tId = obj.rs.getString("T_id");
                teacherIdJComboBox.addItem(tId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillTeacherNameFieldMethod() {
        String tId = teacherIdJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher` WHERE 	T_id = '" + tId + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String tName = obj.rs.getString("T_name");
                teacherNameJLabel.setVisible(true);
                teacherNameJLabel.setText(tName);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        teacherCourseCodeComboBox.setVisible(true);
    }

    private void fillTeacherCourseCodeJCombobox() {
        teacherCourseCodeComboBox.removeActionListener(this);

        String[] cC = new String[100];
        int i = 0;
        int len;

        String tDept = teacherDeptmentJComboBox.getSelectedItem().toString();
        String tId = teacherIdJComboBox.getSelectedItem().toString();
        teacherCourseCodeComboBox.removeAllItems();
        teacherCourseCodeComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();

        String query = "SELECT * FROM `course` WHERE dept_name = '" + tDept + "'";
        String query1 = "SELECT * FROM `teacher_registration` WHERE T_id = '" + tId + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);
            while (obj.rs.next()) {
                cC[i] = obj.rs.getString("C_code");
                teacherCourseCodeComboBox.addItem(cC[i]);
                i++;

            }

            len = i;

            obj.rs = obj.stmt.executeQuery(query1);
            while (obj.rs.next()) {

                String cCode = obj.rs.getString("C_code");
                
                for (int j = 0; j < len; j++) {
                    if (cCode.equals(cC[j])) {
                        teacherCourseCodeComboBox.removeItem(cCode);
                    } else {
                        continue;
                    }
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        teacherCourseJLabel.setVisible(true);
    }

    private void fillTeacherCourseFieldMethod() {
        String cCode = teacherCourseCodeComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `course` WHERE C_code = '" + cCode + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String nm = obj.rs.getString("C_title");
                teacherCourseJLabel.setText(nm);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        teacherSeaasionJComboBox.setVisible(true);
        teacherYearJTextField.setVisible(true);
        teacherSectionJComboBox.setVisible(true);
        okJButton.setVisible(true);
    }

    //take information
    //teacher
    private void teacherInformatinTakingMethod() {

        String dept = teacherDeptmentJComboBox.getSelectedItem().toString();
        String id = teacherIdJComboBox.getSelectedItem().toString();
        String nm = teacherNameJLabel.getText();
        String cCode = teacherCourseCodeComboBox.getSelectedItem().toString();
        String cTitle = teacherCourseJLabel.getText();
        String session = teacherSeaasionJComboBox.getSelectedItem().toString();
        String year = teacherYearJTextField.getText();
        String section = teacherSectionJComboBox.getSelectedItem().toString();

        int y = Integer.parseInt(year);

        if (dept.equals("Select one") || id.equals("Select one") || cCode.equals("Select one") || year.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up Properly....");
        } else {
            ConnectorClass obj = new ConnectorClass();
            String query = "INSERT INTO teacher_registration " + "VALUES ('" + id + "','" + nm + "','" + cCode + "','" + cTitle + "'\n"
                    + ",'" + dept + "','" + session + "','" + y + "','" + section + "')";
            try {

                obj.stmt.executeUpdate(query);
                obj.con.close();
                JOptionPane.showMessageDialog(null,"New Account Created");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        visiableMethod();
        teacherResistationMethod();

    }

    //student
    private void studentInformatinTakingMethod() {
        String dept = studentDeptmentJComboBox.getSelectedItem().toString();
        String id = studentIdJComboBox.getSelectedItem().toString();
        String nm = studentNameFieldJLabel.getText();
        String cCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        String cTitle = studentCourseJLabel.getText();
        String session = studentSeaasionJComboBox.getSelectedItem().toString();
        String year = studentYearJTextField.getText();
        String section = studentSectionJComboBox.getSelectedItem().toString();
        String cT = studentCourseTeacherJComboBox.getSelectedItem().toString();
        
        int y = Integer.parseInt(year);

        if (dept.equals("Select one") || id.equals("Select one") || cCode.equals("Select one") || cT.equals("Select one") || year.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up Properly....");
        } else {
            ConnectorClass obj = new ConnectorClass();
            String query = "INSERT INTO student_registration " + "VALUES ('" + id + "','" + nm + "','" + cCode + "','" + cTitle + "'\n"
                    + ",'" + dept + "','" + session + "','" + y + "','" + section + "','" + cT + "')";
            try {

                obj.stmt.executeUpdate(query);
                obj.con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        visiableMethod();
        studentResistationMethod();

    }

    //visible
    private void visiableMethod() {

        okJButton.setVisible(false);

        studentResistrationFromTitleJLabel.setVisible(false);
        teacherResistrationFromTitleJLabel.setVisible(false);

        studentDeptmentJLabel.setVisible(false);
        studentDeptmentJComboBox.setVisible(false);
        studentIdJLabel.setVisible(false);
        studentIdJComboBox.setVisible(false);
        studentNameTitleJLabel.setVisible(false);
        studentNameFieldJLabel.setVisible(false);
        studentCourseCodeJLabel.setVisible(false);
        studentCourseCodeJComboBox.setVisible(false);
        studentCourseTitleJLabel.setVisible(false);
        studentCourseJLabel.setVisible(false);
        studentSeasionJLabel.setVisible(false);
        studentSeaasionJComboBox.setVisible(false);
        studentYearJLabel.setVisible(false);
        studentYearJTextField.setVisible(false);
        studentSectionJLabel.setVisible(false);
        studentSectionJComboBox.setVisible(false);
        studentCourseTeacherJLabel.setVisible(false);
        studentCourseTeacherJComboBox.setVisible(false);

        teacherDeptmentJLabel.setVisible(false);
        teacherDeptmentJComboBox.setVisible(false);
        teacherIdJTitleLabel.setVisible(false);
        teacherIdJComboBox.setVisible(false);
        teacherNameTitleJLabel.setVisible(false);
        teacherNameJLabel.setVisible(false);
        teacherCourseCodeJLabel.setVisible(false);
        teacherCourseCodeComboBox.setVisible(false);
        teacherCourseTitleJLabel.setVisible(false);
        teacherCourseJLabel.setVisible(false);
        teacherSeasionJLabel.setVisible(false);
        teacherSeaasionJComboBox.setVisible(false);
        teacherYearJLabel.setVisible(false);
        teacherYearJTextField.setVisible(false);
        teacherSectionJComboBox.setVisible(false);
        teacherSectionJLabel.setVisible(false);

    }

    //setnulltext
    private void setNullTextAll() {
        studentNameFieldJLabel.setText("");
        studentCourseJLabel.setText("");
        studentYearJTextField.setText("");

        teacherNameJLabel.setText("");
        teacherCourseJLabel.setText("");
        teacherYearJTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formJPanel = new javax.swing.JPanel();
        studentCourseTeacherJLabel = new javax.swing.JLabel();
        studentNameFieldJLabel = new javax.swing.JLabel();
        studentNameTitleJLabel = new javax.swing.JLabel();
        studentCourseTitleJLabel = new javax.swing.JLabel();
        studentCourseCodeJLabel = new javax.swing.JLabel();
        studentDeptmentJLabel = new javax.swing.JLabel();
        studentSeasionJLabel = new javax.swing.JLabel();
        studentSeaasionJComboBox = new javax.swing.JComboBox();
        studentYearJLabel = new javax.swing.JLabel();
        studentYearJTextField = new javax.swing.JTextField();
        studentSectionJLabel = new javax.swing.JLabel();
        studentSectionJComboBox = new javax.swing.JComboBox();
        studentResistrationFromTitleJLabel = new javax.swing.JLabel();
        studentCourseCodeJComboBox = new javax.swing.JComboBox();
        studentCourseJLabel = new javax.swing.JLabel();
        studentCourseTeacherJComboBox = new javax.swing.JComboBox();
        studentIdJComboBox = new javax.swing.JComboBox();
        teacherResistrationFromTitleJLabel = new javax.swing.JLabel();
        teacherIdJTitleLabel = new javax.swing.JLabel();
        teacherNameJLabel = new javax.swing.JLabel();
        teacherIdJComboBox = new javax.swing.JComboBox();
        studentIdJLabel = new javax.swing.JLabel();
        teacherNameTitleJLabel = new javax.swing.JLabel();
        teacherCourseTitleJLabel = new javax.swing.JLabel();
        teacherCourseCodeJLabel = new javax.swing.JLabel();
        teacherCourseJLabel = new javax.swing.JLabel();
        teacherCourseCodeComboBox = new javax.swing.JComboBox();
        teacherDeptmentJLabel = new javax.swing.JLabel();
        teacherDeptmentJComboBox = new javax.swing.JComboBox();
        teacherYearJTextField = new javax.swing.JTextField();
        teacherYearJLabel = new javax.swing.JLabel();
        teacherSeaasionJComboBox = new javax.swing.JComboBox();
        teacherSeasionJLabel = new javax.swing.JLabel();
        studentDeptmentJComboBox = new javax.swing.JComboBox();
        okJButton = new javax.swing.JButton();
        teacherSectionJLabel = new javax.swing.JLabel();
        teacherSectionJComboBox = new javax.swing.JComboBox();
        backJButton = new javax.swing.JButton();
        studentResistationJRadioButton = new javax.swing.JRadioButton();
        teacherResistationJRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        formJPanel.setBackground(new java.awt.Color(0, 0, 0));
        formJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        studentCourseTeacherJLabel.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        studentCourseTeacherJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentCourseTeacherJLabel.setText("Course Teacher :");
        formJPanel.add(studentCourseTeacherJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 210, 30));

        studentNameFieldJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentNameFieldJLabel.setForeground(new java.awt.Color(0, 153, 153));
        formJPanel.add(studentNameFieldJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 340, 30));

        studentNameTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentNameTitleJLabel.setForeground(new java.awt.Color(0, 153, 153));
        studentNameTitleJLabel.setText("Student Name     :");
        formJPanel.add(studentNameTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        studentCourseTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentCourseTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentCourseTitleJLabel.setText("Course Title        :");
        formJPanel.add(studentCourseTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 210, -1));

        studentCourseCodeJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentCourseCodeJLabel.setForeground(new java.awt.Color(0, 153, 153));
        studentCourseCodeJLabel.setText("Course Code       :");
        formJPanel.add(studentCourseCodeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 210, 30));

        studentDeptmentJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentDeptmentJLabel.setForeground(new java.awt.Color(0, 153, 153));
        studentDeptmentJLabel.setText("Departnent          :");
        formJPanel.add(studentDeptmentJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 210, 30));

        studentSeasionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentSeasionJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentSeasionJLabel.setText("Session                 :");
        formJPanel.add(studentSeasionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, 20));

        studentSeaasionJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentSeaasionJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentSeaasionJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spring", "Summer", "Fall" }));
        formJPanel.add(studentSeaasionJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 130, 30));

        studentYearJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentYearJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentYearJLabel.setText("Year :");
        formJPanel.add(studentYearJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        studentYearJTextField.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentYearJTextField.setForeground(new java.awt.Color(0, 153, 204));
        formJPanel.add(studentYearJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 120, 30));

        studentSectionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentSectionJLabel.setForeground(new java.awt.Color(0, 204, 204));
        studentSectionJLabel.setText("Section                  :");
        formJPanel.add(studentSectionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 200, 30));

        studentSectionJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentSectionJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentSectionJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));
        studentSectionJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentSectionJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(studentSectionJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 111, 30));

        studentResistrationFromTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentResistrationFromTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentResistrationFromTitleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        studentResistrationFromTitleJLabel.setText("Student Registration Form");
        formJPanel.add(studentResistrationFromTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 270, -1));

        studentCourseCodeJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentCourseCodeJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentCourseCodeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Course Code" }));
        formJPanel.add(studentCourseCodeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 340, 30));

        studentCourseJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentCourseJLabel.setForeground(new java.awt.Color(0, 153, 204));
        formJPanel.add(studentCourseJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 340, 30));

        studentCourseTeacherJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentCourseTeacherJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentCourseTeacherJComboBox.setMaximumRowCount(1000);
        studentCourseTeacherJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select The Teacher" }));
        formJPanel.add(studentCourseTeacherJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 340, 30));

        studentIdJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentIdJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentIdJComboBox.setMaximumRowCount(1000);
        studentIdJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select An ID" }));
        studentIdJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIdJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(studentIdJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 340, 30));

        teacherResistrationFromTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherResistrationFromTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherResistrationFromTitleJLabel.setText("Teacher Registration Form");
        formJPanel.add(teacherResistrationFromTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 240, -1));

        teacherIdJTitleLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherIdJTitleLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherIdJTitleLabel.setText("Teacher ID           :");
        formJPanel.add(teacherIdJTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 30));

        teacherNameJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherNameJLabel.setForeground(new java.awt.Color(0, 153, 204));
        formJPanel.add(teacherNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 340, 30));

        teacherIdJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherIdJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        teacherIdJComboBox.setMaximumRowCount(1000);
        teacherIdJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select An ID" }));
        teacherIdJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherIdJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(teacherIdJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 340, 30));

        studentIdJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentIdJLabel.setForeground(new java.awt.Color(0, 153, 153));
        studentIdJLabel.setText("Student ID            :");
        formJPanel.add(studentIdJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 210, -1));

        teacherNameTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherNameTitleJLabel.setForeground(new java.awt.Color(0, 153, 153));
        teacherNameTitleJLabel.setText("Teacher Name    :");
        formJPanel.add(teacherNameTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 204, 30));

        teacherCourseTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherCourseTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherCourseTitleJLabel.setText("Course Title        :");
        formJPanel.add(teacherCourseTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, 30));

        teacherCourseCodeJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherCourseCodeJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherCourseCodeJLabel.setText("Course Code       :");
        formJPanel.add(teacherCourseCodeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 210, 30));

        teacherCourseJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherCourseJLabel.setForeground(new java.awt.Color(0, 153, 204));
        formJPanel.add(teacherCourseJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 340, 30));

        teacherCourseCodeComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherCourseCodeComboBox.setForeground(new java.awt.Color(0, 153, 204));
        teacherCourseCodeComboBox.setMaximumRowCount(1000);
        teacherCourseCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Course Code" }));
        teacherCourseCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCourseCodeComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(teacherCourseCodeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 337, 30));

        teacherDeptmentJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherDeptmentJLabel.setForeground(new java.awt.Color(0, 153, 153));
        teacherDeptmentJLabel.setText("Departnent          :");
        formJPanel.add(teacherDeptmentJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 210, -1));

        teacherDeptmentJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherDeptmentJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        teacherDeptmentJComboBox.setMaximumRowCount(1000);
        teacherDeptmentJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Department" }));
        teacherDeptmentJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherDeptmentJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(teacherDeptmentJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 340, 29));

        teacherYearJTextField.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherYearJTextField.setForeground(new java.awt.Color(0, 153, 204));
        teacherYearJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherYearJTextFieldActionPerformed(evt);
            }
        });
        formJPanel.add(teacherYearJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 120, 30));

        teacherYearJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherYearJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherYearJLabel.setText("Year :");
        formJPanel.add(teacherYearJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 71, 30));

        teacherSeaasionJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherSeaasionJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        teacherSeaasionJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spring", "Summer", "Fall" }));
        teacherSeaasionJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherSeaasionJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(teacherSeaasionJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 132, 30));

        teacherSeasionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherSeasionJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherSeasionJLabel.setText("Session                 :");
        formJPanel.add(teacherSeasionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 204, 30));

        studentDeptmentJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentDeptmentJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentDeptmentJComboBox.setMaximumRowCount(1000);
        studentDeptmentJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Department" }));
        studentDeptmentJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDeptmentJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(studentDeptmentJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 340, 30));

        okJButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        okJButton.setForeground(new java.awt.Color(0, 153, 204));
        okJButton.setText("OK");
        formJPanel.add(okJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 80, -1));

        teacherSectionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherSectionJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherSectionJLabel.setText("Section                  :");
        formJPanel.add(teacherSectionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 200, 30));

        teacherSectionJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherSectionJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        teacherSectionJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));
        teacherSectionJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherSectionJComboBoxActionPerformed(evt);
            }
        });
        formJPanel.add(teacherSectionJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 111, 30));

        backJButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backJButton.setForeground(new java.awt.Color(0, 153, 204));
        backJButton.setText("Back");
        formJPanel.add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 80, 34));

        studentResistationJRadioButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentResistationJRadioButton.setForeground(new java.awt.Color(0, 0, 204));
        studentResistationJRadioButton.setText("Student Registration");
        studentResistationJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentResistationJRadioButtonActionPerformed(evt);
            }
        });

        teacherResistationJRadioButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherResistationJRadioButton.setForeground(new java.awt.Color(0, 0, 204));
        teacherResistationJRadioButton.setText("Teacher Registration");
        teacherResistationJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherResistationJRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(teacherResistationJRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentResistationJRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(formJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherResistationJRadioButton)
                    .addComponent(studentResistationJRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentSectionJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentSectionJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentSectionJComboBoxActionPerformed

    private void studentIdJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIdJComboBoxActionPerformed

    private void teacherIdJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherIdJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherIdJComboBoxActionPerformed

    private void teacherCourseCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCourseCodeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherCourseCodeComboBoxActionPerformed

    private void teacherDeptmentJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherDeptmentJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherDeptmentJComboBoxActionPerformed

    private void teacherYearJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherYearJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherYearJTextFieldActionPerformed

    private void teacherSeaasionJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherSeaasionJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherSeaasionJComboBoxActionPerformed

    private void studentDeptmentJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDeptmentJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentDeptmentJComboBoxActionPerformed

    private void studentResistationJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentResistationJRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentResistationJRadioButtonActionPerformed

    private void teacherResistationJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherResistationJRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherResistationJRadioButtonActionPerformed

    private void teacherSectionJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherSectionJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherSectionJComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrationJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrationJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JPanel formJPanel;
    private javax.swing.JButton okJButton;
    private javax.swing.JComboBox studentCourseCodeJComboBox;
    private javax.swing.JLabel studentCourseCodeJLabel;
    private javax.swing.JLabel studentCourseJLabel;
    private javax.swing.JComboBox studentCourseTeacherJComboBox;
    private javax.swing.JLabel studentCourseTeacherJLabel;
    private javax.swing.JLabel studentCourseTitleJLabel;
    private javax.swing.JComboBox studentDeptmentJComboBox;
    private javax.swing.JLabel studentDeptmentJLabel;
    private javax.swing.JComboBox studentIdJComboBox;
    private javax.swing.JLabel studentIdJLabel;
    private javax.swing.JLabel studentNameFieldJLabel;
    private javax.swing.JLabel studentNameTitleJLabel;
    private javax.swing.JRadioButton studentResistationJRadioButton;
    private javax.swing.JLabel studentResistrationFromTitleJLabel;
    private javax.swing.JComboBox studentSeaasionJComboBox;
    private javax.swing.JLabel studentSeasionJLabel;
    private javax.swing.JComboBox studentSectionJComboBox;
    private javax.swing.JLabel studentSectionJLabel;
    private javax.swing.JLabel studentYearJLabel;
    private javax.swing.JTextField studentYearJTextField;
    private javax.swing.JComboBox teacherCourseCodeComboBox;
    private javax.swing.JLabel teacherCourseCodeJLabel;
    private javax.swing.JLabel teacherCourseJLabel;
    private javax.swing.JLabel teacherCourseTitleJLabel;
    private javax.swing.JComboBox teacherDeptmentJComboBox;
    private javax.swing.JLabel teacherDeptmentJLabel;
    private javax.swing.JComboBox teacherIdJComboBox;
    private javax.swing.JLabel teacherIdJTitleLabel;
    private javax.swing.JLabel teacherNameJLabel;
    private javax.swing.JLabel teacherNameTitleJLabel;
    private javax.swing.JRadioButton teacherResistationJRadioButton;
    private javax.swing.JLabel teacherResistrationFromTitleJLabel;
    private javax.swing.JComboBox teacherSeaasionJComboBox;
    private javax.swing.JLabel teacherSeasionJLabel;
    private javax.swing.JComboBox teacherSectionJComboBox;
    private javax.swing.JLabel teacherSectionJLabel;
    private javax.swing.JLabel teacherYearJLabel;
    private javax.swing.JTextField teacherYearJTextField;
    // End of variables declaration//GEN-END:variables

}
