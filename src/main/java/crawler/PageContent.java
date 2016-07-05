package crawler;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Simple bean to hold content of a page (i.e. internal links, external links and resources)
 * Internal links are store recursively as a PageContent objects
 * User: Jonathan Moore
 */
public class PageContent {
    private final String address;
    private final Set<PageContent> internalLinks = new LinkedHashSet<>();
    private final Set<String> externalLinks = new LinkedHashSet<>();
    private final Set<String> resources = new LinkedHashSet<>();

    public PageContent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void addInternalLink(PageContent link) {
        internalLinks.add(link);
    }

    public void addExternalLink(String link) {
        externalLinks.add(link);
    }

    public void addResource(String resource) {
        resources.add(resource);
    }

    public Set<PageContent> internalLinks() {
        return new LinkedHashSet<>(internalLinks);
    }

    public Set<String> externalLinks() {
        return new LinkedHashSet<>(externalLinks);
    }

    public Set<String> resources() {
        return new LinkedHashSet<>(resources);
    }
}
