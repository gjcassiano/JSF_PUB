/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Atendente {
    private String idAtendente;
    private String nome;
    private String idade;
    private String dataEntrada;
    private String dataSaid;
    private String salario;

    /**
     * @return the idAtendente
     */
    public String getIdAtendente() {
        return idAtendente;
    }

    /**
     * @param idAtendente the idAtendente to set
     */
    public void setIdAtendente(String idAtendente) {
        this.idAtendente = idAtendente;
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
     * @return the idade
     */
    public String getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(String idade) {
        this.idade = idade;
    }

    /**
     * @return the dataEntrada
     */
    public String getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataSaid
     */
    public String getDataSaid() {
        return dataSaid;
    }

    /**
     * @param dataSaid the dataSaid to set
     */
    public void setDataSaid(String dataSaid) {
        this.dataSaid = dataSaid;
    }

    /**
     * @return the salario
     */
    public String getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(String salario) {
        this.salario = salario;
    }
    
}
