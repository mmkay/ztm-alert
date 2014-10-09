package pl.mmkay.ztm.aggregation;

import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.mmkay.ztm.Application;
import pl.mmkay.ztm.repository.NewsItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SiteAggregatorTests {  
    private static final Logger LOG = LoggerFactory.getLogger(SiteAggregatorTests.class);
    
    @Autowired
    private SiteAggregator aggregator;
    @Autowired
    private NewsItemRepository itemRepository;
    
    @Test
    public void connectsToSite() throws IOException {
        // arrange & act
        Document page = aggregator.getPage();
        
        // assert
        assertThat(page).isNotNull();
    }
    
    @Test
    public void siteContainsNews() throws IOException {
        // arrange & act
        Document page = aggregator.getPage();
        Elements newsElements = page.getElementsByClass("news");
        
        // assert
        assertThat(newsElements).isNotEmpty();
    }
    
    @Test
    public void aggregatesItems() {
        // arrange
        itemRepository.deleteAll();
        
        // act
        aggregator.aggregate();
        
        // assert
        assertThat(itemRepository.findAll()).isNotEmpty();
    }
}
