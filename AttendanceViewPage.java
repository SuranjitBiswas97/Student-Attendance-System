/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendancesystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author F.C
 */
public class AttendanceViewPage extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form AttendanceViewPage
     */
    int len;

    public AttendanceViewPage(String nm, String pass) {
        initComponents();
        setTitle("My Attendance");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fillIdLebelMethod(nm, pass);
        fillComboboxCourseCode();

        backJButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backJButton) {
            setVisible(false);
            LogInPage lIP = new LogInPage();
        }

        if (e.getSource() == studentCourseCodeJComboBox) {
            String check = studentCourseCodeJComboBox.getSelectedItem().toString();

            if (check.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select a Department....");
            } else {
                fillComboboxSession();
                fillComboboxYear();
                fillComboboxSection();
                fillCourseTitle();
                getAttendentMethod();
            }
        }
    }

    private void fillComboboxSession() {
        String sId = studentIdJLabel.getText();
        String sdept = studentDepartmentJLabel.getText();
        String sCCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE Dept = '" + sdept + "' and S_id = '" + sId + "' and C_code = '" + sCCode + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String session = obj.rs.getString("Session");
                studentSessionJLabel.setText(session);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxYear() {
        String sId = studentIdJLabel.getText();
        String sdept = studentDepartmentJLabel.getText();
        String sCCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        String session = studentSessionJLabel.getText();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE Dept = '" + sdept + "' and S_id = '" + sId + "' and C_code = '" + sCCode + "' and Session = '" + session + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                int y = obj.rs.getInt("Year");
                String year = ""+y;
                studentYearJLabel.setText(year);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxSection() {
        String sId = studentIdJLabel.getText();
        String sdept = studentDepartmentJLabel.getText();
        String sCCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        String session = studentSessionJLabel.getText();
        String year = studentYearJLabel.getText();
        
        int y = Integer.parseInt(year);
        
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE Dept = '" + sdept + "' and S_id = '" + sId + "' and C_code = '" + sCCode + "' and Session = '" + session + "'and Year = '" + y + "'";

        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String sec = obj.rs.getString("Section");
                studentSectionJLabel.setText(sec);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void fillCourseTitle() {
        String cCode = studentCourseCodeJComboBox.getSelectedItem().toString();
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

    private void fillIdLebelMethod(String uNm, String pass) {
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student` WHERE S_user = '" + uNm + "' And S_pass = '" + pass + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String sNm = obj.rs.getString("S_name");
                String sId = obj.rs.getString("S_id");
                String sDept = obj.rs.getString("Dept_name");

                studentNameJLabel.setText(sNm);
                studentIdJLabel.setText(sId);
                studentDepartmentJLabel.setText(sDept);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fillComboboxCourseCode() {
        studentCourseCodeJComboBox.removeActionListener(this);

        String sId = studentIdJLabel.getText();
        String sdept = studentDepartmentJLabel.getText();
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE Dept = '" + sdept + "' and S_id = '" + sId + "'";

        studentCourseCodeJComboBox.removeAllItems();
        studentCourseCodeJComboBox.addItem("Select one");
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String cCode = obj.rs.getString("C_code");
                studentCourseCodeJComboBox.addItem(cCode);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentCourseCodeJComboBox.addActionListener(this);

    }

    private void getAttendentMethod() {

        int p = 0;
        int a = 0;

        String[] date = new String[100];
        String[] st = new String[100];

        String sId = studentIdJLabel.getText();
        String sNm = studentNameJLabel.getText();
        String cCode = studentCourseCodeJComboBox.getSelectedItem().toString();
        String dept = studentDepartmentJLabel.getText();
        String section = studentSectionJLabel.getText();
        String session = studentSessionJLabel.getText();
        String y = studentYearJLabel.getText();

        int i = 0;
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `attendance` WHERE S_id = '" + sId + "' And c_code = '" + cCode + "' And dept = '" + dept + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {

                date[i] = obj.rs.getString("date");
                st[i] = obj.rs.getString(6);

                if (st[i].equals("Present")) {
                    p++;
                } else {
                    a++;
                }

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        len = i;

        for (int j = 0; j < len; j++) {
            Object[] row = {date[j], st[j]};
            DefaultTableModel model = (DefaultTableModel) viewJTable.getModel();
            model.addRow(row);
        }

        presentJLabel.setText("" + p);
        absencesJLabel.setText("" + a);

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
        jPanel1 = new javax.swing.JPanel();
        studentIdTitleJLabel = new javax.swing.JLabel();
        studentNameTitleJLabel = new javax.swing.JLabel();
        studentCourseCodeTitleJLabel = new javax.swing.JLabel();
        studentSectionTitleJLabel = new javax.swing.JLabel();
        studentDepartmentTitleJLabel = new javax.swing.JLabel();
        studentSessionTitleJLabel = new javax.swing.JLabel();
        studentYearTitleJLabel = new javax.swing.JLabel();
        studentIdJLabel = new javax.swing.JLabel();
        studentNameJLabel = new javax.swing.JLabel();
        studentCourseCodeJComboBox = new javax.swing.JComboBox();
        studentDepartmentJLabel = new javax.swing.JLabel();
        studentSectionJLabel = new javax.swing.JLabel();
        studentSessionJLabel = new javax.swing.JLabel();
        studentYearJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewJTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        backJButton = new javax.swing.JButton();
        courseTitliLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        presentJLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        absencesJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        studentIdTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentIdTitleJLabel.setText("Student Id :");

        studentNameTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentNameTitleJLabel.setText("Student Name :");

        studentCourseCodeTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentCourseCodeTitleJLabel.setText("Course Code :");

        studentSectionTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentSectionTitleJLabel.setText("Section           :");

        studentDepartmentTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentDepartmentTitleJLabel.setText("Department :");

        studentSessionTitleJLabel.setFont(new java.awt.Font("Rockwell", 3, 18)); // NOI18N
        studentSessionTitleJLabel.setText("Session        :");

        studentYearTitleJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentYearTitleJLabel.setText("Year               :");

        studentIdJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        studentNameJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentNameJLabel.setForeground(new java.awt.Color(0, 102, 204));

        studentCourseCodeJComboBox.setBackground(new java.awt.Color(255, 102, 0));
        studentCourseCodeJComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        studentCourseCodeJComboBox.setMaximumRowCount(1000);
        studentCourseCodeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select one" }));

        studentDepartmentJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        studentSectionJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        studentSessionJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        studentYearJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(studentDepartmentTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentNameTitleJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentIdTitleJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(studentDepartmentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(studentSessionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentSessionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(studentIdJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentSectionTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(studentCourseCodeTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentYearTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentCourseCodeJComboBox, 0, 196, Short.MAX_VALUE)
                    .addComponent(studentSectionJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentYearJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentCourseCodeTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentCourseCodeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(studentIdJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentNameTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentSectionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(studentSectionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentSessionJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(studentSessionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(studentYearTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentDepartmentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentDepartmentTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentYearJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        viewJTable.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        viewJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(viewJTable);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(255, 0, 0));

        backJButton.setText("BACK");

        courseTitliLabel.setFont(new java.awt.Font("Papyrus", 3, 18)); // NOI18N
        courseTitliLabel.setForeground(new java.awt.Color(255, 0, 0));
        courseTitliLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        courseTitliLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PRESENT :");

        presentJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        presentJLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ABSENCE");

        absencesJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        absencesJLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DAYS");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DAYS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(absencesJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(presentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absencesJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel absencesJLabel;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel courseTitliLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel presentJLabel;
    private javax.swing.JComboBox studentCourseCodeJComboBox;
    private javax.swing.JLabel studentCourseCodeTitleJLabel;
    private javax.swing.JLabel studentDepartmentJLabel;
    private javax.swing.JLabel studentDepartmentTitleJLabel;
    private javax.swing.JLabel studentIdJLabel;
    private javax.swing.JLabel studentIdTitleJLabel;
    private javax.swing.JLabel studentNameJLabel;
    private javax.swing.JLabel studentNameTitleJLabel;
    private javax.swing.JLabel studentSectionJLabel;
    private javax.swing.JLabel studentSectionTitleJLabel;
    private javax.swing.JLabel studentSessionJLabel;
    private javax.swing.JLabel studentSessionTitleJLabel;
    private javax.swing.JLabel studentYearJLabel;
    private javax.swing.JLabel studentYearTitleJLabel;
    private javax.swing.JTable viewJTable;
    // End of variables declaration//GEN-END:variables

}
