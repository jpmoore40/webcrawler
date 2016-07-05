package crawler;

import crawler.markup.DefaultMarkupSupplier;
import crawler.parser.BasicRegexParser;

import java.util.Optional;

/**
 * Entry point for the web crawler
 * User: Jonathan Moore
 */
public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: Main <URL> [max depth]");
        } else {
            int maxDepth = args.length >= 2 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;
            WebCrawler crawler = new WebCrawler(args[0], new BasicRegexParser(), new DefaultMarkupSupplier(), maxDepth);
            Optional<PageContent> content = crawler.parse();
            content.ifPresent(new SiteMapPrinter()::createSiteMap);
        }
    }
}
