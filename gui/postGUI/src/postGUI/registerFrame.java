package postGUI;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registerFrame {
    JFrame frame;
    private JTextField textField_1;
    private JTextField textField_2;
    
   
   
    public registerFrame () {
        frame = new JFrame();
        frame.setSize(400,200);
        
        JLabel lblNewUsername = new JLabel("New Username: ");
        
        JLabel lblNewPassword = new JLabel("New Password:");
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        
        JButton btnRegisterUser = new JButton("Register User");
        btnRegisterUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField_1.getText();
                String password = textField_2.getText();
                
                Boolean success = data.registerUser(username,password);
                if (success) {
                    frame.dispose();
                    loginFrame newFrame = new loginFrame();
                } else {
                    //JOptionPane.showMessageDialog(null, "Username already exists.");
                }
            }
        });
        
        JButton btnBackToLogin = new JButton("Back to Login");
        btnBackToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loginFrame newFrame = new loginFrame ();
            }
        });
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblNewPassword, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblNewUsername)
        					.addGap(40)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(btnRegisterUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnBackToLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addContainerGap(123, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(16)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewUsername)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewPassword)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
        			.addComponent(btnRegisterUser)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnBackToLogin))
        );
        frame.getContentPane().setLayout(groupLayout);
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        //registerFrame makeFrame = new registerFrame();
        //run above line to run as standalone frame
    }
}
