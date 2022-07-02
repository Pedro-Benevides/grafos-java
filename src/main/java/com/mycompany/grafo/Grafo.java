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

    public boolean checkConexo(boolean showPrint) {
        for (Vertice vertice : this.vertices) {
            if (vertice.getArestas().size() < 1) {
                if (showPrint) {
                    System.out.println("Grafo não Conexo, vertice de valor " + vertice.getValor() + " sem conexão");
                }
                return false;
            }
        }
        if (showPrint) {
            System.out.println("Grafo Conexo, todos os vertices tem ao menos uma conexão");
        }
        return true;
    }

    public void adjacencias(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        vertice.getArestas().forEach((aresta) -> {
            if (vertice == aresta.getInicio()) {
                System.out.print("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
            } else {
                System.out.print("[" + aresta.getFinal().getValor() + " -> " + aresta.getInicio().getValor() + "]");

            }
        });
    }

    public void matrizAdjacencias() {
        // criandoMatriz
        int countV = this.vertices.size();
        int[][] matrizAdjacencia = new int[countV][countV];

        this.arestas.forEach((aresta) -> {
            matrizAdjacencia[aresta.getInicio().getValor() - 1][aresta.getFinal().getValor() - 1] = 1;
            matrizAdjacencia[aresta.getFinal().getValor() - 1][aresta.getInicio().getValor() - 1] = 1;
        });

        // printMatriz
        System.out.print('\t');
        for (Vertice verticeCol : this.vertices) {
            System.out.print("|" + verticeCol.getValor() + "|");

        }
        System.out.println("\n");
        for (Vertice verticeCol : this.vertices) {
            System.out.print("|" + verticeCol.getValor() + "|" + '\t');
            for (Vertice verticeRow : this.vertices) {
                System.out.print("[" + matrizAdjacencia[verticeCol.getValor() - 1][verticeRow.getValor() - 1] + "]");
            }
            System.out.println();
        }
    }

    public void caminhoEuler() {
        int countPares = 0;
        int countImpares = 0;
        boolean conexo = this.checkConexo(false);

        for (Vertice vertice : this.vertices) {
            int countA = vertice.getArestas().size();

            // System.out.println(vertice.getValor() + ": " + countA);

            if (countA % 2 == 0) {
                countPares += 1;
            } else {
                countImpares += 1;
            }
        }

        boolean checkAllPares = (countImpares == 0 && countPares == this.vertices.size());
        boolean checkImpares = (countImpares == 2 && countPares == this.vertices.size() - 2);

        if ((checkAllPares && conexo) || (checkImpares && conexo)) {
            System.out.println("Caminho de Euler é possivel");
        } else {
            System.out.println("Caminho de Euler Impossivel");
        }
    }
}
