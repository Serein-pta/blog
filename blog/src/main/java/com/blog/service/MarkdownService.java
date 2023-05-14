package com.blog.service;

import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;

public interface MarkdownService {


    public String render(String markdown) throws IOException;

}
