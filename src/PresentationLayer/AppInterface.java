/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle;
import javax.swing.JOptionPane;


import BusinessObjects.IEmailKontakt;
import DataLayer.DataLayerManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.NoEmailKontaktFoundException;
import Exceptions.NoNextEmailKontaktFoundException;
import Exceptions.NoPreviousEmailKontaktFoundException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * 
 * @author Lukas Schomann
 */
public class AppInterface extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form AppInterface
	 */

	private static IEmailKontaktDAO dao;

	public AppInterface() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		vorname_txt = new javax.swing.JTextField();
		name_txt = new javax.swing.JTextField();
		email_txt = new javax.swing.JTextField();
		vorname_lbl = new javax.swing.JLabel();
		name_lbl = new javax.swing.JLabel();
		email_lbl = new javax.swing.JLabel();
		id_lbl = new javax.swing.JLabel();
		id_txt = new javax.swing.JTextField();
		header_lbl = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		file_menu = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Email Kontakt Manager v0.1");

		vorname_lbl.setText("Vorname:");

		name_lbl.setText("Name:");

		email_lbl.setText("eMail:");

		id_lbl.setText("ID:");

		id_txt.setEnabled(false);

		header_lbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		header_lbl.setText("Email Kontakt Manager");
		{
			prev_btn = new JButton();
			prev_btn.setText("<-");
			prev_btn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					try {
						prev_btnMouseClicked(evt);
					} catch (NoPreviousEmailKontaktFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		{
			next_btn = new JButton();
			next_btn.setText("->");
			next_btn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					try {
						next_btnMouseClicked(evt);
					} catch (NoNextEmailKontaktFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		file_menu.setText("File");

		menuBar.add(file_menu);
		{
			end_program = new JMenuItem();
			file_menu.add(end_program);
			end_program.setText("Quit");
			end_program.addActionListener(new ActionListener()  {
				public void actionPerformed(ActionEvent evt) {
					try {
						end_programActionPerformed(evt);
					} catch (NoEmailKontaktFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		}

		jMenu2.setText("Edit");
		menuBar.add(jMenu2);
		{
			edit_new_entry = new JMenuItem();
			jMenu2.add(edit_new_entry);
			edit_new_entry.setText("New");
			edit_new_entry.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					edit_new_entryActionPerformed(evt);
				}
			});
		}

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(header_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(prev_btn, GroupLayout.Alignment.BASELINE, 0, 26, Short.MAX_VALUE)
			    .addComponent(id_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(id_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(next_btn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(vorname_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(vorname_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(name_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(name_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(email_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(email_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(24, 24));
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
			    .addGroup(layout.createSequentialGroup()
			        .addGroup(layout.createParallelGroup()
			            .addComponent(vorname_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addGroup(layout.createSequentialGroup()
			                .addPreferredGap(vorname_lbl, email_lbl, LayoutStyle.ComponentPlacement.INDENT)
			                .addGroup(layout.createParallelGroup()
			                    .addComponent(email_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                    .addComponent(name_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                        .addPreferredGap(email_lbl, id_lbl, LayoutStyle.ComponentPlacement.INDENT)
			                        .addComponent(id_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                        .addGap(9)))
			                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addGroup(layout.createParallelGroup()
			            .addGroup(layout.createSequentialGroup()
			                .addComponent(email_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
			                .addGap(0, 0, Short.MAX_VALUE))
			            .addGroup(layout.createSequentialGroup()
			                .addComponent(name_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
			                .addGap(0, 0, Short.MAX_VALUE))
			            .addGroup(layout.createSequentialGroup()
			                .addComponent(vorname_txt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
			                .addGap(0, 0, Short.MAX_VALUE))
			            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                .addComponent(id_txt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			                .addGap(76)
			                .addComponent(prev_btn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
			                .addComponent(next_btn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
			                .addGap(0, 13, Short.MAX_VALUE))))
			    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			        .addComponent(header_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 52, Short.MAX_VALUE)))
			.addContainerGap(25, 25));
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				try {
					thisWindowOpened(evt);
				} catch (NoEmailKontaktFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		pack();
		this.setSize(316, 258);
	}// </editor-fold>//GEN-END:initComponents

	
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AppInterface.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AppInterface.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AppInterface.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AppInterface.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AppInterface().setVisible(true);

				/** Main Routine */
				DataLayerManager _instance = DataLayerManager.getInstance();
				dao = _instance.getDataLayer().getEmailKontaktDao();
			}
		});
	}

	private void next_btnMouseClicked(MouseEvent evt) throws NoNextEmailKontaktFoundException {
		System.out.println("next_btn mouseClicked");
		update(dao.next(getCurrent()));
	}

	private void prev_btnMouseClicked(MouseEvent evt) throws NoPreviousEmailKontaktFoundException {
		System.out.println("prev_btn mouseClicked");
		update(dao.previous(getCurrent()));
	}

	private void thisWindowOpened(WindowEvent evt) throws NoEmailKontaktFoundException {
		System.out.println("Window opend");
		IEmailKontakt baseEntry = dao.first();
		update(baseEntry);


	}

	private void end_programActionPerformed(ActionEvent evt) throws NoEmailKontaktFoundException {
		System.out.println("end_program actionPerformed");
		
		//Show dialog if user want to save state
		int n = JOptionPane.showConfirmDialog(
			    this,
			    "Save your changes?",
			    "Really quit?",
			    JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){

				int id = Integer.parseInt(this.id_txt.getText());
				IEmailKontakt c = dao.select(id);
				c.setEmail(this.email_txt.getText());
				c.setVorname(this.vorname_txt.getText());
				c.setNachname(this.name_txt.getText());
				
				
				dao.save(c);

		}

	}

	private void edit_new_entryActionPerformed(ActionEvent evt) {
		System.out.println("edit_new_entry actionPerformed");
	
		update(create_new_entry());
	}
	
	// Help methods
	private IEmailKontakt create_new_entry() {
		
		IEmailKontakt k = dao.create();
		dao.save(k);
		update(k);
		return k;
	}
	
	
	private IEmailKontakt current_kontakt;
	
	// Add IEmailKontakt Objekt to txt Fields 
	private void update(IEmailKontakt t) {
		this.id_txt.setText(Integer.toString(t.getID()));
		this.vorname_txt.setText(t.getVorname());
		this.name_txt.setText(t.getNachname());
		this.email_txt.setText(t.getEmail());
		this.setCurrent(t);
	}
	
	// Return the current set contact
	private IEmailKontakt getCurrent(){
		
		return this.current_kontakt;
	}
	
	// Set the current contact to current_kontakt
	private void setCurrent(IEmailKontakt k)
	{
		this.current_kontakt = k;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel email_lbl;
	private javax.swing.JTextField email_txt;
	private javax.swing.JMenu file_menu;
	private javax.swing.JLabel header_lbl;
	private javax.swing.JLabel id_lbl;
	private javax.swing.JTextField id_txt;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar menuBar;
	private JMenuItem end_program;
	private JMenuItem edit_new_entry;
	private JButton prev_btn;
	private JButton next_btn;
	private javax.swing.JLabel name_lbl;
	private javax.swing.JTextField name_txt;
	private javax.swing.JLabel vorname_lbl;
	private javax.swing.JTextField vorname_txt;
	// End of variables declaration//GEN-END:variables

}
