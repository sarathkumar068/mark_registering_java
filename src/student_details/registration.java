package student_details;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class registration {
    String EXAM[] = {"CAT-1", "CAT-2", "MODAL"};

    public registration() {
        JFrame f = new JFrame();
        f.setLayout(null);

        Label titleLabel = new Label(" EXAM MARK REGISTRATION ");
        titleLabel.setBounds(270, 0, 175, 50);
        f.add(titleLabel);

        JLabel n = new JLabel("Name:");
        JLabel p = new JLabel("Roll No:");
        JLabel gender = new JLabel("CLASS");
        JLabel d = new JLabel("EXAM");
        JTextField name = new JTextField(20);
        JTextField pass = new JTextField(20);
        JTextArea area = new JTextArea("");
        JRadioButton jrb = new JRadioButton("A");
        JRadioButton jrb1 = new JRadioButton("B");
        JComboBox<String> jc = new JComboBox<>(EXAM);
        JLabel sub1 = new JLabel("OOPs :");
        JLabel sub2 = new JLabel("MATHS :");
        JLabel sub3 = new JLabel("FDS :");
        JLabel sub4 = new JLabel("DS :");
        JLabel sub5 = new JLabel("DPCO :");
        JTextField sub_1 = new JTextField(20);
        JTextField sub_2 = new JTextField(20);
        JTextField sub_3 = new JTextField(20);
        JTextField sub_4 = new JTextField(20);
        JTextField sub_5 = new JTextField(20);
        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Cancel");

        f.add(n);
        f.add(name);
        f.add(p);
        f.add(pass);
        f.add(gender);
        f.add(jrb);
        f.add(jrb1);

        f.add(d);
        f.add(sub1);
        f.add(sub_1);
        f.add(sub2);
        f.add(sub_2);
        f.add(sub3);
        f.add(sub_3);
        f.add(sub4);
        f.add(sub_4);
        f.add(sub5);
        f.add(sub_5);
        f.add(jc);
        f.add(area);
        f.add(b1);
        f.add(b2);

        n.setBounds(40, 30, 90, 30);
        name.setBounds(130, 30, 150, 20);
        p.setBounds(40, 70, 90, 30);
        pass.setBounds(130, 70, 150, 20);
        gender.setBounds(40, 110, 90, 30);
        jrb.setBounds(130, 110, 80, 30);
        jrb1.setBounds(220, 110, 90, 30);

        d.setBounds(40, 150, 90, 30);
        jc.setBounds(130, 150, 100, 30);

        sub1.setBounds(40, 190, 90, 30);
        sub_1.setBounds(130, 190, 150, 20);
        sub2.setBounds(40, 230, 90, 30);
        sub_2.setBounds(130, 230, 150, 20);
        sub3.setBounds(40, 270, 90, 30);
        sub_3.setBounds(130, 270, 150, 20);
        sub4.setBounds(40, 310, 90, 30);
        sub_4.setBounds(130, 310, 150, 20);
        sub5.setBounds(40, 350, 90, 30);
        sub_5.setBounds(130, 350, 150, 20);

        area.setBounds(300, 340, 150, 20);

        b1.setBounds(100, 400, 90, 50);
        b2.setBounds(300, 400, 90, 50);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the application when the "Cancel" button is clicked
                System.exit(0);
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Connect to MySQL database and insert data
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_registration", "root", "admin")) {
                    String sql = "INSERT INTO student_details (name, roll_no, class, exam, oops, maths, fds, ds, dpco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, name.getText());
                        preparedStatement.setString(2, pass.getText());
                        preparedStatement.setString(3, jrb.isSelected() ? "A" : "B");
                        preparedStatement.setString(4, jc.getSelectedItem().toString());
                        preparedStatement.setInt(5, Integer.parseInt(sub_1.getText()));
                        preparedStatement.setInt(6, Integer.parseInt(sub_2.getText()));
                        preparedStatement.setInt(7, Integer.parseInt(sub_3.getText()));
                        preparedStatement.setInt(8, Integer.parseInt(sub_4.getText()));
                        preparedStatement.setInt(9, Integer.parseInt(sub_5.getText()));

                        preparedStatement.executeUpdate();
                        area.setText("Data inserted successfully!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        f.setSize(700, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Exam Register");
        f.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new registration();
        });
    }
}

