package com.blog.service.impl;

import com.blog.service.MarkdownService;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import org.commonmark.parser.Parser;
import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

@Service
public class MarkdownServiceImpl implements MarkdownService {

    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownServiceImpl() {
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
    }

    @Override
    public String render(String markdown) throws IOException {
        Reader reader = new StringReader(markdown);
        Node document = parser.parseReader(reader);
        return renderer.render(document);
    }
}
