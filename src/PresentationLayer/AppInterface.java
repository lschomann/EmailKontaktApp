/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import DataLayer.DataLayerManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import BusinessObjects.IEmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.NoEmailKontaktFoundException;
import Exceptions.NoNextEmailKontaktFoundException;
import Exceptions.NoPreviousEmailKontaktFoundException;



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
	private IEmailKontakt current_kontakt;
	private Timer searchTimer;
	private boolean isDirty;
	private Timer dirtyTimer;

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
		vorname_txt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				vorname_txtFocusLost(evt);
			}
		});
		vorname_txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt){
				vorname_txtKeyTyped(evt);
			}
		});
		name_txt = new javax.swing.JTextField();
		name_txt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				name_txtFocusLost(evt);
			}
		});
		name_txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt){
				name_txtKeyTyped(evt);
			}
		});
		email_txt = new javax.swing.JTextField();
		email_txt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				email_txtFocusLost(evt);
			}
		});
		email_txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt){
				email_txtKeyTyped(evt);
			}
		});
		
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
						showStatus(String.format("%s%s", e.getClass(), (e.getMessage() == null ? "" : " : " +e.getMessage())));
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
						showStatus(String.format("%s%s", e.getClass(), (e.getMessage() == null ? "" : " : " +e.getMessage())));
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
			end_program.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						end_programActionPerformed(evt);
					} catch (NoEmailKontaktFoundException e) {
						showStatus(String.format("%s%s", e.getClass(), (e.getMessage() == null ? "" : " : " +e.getMessage())));
					}
				}
			});

		}

		jMenu2.setText("Edit");
		menuBar.add(jMenu2);
		{
			edit_new_entry = new JMenuItem();
			jMenu2.add(edit_new_entry);
			jMenu2.add(getDelete_entry());
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
			.addGroup(layout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			        .addComponent(prev_btn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
			        .addComponent(next_btn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			    .addGroup(layout.createSequentialGroup()
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			            .addComponent(getSearch_txt(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(getSearch_lbl(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))))
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(id_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(id_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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
			.addContainerGap());
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
			    .addGroup(layout.createSequentialGroup()
			        .addGroup(layout.createParallelGroup()
			            .addComponent(vorname_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                .addComponent(getSearch_lbl(), 0, 40, Short.MAX_VALUE)
			                .addGap(6))
			            .addGroup(layout.createSequentialGroup()
			                .addGap(7)
			                .addGroup(layout.createParallelGroup()
			                    .addComponent(email_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                    .addComponent(name_lbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                        .addPreferredGap(email_lbl, id_lbl, LayoutStyle.ComponentPlacement.INDENT)
			                        .addComponent(id_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
			                .addGap(8)))
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
			                .addGroup(layout.createParallelGroup()
			                    .addComponent(getSearch_txt(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
			                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                        .addComponent(id_txt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			                        .addGap(29)))
			                .addGap(47)
			                .addComponent(prev_btn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
			                .addComponent(next_btn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
			                .addGap(0, 13, Short.MAX_VALUE))))
			    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			        .addComponent(header_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 47, Short.MAX_VALUE)))
			.addContainerGap(38, 38));
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				try {
					thisWindowOpened(evt);
				} catch (NoEmailKontaktFoundException e) {
					showStatus(String.format("%s%s", e.getClass(), (e.getMessage() == null ? "" : " : " +e.getMessage())));
				}
			}
		});

		pack();
		this.setSize(316, 258);
	}// </editor-fold>//GEN-END:initComponents

	protected void email_txtKeyTyped(KeyEvent evt) {
		this.startDirtyTimer();
	}

	protected void name_txtKeyTyped(KeyEvent evt) {
		this.startDirtyTimer();
		
	}

	protected void vorname_txtKeyTyped(KeyEvent evt) {
		this.startDirtyTimer();
	}

	private void startDirtyTimer() {
		if (this.dirtyTimer == null){
			final AppInterface self = this;
			ActionListener p = new ActionListener() {
			    public void actionPerformed(ActionEvent evt) {
			    	self.isDirty = (
		    			(self.vorname_txt.getText() != null && !self.vorname_txt.getText().equals(self.getCurrent().getVorname()) 
		    				|| self.getCurrent().getVorname() == self.vorname_txt.getText())
		    			|| (self.name_txt.getText() != null && !self.name_txt.getText().equals(self.getCurrent().getNachname()) 
			    				|| self.getCurrent().getNachname() == self.name_txt.getText())
			    		|| (self.email_txt.getText() != null && !self.email_txt.getText().equals(self.getCurrent().getEmail()) 
			    				|| self.getCurrent().getEmail() == self.email_txt.getText())
			    	);
			    	self.dirtyTimer.stop();
			    }
			};
			this.dirtyTimer = new Timer(50, p);
		}
		this.dirtyTimer.restart();
	}

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

	private void next_btnMouseClicked(MouseEvent evt)
			throws NoNextEmailKontaktFoundException {
		System.out.println("next_btn mouseClicked");
		update(dao.next(getCurrent()));
	}

	private void prev_btnMouseClicked(MouseEvent evt)
			throws NoPreviousEmailKontaktFoundException {
		System.out.println("prev_btn mouseClicked");
		update(dao.previous(getCurrent()));
	}

	private void thisWindowOpened(WindowEvent evt)
			throws NoEmailKontaktFoundException {
		System.out.println("Window opend");
		IEmailKontakt baseEntry = dao.first();
		update(baseEntry);
	}

	private void end_programActionPerformed(ActionEvent evt)
			throws NoEmailKontaktFoundException {
		System.out.println("end_program actionPerformed");

		// Show dialog if user want to save state
		int n = JOptionPane.showConfirmDialog(this, "Save your changes?",
				"Really quit?", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {

			IEmailKontakt c = getContact();

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

	// Add IEmailKontakt Objekt to txt Fields
	private void update(IEmailKontakt t) {
		this.id_txt.setText(Integer.toString(t.getID()));
		this.vorname_txt.setText(t.getVorname());
		this.name_txt.setText(t.getNachname());
		this.email_txt.setText(t.getEmail());
		this.setCurrent(t);
		this.isDirty = false;
	}

	// Create IEmailKontakt Object
	private IEmailKontakt getContact() {
		int id = Integer.parseInt((this.id_txt.getText() == null ? "0" : this.id_txt.getText()));
		IEmailKontakt c = null;
		try {
			c = dao.select(id);
		} catch (NoEmailKontaktFoundException e) {
			c = dao.create();
		}
		c.setEmail((this.email_txt.getText()));
		c.setVorname((this.vorname_txt.getText()));
		c.setNachname((this.name_txt.getText()));

		return c;
	}

	// Return the current set contact
	private IEmailKontakt getCurrent() {
		return this.current_kontakt;
	}

	// Set the current contact to current_kontakt
	private void setCurrent(IEmailKontakt k) {
		this.current_kontakt = k;
	}

	private void vorname_txtFocusLost(FocusEvent evt) {
		System.out.println("vorname_txt focusLost");

		IEmailKontakt k = getContact();
		if (isDirty){
			dao.save(k);
			isDirty = false;
		}
	}

	private void name_txtFocusLost(FocusEvent evt) {
		System.out.println("name_txt focusLost");

		IEmailKontakt k = getContact();
		if (isDirty){
			dao.save(k);
			isDirty = false;
		}
	}

	private void email_txtFocusLost(FocusEvent evt) {
		System.out.println("email_txt focusLost");

		IEmailKontakt k = getContact();
		
		if (isDirty){
			dao.save(k);
			isDirty = false;
		}
	}

	private JMenuItem getDelete_entry() {
		if (delete_entry == null) {
			delete_entry = new JMenuItem();
			delete_entry.setText("Delete");
			delete_entry.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						delete_entryActionPerformed(evt);
					} catch (NoNextEmailKontaktFoundException e) {
						showStatus(String.format("%s%s", e.getClass(), (e.getMessage() == null ? "" : " : " +e.getMessage())));
					}
				}
			});
		}
		return delete_entry;
	}

	private void delete_entryActionPerformed(ActionEvent evt) throws NoNextEmailKontaktFoundException {
		System.out.println("delete_entry actionPerformed");
		IEmailKontakt k = getCurrent();
		try {
			// If a following entry exists
			update(dao.next(k));
		}
		catch(NoNextEmailKontaktFoundException e) {
			try {
				// If a previous entry exists
				update(dao.previous(k));
			}
			catch(NoPreviousEmailKontaktFoundException f) {
				// Create a new empty entry
				update(this.getContact());
			}
		}
		finally{
			// Delete the selected entry from database
			dao.delete(k);
		}
		
	}
	
	private JTextField getSearch_txt() {
		if(search_txt == null) {
			search_txt = new JTextField();
			search_txt.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent evt) {
					search_txtKeyTyped(evt);
				}
			});
		}
		return search_txt;
	}
	
	private JLabel getSearch_lbl() {
		if(search_lbl == null) {
			search_lbl = new JLabel();
			search_lbl.setText("Suchen:");
		}
		return search_lbl;
	}
	
	private void search_txtKeyTyped(KeyEvent evt) {
		System.out.println("search_txt.keyPressed, event="+evt);
		this.startSearchTimer();
	}

	private void startSearchTimer() {
		if (searchTimer == null){
			final AppInterface self = this;
			
			int delay = 300; //milliseconds
			ActionListener taskPerformer = new ActionListener() {
			    public void actionPerformed(ActionEvent evt) {
			    	try{
			    		String searchText = self.search_txt.getText() == null ? "" : self.search_txt.getText();
						IEmailKontakt[] objs = dao.select(searchText);
						if (objs.length > 0){
							self.update(objs[0]);
						}
					}
					catch(NoEmailKontaktFoundException e){
						// pass
					}
			    	finally{
			    		self.searchTimer.stop();
			    	}
			    }
			};
			this.searchTimer = new Timer(delay, taskPerformer);
		}
		
		this.searchTimer.restart();
	}
	
	private void showStatus(String msg){
		System.out.println(msg);
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
	private JLabel search_lbl;
	private JTextField search_txt;
	private JMenuItem delete_entry;
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
