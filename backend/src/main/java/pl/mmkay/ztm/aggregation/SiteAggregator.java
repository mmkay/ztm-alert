package pl.mmkay.ztm.aggregation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.mmkay.ztm.model.NewsItem;
import pl.mmkay.ztm.repository.NewsItemRepository;

@Service
public class SiteAggregator {
    private static final Logger LOG = LoggerFactory.getLogger(SiteAggregator.class);
    
    @Value("${site.url}")
    private String siteUrl;
    
    @Autowired
    private NewsItemRepository newsRepository;
    
    public void aggregate() {
        try {
            Document page = getPage();
            List<NewsItem> items = new ArrayList<>();
            Elements newsElements = page.getElementsByClass("news");
            newsElements.stream().forEach(n -> {
                NewsItem item = new NewsItem();
                item.setContent(n.text());
                items.add(item);
            });
            newsRepository.save(items);
        } catch (IOException ex) {
            LOG.error("Exception reading page!");
        }
    }

    Document getPage() throws IOException {
        Document document = Jsoup.connect(siteUrl).get();
        return document;
    }
}
