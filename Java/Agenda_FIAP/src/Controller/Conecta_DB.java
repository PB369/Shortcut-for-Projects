
package Controller;


public class Conecta_DB {
    
    public static  void carregaDriver(){
       try { // Conecttando o driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver e Bancos de Dados carregados com sucesso!");
       } catch (Exception ex) { // Erro driver não encontrado
            System.out.println("Driver nao pode ser carregado!\n ou DB Inexistente");
        }

    }
    
}
