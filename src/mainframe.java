import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;


import javax.swing.JPasswordField;
import javax.swing.JFileChooser;
import javax.swing.*;
public class mainframe extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private File file;
	private JFileChooser jfc =  new JFileChooser(".");
	
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
	
	/**
	 * Launch the application.
	 */
//	
	 public static String cipherEncryption(String msg, String key){
		 if(key == "") 
		 	{
		 		JOptionPane.showMessageDialog(null,"Password Field is Empty!");
		 	}
		 	else {
	        String encrypHexa = "";
	        int keyItr = 0; 
	        for(int i=0; i< msg.length(); i++){
	            int temp = msg.charAt(i) ^ key.charAt(keyItr);
	            
	            encrypHexa += String.format("%02x",(byte) temp);
	            keyItr++; 
	            if(keyItr >= key.length()){
	                keyItr = 0; 
	            }
	        }
			return encrypHexa;
		 	}
		 	return "";
	 }
	 
	 public static String cipherDecryption(String msg, String key) {
		 
		 if(key == "") 
		 	{
		 		JOptionPane.showMessageDialog(null,"Password Field is Empty!");
		 	}
		 	else {
	       String hexToDeci = "";
	        for (int i = 0; i < msg.length()-1; i+=2) {
	            String output = msg.substring(i, (i+2));
	            int decimal = Integer.parseInt(output, 16);
	            hexToDeci += (char)decimal;
	        }

	        String decrypText = "";
	        int keyItr = 0;
	        for (int i = 0; i < hexToDeci.length(); i++) {
	            int temp = hexToDeci.charAt(i) ^ key.charAt(keyItr);

	            decrypText += (char)temp;
	            keyItr++;
	            if(keyItr >= key.length()){
	                keyItr = 0;
	            }

	        }

	        return decrypText;
		 	}
		 	return "";
	    }
	 
		 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				//	ImageIcon img = new ImageIcon("/img/icon.png");
				//	mainframe mainframe = new mainframe();
				//	mainframe.setIconImage(img.getImage());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public mainframe() {
		
	//	setIconImage(logo.getImage());
		setResizable(false);
		setTitle("TextCrypt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Image icon = new ImageIcon(getClass().getClassLoader().getResource("/img/icon.png")).getImage();
		setIconImage(icon.getImage());
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Encrypted/Plain Text");
		lblNewLabel.setBounds(297, 20, 155, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 43, 292, 146);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(207, 217, 72, 13);
		contentPane.add(lblNewLabel_1);
		
		final JTextArea textArea_1 = new JTextArea();
		
		
		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 
				
				 String contents = textArea.getText();
		           
		            char[] inkey = passwordField.getPassword();
		            String inkeystr = String.valueOf(inkey);
		            if(inkeystr.length() == 0) 
				 	{
				 		JOptionPane.showMessageDialog(null,"Password Field is Empty!");
				 	}
		            else {
		            try {
						contents = cipherEncryption(contents, inkeystr);
					} catch (Exception e1) {
						 
						e1.printStackTrace();
					}
		            textArea_1.setText(contents);
		            String null1 = "";
		            passwordField.setText(null1);
		            }
				
			}
		});
		btnNewButton.setBounds(207, 247, 128, 21);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(207, 293, 292, 146);
		contentPane.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("OUTPUT");
		lblNewLabel_2.setBounds(334, 278, 103, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Copy to clipboard");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StringSelection stringSelection = new StringSelection (textArea_1.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				
			}
		});
		btnNewButton_1.setBounds(207, 459, 134, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save to a file");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textArea_1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Output Field is Empty!");
				}
				else 
				{
					int status = jfc.showSaveDialog(null);
					file = jfc.getSelectedFile();
					if(status==0)
					{
						try {
							
							PrintWriter pw = new PrintWriter(file);
							String con = textArea_1.getText();
							pw.print(con);
							pw.flush();
							pw.close();
							JOptionPane.showMessageDialog(null,"Saved successfully");
						
						} catch (FileNotFoundException e1) {
						
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,"Failed to save the file due to an unknown error!");
						}
					}
				}
				
				
				
			}
		});
		btnNewButton_2.setBounds(351, 459, 147, 21);
		contentPane.add(btnNewButton_2);
		JButton btnNewButton_3 = new JButton("Decrypt");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String contents = textArea.getText();
		            
		            char[] inkey = passwordField.getPassword();
		            String inkeystr = String.valueOf(inkey);
		            if(inkeystr.length() == 0) 
				 	{
				 		JOptionPane.showMessageDialog(null,"Password Field is Empty!");
				 	}
		            else {
		           
		            try {
						contents = cipherDecryption(contents, inkeystr);
					} catch (Exception e1) {
						 
						e1.printStackTrace();
					}
		          
		            textArea_1.setText(contents);
		            String null1 = "";
		            passwordField.setText(null1);
		            }
				
			}
		});
		btnNewButton_3.setBounds(375, 247, 124, 21);
		contentPane.add(btnNewButton_3);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					passwordField.setEchoChar((char)0);
				}
				else
				{
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(413, 213, 164, 21);
		contentPane.add(chckbxNewCheckBox);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(279, 214, 128, 19);
		contentPane.add(passwordField);
	}
}
