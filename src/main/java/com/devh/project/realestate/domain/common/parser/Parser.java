package com.devh.project.realestate.domain.common.parser;

import org.jsoup.nodes.Document;

public abstract class Parser<T> {
    protected abstract T parse(Document document);
}
