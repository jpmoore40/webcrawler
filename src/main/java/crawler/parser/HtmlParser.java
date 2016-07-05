package crawler.parser;

import java.util.function.Consumer;

/**
 * Definition of a parer for HTML
 * User: Jonathan Moore
 */
public interface HtmlParser {
    void parseLinks(String input, Consumer<String> linkConsumer);
    void parseResources(String input, Consumer<String> resourceConsumer);
}
