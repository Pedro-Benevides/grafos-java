package com.mycompany.grafo;

public class Vertice {

    private int valor;
    private Aresta aresta;

    public Vertice(int valor) {
        this.valor = valor;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    public Aresta getAresta() {
        return this.aresta;
    }

    public void setAresta(Aresta a) {
        this.aresta = a;
    }

}
