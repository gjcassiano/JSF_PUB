/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Cliente {
    private String idCliente;
    private String nome;
    private String hrChegada;
    private String hrSaida;
    private String formPagmt;

    /**
     * @return the idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the hrChegada
     */
    public String getHrChegada() {
        return hrChegada;
    }

    /**
     * @param hrChegada the hrChegada to set
     */
    public void setHrChegada(String hrChegada) {
        this.hrChegada = hrChegada;
    }

    /**
     * @return the hrSaida
     */
    public String getHrSaida() {
        return hrSaida;
    }

    /**
     * @param hrSaida the hrSaida to set
     */
    public void setHrSaida(String hrSaida) {
        this.hrSaida = hrSaida;
    }

    /**
     * @return the formPagmt
     */
    public String getFormPagmt() {
        return formPagmt;
    }

    /**
     * @param formPagmt the formPagmt to set
     */
    public void setFormPagmt(String formPagmt) {
        this.formPagmt = formPagmt;
    }

   
    
}
