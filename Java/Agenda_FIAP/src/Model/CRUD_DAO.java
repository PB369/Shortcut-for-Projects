/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Agenda_FIAP;
import static View.Agenda_FIAP.cod1_txt;
import static View.Agenda_FIAP.cod2_txt;
import static View.Agenda_FIAP.end1_txt;
import static View.Agenda_FIAP.end_txt;
import static View.Agenda_FIAP.nome1_txt;
import static View.Agenda_FIAP.nome_txt;
import static View.Agenda_FIAP.refresh;
import static View.Agenda_FIAP.tel1_txt;
import static View.Agenda_FIAP.tel_txt;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio
 */
public class CRUD_DAO {
     // variáveis inicio
 static int cod;
 static String nom;
 static String em;
 static long tel;
       
  public static String url = "jdbc:mysql://localhost/aprender"; // enderço do BD
  public static String username = "root";        //nome de um usuário de seu BD
  public static String password = "";  // senha do BD

    
    
    // variáveis fim 
    public static void cadastrar(){
         // inicio do código
      
      nom = nome_txt.getText(); // recebendo o nome
em = end_txt.getText(); // recebendo o email
 tel = Long.valueOf(tel_txt.getText());// recebendo o telefone
   Controller.Conecta_DB.carregaDriver();
       
      try { 
                       
               
            Connection con = null;
            
            
    try {
    con = (Connection) DriverManager.getConnection(url, username, password);
    } catch (SQLException ex) {

    Logger.getLogger(Agenda_FIAP.class.getName()).log(Level.SEVERE, null, ex);

            
                   }

            // Recebendo os dados a serem inseridos na tabela
            String sql = "INSERT INTO cliente(cli_nome,cli_end,cli_tel) values('"+nom+"','"+em+"','"+tel+"')";
     
            try { // Tratamento de Erros para inserção

                // Criando varialvel que executara a inserção
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute(); // Executando a inserção

  JOptionPane.showMessageDialog(null,"\nInserção realizada com sucesso!!!\n","",-1);
                nome_txt.setText("");
                end_txt.setText("");
                tel_txt.setText("");
                refresh();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"\nErro na inserção!","ERRO!",0);
            }

        }catch(NumberFormatException erro){
            // Tratamento de erro caso o usuario não digite o telefone corretamente
            JOptionPane.showMessageDialog(null,"Digite os dados corretamente","ERRO",0);
            tel_txt.setText("");
        }

       
              

      
      // fim do código 
    }
    
    
    public static void consultar(){
       // Inicio código 
       try{     //Iniciando o possivel tratamento de erros
            //Declarando a variavel código

            int codigo = Integer.valueOf(cod1_txt.getText());

            

            try {// Tratamento de erro para a conexao
                // Declarando  a variavel de conexão con
                // e estabelendo a conexão

                Connection con = null;

                try {

                    con = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda_FIAP.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Declarando uma string com o comando mySQL para consulta
                String sql = "SELECT cli_nome,cli_end, cli_tel  FROM cliente where cli_cod = "+codigo;
                // Criando variavel que executara o comando da string sql

                Statement stm = (Statement) con.createStatement();
             
                    try//Tratamento de erro da consulta
                { //Criando variavel que exibira os resultados
                    //Executando o comando da string sql na variavel rs
                    ResultSet rs = stm.executeQuery(sql);

                    int i=0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        String nome = rs.getString("cli_nome");
                        String end = rs.getString("cli_end");
                        String telefone = rs.getString("cli_tel");

                        i++;

                        //JOptionPane.showMessageDialog(null,"Nome: " + nome + "\nEndereço: " +end + "\nTelefone: " +telefone, "Resultado",-1);
                       
                        View.Agenda_FIAP.nome1_txt.setText(String.valueOf(nome));
                        View.Agenda_FIAP.end1_txt.setText(String.valueOf(end));
                        View.Agenda_FIAP.tel1_txt.setText(String.valueOf(telefone));

                    }

                    if(i==0){ // Verificando se ha dados cadastrados atraves da variavel i

                        JOptionPane.showMessageDialog(null,"Dado não cadastrado","Resultado",-1);

                    }

                } catch (Exception ex) { // Consulta mal sucedida
                    JOptionPane.showMessageDialog(null,"\nErro ao consultar!","ERRO",0);
                }

            } catch (SQLException ex) {
                // Conexão com servidor mal sucedida
                JOptionPane.showMessageDialog(null,"Erro ao conectar com o servidor","ERRO!",0);
            }

        }catch(NumberFormatException erro){
            // Código fora do formato
            JOptionPane.showMessageDialog(null,"Digite o código corretamante","ERRO",0);
            cod1_txt.setText("");
        }
 
       
       
       
       
       // Término código  
    }
    
    public static void atualizar(){
        // Início do código
             
      nom = nome1_txt.getText(); // recebendo o nome
      em = end1_txt.getText(); // recebendo o email         
      tel = Long.valueOf(tel1_txt.getText());// recebendo o telefone

       
       
      try {     
            Connection con = null;
      try {
            con = (Connection) DriverManager.getConnection(url, username, password);
      }catch (SQLException ex) {
            Logger.getLogger(Agenda_FIAP.class.getName()).log(Level.SEVERE, null, ex);
      }
            String sql = "UPDATE cliente SET cli_nome='"+nom+"',cli_end='"+em+"',cli_tel='"+tel+"' WHERE cli_cod="+cod1_txt.getText();
            
     
            try { 
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute(); // Executando a inserção

                JOptionPane.showMessageDialog(null,"\nAtualização realizada com sucesso!!!\n","",-1);
               cod1_txt.setText("");
                nome1_txt.setText("");
               end1_txt.setText("");
                tel1_txt.setText("");
                refresh();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"\nErro na inserção!","ERRO!",0);
            }

        }catch(NumberFormatException erro){
            // Tratamento de erro caso o usuario não digite o telefone corretamente
            JOptionPane.showMessageDialog(null,"Digite os dados corretamente","ERRO",0);
            tel1_txt.setText("");
        }    

        
        
        // Término do código
        
        
    }
    
    public static void limpar(){
          cod1_txt.setText("");
                nome1_txt.setText("");
               end1_txt.setText("");
                tel1_txt.setText("");
    }
    
    public static void excluir(){
        // Inicio código
        
        
        int codigo = Integer.valueOf(cod2_txt.getText()); // Recebendo o código

                

        try {// Tratamento de erro para a conexao
            // Declarando  a variavel de conexão con
            // e estabelendo a conexão
            Connection con = null;

                try {
                    con = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda_FIAP.class.getName()).log(Level.SEVERE, null, ex);
                }
           

            // Criando String com comando SQL para exclusão
            String sql = "DELETE FROM cliente WHERE cli_cod = "+codigo;

            try // Tratamento de erros para exclusão
            {// Criando Variavel para executar a ação
                PreparedStatement excluir = (PreparedStatement) con.prepareStatement(sql);
                excluir.execute();// Executando a exclusão

               // JOptionPane.showMessageDialog(null,"\nExclusão realizada com sucesso!!!\n","",-1);
                //cod2_txt.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"\nErro na exclusão!","ERRO!",0);
            }

        } catch(NumberFormatException erro){ // Codigo digitado com caracteres não numericos
            JOptionPane.showMessageDialog(null,"Digite o código corretamante","ERRO",0);
            cod2_txt.setText("");

        }
        
        //Término código
    }
    
    
}
