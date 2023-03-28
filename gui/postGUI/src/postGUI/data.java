package postGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class data {
    
    //connect to postgresql database on local machine 							ex: jdbc:postgresql://localhost:5432/alt
	static Connection connect() {
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alt");
            //System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
    //authenticate a login to database with user/pass combination
    public static int login (String username, String password) {
        // 1 = yes 2 = no
       String sql = "Select * from login where username = " + "'" + username + "';";
       ResultSet rs = null;
       Connection conn = connect();
       try {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String sqlUser = rs.getString(1);
            String sqlPass = rs.getString(2);
            if (sqlUser.equals(username) && sqlPass.equals(password)) {
                return 1;
            }
            
        }
        
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     JOptionPane.showMessageDialog(null, "Invalid username/password.");  
     return 2;
    }
    
    
    //insert a user into login table
    public static boolean registerUser (String username,String password) {
        String sql;
               
            sql = "INSERT INTO login VALUES(" + "'" + username + "'" +  "," + "'" + password + "');";
                
        
        try { 
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "User Registered, Proceed to login.");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Username already exists. Try Again.");
            return false;
        }
        return true;
    }
    
    //method of insertFrame
    public static void insertCarInfo(JTextField textField, JTextField textField_1, JTextField textField_2,JTextField textField_3,JTextField textField_4,JTextField textField_5,JTextField textField_6) {
    	String a = textField.getText();
    	String b = textField_1.getText();
    	String c = textField_2.getText();
    	String d = textField_3.getText();
    	String e = textField_4.getText();
    	String f = textField_5.getText();
    	String g = textField_6.getText();
    	
    	String sql = "INSERT INTO carINFO VALUES (" +  "'" + a + "'" + "," + "'" + b + "'" +"," + "'" + c  + "'" + "," + "'" + d  + "'" + "," + "'" + e  + "'" + "," + "'" + f  + "'" + "," + "'" + g + "'" + ");";
    	
    	 try { 
             Connection conn = connect();
             Statement statement = conn.createStatement();
             statement.execute(sql);
             JOptionPane.showMessageDialog(null, "Insert Success.");
         } catch (SQLException ee) {
             ee.printStackTrace();
             JOptionPane.showMessageDialog(null, "Insert Failed.");
         }
    }
    
    //method of insertFrame
    public static void insertRenterInfo(JTextField textField, JTextField textField_1, JTextField textField_2,JTextField textField_3,JTextField textField_4,JTextField textField_5,JTextField textField_6) {
    	String a = textField.getText();
    	String b = textField_1.getText();
    	String c = textField_2.getText();
    	String d = textField_3.getText();
    	String e = textField_4.getText();
    	String f = textField_5.getText();
    	String g = textField_6.getText();
    	
    	String sql = "INSERT INTO renter VALUES (" +  "'" + a + "'" + "," + "'" + b + "'" +"," + "'" + c  + "'" + "," + "'" + d  + "'" + "," + "'" + e  + "'" + "," + "'" + f  + "'" + "," + "'" + g + "'" + ");";
    	
    	 try { 
             Connection conn = connect();
             Statement statement = conn.createStatement();
             statement.execute(sql);
             JOptionPane.showMessageDialog(null, "Insert Success.");
         } catch (SQLException ee) {
             ee.printStackTrace();
             JOptionPane.showMessageDialog(null, "Insert Failed.");
         }
    }
    
    //method of insertFrame
    public static void insertInsuranceInfo(JTextField textField_14, JTextField textField_15, JTextField textField_16,
			JTextField textField_17) {
    	String a = textField_14.getText();
    	String b = textField_15.getText();
    	String c = textField_16.getText();
    	String d = textField_17.getText();
    	String sql = "INSERT INTO insurance VALUES (" +  "'" + a + "'" + "," + "'" + b + "'" +"," + "'" + c  + "'" + "," + "'" + d + "');";
    	
    	try { 
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Insert Success.");
        } catch (SQLException ee) {
            ee.printStackTrace();
            JOptionPane.showMessageDialog(null, "Insert Failed.");
        }
	}
    
    public static void delete(String selectedItem, String selectedItem2, String selectedItem3, String text) {
		
    	String sql = "Delete from " + selectedItem + " Where " + selectedItem2 + " " + selectedItem3 + " '" + text + "';";
    	System.out.println(sql);
    	try { 
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Delete Success.");
        } catch (SQLException ee) {
            ee.printStackTrace();
            JOptionPane.showMessageDialog(null, "Delete Failed.");
        }
		
	}
    
    public static void update(String selectedItem, String selectedItem2, String selectedItem3, String selectedItem4, String text, String text2) {
    	
    	String sql = "Update " + selectedItem + " Set " + selectedItem2 + " = '" + text + "' Where " + selectedItem3 + " " + selectedItem4 + " '" + text2 + "';" ;
    	System.out.println(sql);
    	
    	try { 
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Update Success.");
        } catch (SQLException ee) {
            ee.printStackTrace();
            JOptionPane.showMessageDialog(null, "Update Failed.");
        }
    }
    
    //default table upon opening app
    public  static JTable makeJTable () throws SQLException {
        
        String sql = "Select * from CarInfo;";
        
        Connection conn = connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
               
                
                return new JTable(buildTableModel(rs));
    }
    
    //perform an sql query with the interface
    public  static JTable customQueryTable (JTextField textField) throws SQLException {
        
        String sql = textField.getText();
        System.out.println(sql);
        
        
        Connection conn = connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
               
                
                return new JTable(buildTableModel(rs));
    }
    
    //quicksearch  sql database with dropdown menus and textfields
    public static JTable quickQueryJTable (JComboBox comboBox, JComboBox comboBox_1, JComboBox comboBox_2, JComboBox comboBox_3, JTextField textField_1) throws SQLException {
    	String sql;
		String a = (String) comboBox.getSelectedItem();
		String b = (String) comboBox_1.getSelectedItem();
		String c = (String) comboBox_2.getSelectedItem();
		String d = (String) comboBox_3.getSelectedItem();
		String e = textField_1.getText();
		
		//if (e.equalsIgnoreCase("LIKE")) {
			//sql = "Select " + a + " From " + b + " Where " + c + " " + d + " " + "'" + e + "';";
		//} else {
			sql = "Select " + a + " From " + b + " Where " + c + " " + d + " " + "'" + e + "';";
		//}
    	 System.out.println(sql);
    	 Connection conn = connect();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql);
         
         
         return new JTable(buildTableModel(rs));
    }
    
    
    //create model to build JTable
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector columnNames = new Vector(5);
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel( data, columnNames);
    }

	

	

	

}
