/*create table CarInfo (Make text, Model text, carID text, Color text, Year int, Class text, DailyRate real, PRIMARY KEY (carID));
create table Renter (Name text, DOB text, SSN int, PickupDate text, DropOffDate text, InsuranceID int, carID text, PRIMARY KEY (SSN)); 
create table Insurance (CompanyName text, InsuranceID int, CoverageAmount real, CoverageTimeInDays int, PRIMARY KEY (InsuranceID));
copy CarInfo FROM '/Users/alt/cinfo.csv' DELIMITERS ',' CSV;
copy Renter FROM '/Users/alt/cuinfo.csv' DELIMITERS ',' CSV;
copy Insurance FROM '/Users/alt/iinfo.csv' DELIMITERS ',' CSV;
alter table renter add constraint a FOREIGN KEY (carid) references carinfo(carID);


*/

package postGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.sql.SQLException;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminFrame {

    JFrame frmCarManagementDatabase;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    
    public adminFrame () throws SQLException {
        frmCarManagementDatabase = new JFrame();
        frmCarManagementDatabase.setTitle("Database Management");
        frmCarManagementDatabase.setSize(1000,750);
        frmCarManagementDatabase.setResizable(true);
        
        table = data.makeJTable();
        table.setEnabled(false);
        
        //table = makeJTable();
        JScrollPane a = new JScrollPane(table);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmCarManagementDatabase.dispose();
                loginFrame newFrame = new loginFrame();
            }
        });
        
        JLabel lblLiveQuery = new JLabel("SQL Statement:");
        lblLiveQuery.setHorizontalAlignment(SwingConstants.CENTER);
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnQuery = new JButton("Query");
        btnQuery.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					JTable a = data.customQueryTable(textField);
					table.setModel(a.getModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid Query");
				}
        	}
        });
        
        
        //arrays for attributes and the combobox for select *
        JComboBox comboBox = new JComboBox();
        String[] carsSelect = {"*", "Make","Model","CarID", "Color", "Year", "Class", "DailyRate", "In Stock"};
        String[] renterSelect = {"*","Name","DOB", "SSN", "PickupDate", "DropoffDate", "InsuranceID", "CarID", "Rental Total"};
        String[] insuranceSelect = {"*", "CompanyName", "InsuranceID", "CoverageAmount", "CoverageTimeInDays"};
        String[] NcarsSelect = {"Make","Model","CarID", "Color", "Year", "Class", "DailyRate", "In Stock"};
        String[] NrenterSelect = {"Name","DOB", "SSN", "PickupDate", "DropoffDate", "InsuranceID", "CarID", "Rental Total"};
        String[] NinsuranceSelect = {"CompanyName", "InsuranceID", "CoverageAmount", "CoverageTimeInDays"};
        
        JComboBox carCombo = new JComboBox(carsSelect);
        JComboBox renterCombo = new JComboBox(renterSelect);
        JComboBox insuranceCombo = new JComboBox(insuranceSelect);
        
        
        JLabel lblFrom = new JLabel("From");
        
      
        JLabel lblWhere = new JLabel("Where");
        
        
        //operators
       
        String[] operator = {">","<", ">=", "<=", "=", "!="};
        JComboBox comboBox_3 = new JComboBox(operator);
        
        //WHERE same as select
        JComboBox comboBox_2 = new JComboBox();
        
        
      //arrays for Table Selection FROM
        String[] tables = {"CarInfo", "Renter", "Insurance"};
        JComboBox comboBox_1 = new JComboBox(tables);
        comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
               
            	String selected = "";
                selected =  (String) comboBox_1.getSelectedItem();
                //System.out.println(selected);
                if(selected.equals("CarInfo")) {
                	comboBox.removeAllItems();
            		comboBox_2.removeAllItems();
                	for (int i = 0; i < carCombo.getItemCount(); i++) {
                		comboBox.addItem(carsSelect[i]);
                		
                	}
                	
                	for (int i = 0; i < NcarsSelect.length; i++) {
                		comboBox_2.addItem(NcarsSelect[i]);
                	}
                	
                }
                if(selected.toString().equals("Renter")) {
                	comboBox.removeAllItems();
            		comboBox_2.removeAllItems();
                	for (int i = 0; i < renterCombo.getItemCount(); i++) {
                		comboBox.addItem(renterSelect[i]);
                		               		
                	}
                	
                	for (int i = 0; i < NrenterSelect.length; i++) {
                		comboBox_2.addItem(NrenterSelect[i]);
                	}
                }
                if(selected.toString().equals("Insurance")) {
                	comboBox.removeAllItems();
            		comboBox_2.removeAllItems();
                	for (int i = 0; i < insuranceCombo.getItemCount(); i++) {
                		comboBox.addItem(insuranceSelect[i]);
                	}
                	
                	for (int i = 0; i < NinsuranceSelect.length; i++) {
                		comboBox_2.addItem(NinsuranceSelect[i]);
                	}
                }   

            }
        });
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        JButton quickQuery = new JButton("Query");
        quickQuery.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					JTable a = data.quickQueryJTable(comboBox, comboBox_1, comboBox_2, comboBox_3, textField_1);
					table.setModel(a.getModel());
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
        	}
        });
        
        JLabel lblTableSelectQuery = new JLabel("Table Select Query:");
        
        JLabel lblSelect = new JLabel("Select:");
        lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnSelectFrom_1 = new JButton("All Renter Information");
        btnSelectFrom_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JTextField a = new JTextField("Select * from Renter;");
        		try {
					JTable b = data.customQueryTable(a);
					table.setModel(b.getModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        JButton btnSelectFrom = new JButton("All Car Information");
        btnSelectFrom.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JTextField a = new JTextField("Select * from CarInfo;");
        		try {
        			JTable b = data.customQueryTable(a);
					table.setModel(b.getModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        JButton btnSelectFrom_2 = new JButton("All Insurance Information");
        btnSelectFrom_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JTextField a = new JTextField("Select * from insurance;");
        		try {
        			JTable b = data.customQueryTable(a);
					table.setModel(b.getModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        JButton btnInsert = new JButton("Insert");
        btnInsert.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		insertFrame newFrame = new insertFrame();
        	}
        });
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		deleteFrame newFrame = new deleteFrame();
        	}
        });
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		updateFrame newFrame = new updateFrame();
        	}
        });
        
        JButton btnNewButton = new JButton("All in-Stock");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JTextField a = new JTextField("Select * from carinfo where carinfo.carID NOT IN (Select carid from renter);");
        		try {
					JTable b = data.customQueryTable(a);
					table.setModel(b.getModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        
        
        
        GroupLayout groupLayout = new GroupLayout(frmCarManagementDatabase.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(35)
        					.addComponent(lblTableSelectQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGap(34))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblLiveQuery, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblSelect, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(comboBox, 0, 113, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblFrom, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(comboBox_1, 0, 113, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblWhere, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(comboBox_2, 0, 113, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(comboBox_3, 0, 66, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(quickQuery, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(16))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(a, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
        			.addGap(16))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnSelectFrom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        				.addComponent(btnInsert, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(41)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        							.addGap(35)
        							.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(btnSelectFrom_1, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        							.addGap(35)
        							.addComponent(btnSelectFrom_2, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
        					.addContainerGap())
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(590)
        					.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGap(16))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblLiveQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        				.addComponent(btnQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(3)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(quickQuery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblTableSelectQuery)
        				.addComponent(lblSelect)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblFrom)
        				.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblWhere)
        				.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(a, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSelectFrom_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnSelectFrom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnSelectFrom_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnInsert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnLogout)
        				.addComponent(btnNewButton))
        			.addGap(12))
        );
        frmCarManagementDatabase.getContentPane().setLayout(groupLayout);
        frmCarManagementDatabase.setVisible(true);
        
        
    }
    
    public static void main (final String[] args) throws SQLException {
        //adminFrame userFrame = new adminFrame();
        //can be run as a frame by itself.
    }
}
