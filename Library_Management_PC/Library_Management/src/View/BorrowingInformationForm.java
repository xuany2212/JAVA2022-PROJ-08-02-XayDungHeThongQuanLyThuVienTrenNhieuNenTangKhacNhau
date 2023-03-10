/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.*;
import Controller.*;
import Model.*;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BorrowingInformationForm extends javax.swing.JPanel {
    
    MainFormOfLibrarian mainFormOfLibrarian;
    ArrayList<Borrower> arrAvailableBorrower;  
    
    /**
     * Creates new form panelTimKiemThongTinMuonSach
     */
    public BorrowingInformationForm(MainFormOfLibrarian mainFormOfLibrarian) {
        initComponents();
        this.mainFormOfLibrarian = mainFormOfLibrarian;
        try {
            arrAvailableBorrower = Borrower_Controller.getInstance().getListBorrower();
            initListBorrower(arrAvailableBorrower);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfIdBorower = new javax.swing.JTextField();
        btnSearcch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbIdBorrowCard = new javax.swing.JLabel();
        lbIdBorrower = new javax.swing.JLabel();
        lbNameBorrower = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSearchBorrower = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBorrowingInformation = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDetailBorrowingInfor = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Nh???p m?? ng?????i m?????n ");

        tfIdBorower.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfIdBorowerKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfIdBorowerKeyTyped(evt);
            }
        });

        btnSearcch.setText("T??m");
        btnSearcch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearcchActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Th??ng tin ng?????i m?????n"));

        lbIdBorrowCard.setText("M?? th??? : ");

        lbIdBorrower.setText("M?? ng?????i m?????n : ");

        lbNameBorrower.setText("T??n ng?????i m?????n : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIdBorrowCard, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIdBorrower, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameBorrower, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIdBorrowCard, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIdBorrower, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNameBorrower, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jListSearchBorrower.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListSearchBorrowerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListSearchBorrower);

        tableBorrowingInformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? th??ng tin m?????n", "Ng??y ????ng k?? m?????n", "H???n tr???", "Tr???ng Th??i", "Ti???n ?????t c???c"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBorrowingInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBorrowingInformationMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableBorrowingInformation);

        tableDetailBorrowingInfor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? b???n sao s??ch", "T??n s??ch", "Tr???ng th??i", "Ng??y m????n", "Ng??y tr???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableDetailBorrowingInfor);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh s??ch th??ng tin m?????n ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chi ti???t m?????n tr??? ");

        btnCancel.setText("Tho??t");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jButton1.setText("Chi ti???t");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(tfIdBorower))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearcch, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfIdBorower, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnSearcch)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSearcch, tfIdBorower});

    }// </editor-fold>//GEN-END:initComponents

    private void tfIdBorowerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdBorowerKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
