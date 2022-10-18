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
public class TeacherShowAttendanceJFrame extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form TeacherShowAttendanceJFrame
     */
    int p;
    int a;

    int i = 0;
    int len = 0;

    public TeacherShowAttendanceJFrame(String tId, String tNm, String tCC, String tDept, String tSession, String tYear, String sDept, String sSession, String sYear, String sSection, String cTitle) {
        initComponents();
        setTitle("Student Attendance View");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String tIdGet = tId;
        String tNmGet = tNm;
        String tCCGet = tCC;
        String tDeptGet = tDept;
        String tSessionGet = tSession;
        String tYearGet = tYear;

        String sDeptGet = sDept;
        String sSessionGet = sSession;
        String sYearGet = sYear;
        String sSectionGet = sSection;
        String cTitleGet = cTitle;

        teacherIdJLabel1.setText(tIdGet);
        teacherNameJLabel.setText(tNmGet);
        teacherDeptJLabelLabel.setText(tDeptGet);
        courseJLabel.setText(tCCGet);
        teacherSessionJLabel.setText(tSessionGet);
        yearTeacherJLabel.setText(tYearGet);

        studentDepartmentJLabel.setText(sDeptGet);
        sessionStudentJLabel.setText(sSessionGet);
        sessionStudentYearJLabel.setText(sYearGet);
        studentSectionJLabel1.setText(sSectionGet);
        courseTitliLabel.setText(cTitleGet);

        studentIdJComboBox.addActionListener(this);
        backJButton.addActionListener(this);

        fillStudentIdComboBoxMethod();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentIdJComboBox) {
            String chech = studentIdJComboBox.getSelectedItem().toString();
            if (chech.equals("Select one")) {
                JOptionPane.showMessageDialog(rootPane, "Select An ID...");
            } else {
                collectInformationMethod();
            }
        }
        if(e.getSource()==backJButton){
            setVisible(false);
        }
    }

    private void collectInformationMethod() {

        String[] date = new String[100];
        String[] st = new String[100];

        String sId = studentIdJComboBox.getSelectedItem().toString();
        String cCode = courseJLabel.getText();
        String dept = studentDepartmentJLabel.getText();
        for (int j = 0; j < len; j++) {
            DefaultTableModel model1 = (DefaultTableModel) viewJTable.getModel();
            model1.removeRow(0);
        }
        
        i = 0;
        len = 0;
        p = 0;
        a = 0;

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

    private void fillStudentIdComboBoxMethod() {
        studentIdJComboBox.removeActionListener(this);
        String dept = studentDepartmentJLabel.getText();
        String cCode = courseJLabel.getText();
        String session = sessionStudentJLabel.getText();
        String year = sessionStudentYearJLabel.getText();
        String section = studentSectionJLabel1.getText();
        String cTeacher = teacherNameJLabel.getText();

        studentIdJComboBox.removeAllItems();
        studentIdJComboBox.addItem("Select one");
        ConnectorClass obj = new ConnectorClass();
        String query = "SELECT * FROM `student_registration` WHERE Dept = '" + dept + "' And C_code = '" + cCode + "'And Session = '" + session + "'And Year = '" + year + "'And Section = '" + section + "'And C_teacher = '" + cTeacher + "'";
        try {

            obj.rs = obj.stmt.executeQuery(query);

            while (obj.rs.next()) {
                String sId = obj.rs.getString("S_id");
                studentIdJComboBox.addItem(sId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        studentIdJComboBox.addActionListener(this);
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
        teacherNameTitleJLabel1 = new javax.swing.JLabel();
        teacherIdTitleJLabel = new javax.swing.JLabel();
        teacherDeptTitleJLabel = new javax.swing.JLabel();
        sessionTitleJLabel = new javax.swing.JLabel();
        yearTeacherTitleJLabel = new javax.swing.JLabel();
        courseTitleJLabel = new javax.swing.JLabel();
        teacherNameJLabel = new javax.swing.JLabel();
        teacherIdJLabel1 = new javax.swing.JLabel();
        teacherDeptJLabelLabel = new javax.swing.JLabel();
        yearTeacherJLabel = new javax.swing.JLabel();
        teacherSessionJLabel = new javax.swing.JLabel();
        courseJLabel = new javax.swing.JLabel();
        studentIdJComboBox = new javax.swing.JComboBox();
        studentIdTitleJLabel = new javax.swing.JLabel();
        sessionStudentTitleJLabel = new javax.swing.JLabel();
        sessionStudentJLabel = new javax.swing.JLabel();
        sessionStudentYearJLabel = new javax.swing.JLabel();
        studentSectionTitleJLabel = new javax.swing.JLabel();
        studentSectionJLabel1 = new javax.swing.JLabel();
        studentDeptmentTitleJLabel = new javax.swing.JLabel();
        studentDepartmentJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        presentJLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        absencesJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        courseTitliLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        teacherNameTitleJLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherNameTitleJLabel1.setForeground(new java.awt.Color(0, 153, 204));
        teacherNameTitleJLabel1.setText("Teacher Name  :");

        teacherIdTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherIdTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherIdTitleJLabel.setText("Teacher Id         :");

        teacherDeptTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherDeptTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        teacherDeptTitleJLabel.setText("Department       :");

        sessionTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sessionTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        sessionTitleJLabel.setText("Session :");

        yearTeacherTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        yearTeacherTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        yearTeacherTitleJLabel.setText("Year :");

        courseTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        courseTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        courseTitleJLabel.setText("Course Code :");

        teacherNameJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherNameJLabel.setForeground(new java.awt.Color(0, 153, 204));

        teacherIdJLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherIdJLabel1.setForeground(new java.awt.Color(0, 153, 204));

        teacherDeptJLabelLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherDeptJLabelLabel.setForeground(new java.awt.Color(0, 153, 204));

        yearTeacherJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        yearTeacherJLabel.setForeground(new java.awt.Color(0, 153, 204));

        teacherSessionJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        teacherSessionJLabel.setForeground(new java.awt.Color(0, 153, 204));

        courseJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        courseJLabel.setForeground(new java.awt.Color(0, 153, 204));

        studentIdJComboBox.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentIdJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        studentIdJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        studentIdTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentIdTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentIdTitleJLabel.setText("Student ID  :");

        sessionStudentTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sessionStudentTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        sessionStudentTitleJLabel.setText("Session :");

        sessionStudentJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sessionStudentJLabel.setForeground(new java.awt.Color(0, 153, 204));

        sessionStudentYearJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sessionStudentYearJLabel.setForeground(new java.awt.Color(0, 153, 204));

        studentSectionTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentSectionTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentSectionTitleJLabel.setText("Section:");

        studentSectionJLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentSectionJLabel1.setForeground(new java.awt.Color(0, 153, 204));

        studentDeptmentTitleJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentDeptmentTitleJLabel.setForeground(new java.awt.Color(0, 153, 204));
        studentDeptmentTitleJLabel.setText("Department :");

        studentDepartmentJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        studentDepartmentJLabel.setForeground(new java.awt.Color(0, 153, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 204));
        jLabel3.setText("Year :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherDeptTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherNameTitleJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sessionStudentTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sessionStudentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sessionStudentYearJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(studentSectionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(studentSectionJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(studentDeptmentTitleJLabel)
                                .addGap(26, 26, 26)
                                .addComponent(studentDepartmentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(studentIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentIdJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(teacherDeptJLabelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(409, 409, 409)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yearTeacherJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherSessionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(teacherIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teacherIdJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(293, 293, 293)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(yearTeacherTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sessionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(courseTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(courseJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(155, 155, 155))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(courseTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(courseJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(teacherSessionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sessionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(yearTeacherJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(yearTeacherTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherIdJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(teacherNameTitleJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherDeptJLabelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherDeptTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sessionStudentTitleJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sessionStudentJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(studentSectionTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sessionStudentYearJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(studentSectionJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentDeptmentTitleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentIdJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentIdTitleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentDepartmentJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        viewJTable.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("PRESENT :");

        presentJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        presentJLabel.setForeground(new java.awt.Color(0, 153, 204));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("DAYS");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 204));
        jLabel2.setText("ABSENCE :");

        absencesJLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        absencesJLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 204));
        jLabel5.setText("DAYS");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        courseTitliLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        courseTitliLabel.setForeground(new java.awt.Color(255, 0, 0));
        courseTitliLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        courseTitliLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        backJButton.setText("BACK");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseTitliLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(absencesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(presentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1116, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presentJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(absencesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private javax.swing.JLabel courseJLabel;
    private javax.swing.JLabel courseTitleJLabel;
    private javax.swing.JLabel courseTitliLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel presentJLabel;
    private javax.swing.JLabel sessionStudentJLabel;
    private javax.swing.JLabel sessionStudentTitleJLabel;
    private javax.swing.JLabel sessionStudentYearJLabel;
    private javax.swing.JLabel sessionTitleJLabel;
    private javax.swing.JLabel studentDepartmentJLabel;
    private javax.swing.JLabel studentDeptmentTitleJLabel;
    private javax.swing.JComboBox studentIdJComboBox;
    private javax.swing.JLabel studentIdTitleJLabel;
    private javax.swing.JLabel studentSectionJLabel1;
    private javax.swing.JLabel studentSectionTitleJLabel;
    private javax.swing.JLabel teacherDeptJLabelLabel;
    private javax.swing.JLabel teacherDeptTitleJLabel;
    private javax.swing.JLabel teacherIdJLabel1;
    private javax.swing.JLabel teacherIdTitleJLabel;
    private javax.swing.JLabel teacherNameJLabel;
    private javax.swing.JLabel teacherNameTitleJLabel1;
    private javax.swing.JLabel teacherSessionJLabel;
    private javax.swing.JTable viewJTable;
    private javax.swing.JLabel yearTeacherJLabel;
    private javax.swing.JLabel yearTeacherTitleJLabel;
    // End of variables declaration//GEN-END:variables

}
