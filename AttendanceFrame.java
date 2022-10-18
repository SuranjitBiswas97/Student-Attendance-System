/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendancesystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author F.C
 */
public class AttendanceFrame extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form AttendanceFrame
     */
    int len;
    int i = 0, j = 0;
    int k = 0;
    Date dNow = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    String date = ft.format(dNow);
    int peviousIndex;

    public AttendanceFrame(String nm, String pass) {
        initComponents();
        setTitle("This is Attendance Given Page..");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dateJLabel.setText(date);
        jPanel3.setVisible(false);

        fillIdLebelMethod(nm, pass);
        fillComboboxCourseCode();
        fillDepartmentCombobox();

        saveJButton.setEnabled(false);

        okJButton.addActionListener(this);
        saveJButton.addActionListener(this);
        attendentjButton.addActionListener(this);
        absenceJButton.addActionListener(this);
        backJButton.addActionListener(this);
        changeJButton.addActionListener(this);
        courseCodeJComboBox.addActionListener(this);
        viewJButton.addActionListener(this);
        changeJButton.setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okJButton) {
            okMethod();
        }
        if (e.getSource() == absenceJButton) {
            absenceMethod();
        }
        if (e.getSource() == attendentjButton) {
            attendanceMethod();
        }
        if (e.getSource() == backJButton) {
            setVisible(false);
            LogInPage lIP = new LogInPage();
        }
        if (e.getSource() == saveJButton) {
            saveMethod();
            setVisible(false);
            LogInPage lIP = new LogInPage();

        }
        if (e.getSource() == changeJButton) {
            changeMethod();
        }
        if (e.getSource() == courseCodeJComboBox) {
            String chech = courseCodeJComboBox.getSelectedItem().toString();
            if (e.getSource() == "Select one") {
                JOptionPane.showMessageDialog(rootPane, "select a Course Code...");
            } else {
                jPanel3.setVisible(true);
                fillComboboxSession();
                fillComboboxYear();
                fillCourseTitle();
                fillStudentDeptmentJComboBox();
                studentSectionJLabel.setVisible(false);
                studentSectionJComboBox.setVisible(false);
                sessionStudentTitleJLabel.setVisible(false);
                sessionStudentJComboBox1.setVisible(false);
                sessionYearJComboBox.setVisible(false);
                studentDeptmentJComboBox.addActionListener(this);
            }

        }

        if (e.getSource() == viewJButton) {
            viewMethod();
        }
        if (e.getSource() == studentDeptmentJComboBox) {
            String chech = courseCodeJComboBox.getSelectedItem().toString();
            if (e.getSource() == "Select one") {
                JOptionPane.showMessageDialog(rootPane, "select a Department...");
            } else {
                studentSectionJLabel.setVisible(true);
                studentSectionJComboBox.setVisible(true);
                fillStudentSectionJComboBox();
            }

        }
        if (e.getSource() == studentSectionJComboBox) {
            String chech = courseCodeJComboBox.getSelectedItem().toString();
            if (e.getSource() == "Select one") {
                JOptionPane.showMessageDialog(rootPane, "select a Section...");
            } else {
                sessionStudentTitleJLabel.setVisible(true);
                sessionStudentJComboBox1.setVisible(true);

                fillSessionStudentJComboBox();
            }

        }
        if (e.getSource() == sessionStudentJComboBox1) {
            String chech = courseCodeJComboBox.getSelectedItem().toString();
            if (e.getSource() == "Select one") {
                JOptionPane.showMessageDialog(rootPane, "select a Session...");
            } else {
                sessionYearJComboBox.setVisible(true);
                fillStudentYearJComboBox();
            }

        }
        if (e.getSource() == sessionYearJComboBox) {
            String chech = sessionYearJComboBox.getSelectedItem().toString();
            if (e.getSource() == "Select one") {
                JOptionPane.showMessageDialog(rootPane, "select a Session...");
            }
        }

    }

    private void fillStudentYearJComboBox() {
        sessionYearJComboBox.removeActionListener(this);

        String[] year = new String[100];

        int k = 0;
        int len = 0;

        String tNm = teacherNameJLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();
        String sSec = studentSectionJComboBox.getSelectedItem().toString();
        String sSession = sessionStudentJComboBox1.getSelectedItem().toString();
        String sdept = studentDeptmentJComboBox.getSelectedItem().toString();

        sessionYearJComboBox.removeAllItems();
        sessionYearJComboBox.addItem("Select one");

        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE C_code = '" + tCCode + "' and C_teacher = '" + tNm + "'and Dept = '" + sdept + "'and Section = '" + sSec + "'and Session = '" + sSession + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String year1 = obj.rs.getString("Year");
                year[k] = obj.rs.getString("Year");
                for (int j = 0; j <= len; j++) {
                    if (year[j].equals(year1)) {
                        sessionYearJComboBox.removeItem(year1);
                    }
                }
                sessionYearJComboBox.addItem(year1);
                k++;
                len = k;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        sessionYearJComboBox.addActionListener(this);
    }

    private void fillStudentDeptmentJComboBox() {

        studentDeptmentJComboBox.removeActionListener(this);
        String[] dept = new String[100];

        int k = 0;
        int len = 0;

        String tNm = teacherNameJLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();

        studentDeptmentJComboBox.removeAllItems();
        studentDeptmentJComboBox.addItem("Select one");

        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE C_code = '" + tCCode + "' and C_teacher = '" + tNm + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {

                dept[k] = obj.rs.getString("Dept");
                String dept1 = obj.rs.getString("Dept");
                for (int j = 0; j <= len; j++) {
                    if (dept[j].equals(dept1)) {
                        studentDeptmentJComboBox.removeItem(dept1);
                    }
                }
                studentDeptmentJComboBox.addItem(dept1);
                k++;
                len = k;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void fillStudentSectionJComboBox() {

        studentSectionJComboBox.removeActionListener(this);

        String[] sec = new String[100];

        int k = 0;
        int len = 0;

        String tNm = teacherNameJLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();
        String sdept = studentDeptmentJComboBox.getSelectedItem().toString();

        studentSectionJComboBox.removeAllItems();
        studentSectionJComboBox.addItem("Select one");

        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE C_code = '" + tCCode + "' and C_teacher = '" + tNm + "'and Dept = '" + sdept + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String sec1 = obj.rs.getString("Section");
                sec[k] = obj.rs.getString("Section");
                for (int j = 0; j <= len; j++) {
                    if (sec[j].equals(sec1)) {
                        studentSectionJComboBox.removeItem(sec1);
                    }
                }
                studentSectionJComboBox.addItem(sec1);
                k++;
                len = k;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentSectionJComboBox.addActionListener(this);

    }

    private void fillSessionStudentJComboBox() {

        sessionStudentJComboBox1.removeActionListener(this);

        String[] session = new String[100];

        int k = 0;
        int len = 0;

        String tNm = teacherNameJLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();
        String sSec = studentSectionJComboBox.getSelectedItem().toString();
        String sdept = studentDeptmentJComboBox.getSelectedItem().toString();

        sessionStudentJComboBox1.removeAllItems();
        sessionStudentJComboBox1.addItem("Select one");

        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE C_code = '" + tCCode + "' and C_teacher = '" + tNm + "'and Dept = '" + sdept + "'and Section = '" + sSec + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String session1 = obj.rs.getString("Session");
                session[k] = obj.rs.getString("Session");
                for (int j = 0; j <= len; j++) {
                    if (session[j].equals(session1)) {
                        sessionStudentJComboBox1.removeItem(session1);
                    }
                }
                sessionStudentJComboBox1.addItem(session1);
                k++;
                len = k;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        sessionStudentJComboBox1.addActionListener(this);

    }

    private void viewMethod() {
        String tId = teacherIdJLabel1.getText();
        String tNm = teacherNameJLabel.getText();
        String tCC = courseCodeJComboBox.getSelectedItem().toString();
        String tDept = teacherDeptJLabelLabel.getText();
        String tSession = teacherSessionJLabel.getText();
        String tYear = yearTeacherJLabel.getText();

        String sDept = studentDeptmentJComboBox.getSelectedItem().toString();
        String sSession = sessionStudentJComboBox1.getSelectedItem().toString();
        String sYear = sessionYearJComboBox.getSelectedItem().toString();
        String sSection = studentSectionJComboBox.getSelectedItem().toString();
        String cTitle = courseTitliLabel.getText();

        if (tCC.equals("Select one") || sDept.equals("Select one") || sSession.equals("Select one") || sYear.equals("Select one") || sSection.equals("Select one")) {
            JOptionPane.showMessageDialog(rootPane, "Fill  Up Accuratelly...");
        } else {
            TeacherShowAttendanceJFrame tSAJF = new TeacherShowAttendanceJFrame(tId, tNm, tCC, tDept, tSession, tYear, sDept, sSession, sYear, sSection, cTitle);
        }

    }

    private void fillDepartmentCombobox() {

        ConnectorClass obj = new ConnectorClass();

        studentDeptmentJComboBox.removeActionListener(this);

        String query = "Select * from department";
        studentDeptmentJComboBox.removeAllItems();

        studentDeptmentJComboBox.addItem("Select one");

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String dept = obj.rs.getString("Dept_name");
                studentDeptmentJComboBox.addItem(dept);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentDeptmentJComboBox.addActionListener(this);

    }

    private void changeMethod() {
        String change = "";
        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
        String status = model.getValueAt(k, 2).toString();
        String status1 = model.getValueAt(k, 3).toString();

        if (status.equals("YES")) {
            int reply = JOptionPane.showConfirmDialog(rootPane, "The student already Given Absences.. \nDo you want to change?? ");
            if (reply == JOptionPane.YES_OPTION) {
                change = "yes";
            }
            if (reply == JOptionPane.NO_OPTION) {
                change = "no";
            }

            if (change.equals("yes")) {
                model.setValueAt("", k, 2);
                model.setValueAt("YES", k, 3);
            }

        }

        if (status1.equals("YES")) {
            int reply = JOptionPane.showConfirmDialog(rootPane, "The student already Given Absences.. \nDo you want to change?? ");
            if (reply == JOptionPane.YES_OPTION) {
                change = "yes";
            }
            if (reply == JOptionPane.NO_OPTION) {
                change = "no";
            }

            if (change.equals("yes")) {
                model.setValueAt("", k, 3);
                model.setValueAt("YES", k, 2);
            }
        }
        changeJButton.setEnabled(false);

        attendanceJTable.setRowSelectionInterval(peviousIndex, peviousIndex);
        k = attendanceJTable.getSelectedRow();
        idJLabel.setText(model.getValueAt(k, 0).toString());
        nameJLabel.setText(model.getValueAt(k, 1).toString());

    }

    private void saveMethod() {

        String tId = teacherIdJLabel1.getText();;
        String cCode = courseCodeJComboBox.getSelectedItem().toString();
        String dept = teacherDeptJLabelLabel.getText();
        String section = studentSectionJComboBox.getSelectedItem().toString();
        String session = sessionStudentJComboBox1.getSelectedItem().toString();
        String year = sessionYearJComboBox.getSelectedItem().toString();

        int row = 0;

        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();

        for (int j = 0; j < len; j++) {
            String sId = model.getValueAt(row, 0).toString();
            String sNm = model.getValueAt(row, 1).toString();
            String status1 = model.getValueAt(row, 2).toString();
            String status2 = model.getValueAt(row, 3).toString();
            if (status1.equals("YES")) {
                String st = "Present";
                ConnectorClass obj = new ConnectorClass();
                String query = "INSERT INTO attendance " + "VALUES ('" + date + "','" + sId + "','" + sNm + "','" + cCode + "'\n"
                        + ",'" + dept + "','" + st + "','" + tId + "','" + section + "','" + session + "','" + year + "')";
                try {

                    obj.stmt.executeUpdate(query);
                    obj.con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (status2.equals("YES")) {
                String st = "Absense";
                ConnectorClass obj = new ConnectorClass();
                String query = "INSERT INTO attendance " + "VALUES ('" + date + "','" + sId + "','" + sNm + "','" + cCode + "'\n"
                        + ",'" + dept + "','" + st + "','" + tId + "','" + section + "','" + session + "','" + year + "')";
                try {

                    obj.stmt.executeUpdate(query);
                    obj.con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            row++;
        }
    }

    private void absenceMethod() {

        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
        model.setValueAt("YES", k, 3);
        k++;
        if (k >= len) {
            JOptionPane.showMessageDialog(rootPane, "List Have been Finished");
            attendentjButton.setEnabled(false);
            absenceJButton.setEnabled(false);
            saveJButton.setEnabled(true);

        } else {
            idJLabel.setText(model.getValueAt(k, 0).toString());
            nameJLabel.setText(model.getValueAt(k, 1).toString());
            peviousIndex = k;
            saveJButton.setEnabled(true);
        }
    }

    private void attendanceMethod() {

        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
        model.setValueAt("YES", k, 2);
        k++;
        if (k >= len) {
            JOptionPane.showMessageDialog(rootPane, "List Have been Finished");
            attendentjButton.setEnabled(false);
            absenceJButton.setEnabled(false);

        } else {
            idJLabel.setText(model.getValueAt(k, 0).toString());
            nameJLabel.setText(model.getValueAt(k, 1).toString());
            peviousIndex = k;
        }

    }

    private void okMethod() {
        String[] id = new String[100];
        String[] nm = new String[100];
        String[] present = new String[100];
        String[] absence = new String[100];

        String t = teacherNameJLabel.getText();
        String d = studentDeptmentJComboBox.getSelectedItem().toString();
        String cC = courseCodeJComboBox.getSelectedItem().toString();
        String sec = studentSectionJComboBox.getSelectedItem().toString();
        String session = sessionStudentJComboBox1.getSelectedItem().toString();
        String year = sessionYearJComboBox.getSelectedItem().toString();
        

        int i = 0;

        if (d.equals("Select one") || cC.equals("Select one") || sec.equals("Select one") || session.equals("Select one") || year.equals("Select one")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Up Properly...");
        } else {
            int y = Integer.parseInt(year);
            ConnectorClass obj = new ConnectorClass();
            String query = "SELECT * FROM `student_registration` WHERE C_teacher = '" + t + "' And Dept = '" + d + "' And C_code = '" + cC + "'\n"
                    + " And Section = '" + sec + "' And Session = '" + session + "' And Year = '" + y + "' order by S_id asc";
            try {

                obj.rs = obj.stmt.executeQuery(query);

                while (obj.rs.next()) {

                    id[i] = obj.rs.getString(1);
                    nm[i] = obj.rs.getString(2);
                    present[i] = "";
                    absence[i] = "";

                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            len = i;

            for (int j = 0; j < len; j++) {
                Object[] row = {id[j], nm[j], present[j], absence[j]};
                DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
                model.addRow(row);
            }
            selectRow();
        }

    }

    private void selectRow() {
        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
        attendanceJTable.setRowSelectionInterval(i, j);
        k = attendanceJTable.getSelectedRow();
        idJLabel.setText(model.getValueAt(k, 0).toString());
        nameJLabel.setText(model.getValueAt(k, 1).toString());

    }

//teacher
    private void fillIdLebelMethod(String uNm, String pass) {
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher` WHERE T_user = '" + uNm + "' And T_pass = '" + pass + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String tNm = obj.rs.getString("T_name");
                String tId = obj.rs.getString("T_id");
                String tDept = obj.rs.getString("dept_name");

                teacherNameJLabel.setText(tNm);
                teacherIdJLabel1.setText(tId);
                teacherDeptJLabelLabel.setText(tDept);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxCourseCode() {
        courseCodeJComboBox.removeActionListener(this);
        String tId = teacherIdJLabel1.getText();
        String tdept = teacherDeptJLabelLabel.getText();

        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher_registration` WHERE Dept = '" + tdept + "' and T_id = '" + tId + "'";

        courseCodeJComboBox.removeAllItems();
        courseCodeJComboBox.addItem("Select one");

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String cCode = obj.rs.getString("C_code");
                courseCodeJComboBox.addItem(cCode);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        courseCodeJComboBox.addActionListener(this);
    }

    private void fillComboboxSession() {
        String tId = teacherIdJLabel1.getText();
        String tdept = teacherDeptJLabelLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher_registration` WHERE Dept = '" + tdept + "' and T_id = '" + tId + "' and C_code = '" + tCCode + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String session = obj.rs.getString("Session");
                teacherSessionJLabel.setText(session);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxYear() {
        String tId = teacherIdJLabel1.getText();
        String tdept = teacherDeptJLabelLabel.getText();
        String tCCode = courseCodeJComboBox.getSelectedItem().toString();
        String session = teacherSessionJLabel.getText();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `teacher_registration` WHERE Dept = '" + tdept + "' and T_id = '" + tId + "' and C_code = '" + tCCode + "' and Session = '" + session + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String year = obj.rs.getString("Year");
                yearTeacherJLabel.setText(year);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillCourseTitle() {
        String cCode = courseCodeJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `course` WHERE C_code = '" + cCode + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String cTitle = obj.rs.getString("C_title");

                courseTitliLabel.setText(cTitle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        teacherNameTitleJLabel1 = new javax.swing.JLabel();
        teacherIdTitleJLabel = new javax.swing.JLabel();
        teacherDeptTitleJLabel = new javax.swing.JLabel();
        sessionTitleJLabel = new javax.swing.JLabel();
        yearTeacherTitleJLabel = new javax.swing.JLabel();
        courseTitleJLabel = new javax.swing.JLabel();
        teacherNameJLabel = new javax.swing.JLabel();
        teacherIdJLabel1 = new javax.swing.JLabel();
        courseCodeJComboBox = new javax.swing.JComboBox();
        teacherDeptJLabelLabel = new javax.swing.JLabel();
        yearTeacherJLabel = new javax.swing.JLabel();
        teacherSessionJLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        sessionStudentTitleJLabel = new javax.swing.JLabel();
        sessionStudentJComboBox1 = new javax.swing.JComboBox();
        okJButton = new javax.swing.JButton();
        studentSectionJLabel = new javax.swing.JLabel();
        studentSectionJComboBox = new javax.swing.JComboBox();
        studentDeptmentJComboBox = new javax.swing.JComboBox();
        studentDeptmentJLabel = new javax.swing.JLabel();
        viewJButton = new javax.swing.JButton();
        sessionYearJComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceJTable = new javax.swing.JTable();
        attendentjButton = new javax.swing.JButton();
        absenceJButton = new javax.swing.JButton();
        studentNameIdJLabel = new javax.swing.JLabel();
        idJLabel = new javax.swing.JLabel();
        nameJLabel = new javax.swing.JLabel();
        saveJButton = new javax.swing.JButton();
        changeJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        courseTitliLabel = new javax.swing.JLabel();
        dateTitleJLabel = new javax.swing.JLabel();
        dateJLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setDoubleBuffered(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        teacherNameTitleJLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherNameTitleJLabel1.setForeground(new java.awt.Color(0, 102, 153));
        teacherNameTitleJLabel1.setText("Teacher Name          :");

        teacherIdTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherIdTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        teacherIdTitleJLabel.setText("Teacher Id                 :");

        teacherDeptTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        teacherDeptTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        teacherDeptTitleJLabel.setText("Department              :");

        sessionTitleJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        sessionTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        sessionTitleJLabel.setText("Session           :");

        yearTeacherTitleJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        yearTeacherTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        yearTeacherTitleJLabel.setText("Year                :");

        courseTitleJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        courseTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        courseTitleJLabel.setText("Course Code :");

        teacherNameJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherNameJLabel.setForeground(new java.awt.Color(0, 102, 204));

        teacherIdJLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherIdJLabel1.setForeground(new java.awt.Color(0, 102, 204));

        courseCodeJComboBox.setFont(new java.awt.Font("Rockwell", 3, 18)); // NOI18N
        courseCodeJComboBox.setForeground(new java.awt.Color(0, 102, 153));
        courseCodeJComboBox.setMaximumRowCount(1000);
        courseCodeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one" }));

        teacherDeptJLabelLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherDeptJLabelLabel.setForeground(new java.awt.Color(0, 102, 204));

        yearTeacherJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        yearTeacherJLabel.setForeground(new java.awt.Color(0, 102, 204));

        teacherSessionJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherSessionJLabel.setForeground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(teacherIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(teacherIdJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(teacherNameTitleJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(teacherNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(teacherDeptTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teacherDeptJLabelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(yearTeacherTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearTeacherJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(courseTitleJLabel)
                            .addComponent(sessionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(courseCodeJComboBox, 0, 145, Short.MAX_VALUE)
                            .addComponent(teacherSessionJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(teacherIdTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(courseTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(courseCodeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherIdJLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(teacherNameTitleJLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(teacherNameJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sessionTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(teacherSessionJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yearTeacherTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearTeacherJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(teacherDeptTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(teacherDeptJLabelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 950, 120));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sessionStudentTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sessionStudentTitleJLabel.setForeground(new java.awt.Color(0, 102, 153));
        sessionStudentTitleJLabel.setText("Session :");
        jPanel3.add(sessionStudentTitleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 110, 30));

        sessionStudentJComboBox1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sessionStudentJComboBox1.setForeground(new java.awt.Color(0, 102, 153));
        sessionStudentJComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one", "Spring", "Summer", "Fall" }));
        jPanel3.add(sessionStudentJComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 150, 30));

        okJButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        okJButton.setForeground(new java.awt.Color(0, 153, 0));
        okJButton.setText("OK");
        okJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(okJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 70, 30));

        studentSectionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentSectionJLabel.setForeground(new java.awt.Color(0, 102, 153));
        studentSectionJLabel.setText("Section :");
        jPanel3.add(studentSectionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 80, 30));

        studentSectionJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentSectionJComboBox.setForeground(new java.awt.Color(0, 102, 153));
        studentSectionJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one", "A", "B", "C", "D" }));
        studentSectionJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentSectionJComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(studentSectionJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 150, 30));

        studentDeptmentJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentDeptmentJComboBox.setForeground(new java.awt.Color(0, 102, 153));
        studentDeptmentJComboBox.setMaximumRowCount(1000);
        studentDeptmentJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one" }));
        studentDeptmentJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDeptmentJComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(studentDeptmentJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 150, 30));

        studentDeptmentJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentDeptmentJLabel.setForeground(new java.awt.Color(0, 102, 153));
        studentDeptmentJLabel.setText("Department :");
        jPanel3.add(studentDeptmentJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, 30));

        viewJButton.setBackground(new java.awt.Color(0, 255, 51));
        viewJButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        viewJButton.setForeground(new java.awt.Color(255, 0, 51));
        viewJButton.setText("VIEW");
        viewJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(viewJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, 90, 30));

        sessionYearJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sessionYearJComboBox.setForeground(new java.awt.Color(0, 102, 153));
        sessionYearJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(sessionYearJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 150, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Year :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 60, 30));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 950, 120));

        attendanceJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Present", "Absence"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        attendanceJTable.setColumnSelectionAllowed(true);
        attendanceJTable.setSelectionForeground(new java.awt.Color(204, 0, 204));
        attendanceJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendanceJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(attendanceJTable);
        attendanceJTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 920, 200));

        attendentjButton.setText("Present");
        attendentjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendentjButtonActionPerformed(evt);
            }
        });
        jPanel4.add(attendentjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 620, 111, 30));

        absenceJButton.setText("Absence");
        jPanel4.add(absenceJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 105, 30));

        studentNameIdJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentNameIdJLabel.setForeground(new java.awt.Color(0, 51, 153));
        studentNameIdJLabel.setText("The Student ID And Name  :");
        jPanel4.add(studentNameIdJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 280, 49));

        idJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        idJLabel.setForeground(new java.awt.Color(0, 153, 204));
        jPanel4.add(idJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, 170, 49));

        nameJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nameJLabel.setForeground(new java.awt.Color(0, 153, 204));
        jPanel4.add(nameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 550, 180, 40));

        saveJButton.setText("Save");
        jPanel4.add(saveJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 620, 130, 30));

        changeJButton.setText("Change");
        jPanel4.add(changeJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 620, 132, 30));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        courseTitliLabel.setBackground(new java.awt.Color(204, 0, 204));
        courseTitliLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        courseTitliLabel.setForeground(new java.awt.Color(255, 0, 0));
        courseTitliLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        courseTitliLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dateTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dateTitleJLabel.setForeground(new java.awt.Color(0, 102, 204));
        dateTitleJLabel.setText("Date :");

        dateJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dateJLabel.setForeground(new java.awt.Color(0, 102, 204));

        backJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backJButton.setForeground(new java.awt.Color(0, 204, 102));
        backJButton.setText("BACK");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(dateTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateTitleJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void attendentjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendentjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attendentjButtonActionPerformed

    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okJButtonActionPerformed

    private void attendanceJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendanceJTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) attendanceJTable.getModel();
        k = attendanceJTable.getSelectedRow();
        idJLabel.setText(model.getValueAt(k, 0).toString());
        nameJLabel.setText(model.getValueAt(k, 1).toString());
        changeJButton.setEnabled(true);


    }//GEN-LAST:event_attendanceJTableMouseClicked

    private void studentSectionJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentSectionJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentSectionJComboBoxActionPerformed

    private void studentDeptmentJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDeptmentJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentDeptmentJComboBoxActionPerformed

    private void viewJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton absenceJButton;
    private javax.swing.JTable attendanceJTable;
    private javax.swing.JButton attendentjButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton changeJButton;
    private javax.swing.JComboBox courseCodeJComboBox;
    private javax.swing.JLabel courseTitleJLabel;
    private javax.swing.JLabel courseTitliLabel;
    private javax.swing.JLabel dateJLabel;
    private javax.swing.JLabel dateTitleJLabel;
    private javax.swing.JLabel idJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JButton okJButton;
    private javax.swing.JButton saveJButton;
    private javax.swing.JComboBox sessionStudentJComboBox1;
    private javax.swing.JLabel sessionStudentTitleJLabel;
    private javax.swing.JLabel sessionTitleJLabel;
    private javax.swing.JComboBox sessionYearJComboBox;
    private javax.swing.JComboBox studentDeptmentJComboBox;
    private javax.swing.JLabel studentDeptmentJLabel;
    private javax.swing.JLabel studentNameIdJLabel;
    private javax.swing.JComboBox studentSectionJComboBox;
    private javax.swing.JLabel studentSectionJLabel;
    private javax.swing.JLabel teacherDeptJLabelLabel;
    private javax.swing.JLabel teacherDeptTitleJLabel;
    private javax.swing.JLabel teacherIdJLabel1;
    private javax.swing.JLabel teacherIdTitleJLabel;
    private javax.swing.JLabel teacherNameJLabel;
    private javax.swing.JLabel teacherNameTitleJLabel1;
    private javax.swing.JLabel teacherSessionJLabel;
    private javax.swing.JButton viewJButton;
    private javax.swing.JLabel yearTeacherJLabel;
    private javax.swing.JLabel yearTeacherTitleJLabel;
    // End of variables declaration//GEN-END:variables

}
