package com.mycompany.grafo;

/**
 *
 * @author Pedro Victor Santana Benevides
 */
public class Main {

    public static void main(String[] args) {
        Grafo g = new Grafo();
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Aresta a = new Aresta(v1, v2);

        g.addVertice(v1);
        g.addVertice(v2);
        g.addAresta(a);

        g.showVertices();
        g.showConections();

        // System.out.println("deletando vertice:");
        // g.deleteVertice(1);
        // g.showVertices();
        // g.showConections();

        System.out.println("deletando aresta:");
        g.deleteAresta(2,1);
        g.showVertices();
        g.showConections();


    }
}
