package Bonus;

import Compulsory.Catalog.Catalog;
import Compulsory.Catalog.CatalogUtil;
import Compulsory.Document.Article;
import Compulsory.Document.Book;
import Compulsory.Document.Document;
import org.jgrapht.Graph;
import org.jgrapht.alg.color.BrownBacktrackColoring;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import java.util.Set;

public class Main {
    private static final Catalog catalog = new Catalog("MyDocuments");

    /**
     * Creates a graph of the documents
     * Each of them becomes a vertex in the graph and an edge is added between two if they have at least one tag in common
     *
     * @param catalog of documents
     * @return the graph required
     */

    public static Graph<Document, DefaultEdge> getGraphFromCatalog(Catalog catalog) {
        Graph<Document, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        for (Document document : catalog.getDocs()) {
            graph.addVertex(document);
        }
        for (Document document1 : catalog.getDocs()) {
            for (Document document2 : catalog.getDocs()) {
                if (!document1.equals(document2)) {
                    Set<String> set1 = document1.getTags().keySet();
                    Set<String> set2 = document2.getTags().keySet();
                    int initialSize = set1.size();
                    set1.removeAll(set2);
                    if (initialSize != set1.size()) {
                        graph.addEdge(document1, document2);
                    }
                }
            }
        }
        return graph;
    }

    /**
     *
     */

    private void testCreateSave() {
        var book = new Book("article1", "Destinations To Visit in Your Lifetime", "D:/PA/", "3/6/2023");
        var book1 = new Book("article2", "Abracadabra", "Location2", "23/12/2022");
        var article = new Article("book1", "The Flavor Bible", "D:/PA/", "3/6/2023");
        book1.addTag("1", "A");
        book1.addTag("2", "B");
        book1.addTag("3", "C");
        book.addTag("1", "A");
        book.addTag("2", "B");
        book.addTag("3", "C");
        article.addTag("1", "A");
        article.addTag("2", "B");
        article.addTag("3", "C");
        catalog.add(book);
        catalog.add(book1);
        catalog.add(article);
        System.out.println(catalog);
        try {
            CatalogUtil.save(catalog, "C:/Users/pinza/Desktop/Lab5/src/main/java/Bonus/catalog.json");
            System.out.println("Saved");
        } catch (java.io.IOException exception) {
            System.out.println("There is a problem with the saving process");
        }


    }

    /**
     * This method generates a random directed graph with a given number of vertices and edges
     *
     * @param noVertices
     * @param noEdges
     * @return
     */
    public static Graph<Integer, DefaultEdge> generateRandomGraph(int noVertices, int noEdges) {
        Graph<Integer, DefaultEdge> graph = new DirectedPseudograph<>(SupplierUtil.createIntegerSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
        GnmRandomGraphGenerator<Integer, DefaultEdge> generator = new GnmRandomGraphGenerator<>(noVertices, noEdges);
        generator.generateGraph(graph);

        return graph;
    }

    public static void main(String[] args) {

        Main app = new Main();
        app.testCreateSave();

        String filePath = "C:/Users/pinza/Desktop/Lab5/src/main/java/Bonus/catalog.html";
        InfoCommand infoCommand = new InfoCommand(catalog, filePath);
        infoCommand.execCommand();

        Graph<Integer, DefaultEdge> graph = generateRandomGraph(62, 26);
        BrownBacktrackColoring<Integer, DefaultEdge> algorithm = new BrownBacktrackColoring<>(graph);
        System.out.println(algorithm.getChromaticNumber());
        System.out.println(algorithm.getColoring());
        Graph<Document, DefaultEdge> graphCatalog = getGraphFromCatalog(catalog);
        System.out.println(graphCatalog);
        BrownBacktrackColoring<Document, DefaultEdge> algorithmCatalog = new BrownBacktrackColoring<>(graphCatalog);
        System.out.println(algorithmCatalog.getChromaticNumber());
        System.out.println(algorithm.getColoring().toString());

    }
}
