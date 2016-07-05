package crawler;

import java.util.function.Consumer;

/**
 * Class to print out a site map, starting from the supplied root PageContent
 * User: Jonathan
 */
public class SiteMapPrinter {

    private static final String PAD = "--";

    private Consumer<String> textOutput = System.out::println;

    public SiteMapPrinter() { }

    public SiteMapPrinter(Consumer<String> textOutput) {
        this.textOutput = textOutput;
    }

    public void createSiteMap(PageContent pageContent) {
        createSiteMap("", pageContent);
    }

    private void createSiteMap(String currentIndent, PageContent pageContent) {
        textOutput.accept(currentIndent + "->" + pageContent.getAddress());

        String newIndent = currentIndent + PAD;

        printResources(newIndent, pageContent);
        printExternalLinks(newIndent, pageContent);
        printInternalLinks(newIndent, pageContent);
    }

    private void printResources(String indent, PageContent pageContent) {
        pageContent.resources().stream().forEach(s -> textOutput.accept(indent + "->" + s));
    }

    private void printExternalLinks(String indent, PageContent pageContent) {
        pageContent.externalLinks().stream().forEach(s -> textOutput.accept(indent + "->" + s));
    }

    private void printInternalLinks(String indent, PageContent pageContent) {
        pageContent.internalLinks().stream().forEach(content -> createSiteMap(indent, content));
    }
}
