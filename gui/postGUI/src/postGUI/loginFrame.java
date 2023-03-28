package postGUI;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class loginFrame {
    
    JFrame frame;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    public loginFrame () {
        frame = new JFrame();
        frame.setSize(400,200);
        frame.setResizable(false);
        
        JLabel lblLogin = new JLabel("Existing Login:");
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        
        passwordField = new JPasswordField();
        
        passwordField_1 = new JPasswordField();
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int isLogin = data.login(passwordField.getText(),passwordField_1.getText());
                
                if (isLogin == 1) {
                    frame.dispose();
                    try {
                        adminFrame newFrame = new adminFrame();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                
                if (isLogin == 2) {
                    //invalid case do nothing.
                }
            
                
            }
        });
        
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                registerFrame registerFrame = new registerFrame ();
            }
        });
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblLogin)
                        .addComponent(lblPassword))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(passwordField_1)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(111, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(19)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLogin)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogin))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegister))
                    .addContainerGap(115, Short.MAX_VALUE))
        );
        frame.getContentPane().setLayout(groupLayout);
      
        
        frame.setVisible(true);
    }
    
    
    public static void main(final String[] args) {
        loginFrame makeFrame = new loginFrame();
        //this is where JAR build starts
        
        
        
    }
}
