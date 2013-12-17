import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;


public class ErrorWindow {

	private JDialog dialog;
	private String Message;
	/**
	 * Create the application.
	 */
	public ErrorWindow(String Message) {
		this.Message = Message;
		initialize();
		dialog.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dialog = new JDialog();
		dialog.setResizable(false);
		dialog.setBounds(100, 100, 503, 148);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setHorizontalAlignment(lblNewLabel.CENTER);
		lblNewLabel.setText(Message);
	}

}
