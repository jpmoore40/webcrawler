package crawler.markup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Default implementation of MarkupSupplier that reads the web page markup using a BufferedReader
 * User: Jonathan Moore
 */
public class DefaultMarkupSupplier implements MarkupSupplier {

    private static final Logger logger = Logger.getLogger(MarkupSupplier.class.getName());

    @Override
    public Optional<String> getPageMarkup(String link) {
        Optional<URL> url = toUrl(link);
        if(url.isPresent()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.get().openStream()))) {
                return Optional.of(br.lines().collect(Collectors.joining("\n")));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error reading URL " + link, e);
            }
        }
        return Optional.empty();
    }

    private Optional<URL> toUrl(String address) {
        try {
            return Optional.of(new URL(address));
        } catch (MalformedURLException e) {
            logger.info("Invalid URL: " + address);
            return Optional.empty();
        }
    }
}
