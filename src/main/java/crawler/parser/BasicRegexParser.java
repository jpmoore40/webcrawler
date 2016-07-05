package crawler.parser;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a very simple (and not very effective) regex based implementation of {@link HtmlParser}.
 * Regex is not good for this so it would be much better to use something like Jsoup
 * User: Jonathan Moore
 */
public class BasicRegexParser implements HtmlParser {
    private static final Pattern HREF_PATTERN = Pattern.compile("<a .*?href=\"(.*?)\".*?>");
    private static final Pattern IMG_PATTERN = Pattern.compile("<img .*?src=\"(.*?)\".*>");

    @Override
    public void parseLinks(String input, Consumer<String> linkConsumer) {
        parseInput(input, HREF_PATTERN, linkConsumer);
    }

    @Override
    public void parseResources(String input, Consumer<String> resourceConsumer) {
        parseInput(input, IMG_PATTERN, resourceConsumer);
    }

    private void parseInput(String input, Pattern pattern, Consumer<String> consumer) {
        Matcher m = pattern.matcher(input);
        while(m.find()) {
            consumer.accept(m.group(1));
        }
    }
}
