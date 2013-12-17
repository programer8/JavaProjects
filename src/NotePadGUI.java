import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NotePadGUI {

	private JFrame frame;
	private JTextArea textArea;
	private JMenuItem mntmSave;
	private boolean dirtyBit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePadGUI window = new NotePadGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotePadGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setAlignmentY(JTextField.TOP);
		panel.add(textArea, BorderLayout.CENTER);
//		Attaching action Listeners
		mntmSave.addActionListener(save);
		mntmNew.addActionListener(newFile);
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				dirtyBit = true;
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				dirtyBit = true;
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				dirtyBit = true;
			}
		});
	}
	private ActionListener save = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dirtyBit){
				FileDialog fileDialog = new FileDialog(frame,"Save",FileDialog.SAVE);
				fileDialog.setVisible(true);
				String path = fileDialog.getDirectory() + fileDialog.getFile();
				File file = new File(path);
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					if(!file.exists())
						file.createNewFile();
					fileOutputStream.write(textArea.getText().getBytes());
					fileOutputStream.close();
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} else {
				ErrorWindow messageWindow = new ErrorWindow("No Changes to be Saved");
			}
		}
	};
	
	private ActionListener newFile = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dirtyBit)
				mntmSave.doClick();
			JFileChooser fileChooser  = new JFileChooser();
			File file = fileChooser.getSelectedFile().getAbsoluteFile();
			fileChooser.setVisible(true);
		}
	};
}
