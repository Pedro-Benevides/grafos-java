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

        this.arestas.removeIf(aresta -> {

            if (aresta.getInicio().getValor() == valorInicio
                    && aresta.getFinal().getValor() == valorFinal) {
                aresta.getInicio().getArestas().removeIf(arestaV -> arestaV == aresta);
                aresta.getFinal().getArestas().removeIf(arestaV -> arestaV == aresta);

            } else if (aresta.getInicio().getValor() == valorFinal
                    && aresta.getFinal().getValor() == valorInicio) {
                aresta.getInicio().getArestas().removeIf(arestaV -> arestaV == aresta);
                aresta.getFinal().getArestas().removeIf(arestaV -> arestaV == aresta);

            }

            return (aresta.getInicio().getValor() == valorInicio
                    || aresta.getFinal().getValor() == valorInicio)
                    &&
                    (aresta.getInicio().getValor() == valorFinal
                            || aresta.getFinal().getValor() == valorFinal);
        });

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

    public void testConection(int v1, int v2) {
        this.arestas.forEach((aresta) -> {
            boolean testInicio = (aresta.getInicio().getValor() == v1
                    || aresta.getInicio().getValor() == v2);

            boolean testFim = (aresta.getFinal().getValor() == v1
                    || aresta.getFinal().getValor() == v2);

            if (testInicio && testFim) {
                System.out.println("Aresta encontrada: ");
                System.out.println(
                        "[" + aresta.getInicio().getValor() + " -> "
                                + aresta.getFinal().getValor() + "]");
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

    private void testConection(Vertice vertice) {
        vertice.setVisitado(true);
        ArrayList<Vertice> adjacencias = adjacencias(vertice);
        if (adjacencias.size() >= 1) {
            for (Vertice adjacente : adjacencias) {
                if (!adjacente.isVisitado()) {
                    this.testConection(adjacente);
                }
            }

        }

    }

    public boolean checkConexo(boolean showPrint) {
        ArrayList<Vertice> verticesCopy = this.vertices;
        Vertice firstV = this.vertices.stream().findFirst().get();
        this.testConection(firstV);

        verticesCopy.removeIf(v -> v.isVisitado() == true);

        if (verticesCopy.size() >= 1) {
            if (showPrint) {
                System.out.println("Grafo não Conexo");
            }
            return false;
        } else if (showPrint) {
            System.out.println("Grafo Conexo");
        }
        return true;
    }

    private ArrayList<Vertice> adjacencias(Vertice vertice) {
        ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

        for (Aresta aresta : vertice.getArestas()) {
            if (vertice == aresta.getInicio()) {
                adjacentes.add(aresta.getFinal());
            } else {
                adjacentes.add(aresta.getInicio());
            }
        }

        return adjacentes;
    }

    public ArrayList<Vertice> adjacencias(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();
        ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

        for (Aresta aresta : vertice.getArestas()) {
            if (vertice == aresta.getInicio()) {
                adjacentes.add(aresta.getFinal());
                System.out.print("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
            } else {
                adjacentes.add(aresta.getInicio());
                System.out.print("[" + aresta.getFinal().getValor() + " -> " + aresta.getInicio().getValor() + "]");

            }
        }

        return adjacentes;
    }

    public void matrizAdjacencias() {
        int countV = this.vertices.size();
        int[][] matrizAdjacencia = new int[countV][countV];

        this.arestas.forEach((aresta) -> {
            matrizAdjacencia[aresta.getInicio().getValor() - 1][aresta.getFinal().getValor() - 1] = 1;
            matrizAdjacencia[aresta.getFinal().getValor() - 1][aresta.getInicio().getValor() - 1] = 1;
        });

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
