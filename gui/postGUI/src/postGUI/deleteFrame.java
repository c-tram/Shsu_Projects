package postGUI;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class deleteFrame {
	
	JFrame frmDelete;
	private JTextField textField;
	
	public deleteFrame() {
		frmDelete = new JFrame();
		frmDelete.setTitle("Delete");
		frmDelete.setSize(750,100);
		frmDelete.setResizable(false);
		
		String[] tables = {"CarInfo", "Renter", "Insurance"};
		String[] operator = {">","<", ">=", "<=", "=", "!="};
		String[] NcarsSelect = {"Make","Model","CarID", "Color", "Year", "Class", "DailyRate", "In Stock"};
        String[] NrenterSelect = {"Name","DOB", "SSN", "PickupDate", "DropoffDate", "InsuranceID", "CarID", "Rental Total"};
        String[] NinsuranceSelect = {"CompanyName", "InsuranceID", "CoverageAmount", "CoverageTimeInDays"};
		
		
		JLabel lblNewLabel = new JLabel("DELETE FROM");
		
		JComboBox comboBox = new JComboBox(tables);
		
		JLabel lblWhere = new JLabel("WHERE");
		
		JComboBox comboBox_1 = new JComboBox();
		
		JComboBox comboBox_2 = new JComboBox(operator);

		textField = new JTextField();
		textField.setColumns(10);
		
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
                	
                	
                	for (int i = 0; i < NcarsSelect.length; i++) {
                		comboBox_1.addItem(NcarsSelect[i]);
                	}
                	
                }
                if(selected.toString().equals("Renter")) {
            		comboBox_1.removeAllItems();
                	
                	for (int i = 0; i < NrenterSelect.length; i++) {
                		comboBox_1.addItem(NrenterSelect[i]);
                	}
                }
                
                if(selected.toString().equals("Insurance")) {
            		comboBox_1.removeAllItems();
                	
                	
                	for (int i = 0; i < NinsuranceSelect.length; i++) {
                		comboBox_1.addItem(NinsuranceSelect[i]);
                	}

            }
        } });
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				data.delete( (String) comboBox.getSelectedItem(), (String) comboBox_1.getSelectedItem(), (String) comboBox_2.getSelectedItem(),textField.getText()); 
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmDelete.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWhere)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWhere)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		frmDelete.getContentPane().setLayout(groupLayout);
		frmDelete.setVisible(true);
	}
}
