
package br.inatel.DAO;

import br.inatel.bean.Pedido;
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
public class PedidoDAO {

    	private String TABLE_NAME = "pedido";
	private String COL[] = {"idPedidos","hrPedido","pedidoConcluido","Cliente_idCliente","Atendente_idAtendente","numMesa"};
      
        private AcessoDB acesso;
        private Pedido pedido;
        
        private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

        
  
  
        
    public PedidoDAO() {
        pedido = new Pedido();
        acesso = new AcessoDB();
        acesso.conectar();
    }
    
    public List<Pedido> getPedidos(){
           
                try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("CALL getPedidos();");
                    this.pedidos.clear();
                    int count = 0; 
                    while(rs.next()){
                       Pedido at = new Pedido();
                       at.setIdPedidos(rs.getString(COL[0]));
                       at.setHrPedido(rs.getString(COL[1]));
                       if(rs.getString(COL[2]).equals("0")){
                           at.setPedidoConcluido("não");
                       }else{
                           at.setPedidoConcluido("sim");
                       }
                       
                       at.setCliente_idCliente(rs.getString("cnome"));
                       at.setAtendente_idAtendente(rs.getString("anome"));
                       at.setNumMesa(rs.getString(COL[5]));

                       this.pedidos.add(at);
                        count++;
                    }
              
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Lista: " + ex.getMessage());
                }
               
                
                return this.pedidos;
    }
   
     public void SalvarPedido(){
         	try {
			
//			String  sqlcmd = "INSERT INTO "+TABLE_NAME +" ( "+COL[1]+ " , " + COL[2] +" , "+ COL[3]+" , "+ COL[4]+" , " + COL[5] +  ") values ('" +
//                                this.getPedido().getHrPedido()+"','"+this.getPedido().getPedidoConcluido()+ "','" + this.getPedido().getCliente_idCliente()+ "','" + this.getPedido().getAtendente_idAtendente()+ "','" +this.getPedido().getNumMesa()+"')";
                        Pedido p = this.getPedido();
                        
                        String sqlcmd = "CALL fazerPedido('"+ this.getPedido().getCliente_idCliente()+"','"+this.getPedido().getAtendente_idAtendente()+"'," +this.getPedido().getNumMesa() +");";
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
     public void RemoverPedido(){
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
          public void ConcluirPedido(){

         try {
			String  sqlcmd =  "CALL concluirPedido("+getParameter(COL[0])+") ;";
                        System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        stm.execute(sqlcmd);
                     showMessage(TABLE_NAME+" concluido com sucesso!");
		} catch (SQLException e) {
                     System.out.println("ERRO ao deletar!");
                    System.out.println(e.getMessage());
                }
     }
     
     public List<String>  getClientesEmAberto(){
         List<String> clientes = new ArrayList<String>();
           try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("CALL getClientesEmAberto()");
                    while(rs.next()){
                        clientes.add(rs.getString("nome"));
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                }
         return clientes;
     }
     public List<String> getAtendentesTrabalhando(){
              List<String> atendente = new ArrayList<String>();
           try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("CALL getAtendentesTrabalhando()");
                    while(rs.next()){
                        atendente.add(rs.getString("nome"));
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                }
         return atendente;
     }
    
        
	
}


