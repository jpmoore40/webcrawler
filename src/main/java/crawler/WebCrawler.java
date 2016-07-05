package crawler;

import crawler.markup.MarkupSupplier;
import crawler.parser.HtmlParser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Parser for a website.
 * User: Jonathan Moore
 */
public class WebCrawler {

    private final String rootUrl;
    private final Set<String> visitedLinks = new HashSet<>();
    private final HtmlParser parser;
    private final MarkupSupplier markupSupplier;
    private int maxDepth = Integer.MAX_VALUE;
    private int currentDepth = 0;

    public WebCrawler(String rootUrl, HtmlParser parser, MarkupSupplier markupSupplier) {
        this.rootUrl = rootUrl;
        this.parser = parser;
        this.markupSupplier = markupSupplier;
    }

    public WebCrawler(String rootUrl, HtmlParser parser, MarkupSupplier markupSupplier, int maxDepth) {
        this.rootUrl = rootUrl;
        this.parser = parser;
        this.markupSupplier = markupSupplier;
        this.maxDepth = maxDepth;
    }

    public Optional<PageContent> parse() {
        return parse(rootUrl);
    }

    public Optional<PageContent> parse(String link) {
        return pageVisited(link) ? Optional.empty() : visitPage(link);
    }

    private boolean pageVisited(String link) {
        return visitedLinks.contains(link);
    }

    private Optional<PageContent> visitPage(String link) {
        PageContent pageContent = null;
        if(currentDepth < maxDepth) {
            currentDepth++;
            visitedLinks.add(link);
            Optional<String> content = markupSupplier.getPageMarkup(link);
            if(content.isPresent()) {
                pageContent = new PageContent(link);
                parseContent(content.get(), pageContent);
            }
            currentDepth--;
        }
        return Optional.ofNullable(pageContent);
    }

    private void parseContent(String s, PageContent pageContent) {
        parser.parseLinks(s, link -> addInternalLink(link, pageContent));
        parser.parseLinks(s, link -> addExternalLink(link, pageContent));
        parser.parseResources(s, pageContent::addResource);
    }

    private void addInternalLink(String link, PageContent pageContent) {
        if (isInternalLink(link) && !link.equals(rootUrl)) {
            Optional<PageContent> nextPage = parse(link);
            if(nextPage.isPresent()) {
                pageContent.addInternalLink(nextPage.get());
            }
        }
    }

    private void addExternalLink(String link, PageContent pageContent) {
        if (!isInternalLink(link)) {
            pageContent.addExternalLink(link);
        }
    }

    private boolean isInternalLink(String link) {
        return link.startsWith(rootUrl);
    }
}
