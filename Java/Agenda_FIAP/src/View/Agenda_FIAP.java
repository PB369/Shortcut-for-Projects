/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static Model.CRUD_DAO.password;
import static Model.CRUD_DAO.url;
import static Model.CRUD_DAO.username;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Fabio
 */
public class Agenda_FIAP extends javax.swing.JFrame {

    public Agenda_FIAP() {
        initComponents();
        refresh();
    }

   
     //-- Inicio Jtable 
       public static DefaultTableModel  cliente(ResultSet rs) {
        try {
           ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();
       // AS LINHAS ABAIXO SÃO REFERENTES AOS CAMPOS DA TABELA CLIENTE
            columnNames.addElement("Código");
            columnNames.addElement("Nome");
            columnNames.addElement("Endereço");
            columnNames.addElement("Telefone");
         
            Vector rows = new Vector();
            while (rs.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                }
                rows.addElement(newRow);
            }
           return new DefaultTableModel(rows, columnNames);
       } catch (Exception e) {

           return null;
        }
        }

    
    // ---Fim Jtable
    
    //-----------------------------------------------
    
    
    //--- Inicio Atualiza
     public static void refresh(){
    Controller.Conecta_DB.carregaDriver();
try{
   Connection conn;
          conn = (Connection) DriverManager.getConnection(url, username, password);
    
       
            String sql = "SELECT * FROM cliente;";
PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
ResultSet rs = pst.executeQuery();
dados.setModel(cliente(rs));
}
catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}    
    } 
       

    //--- Fim Atualiza
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dados = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cod1_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nome1_txt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        end1_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tel1_txt = new javax.swing.JTextField();
        consultar_BTN = new javax.swing.JButton();
        alterar_BTN = new javax.swing.JButton();
        LIMPAR_BTN = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cod2_txt = new javax.swing.JTextField();
        EXCLUIR_BTN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        end_txt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tel_txt = new javax.swing.JTextField();
        FECHAR_BTN = new javax.swing.JButton();
        SALVAR_BTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(null);

        dados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dados);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 400, 90);

        jLabel5.setText("CÓDIGO : ");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 100, 80, 30);
        jPanel2.add(cod1_txt);
        cod1_txt.setBounds(80, 100, 90, 30);

        jLabel6.setText("NOME : ");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 150, 70, 20);
        jPanel2.add(nome1_txt);
        nome1_txt.setBounds(80, 140, 190, 30);

        jLabel7.setText("ENDEREÇO : ");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 190, 90, 16);
        jPanel2.add(end1_txt);
        end1_txt.setBounds(80, 180, 190, 30);

        jLabel8.setText("TELEFONE  : ");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(10, 220, 90, 20);
        jPanel2.add(tel1_txt);
        tel1_txt.setBounds(80, 220, 190, 30);

        consultar_BTN.setText("CONSULTAR");
        consultar_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar_BTNActionPerformed(evt);
            }
        });
        jPanel2.add(consultar_BTN);
        consultar_BTN.setBounds(280, 100, 110, 30);

        alterar_BTN.setText("ALTERAR");
        alterar_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterar_BTNActionPerformed(evt);
            }
        });
        jPanel2.add(alterar_BTN);
        alterar_BTN.setBounds(280, 140, 110, 30);

        LIMPAR_BTN.setText("LIMPAR");
        LIMPAR_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPAR_BTNActionPerformed(evt);
            }
        });
        jPanel2.add(LIMPAR_BTN);
        LIMPAR_BTN.setBounds(280, 180, 110, 30);

        jTabbedPane1.addTab("CONSULTAR/ ALTERAR", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("INFORME O CÓDIGO A SER EXCLUIDO");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(50, 20, 320, 70);

        cod2_txt.setToolTipText("INSIRA O CÓDIGO A SER EXCLUIDO !");
        jPanel3.add(cod2_txt);
        cod2_txt.setBounds(140, 100, 120, 22);

        EXCLUIR_BTN.setText("EXCLUIR ");
        EXCLUIR_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXCLUIR_BTNActionPerformed(evt);
            }
        });
        jPanel3.add(EXCLUIR_BTN);
        EXCLUIR_BTN.setBounds(150, 160, 100, 30);

        jTabbedPane1.addTab("EXCLUIR", jPanel3);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("CADASTRO DE CLIENTE ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(120, 10, 210, 40);

        jLabel2.setText("NOME  : ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 70, 70, 30);
        jPanel1.add(nome_txt);
        nome_txt.setBounds(110, 70, 230, 30);

        jLabel3.setText("ENDEREÇO : ");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 120, 120, 30);
        jPanel1.add(end_txt);
        end_txt.setBounds(110, 120, 230, 30);

        jLabel4.setText("TELEFONE : ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(50, 170, 80, 20);
        jPanel1.add(tel_txt);
        tel_txt.setBounds(110, 160, 230, 30);

        FECHAR_BTN.setText("FECHAR");
        FECHAR_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FECHAR_BTNActionPerformed(evt);
            }
        });
        jPanel1.add(FECHAR_BTN);
        FECHAR_BTN.setBounds(300, 230, 80, 30);

        SALVAR_BTN.setText("SALVAR");
        SALVAR_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALVAR_BTNActionPerformed(evt);
            }
        });
        jPanel1.add(SALVAR_BTN);
        SALVAR_BTN.setBounds(50, 220, 100, 30);

        jTabbedPane1.addTab("CADASTRAR", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FECHAR_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FECHAR_BTNActionPerformed
        System.exit(0);
    }//GEN-LAST:event_FECHAR_BTNActionPerformed

    private void SALVAR_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALVAR_BTNActionPerformed
        Model.CRUD_DAO.cadastrar();
    }//GEN-LAST:event_SALVAR_BTNActionPerformed

    private void consultar_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar_BTNActionPerformed
       Model.CRUD_DAO.consultar();
       
    }//GEN-LAST:event_consultar_BTNActionPerformed

    private void alterar_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterar_BTNActionPerformed
       Model.CRUD_DAO.atualizar();
    }//GEN-LAST:event_alterar_BTNActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
    }//GEN-LAST:event_formWindowOpened

    private void LIMPAR_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPAR_BTNActionPerformed
       Model.CRUD_DAO.limpar();
    }//GEN-LAST:event_LIMPAR_BTNActionPerformed

    private void EXCLUIR_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXCLUIR_BTNActionPerformed
         String x = JOptionPane.showInputDialog(null,"Deseja realmente excluir o registro?\n1-Sim\n2-Não");
         int op = Integer.parseInt(x);
         if(op == 1 ){
           Model.CRUD_DAO.excluir();
          JOptionPane.showMessageDialog(null,"Registro excluido com sucesso !"); 
          cod2_txt.setText("");
          refresh();
         }else{
             JOptionPane.showMessageDialog(null,"Registro não excluido");
         }
    }//GEN-LAST:event_EXCLUIR_BTNActionPerformed

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
            java.util.logging.Logger.getLogger(Agenda_FIAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda_FIAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda_FIAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda_FIAP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda_FIAP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EXCLUIR_BTN;
    private javax.swing.JButton FECHAR_BTN;
    private javax.swing.JButton LIMPAR_BTN;
    private javax.swing.JButton SALVAR_BTN;
    private javax.swing.JButton alterar_BTN;
    public static javax.swing.JTextField cod1_txt;
    public static javax.swing.JTextField cod2_txt;
    private javax.swing.JButton consultar_BTN;
    public static javax.swing.JTable dados;
    public static javax.swing.JTextField end1_txt;
    public static javax.swing.JTextField end_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextField nome1_txt;
    public static javax.swing.JTextField nome_txt;
    public static javax.swing.JTextField tel1_txt;
    public static javax.swing.JTextField tel_txt;
    // End of variables declaration//GEN-END:variables
}
