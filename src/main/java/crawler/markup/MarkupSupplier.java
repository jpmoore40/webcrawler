package crawler.markup;

import java.util.Optional;

/**
 * Definition of a class that returns the markup for a url
 * User: Jonathan Moore
 */
public interface MarkupSupplier {
    Optional<String> getPageMarkup(String url);
}
