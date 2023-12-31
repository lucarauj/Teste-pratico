package com.desafio.service;

import com.desafio.model.HtmlTag;
import com.desafio.repository.HtmlTagRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class HtmlTagService {

    private final HtmlTagRepository repository;

    public HtmlTagService(HtmlTagRepository repository) {
        this.repository = repository;
    }

    public String processarURL(String url) {
        try {
            if (!isValidUrl(url)) {
                return "URL inválida!";
            }

            List<HtmlTag> urlInDB = repository.findByUrl(url);

            if (urlInDB.isEmpty()) {
                String html = downloadHtml(url);
                contarHTMLTags(url, html);
            } else {
                return null;
            }
        } catch (IOException e) {
            return "Erro ao processar a Url: " + url;
        }
        return null;
    }

    public List<HtmlTag> filtrarUrl(String url) {
        return repository.findByUrl(url);
    }

    private String downloadHtml(String url) throws IOException {
        return Jsoup.connect(url).get().html();
    }

    private void contarHTMLTags(String url, String html) {
        Document doc = Jsoup.parse(html);
        Elements tags = doc.select("*");

        for (Element tag : tags) {
            String tagName = tag.tagName();

            if (tagName.equals("#root")) {
                continue;
            }

            Optional<HtmlTag> tagInDB = repository.findByUrlAndTag(url, tagName);

            if (tagInDB.isPresent()) {
                HtmlTag existingTag = tagInDB.get();
                existingTag.setContador(existingTag.getContador() + 1);
                repository.save(existingTag);
            } else {
                HtmlTag newTag = new HtmlTag();
                newTag.setUrl(url);
                newTag.setTag(tagName);
                newTag.setContador(1);
                repository.save(newTag);
            }
        }
    }

    public List<HtmlTag> getAllTags() {
        return repository.findAll();
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}