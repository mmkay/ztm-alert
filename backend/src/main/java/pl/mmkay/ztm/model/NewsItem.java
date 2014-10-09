package pl.mmkay.ztm.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NewsItem {
    private String title;
    private String content;
    private List<String> lines;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
