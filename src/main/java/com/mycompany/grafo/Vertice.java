package com.mycompany.grafo;

import java.util.ArrayList;

public class Vertice {

    private int valor;
    private boolean visitado;
    private ArrayList<Aresta> arestas;

    public Vertice(int valor) {
        this.valor = valor;
        this.arestas = new ArrayList<Aresta>();
        this.visitado = false;
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

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

}
