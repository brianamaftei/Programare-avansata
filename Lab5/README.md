# AdvancedProgramming - Lab 5

## Compulsory

----------

- [x] Create an object-oriented model of the problem. You should have at least the following classes: Catalog, Document. Create a class responsible with external operations regarding a catalog.
- [x] Implement the following methods representing commands that will manage the content of the catalog:
  - add: adds a new entry into the catalog;
  - toString: a textual representation of the catalog;
  - save: saves the catalog to an external file using JSON format; you may use Jackson or other library;
  - load: loads the catalog from an external file.
------------------

## Homework
- [x] Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command.
- [x] Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).
-list: prints the list of documents on the screen;
-view: opens a document using the native operating system application (see the Desktop class);
-report: creates (and opens) an HTML report representing the content of the catalog.
- [x] Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.
- [x] The application will signal invalid data or the commands that are not valid using custom exceptions.
- [x] The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR.

-------------------------------

## Bonus
- [x] Use Apache Tika in order to extract metadata from your catalog items and implement the command info in order to display them.
- [x] Each document has a set of tags (extracted metadata, or manually set by user). Two documents are related if they have a common tag.
- [x] Using a minimum number of colors (labels), assign each document a color such that related documents have distinct colors.
- [x] You may want to read about exact algorithms for the graph coloring problem.
- [x] Implement using Graph4J API Brown coloring algorithm or other coloring algorithm.
- [x] Create large instances of the problem and test your algorithm (against JGraphT or other library implementations).

