package crawler.parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for BasicRegexParser
 * User: Jonathan Moore
 */
public class BasicRegexParserTest {

    @Test
    public void testLinks() {
        BasicRegexParser parser = new BasicRegexParser();
        List<String> links = new ArrayList<>();
        parser.parseLinks("<a href=\"www.abc.com/page2\">link1</a><a href=\"www.bbc.com\">link3</a>", links::add);
        assertEquals(2, links.size());
        assertEquals("www.abc.com/page2", links.get(0));
        assertEquals("www.bbc.com", links.get(1));
    }

    @Test
    public void testResources() {
        BasicRegexParser parser = new BasicRegexParser();
        List<String> resources = new ArrayList<>();
        parser.parseResources("dummy <img src=\"abc.gif\"> dummy", resources::add);
        assertEquals(1, resources.size());
        assertEquals("abc.gif", resources.get(0));
    }
}
