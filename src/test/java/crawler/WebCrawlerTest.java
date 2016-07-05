package crawler;

import crawler.markup.TestMarkupSupplier;
import crawler.parser.BasicRegexParser;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Simple Test for WebCrawler
 * User: Jonathan Moore
 */
public class WebCrawlerTest {


    private static final String PAGE1 = "Page 1" +
            "<a href=\"www.abc.com/page2\">link1</a>" +
            "<a href=\"www.bbc.com\">link3</a>" +
            "<img src=\"abc1.jpg\">";
    private static final String PAGE2 = "Page 2" +
            "<a href=\"www.abc.com/page3\">link1</a>" +
            "<a href=\"www.google.com>link3\"</a>" +
            "<img src=\"abc2.jpg\">";
    private static final String PAGE3 = "Page 3" +
            "<a href=\"www.yahoo.com\">link3</a>" +
            "<img src=\"abc3.jpg\">";


    @Test
    public void crawlSite() {
        TestMarkupSupplier supplier = new TestMarkupSupplier();
        supplier.addMarkup("www.abc.com", PAGE1);
        supplier.addMarkup("www.abc.com/page2", PAGE2);
        supplier.addMarkup("www.abc.com/page3", PAGE3);

        WebCrawler crawler = new WebCrawler("www.abc.com", new BasicRegexParser(), supplier);

        Optional<PageContent> optionalContent = crawler.parse();
        assertTrue(optionalContent.isPresent());

        StringBuilder sb = new StringBuilder();
        SiteMapPrinter printer = new SiteMapPrinter(s -> sb.append(s).append("\n"));
        printer.createSiteMap(optionalContent.get());

        String expected = "->www.abc.com\n" +
                "--->abc1.jpg\n" +
                "--->www.bbc.com\n" +
                "--->www.abc.com/page2\n" +
                "----->abc2.jpg\n" +
                "----->www.google.com>link3\n" +
                "----->www.abc.com/page3\n" +
                "------->abc3.jpg\n" +
                "------->www.yahoo.com\n";
        assertEquals(expected, sb.toString());
    }

}
