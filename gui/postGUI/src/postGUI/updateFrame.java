package postGUI;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class updateFrame {
	
	JFrame frmUpdate;
	private JTextField textField;
	private JTextField textField_1;
	
	public updateFrame() {
		frmUpdate = new JFrame();
		frmUpdate.setTitle("Update");
		frmUpdate.setSize(550,150);
		frmUpdate.setResizable(false);
		
		String[] tables = {"CarInfo", "Renter", "Insurance"};
		String[] operator = {">","<", ">=", "<=", "=", "!="};
		String[] NcarsSelect = {"Make","Model","CarID", "Color", "Year", "Class", "DailyRate", "In Stock"};
        String[] NrenterSelect = {"Name","DOB", "SSN", "PickupDate", "DropoffDate", "InsuranceID", "CarID", "Rental Total"};
        String[] NinsuranceSelect = {"CompanyName", "InsuranceID", "CoverageAmount", "CoverageTimeInDays"};
        
        
        
        
        
        
		
		JLabel lblNewLabel = new JLabel("Update");
		
		JComboBox comboBox = new JComboBox(tables);
		
		JLabel lblSet = new JLabel("Set ");
		
		JComboBox comboBox_1 = new JComboBox();
		
		JLabel label = new JLabel("=");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblWhere = new JLabel("Where");
		
		JComboBox comboBox_2 = new JComboBox();
		
		JComboBox comboBox_3 = new JComboBox(operator);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		
		
		
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
               
            	String selected = "";
                selected =  (String) comboBox.getSelectedItem();
                //System.out.println(selected);
                if(selected.equals("CarInfo")) {
            		comboBox_1.removeAllItems();
            		comboBox_2.removeAllItems();
                	
                	
                	for (int i = 0; i < NcarsSelect.length; i++) {
                		comboBox_1.addItem(NcarsSelect[i]);
                		comboBox_2.addItem(NcarsSelect[i]);
                	}
                	
                }
                if(selected.toString().equals("Renter")) {
            		comboBox_1.removeAllItems();
            		comboBox_2.removeAllItems();
                	
                	for (int i = 0; i < NrenterSelect.length; i++) {
                		comboBox_1.addItem(NrenterSelect[i]);
                		comboBox_2.addItem(NrenterSelect[i]);
                	}
                }
                
                if(selected.toString().equals("Insurance")) {
            		comboBox_1.removeAllItems();
            		comboBox_2.removeAllItems();
                	
                	
                	for (int i = 0; i < NinsuranceSelect.length; i++) {
                		comboBox_1.addItem(NinsuranceSelect[i]);
                		comboBox_2.addItem(NinsuranceSelect[i]);
                	}

            }
        } });
		
		
		
		
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				data.update((String) comboBox.getSelectedItem(), (String) comboBox_1.getSelectedItem(), (String) comboBox_2.getSelectedItem(), (String) comboBox_3.getSelectedItem(), textField.getText(), textField_1.getText());
			}
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frmUpdate.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(lblSet, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_1, 0, 111, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWhere, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_2, 0, 105, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_3, 0, 61, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
							.addGap(30)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(lblSet))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWhere)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate))
					.addGap(25))
		);
		frmUpdate.getContentPane().setLayout(groupLayout);
		frmUpdate.setVisible(true);
		
	}
	
	
}
