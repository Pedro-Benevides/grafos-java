package com.mycompany.grafo;

/**
 *
 * @author Pedro Victor Santana Benevides
 */
public class Aresta {

    private Vertice inicio;
    private Vertice fim;

    public Aresta(Vertice vIni, Vertice vFim) {
        this.inicio = vIni;
        this.fim = vFim;

        vIni.setAresta(this);
        vFim.setAresta(this);
    }

    public Vertice getInicio() {
        return this.inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFinal() {
        return this.fim;
    }

    public void setFinal(Vertice fim) {
        this.fim = fim;
    }

}
