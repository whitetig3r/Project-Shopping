package Shopping;

import java.awt.BorderLayout;
import java.lang.*;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopHome extends JFrame {

	/**
	 * Launch the application.
	 */
	
	public int itstock[]={25,10,22,20,10,20,30,8,10,3,10,30};
	public static final int itcost[]={250,50,40,65,20,120,100,450,2500,40,249};
	public static String itemlist[]={"bag","water bottle","pencil","pen","chips","hammer","knife","sunglasses","mobile phone","batteries","headphones"};
	public static float total=0;
	public static JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopHome frame = new ShopHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
			  }
			}
			
		});
	}
	public ShopHome(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 683, 388);
		getContentPane().setLayout(null);
		DefaultListModel<String>items= new DefaultListModel<>();
		StockWriter();
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(247, 90, 210, 141);
		getContentPane().add(textArea);
		for(int i=0;i<10;i++)
		{
			items.addElement(itemlist[i]);
		}
		JList list = new JList(items);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			    e.getFirstIndex();
			    if (!e.getValueIsAdjusting()) {
			          JList list = (JList) e.getSource();
			          int selections[] = list.getSelectedIndices();
			          Object selectionValues[] = list.getSelectedValues();
			          for (int i = 0, n = selections.length; i < n; i++) {
			            if (i == 0) {
			              
			            }
			            System.out.println((String)selectionValues[i] + " ");
			            switch((String)selectionValues[i])
			            {
			               case "bag":
			               { if(itstock[0]>0)
			            	 textArea.setText("1.\n Price:Rs.250 \nHigh quality bag made of neoprene waterproof material.");
			                 
			                break;
			               }
			               case "water bottle":
			               { if(itstock[1]>0)
			            	 textArea.setText("2.\n Price:Rs.50 \n 1 litre high grade plastic water bottle");
			               
			               break;
			               }
			               case "pencil":
			               { if(itstock[2]>0)
			            	 textArea.setText("3.\n Price:Rs.40\n Pack of 10 high quality bonded lead pencils");
			                 
			               break;
			               }
			               case "pen":
			               { if(itstock[3]>0)
			            	 textArea.setText("4.\n Price:Rs.65\nPack of 10 high quality pens");
			               break;
			               }
			               case "chips":
			               { if(itstock[4]>0)
			            	 textArea.setText("5.\n Price:Rs.20 \n The tastiest potato chips with classic salted flavour");
			               break;
			               }
			               case "hammer":
			               { if(itstock[5]>0)
			            	 textArea.setText("6.\n Price:Rs.120\n Wooden base with solid iron head hammer");
			               break;
			               }
			               case "knife":
			               { if(itstock[6]>0)
			            	 textArea.setText("7.\n Price:Rs.100 \n Sharp stainless steel knife");
			                
			               break;
			               }
			               case "sunglasses":
			               { if(itstock[7]>0)
			            	 textArea.setText("8.\n Price:Rs.450\n High quality UV protection sunglasses");
			                 
			               break;
			               }
			               case "mobile phone":
			               { if(itstock[8]>0)
			            	 textArea.setText("9.\n Price:Rs:2500\n Disposable GPRS mobile phone with basic UI");
			                 break;
			               }
			               case "batteries":
			               { if(itstock[9]>0)
			            	 textArea.setText("10.\n Price:Rs40\n AA Batteries with high alkaline content and long life");
			                
			               break;
			               }
			               case "headphones":
			               { if(itstock[10]>0)
			            	 textArea.setText("11.\n Price:Rs249 \n In-ear headphones with 20mm drivers");
			                
			               break;
			               }
			               default:
			               {
			            	   textArea.setText("Select item to see item description!");
			            	   break;
			               }
			               
			            }
			          }
			    }
			}
		});
		list.setBounds(20, 84, 189, 216);
		getContentPane().add(list);
		
		JLabel lblYourCartValue = new JLabel("Your Cart value: Rs.0.00");
		lblYourCartValue.setBounds(278, 284, 189, 16);
		getContentPane().add(lblYourCartValue);
	
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String [] header={"Item"};
		table = new JTable();
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		table.setBounds(484, 85, 182, 216);
		getContentPane().add(table);
		

		JLabel warninglbl = new JLabel("");
		warninglbl.setBounds(265, 284, 165, 16);
		getContentPane().add(warninglbl);
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setBounds(77, 56, 61, 16);
		getContentPane().add(lblItemList);
		
		JLabel lblProductDescription = new JLabel("Product Description:");
		lblProductDescription.setBounds(278, 62, 135, 16);
		getContentPane().add(lblProductDescription);
		JButton btnAddToCart = new JButton("Add to Cart!");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ShoppingCart basket=new ShoppingCart();
		        String key=textArea.getText();
		        if (key.contains("1."))
		        {
		        	if(itstock[0]==0)
		        		warninglbl.setText("Not available in stock!");
		        	else{
		        	warninglbl.setText(" ");
		        	total+=itcost[0];
		        	itstock[0]--;
		        	basket.addItem(itemlist[0],dtm);
		        	System.out.println(itstock[0]);
		        	
		        	}
		        }
		        else if (key.contains("2."))
		        {
		        	if(itstock[1]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
		        		warninglbl.setText(" ");
		        		total+=itcost[1];
		        		basket.addItem(itemlist[1],dtm);
			        	itstock[1]--;
		        	}
		        	
		        }
		        else if (key.contains("3."))
		        {
		        	if(itstock[2]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
	                warninglbl.setText(" ");
		        	total+=itcost[2];
		        	basket.addItem(itemlist[2],dtm);
		        	itstock[2]--;
		        	}
		        }
		        else if (key.contains("4."))
		        {
		        	if(itstock[3]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
		        	warninglbl.setText(" ");
		        	total+=itcost[3];
		        	basket.addItem(itemlist[3],dtm);
		        	itstock[3]--;
		        	}
		        }
		        else if (key.contains("5."))
		        {
		        	if(itstock[4]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
		        	warninglbl.setText(" ");
		        	total+=itcost[4];
		        	basket.addItem(itemlist[4],dtm);
		        	itstock[4]--;
		        	}
		        }
		        else if (key.contains("6."))
		        {
		        	if(itstock[5]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else {
		            warninglbl.setText(" ");
		        	total+=itcost[5];
		        	basket.addItem(itemlist[5],dtm);
		        	itstock[5]--;
		        	}
		        }
		        else if (key.contains("7."))
		        {
		        	if(itstock[6]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
		        	warninglbl.setText(" ");
		        	total+=itcost[6];
		        	basket.addItem(itemlist[6],dtm);
		        	itstock[6]--;
		        	}
		        }
		        else if (key.contains("8."))
		        {
		        	if(itstock[7]==0)
		        	{
		        		warninglbl.setText("Not available in stock!");
		        	}
		        	else{
		        		warninglbl.setText(" ");
		        	total+=itcost[7];
		        	basket.addItem(itemlist[7],dtm);
		        	itstock[7]--;
		        	}
		        }
		        else if (key.contains("9."))
		        {
		        	if(itstock[8]==0)
		        		warninglbl.setText("Not available in stock!");
		        	else{
		        		warninglbl.setText(" ");
		        	total+=itcost[8];
		        	basket.addItem(itemlist[8],dtm);
		        	itstock[8]--;}
		        }
		        else if (key.contains("10."))
		        {
		        	if(itstock[9]==0)
		        		warninglbl.setText("Not available in stock!");
		        	else{
		        	warninglbl.setText(" ");
		        	total+=itcost[9];
		        	basket.addItem(itemlist[9],dtm);
		        	itstock[9]--;
		        	}
		        }
		        else if (key.contains("11."))
		        {
		        	if(itstock[10]==0)
		        		warninglbl.setText("Not availble in stock!");
		        	else{
		        		warninglbl.setText(" ");
		        	total+=itcost[10];
		        	basket.addItem(itemlist[10],dtm);
		        	itstock[10]--;
		        	}
		        }
				lblYourCartValue.setText("Your Cart value: Rs."+total);
			}
	});
	  
		btnAddToCart.setBounds(292, 243, 117, 29);
		getContentPane().add(btnAddToCart);
		
		JButton btnProceedToCheckout = new JButton("Proceed to Checkout!");
		btnProceedToCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float Finalbill=total;
				Bill biller=new Bill(Finalbill);
				biller.BillMaker(dtm);
				StockWriter();
				total=0;
				lblYourCartValue.setText("Your Cart value: Rs."+total);
				ShoppingCart.cleartable(dtm);
			}

			
		});
		btnProceedToCheckout.setBounds(262, 312, 189, 29);
		getContentPane().add(btnProceedToCheckout);
		
		
		
		JLabel lblYourShioppingCart = new JLabel("Your Shopping Cart!");
		lblYourShioppingCart.setBounds(509, 57, 130, 16);
		getContentPane().add(lblYourShioppingCart);
	}
		

	private void StockWriter() {
		 try{
	            File file = new File("stock.txt");
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            for(int i=0;i<10;i++){
	            bw.write(itemlist[i]+"\t"+itstock[i]+"\n");
	            }
	            bw.flush();
	            bw.close();
            }catch(IOException e){
	        e.printStackTrace();
	        }
	    }
		
}
class ShoppingCart extends ShopHome
{
	public void addItem(String item,DefaultTableModel dtm){
		dtm.addRow(new Object[] {item});
	}
	public static void cleartable(DefaultTableModel dtm)
	{
		int i=dtm.getRowCount();
		for(int j=i-1;j>=0;j--)
		{
			dtm.removeRow(j);
		}
	}
	
}
