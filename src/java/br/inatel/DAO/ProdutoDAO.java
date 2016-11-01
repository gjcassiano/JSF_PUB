
package br.inatel.DAO;

import br.inatel.bean.Pedido;
import br.inatel.bean.Produto;
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
public class ProdutoDAO {
	

    	private String TABLE_NAME = "produto";
	private String COL[] = {"idProduto","nome","marca","quantidade","tipo","codBarra","valorUnitario"};
      
        private AcessoDB acesso;
        private Produto produto;
        
        private List<Produto> produtos = new ArrayList<Produto>();

 
        
  
  
        
    public ProdutoDAO() {
        produto = new Produto();
        acesso = new AcessoDB();
        acesso.conectar();
    }
    
    public List<Produto> getProdutos(){
       
                try {
                    Statement stmt;
                    stmt = AcessoDB.conexao.createStatement();
                    ResultSet rs = stmt.executeQuery("CALL getProdutos();");
                    this.produtos.clear();
                    int count = 0; 
                    while(rs.next()){
                       Produto at = new Produto();
                     
                       at.setIdProduto(rs.getString("idProduto"));
                       at.setNome(rs.getString("nome"));
                       at.setMarca(rs.getString("marca"));
                       at.setQuantidade(rs.getString("quantidade"));
                       at.setTipo(rs.getString("tipo"));
                       at.setCodBarra(rs.getString("codBarra"));
                       at.setValorUnitario(rs.getString("valorUnitario"));
                       this.produtos.add(at);
                        count++;
                    }
              
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Lista: " + ex.getMessage());
                }
               
                
                return this.produtos;
    }
   
     public void SalvarProduto(){
         	try {
                     
			String  sqlcmd = "INSERT INTO "+TABLE_NAME +" ( "+COL[1]+ " , " + COL[2] +" , "+ COL[3]+" , "+ COL[4]+" , " + COL[5] + " , "+ COL[6] +  ") values ('" +
                               this.getProduto().getNome()+"','"+this.getProduto().getMarca()+ "','" + this.getProduto().getQuantidade()+ "','" + this.getProduto().getTipo()+ "','" +this.getProduto().getValorUnitario()+"')";
                        Produto p = this.getProduto();
                        
                        System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        
                         stm.execute(sqlcmd);
                         stm.close();
			 showMessage("Produto salvo com sucesso!");
                         
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
     public void RemoverProduto(){
         try {
			String  sqlcmd =  "DELETE FROM "+TABLE_NAME +" WHERE "+COL[0]+ "= "+getParameter(COL[0])+" ;";
                        //System.out.println(sqlcmd);
                        Statement stm = AcessoDB.conexao.createStatement();
                        stm.execute(sqlcmd);
                     showMessage("Produto deletado com sucesso!");
		} catch (SQLException e) {
                     System.out.println("ERRO ao deletar!");
                    System.out.println(e.getMessage());
                }
     }
         
  
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
	
}


