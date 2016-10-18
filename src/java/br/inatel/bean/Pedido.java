
package br.inatel.bean;
public class Pedido {
    private String idPedidos;
    private String hrPedido ;
    private String pedidoConcluido ;
    private String Cliente_idCliente ;
    private String Atendente_idAtendente ;
    private String numMesa ;

    public String getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(String idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getHrPedido() {
        return hrPedido;
    }

    public void setHrPedido(String hrPedido) {
        this.hrPedido = hrPedido;
    }

    public String getPedidoConcluido() {
        return pedidoConcluido;
    }

    public void setPedidoConcluido(String pedidoConcluido) {
        this.pedidoConcluido = pedidoConcluido;
    }

    public String getCliente_idCliente() {
        return Cliente_idCliente;
    }

    public void setCliente_idCliente(String Cliente_idCliente) {
        this.Cliente_idCliente = Cliente_idCliente;
    }

    public String getAtendente_idAtendente() {
        return Atendente_idAtendente;
    }

    public void setAtendente_idAtendente(String Atendente_idAtendente) {
        this.Atendente_idAtendente = Atendente_idAtendente;
    }

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }

    
}
