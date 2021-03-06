/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.city.system.view;

import food.city.system.controller.ItemInfoController;
import food.city.system.dto.ItemDTO;
import food.city.system.dto.OrderItemDetailsDTO;
import food.city.system.dto.SupplierItemsDetailsDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Avishka
 */
public class ItemInfo extends javax.swing.JFrame {

    /**
     * Creates new form ItemInfo
     */
    
    private ItemInfoController itemInfoController;
    private Main main;
    
    public ItemInfo(Main main) {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.main = main;
        main.setVisible(false);
        
        this.itemInfoController = new ItemInfoController();
        String date = LocalDate.now().toString();
                
        loadAllItemsToday(date);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                main.setVisible(true);
                e.getWindow().dispose();
            }
        });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemInfoTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(849, 591));

        mainLabel.setFont(new java.awt.Font("Droid Sans", 1, 24)); // NOI18N
        mainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLabel.setText("Item Information");

        itemInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Buying Price", "Selling Price", "Bought Items", "Selled Items", "Remaining Items"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(itemInfoTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
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
            java.util.logging.Logger.getLogger(ItemInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable itemInfoTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mainLabel;
    // End of variables declaration//GEN-END:variables

    private void loadAllItemsToday(String date) {
        try {
            ArrayList<ItemDTO> allItems = itemInfoController.getALLItems();
            SupplierItemsDetailsDTO supplierItemsDetailsDTO = itemInfoController.getSupplierItemsDetailOnDay(date);
            ArrayList<Object> orderItemDetailsDTO = itemInfoController.getOrderItemsDetailOnDay(date);
            DefaultTableModel dtm = (DefaultTableModel) itemInfoTable.getModel();
            dtm.setRowCount(0);
            
            ArrayList<String> Iids = supplierItemsDetailsDTO.getIids();
            ArrayList<Integer> noOfItemsList = supplierItemsDetailsDTO.getNoItems();
            ArrayList<String> selledIids = (ArrayList<String>) orderItemDetailsDTO.get(0);
            ArrayList<Integer> sellednoOfItemsList = (ArrayList<Integer>) orderItemDetailsDTO.get(1);;
            
            for (ItemDTO itemDTO  : allItems) {
                String Iid = itemDTO.getIid();
                int boughtItems = 0;
                int selledItems = 0;
                int index = 0;
                if (Iids.contains(Iid)) {
                    index = Iids.indexOf(Iid);
                    boughtItems = noOfItemsList.get(index);
                }
                if (selledIids.contains(Iid)) {
                    index = selledIids.indexOf(Iid);
                    selledItems = sellednoOfItemsList.get(index);
                    
                }
                
                Object[] rowData = {
                    itemDTO.getIid(),
                    itemDTO.getName(),
                    itemDTO.getBuyingPrice(),
                    itemDTO.getSellingPrice(),
                    boughtItems,
                    selledItems,
                    itemDTO.getNoOfItems()
                };
                dtm.addRow(rowData);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
