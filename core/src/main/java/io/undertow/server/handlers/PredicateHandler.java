package io.undertow.server.handlers;

import io.undertow.predicate.Predicate;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpHandlers;
import io.undertow.server.HttpServerExchange;

/**
 * @author Stuart Douglas
 */
public class PredicateHandler implements HttpHandler {

    private volatile Predicate<HttpServerExchange> predicate;
    private volatile HttpHandler trueHandler;
    private volatile HttpHandler falseHandler;

    public PredicateHandler(final Predicate<HttpServerExchange> predicate, final HttpHandler trueHandler, final HttpHandler falseHandler) {
        this.predicate = predicate;
        this.trueHandler = trueHandler;
        this.falseHandler = falseHandler;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        HttpHandler next = predicate.resolve(exchange) ? trueHandler : falseHandler;
        HttpHandlers.executeHandler(next, exchange);
    }

    public Predicate<HttpServerExchange> getPredicate() {
        return predicate;
    }

    public PredicateHandler setPredicate(final Predicate<HttpServerExchange> predicate) {
        this.predicate = predicate;
        return this;
    }

    public HttpHandler getTrueHandler() {
        return trueHandler;
    }

    public PredicateHandler setTrueHandler(final HttpHandler trueHandler) {
        this.trueHandler = trueHandler;
        return this;
    }

    public HttpHandler getFalseHandler() {
        return falseHandler;
    }

    public PredicateHandler setFalseHandler(final HttpHandler falseHandler) {
        this.falseHandler = falseHandler;
        return this;
    }
}
