package com.mycompany.grafo;

import java.util.ArrayList;

public class Grafo {

    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo(Vertice vertice) {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.vertices.add(vertice);
    }

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public void addVertice(Vertice v) {
        this.vertices.add(v);
    }

    public void addAresta(Aresta a) {
        this.arestas.add(a);
        a.getInicio().addAresta(a);
        a.getFinal().addAresta(a);
    }

    public void deleteVertice(int valor) {
        Vertice verticeDel = this.vertices.stream().filter(vertice -> vertice.getValor() == valor).findFirst().get();

        this.arestas.removeIf(aresta -> aresta.getInicio() == verticeDel || aresta.getFinal() == verticeDel);
        this.vertices.remove(verticeDel);
    }

    public void deleteAresta(int valorInicio, int valorFinal) {

        this.arestas.removeIf(aresta -> (aresta.getInicio().getValor() == valorInicio
                || aresta.getFinal().getValor() == valorInicio)
                &&
                (aresta.getInicio().getValor() == valorFinal
                        || aresta.getFinal().getValor() == valorFinal));
    }

    public void showVertices() {
        if (this.vertices.isEmpty()) {
            System.out.println("não existem vertices no grafo");
        }
        this.vertices.forEach((vertice) -> {
            System.out.print("|" + vertice.getValor());
        });
        System.out.println("\n");

    }

    public void showConections() {
        if (this.arestas.isEmpty()) {
            System.out.println("não existem arestas no grafo");
        }
        this.arestas.forEach((aresta) -> {
            System.out.println("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
        });
        System.out.println("\n");

    }

    public void testConection(Vertice v1, Vertice v2) {
        this.arestas.forEach((aresta) -> {
            boolean testInicio = (aresta.getInicio() == v1
                    || aresta.getInicio() == v2);

            boolean testFim = (aresta.getFinal() == v1
                    || aresta.getFinal() == v2);

            if (testInicio && testFim) {
                System.out.println("Aresta encontrada: ");
                System.out.println(
                        "[" + aresta.getInicio().getValor() + " -> "
                                + aresta.getFinal().getValor() + "]");
            } else {
                System.out.println("Aresta não encontrada: ");
            }
        });
    }

    public void grau() {
        Integer grauMax = this.vertices.get(0).getValor();
        Integer grauMin = this.vertices.get(0).getValor();
        Double grauMed = 0.0;

        for (Vertice vertice : this.vertices) {
            Integer grauV = this.grauVertice(vertice);
            grauMed += grauV;

            if (grauV > grauMax) {
                grauMax = grauV;
            }

            if (grauV < grauMin) {
                grauMin = grauV;
            }

        }
        grauMed /= this.vertices.size();

        System.out.println("GRAU MÍNIMO: " + grauMin);
        System.out.println("GRAU MÉDIO: " + grauMed);
        System.out.println("GRAU MÁXIMO: " + grauMax);
    }

    private Integer grauVertice(Vertice vertice) {

        return vertice.getArestas().size();
    }

    public Integer grauVertice(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        return vertice.getArestas().size();
    }

    public boolean checkConexo() {
        for (Vertice vertice : this.vertices) {
            if (vertice.getArestas().size() < 1) {
                System.out.println("Grafo não Conexo, vertice de valor " + vertice.getValor() + " sem conexão");
                return false;
            }
        }
        System.out.println("Grafo Conexo, todos os vertices tem ao menos uma conexão");
        return true;
    }

    public void adjacencias(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        vertice.getArestas().forEach((aresta) -> {
            System.out.println("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
        });
    }
}
