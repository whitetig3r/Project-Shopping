package Shopping;

import java.awt.BorderLayout;
import java.io.*;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Bill extends JDialog implements BillPrint{

	private final JPanel contentPanel = new JPanel();
	public String product;

	/**
	 * Create the dialog.
	 */
	public Bill(float Amount) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Thank You for shopping with us!");
			lblNewLabel.setFont(new Font("Lao MN", Font.BOLD, 19));
			lblNewLabel.setBounds(76, 6, 327, 57);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblYourBillIs = new JLabel("Your Bill is:");
			lblYourBillIs.setHorizontalAlignment(SwingConstants.CENTER);
			lblYourBillIs.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			lblYourBillIs.setBounds(143, 62, 173, 33);
			contentPanel.add(lblYourBillIs);
		}
		{
			JLabel BillAmt = new JLabel("Rs."+Amount);
			BillAmt.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			BillAmt.setHorizontalAlignment(SwingConstants.CENTER);
			BillAmt.setBounds(126, 108, 215, 33);
			contentPanel.add(BillAmt);
		}
	}
	public void BillMaker(DefaultTableModel d1)
	{
		File bill=new File("bill.txt");
		try {
			FileWriter fw=new FileWriter(bill);
		    BufferedWriter bw = new BufferedWriter(fw);
		int i=d1.getRowCount();
		for(int j=i-1;j>=0;j--)
		{
			product=d1.getValueAt(j,0).toString();
			bw.write(product+"\n");
			
		}
		bw.write("Total:"+ShopHome.total);
		bw.flush();
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
