package crawler.markup;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Test implementation of MarkupSupplier, based on a Map
 * User: Jonathan Moore
 */
public class TestMarkupSupplier implements MarkupSupplier{

    private Map<String, String> markup = new HashMap<>();

    @Override
    public Optional<String> getPageMarkup(String url) {
        return Optional.ofNullable(markup.get(url));
    }

    public void addMarkup(String url, String markup) {
        this.markup.put(url, markup);
    }
}