//                tfNameAuthor.setText(tfNameAuthor.getText());
                autoComplete(tfIdBorower.getText());
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String kt = tfIdBorower.getText();
                        autoComplete(kt);
                    }
                });
        }
    }//GEN-LAST:event_tfIdBorowerKeyPressed

    private void tfIdBorowerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdBorowerKeyTyped
        if (tfIdBorower.getText().isEmpty()) {
            initListBorrower(arrAvailableBorrower);
        }
        autoComplete(tfIdBorower.getText());
    }//GEN-LAST:event_tfIdBorowerKeyTyped

    private void jListSearchBorrowerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSearchBorrowerMouseClicked
        int i = jListSearchBorrower.getSelectedIndex();
        if (i != -1){
            ((DefaultTableModel)tableDetailBorrowingInfor.getModel()).setRowCount(0);
            try {
                tfIdBorower.setText(arrBorrowerOfList.get(i).getborrower_ID());
                showInforBorrower(arrBorrowerOfList.get(i));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jListSearchBorrowerMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        mainFormOfLibrarian.setContentsPanel(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tableBorrowingInformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBorrowingInformationMouseClicked
        int i = tableBorrowingInformation.getSelectedRow();
        if (i != -1){
            ((DefaultTableModel)tableDetailBorrowingInfor.getModel()).setRowCount(0);
            try {
                binDataToTableDetailBorrowingInformation((String) tableBorrowingInformation.getValueAt(i, 0));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableBorrowingInformationMouseClicked

    private void btnSearcchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearcchActionPerformed
        try {
            // search Borrowerid
            searchBorrower();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowingInformationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearcchActionPerformed
    
    /** H??m n??y ????? t??m ki???m ng?????i m?????n theo t??n ho???c m?? ng?????i m?????n r???i hi???n th??? l??n list
     * @param key t??? kh??a m?? c?? th??? bao g???m trong t??n ho???c m?? ng?????i m?????n
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    private void searchBorrower() throws SQLException, ClassNotFoundException{
        String key = tfIdBorower.getText().toString().trim();
        ArrayList<Borrower> list_borrower = Borrower_Controller.getInstance().searchBorrower(key);
        if (list_borrower.isEmpty()){
            notify("Kh??ng c?? m?? ng?????i m?????n hay t??n n??o th???a m??n");
            return;
        }
        initListBorrower(list_borrower);
    }
    
    /**
     * H??m n??y th???c hi???n ch???c n??ng hi???n th??? th??ng tin ng?????i m?????n l??n m??n h??nh
     * @param borrower ng?????i m?????n hi???n th??? th??ng tin l??n m??n h??nh
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void showInforBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
        lbIdBorrowCard.setText("M?? th???: " + borrower.getborrower_Card().getborrowingcard_Borrower_ID());
        lbIdBorrower.setText("M?? ng?????i m?????n: " + borrower.getborrower_Deposit());
        lbNameBorrower.setText("T??n ng?????i m?????n: " + borrower.getuser_Name());
        binDataToTableBorrowingInformation(borrower);
    }
    
    /**
     * H??m n??y th???c hi???n ch???c n??ng hi???n th??? danh s??ch th??ng tin m?????n tr??? tr????ng ???ng c???a ng?????i m?????n l??n b???ng
     * @param borrower ng?????i m?????n m?? th??? th?? mu???n hi???n th??? th??ng tin l??n b???ng
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void binDataToTableBorrowingInformation(Borrower borrower) throws ClassNotFoundException, SQLException{
        ArrayList<BorrowingInfo> arr = BorrowingInfo_Controller.getInstance().getListBorrowingInformationByIdBorrower(borrower);
        DefaultTableModel model = (DefaultTableModel) tableBorrowingInformation.getModel();
        model.setRowCount(0);
        for (int i = 0;i<arr.size();i++){
            BorrowingInfo brIngor = arr.get(i);
            String status = null;
            if (brIngor.getborrowing_info_Status() == BorrowingInfo.BorrowStatus.BORROWED){
                status = "???? ????ng k??";
            } else if (brIngor.getborrowing_info_Status() == BorrowingInfo.BorrowStatus.LENDING){
                status = "??ang m?????n";
            } else {
                status = "???? tr???";
            }
            model.addRow(new Object[]{
                brIngor.getborrowing_info_ID(),
                brIngor.getborrowing_info_Borrowed_Date(),
                brIngor.getborrowing_info_Expect_Return_Date(),
                status,
                brIngor.getborrowing_info_Deposit()
            });
        }
    }
    
    /**
     * H??m n??y th???c hi???n ch???c n??ng hi???n th??? chi tiet cac cuon sach muon tra tuong ung voi ma thong tin muon tra
     * @param idBorrowerInformation m?? th??ng tin m?????n tr???
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void binDataToTableDetailBorrowingInformation(String idBorrowerInformation) throws ClassNotFoundException, SQLException{
        ArrayList<DetailLentBook> arr = LentBook_Controller.getInstance().getListDetailLentBookByIdBorrowInfor(idBorrowerInformation);
        DefaultTableModel model = (DefaultTableModel) tableDetailBorrowingInfor.getModel();
        model.setRowCount(0);
        for(DetailLentBook b:arr){
           String idBook = BookHelper.getIdBookByIdCoppyOfBook(b.getdetail_lent_book_ID());
           Book book = Book_Controller.getInstance().getBookByIdBook(idBook);
           String status = null;
           if (b.getdetail_lent_book_Status()==0){
               status = "????ng k??";
           } else if (b.getdetail_lent_book_Status()==1){
               status = "??ang m?????n";
           } else if (b.getdetail_lent_book_Status()==2){
               status = "???? tr???";
           }
           model.addRow(new Object[]{
               b.getdetail_lent_book_ID(),
               book.getbook_Title(),
               status,
               b.getdetail_lent_book_LentDate(),
               b.getdetail_lent_book_ReturnDate()
           });
        }
    }
    
    ArrayList<Borrower> arrBorrowerOfList;
    /**
     * H??m n??y th???c hi???n ch???c n??ng hi???n th??? danh s??ch ng?????i m?????n l??n list
     * @param ArrayList<Borrower> Danh s??ch ng?????i m?????n hi???n th??? l??n list
     */
    private void initListBorrower(ArrayList<Borrower> arr) {
        arrBorrowerOfList = arr;
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < arrBorrowerOfList.size(); i++) {
            listModel.addElement(arrBorrowerOfList.get(i).getborrower_ID() + ": " + arrBorrowerOfList.get(i).getuser_Name());
        }
        jListSearchBorrower.setModel(listModel);
    }

    /**
     * H??m n??y th???c hi???n ch???c n??ng t??m c??c ng?????i m?????n c?? t??n b???t ?????u b???ng m???t chu???i k?? t??? n??o ????
     * @param String ch???i k?? t??? b???t ?????u c???a t??c gi???
     */
    private void autoComplete(String kt) {
        String complete = "";
        int start = kt.length();
        int last = kt.length();
        int i;
        ArrayList<Borrower> arr = new ArrayList<>();
        for (i = 0; i < arrAvailableBorrower.size(); i++) {
            if (arrAvailableBorrower.get(i).getborrower_ID().startsWith(kt)) {
                arr.add(arrAvailableBorrower.get(i));
            }
        }
        initListBorrower(arr);
    }
    
    private void notify(String content){
        JOptionPane.showMessageDialog(null, content);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSearcch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jListSearchBorrower;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbIdBorrowCard;
    private javax.swing.JLabel lbIdBorrower;
    private javax.swing.JLabel lbNameBorrower;
    private javax.swing.JTable tableBorrowingInformation;
    private javax.swing.JTable tableDetailBorrowingInfor;
    private javax.swing.JTextField tfIdBorower;
    // End of variables declaration//GEN-END:variables
}
