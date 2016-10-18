/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.DAO;

import br.inatel.bean.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
public class ClientesDAO {
    	private String TABLE_NAME = "cliente";
	private String COL[] = {"idCliente","nome","hrChegada","hrSaida","formaPagmt"};
        
        private AcessoDB acesso;
        private Cliente cliente;
        
        private List<Cliente> clientes = new ArrayList<Cliente>();

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
     
    
        
  
    public enum cols{
          idAtendente,nome,idade,dataEntrada,dataSaid,salario
      }
        
    public ClientesDAO() {
        cliente = new Cliente();
        acesso = new AcessoDB();
        acesso.conectar();
    }
    
    public List<Cliente> getClientes(){
           
                try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM "+TABLE_NAME);
                    this.clientes.clear();
                    int count = 0;
                    while(rs.next()){
                       Cliente at = new Cliente();
                       at.setIdCliente(rs.getString(COL[0]));
                       at.setNome(rs.getString(COL[1]));
                       at.setHrChegada(rs.getString(COL[2]));
                       at.setHrSaida(rs.getString(COL[3]));
                       at.setFormPagmt(rs.getString(COL[4]));

                       this.clientes.add(at);
                        count++;
                    }
              
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Lista: " + ex.getMessage());
                }
               
                
                return this.clientes;
    }
     public void SalvarCliente(){
         	try {
			
			String  sqlcmd = "INSERT INTO "+TABLE_NAME +" ( "+COL[1]+ " , " + COL[2] +" , "+ COL[3]+" , "+ COL[4]+  ") values ('" + this.getCliente().getNome()+"','"+this.getCliente().getHrChegada() + "','" + this.getCliente().getHrSaida()+ "','" + this.getCliente().getFormPagmt()+"')";
                        System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        
                         stm.execute(sqlcmd);
                         stm.close();
			 showMessage(TABLE_NAME + " salvo com sucesso!");
                         
		} catch (SQLException e) {
                          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "erro ao salvar "+TABLE_NAME + e.getMessage());
                    RequestContext.getCurrentInstance().showMessageInDialog(message);   
                    
                }

            
     }
     public String getParameter(String idToFind){
            FacesContext fc =  FacesContext.getCurrentInstance();
            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get(idToFind);

    }
 
     public void showMessage(String text){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(text)); 
     }
     public void RemoverCliente(){

         try {//"DELETE FROM `mydb`.`atendente` WHERE `atendente`.`idAtendente` = 12"?
			String  sqlcmd =  "DELETE FROM "+TABLE_NAME +" WHERE "+COL[0]+ "= "+getParameter(COL[0])+" ;";
                        //System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        stm.execute(sqlcmd);
                     showMessage(TABLE_NAME+" deletado com sucesso!");
		} catch (SQLException e) {
                     System.out.println("ERRO ao deletar!");
                    System.out.println(e.getMessage());
                    
                }
     }
     public List<String> fomasDePagamento(){
         List<String> tipos = new ArrayList<String>();
           try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM formasDePagamento");
                    while(rs.next()){
                        tipos.add(rs.getString("tipo"));
                    }
              
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                }
               
         return tipos;
     }
    
        
	
}
