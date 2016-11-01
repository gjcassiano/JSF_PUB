/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DONTKNOW
 */
@ManagedBean
public class Navigation {
    
    public String goPedidos() {
        return "pedidos";
    }
    public String goPaginaPrincipal() {
        return "paginaprincipal";
    }
      public String goAtendentes() {
        return "atendentes";
    }  public String goClientes() {
        return "clientes";
    }
    
}
