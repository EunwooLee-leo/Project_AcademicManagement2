package Management;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentForm extends JFrame {
    public static DefaultTableModel model;
    UserDAO userDAO = new UserDAO();
    JPanel panel1, panel2, panel3, panel4, panel5;

    JTextField  stdCodeText, stdNameText, rrnText, adrrText, phoneText, yearText, highSchoolText,  highSchoolGraduationYearText;
    static DefaultTableModel tableModel;
    static JTable table;
    JScrollPane scrollPane1;
    JTextPane textPane1;
    static JComboBox comboBox1, comboBox2;
    public StudentForm() throws BadLocationException, SQLException {
        super("학생관리");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 900);
        setLocationRelativeTo(null);

        this.setLayout(null);

        panel();
        label();
        button();
        userDAO.categorySearchBySubjectName();

        setVisible(true);
    }

    void panel() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel1.setLayout(null); // 레이아웃 매니저 설정
        panel2.setLayout(null); // 레이아웃 매니저 설정
        panel3.setLayout(null); // 레이아웃 매니저 설정
        panel4.setLayout(null); // 레이아웃 매니저 설정
        panel5.setLayout(null); // 레이아웃 매니저 설정

        panel1.setBounds(0, 0, 650, 80);
        panel1.setBackground(Color.WHITE);
        panel2.setBounds(0, 80, 700, 300);
        panel2.setBackground(new Color(15,15,112));
        panel3.setBounds(0, 380, 650, 70);
        panel3.setBackground(Color.WHITE);
        panel4.setBounds(0, 450, 650, 250);
        panel4.setBackground(new Color(15,15,112));
        panel5.setBounds(0, 700, 650, 150);
        panel5.setBackground(Color.WHITE);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }

    void label() throws BadLocationException, SQLException {

        JLabel label0 = new JLabel("학생관리");
        label0.setForeground(Color.BLACK);
        label0.setFont(new Font("나눔고딕", Font.BOLD, 20));
        label0.setBounds(275, -10, 100, 100);

        JLabel label1 = new JLabel("학번");
        label1.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label1.setBounds(30, 55, 100, 20);

        JLabel label2 = new JLabel("이름");
        label2.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label2.setBounds(215, 55, 100, 20);

        JLabel label3 = new JLabel("주민등록번호");
        label3.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label3.setBounds(385, 55, 100, 20);

        JLabel label4 = new JLabel("주소");
        label4.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label4.setBounds(30, 100, 100, 20);

        JLabel label5 = new JLabel("전화번호");
        label5.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label5.setBounds(30, 150, 100, 20);

        JLabel label6 = new JLabel("학과");
        label6.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label6.setBounds(385, 150, 100, 20);

        JLabel label7 = new JLabel("입학년도");
        label7.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label7.setBounds(30, 200, 100, 20);

        JLabel label8 = new JLabel("졸업고교");
        label8.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label8.setBounds(385, 200, 100, 20);

        JLabel label9 = new JLabel("고교졸업년도");
        label9.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label9.setBounds(30, 250, 100, 20);

        JLabel label10 = new JLabel("성별");
        label10.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label10.setBounds(385, 250, 100, 20);

        stdCodeText = new JTextField();
        stdCodeText.setBounds(100, 50, 100, 24);
        stdNameText = new JTextField();
        stdNameText.setBounds(265, 50, 100, 24);
        rrnText = new JTextField();
        rrnText.setBounds(490, 50, 110, 24);
        adrrText = new JTextField();
        adrrText.setBounds(100, 100, 400, 24);
        phoneText = new JTextField();
        phoneText.setBounds(100, 150, 200, 24);
        yearText = new JTextField();
        yearText.setBounds(100, 200, 200, 24);
        highSchoolText = new JTextField();
        highSchoolText.setBounds(450, 200, 150, 24);
        highSchoolGraduationYearText = new JTextField();
        highSchoolGraduationYearText.setBounds(150, 250, 150, 24);

        panel2.add(stdCodeText);
        panel2.add(stdNameText);
        panel2.add(rrnText);
        panel2.add(adrrText);
        panel2.add(phoneText);
        panel2.add(yearText);
        panel2.add(highSchoolText);
        panel2.add(highSchoolGraduationYearText);


        textPane1 = new JTextPane();
        textPane1.setBackground(Color.GRAY);
        textPane1.setBounds(15, 15, 600, 220);
        textPane1.setEditable(false);
        textPane1.setCursor(null); // 커서 비활성화
        textPane1.setOpaque(false); // 투명 배경 설정
        textPane1.setFocusable(false); // 포커스 비활성화

        JScrollPane scrollPane = new JScrollPane(textPane1);
        scrollPane.setBounds(15, 25, 600, 250);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);// 검은색 테두리, 두께 2
        scrollPane.setBorder(border);


        // 테이블 모델을 초기화
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 모든 셀을 수정 불가능하게 설정
                return false;
            }
        };
        tableModel.addColumn("학번");
        tableModel.addColumn("이름");
        tableModel.addColumn("주소");
        tableModel.addColumn("주민등록번호");
        tableModel.addColumn("전화번호");
        tableModel.addColumn("학과");
        tableModel.addColumn("입학년도");
        tableModel.addColumn("졸업고교");
        tableModel.addColumn("고교졸업년도");
        tableModel.addColumn("성별");

        // JTable 생성 및 모델 설정
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("나눔고딕", Font.BOLD, 15); // Example: Bold and larger font
        header.setFont(headerFont);


        userDAO.studentInfoAll();

        // JScrollPane에 JTable 추가
        scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(15, 50, 600, 150);
        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);// 검은색 테두리, 두께 2
        scrollPane1.setBorder(border2);

        String imagePath = "./image/resized.png";
        ImageIcon snuUi = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(snuUi);
        imageLabel.setBounds(80,-10, 100,100);
        panel1.add(imageLabel);

        panel1.add(label0);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        panel2.add(label7);
        panel2.add(label8);
        panel2.add(label9);
        panel2.add(label10);
        panel2.add(scrollPane);
        panel4.add(scrollPane1);

    }

    void button() {

        comboBox1 = new JComboBox<>();
        comboBox1.addItem("카테고리를 선택하세요");
        comboBox1.addItem("이름");
        comboBox1.setBounds(70, 20, 150, 24);
        comboBox1.setFont(new Font("나눔고딕", Font.PLAIN, 12));


        comboBox2 = new JComboBox<>();
        comboBox2.addItem("개설학과");
        comboBox2.setBounds(430, 120, 150, 24);
        comboBox2.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        JTextField textField = new JTextField();
        textField.setBounds(260, 20, 150, 24);

        JRadioButton radioButton1 = new JRadioButton("남자");
        radioButton1.setBounds(430, 220, 50, 24);
        JRadioButton radioButton2 = new JRadioButton("여자");
        radioButton2.setBounds(490, 220, 50, 24);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);


        JButton btn1 = new JButton("조회");
        btn1.setBounds(450, 20, 100, 24);
        btn1.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedcategory = (String) comboBox1.getSelectedItem();
                String text = textField.getText();

                if (selectedcategory.equals("이름")) {
                    userDAO.categorySearchByStudentName(text);
                }

            }
        });

        JButton btn2 = new JButton("등록");
        btn2.setBounds(40, 30, 100, 50);
        btn2.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String stnCode = stdCodeText.getText();
                String stdName = stdNameText.getText();
                String addr = adrrText.getText();
                String rrn = rrnText.getText();
                String phone = phoneText.getText();
                String majorName = (String) comboBox2.getSelectedItem();
                String year = yearText.getText();
                String highSchool = highSchoolText.getText();
                String graduationYear = highSchoolGraduationYearText.getText();
                String sex = "";
                if (radioButton1.isSelected()) {
                    sex = radioButton1.getText();
                } else if (radioButton2.isSelected()) {
                    sex = radioButton2.getText();
                }

                userDAO.studentDataInsert(stnCode, stdName, addr, rrn, phone, majorName, year, highSchool, graduationYear, sex);
            }
        });

        JButton btn3 = new JButton("수정");
        btn3.setBounds(200, 30, 100, 50);
        btn3.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stnCode = stdCodeText.getText();
                String stdName = stdNameText.getText();
                String addr = adrrText.getText();
                String rrn = rrnText.getText();
                String phone = phoneText.getText();
                String year = yearText.getText();
                String majorName = (String) comboBox2.getSelectedItem();
                String highSchool = highSchoolText.getText();
                String graduationYear = highSchoolGraduationYearText.getText();
                String sex = "";
                if (radioButton1.isSelected()) {
                    sex = radioButton1.getText();
                } else if (radioButton2.isSelected()) {
                    sex = radioButton2.getText();
                }

                userDAO.updateStudentInfo(stnCode, stdName, addr, rrn, phone, year, majorName, highSchool, graduationYear, sex);

            }
        });

        JButton btn4 = new JButton("삭제");
        btn4.setBounds(360, 30, 100, 50);
        btn4.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stnCode = stdCodeText.getText();
                String stdName = stdNameText.getText();
                String phone = phoneText.getText();

                userDAO.deleteStudentInfo(stnCode, stdName, phone);

            }
        });


        JButton btn5 = new JButton("종료");
        btn5.setBounds(500, 30, 100, 50);
        btn5.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        textPane1.add(comboBox2);
        textPane1.add(radioButton1);
        textPane1.add(radioButton2);
        panel3.add(comboBox1);
        panel3.add(textField);
        panel3.add(btn1);
        panel5.add(btn2);
        panel5.add(btn3);
        panel5.add(btn4);
        panel5.add(btn5);
    }

    public static void main(String[] args) throws BadLocationException, SQLException {
        new StudentForm();
    }
}
