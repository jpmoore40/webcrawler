WebCrawler Notes
================

I have provided a Maven pom.xml though the only external resource used is JUnit. The main class is crawler.Main which
should be provided with the URL of the site to crawl, plus an optional integer value to restrict the depth of pages to
descend. This may be useful when running to prevent excessive run times and memory usage for large sites

I have tried to implement this in the time specified but have had to make some compromises as a result. Ideally I would
prefer to be able to make the following changes:

- I've created a very basic regex parser for the HTML though I appreciate this will not be suitable in a production
environment. Ideally I would use a third party parser such as Jsoup but I didn't in this instance as I thought you would
want to see only code I wrote myself. You would just need to swap in a new implementation of HtmlParser. However it does
mean that the matching of links and images is not guaranteed by any means.
- The WebCrawler builds up a tree of PageContent nodes which are then printed out post processing using a SiteMapPrinter.
The printer currently accepts a Consumer<String> to send the output to - the default is System.out.println.
It would be better to re-implement this as a more generic traverser that processes each node, which would give more
flexibility on output format.
- Alternatively it may be more suitable for larger sites to process the links in-line rather than build up the tree as I
have done. Building the tree takes time and memory so a processing them in-line would result in more constant output and
a smaller memory footprint. This would require some refactoring of the WebCrawler class to separate out the concerns better.
To get round this in the short term I have added the maxDepth command line parameter as noted above
- I have not implemented any concurrency though this would be an obvious improvement.
- Determining which links are internal and which are external needs more work
- Tests are only very rudimentary - there should be more!


