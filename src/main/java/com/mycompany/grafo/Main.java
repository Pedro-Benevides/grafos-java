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
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Aresta a1 = new Aresta(v1, v2);
        Aresta a2 = new Aresta(v1, v3);
        Aresta a4 = new Aresta(v1, v4);
        Aresta a3 = new Aresta(v2, v3);

        // ADICIONAR VERTICES E ARESTAS ENTRE DOIS VERTICES
        g.addVertice(v1);
        g.addVertice(v2);
        g.addVertice(v3);
        g.addVertice(v4);

        g.addAresta(a1);
        g.addAresta(a2);
        g.addAresta(a3);
        g.addAresta(a4);

        // MOSTRAR VERTICES E ARESTAS NO GRAFO
        // g.showVertices();
        // g.showConections();

        // DELETAR VERTICE
        // System.out.println("deletando vertice:");
        // g.deleteVertice(1);
        // g.showVertices();
        // g.showConections();

        // DELETAR ARESTA
        // System.out.println("deletando aresta:");
        // g.deleteAresta(2, 1);
        // g.showVertices();
        // g.showConections();

        // GRAU
        // System.out.println(g.grau());
        // System.out.println(g.grauVertice(1));

        // CHECAR GRAFO CONEXO
        // g.checkConexo();

        // ADJACENCIAS DE UM VERTICE
        // g.adjacencias(1);

        // GRAU MINIMO, MEDIO, MAXIMO
        // g.grau();

        // MATRIZ ADJACENCIAS
        g.matrizAdjacencias();
    }
}
