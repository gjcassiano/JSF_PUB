/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.DAO;

import br.inatel.bean.Atendente;
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
public class AtendentesDAO {
    	private String TABLE_NAME = "atendente";
	private String COL[] = {"idAtendente","nome","idade","dataEntrada","dataSaida","salario"};
        
        private AcessoDB acesso;
        private Atendente atendente;
        
        private List<Atendente> atendentes = new ArrayList<Atendente>();
     
    
    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }
    public Atendente getAtendente() {
        return atendente;
    }
    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
        public enum cols{
            idAtendente,nome,idade,dataEntrada,dataSaid,salario
        }
        
    public AtendentesDAO() {
        atendente = new Atendente();
        acesso = new AcessoDB();
        acesso.conectar();
    }
    
    public List<Atendente> getAtendentes(){
           
                try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM atendente");
                    this.atendentes.clear();
                    int count = 0;
                    while(rs.next()){
                       Atendente at = new Atendente();
                       at.setIdAtendente(rs.getString(COL[0]));
                       at.setNome(rs.getString(COL[1]));
                       at.setIdade(rs.getString(COL[2]));
                       //at.setDataEntrada(rs.getString(COL[3]));
                       //at.setDataSaid(rs.getString(COL[4]));
                       at.setSalario(rs.getString(COL[5]));
                       this.atendentes.add(at);
                      //  System.out.println(at.getNome());
                        count++;
                    }
                   //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", count + " atendentes cadastrados");
                   //RequestContext.getCurrentInstance().showMessageInDialog(message);   
                    // FacesContext context = FacesContext.getCurrentInstance();
                     //     context.addMessage(null, new FacesMessage("Imformação",  "Atendente deletado com sucesso!") );
                    
      
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Lista: " + ex.getMessage());
                }
               
                
                return this.atendentes;
    }
   
     public void SalvarAtendente(){
         	try {
			
			String  sqlcmd = "INSERT INTO "+TABLE_NAME +" ( "+COL[0]+" , "+COL[1]+ " , " + COL[2] +" , "+ COL[3]+" , "+ COL[4]+" , "+ COL[5]+ ") values ('" +this.atendente.getIdAtendente() + "','" + this.atendente.getNome()+"','"+this.atendente.getIdade()+ "','" + "0000-00-00"+ "','" + "0000-00-00"+ "','" + this.atendente.getSalario()+"')";
                        System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        
                         stm.execute(sqlcmd);
                         stm.close();
			 showMessage("Atendente salvo com sucesso!");
			
                         
		} catch (SQLException e) {
                          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "erro ao salvar atendente" + e.getMessage());
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
     public void RemoverAtendente(){

         try {//"DELETE FROM `mydb`.`atendente` WHERE `atendente`.`idAtendente` = 12"?
                   
             
			String  sqlcmd =  "DELETE FROM "+TABLE_NAME +" WHERE "+COL[0]+ "= "+getParameter("idCliente")+" ;";
                        //System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        stm.execute(sqlcmd);
                     showMessage("Atendente deletado com sucesso!");
		} catch (SQLException e) {
                     System.out.println("ERRO ao deletar!");
                    System.out.println(e.getMessage());
                    
                }
     }
    
        
	
}
