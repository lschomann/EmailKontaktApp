/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle;

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
 * @author lschomann
 */
public class AppInterface extends javax.swing.JFrame{

    /**
     * Creates new form AppInterface
     */
    public AppInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        file_end_menu = new javax.swing.JMenuItem();
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
        }
        {
        	next_btn = new JButton();
        	next_btn.setText("->");
        }

        file_menu.setText("File");

        file_end_menu.setText("Beenden");
        file_menu.add(file_end_menu);

        menuBar.add(file_menu);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setVerticalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addComponent(header_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    .addComponent(id_txt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(id_lbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(next_btn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	    .addComponent(prev_btn, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
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
        	.addContainerGap());
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
        	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))))
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
        	                .addGap(0, 80, Short.MAX_VALUE)
        	                .addComponent(prev_btn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        	                .addGap(0, 52, GroupLayout.PREFERRED_SIZE))))
        	    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
        	        .addComponent(header_lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	        .addComponent(next_btn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 8, Short.MAX_VALUE)))
        	.addContainerGap(38, 38));

        pack();
        this.setSize(316, 230);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppInterface().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel email_lbl;
    private javax.swing.JTextField email_txt;
    private javax.swing.JMenuItem file_end_menu;
    private javax.swing.JMenu file_menu;
    private javax.swing.JLabel header_lbl;
    private javax.swing.JLabel id_lbl;
    private javax.swing.JTextField id_txt;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar menuBar;
    private JButton prev_btn;
    private JButton next_btn;
    private javax.swing.JLabel name_lbl;
    private javax.swing.JTextField name_txt;
    private javax.swing.JLabel vorname_lbl;
    private javax.swing.JTextField vorname_txt;
    // End of variables declaration//GEN-END:variables


   
}
