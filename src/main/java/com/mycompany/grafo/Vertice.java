package com.mycompany.grafo;

import java.util.ArrayList;

public class Vertice {

    private int valor;
    private ArrayList<Aresta> arestas;

    public Vertice(int valor) {
        this.valor = valor;
        this.arestas = new ArrayList<Aresta>();
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

    public ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    public void addAresta(Aresta a) {
        this.arestas.add(a);
    }

}
