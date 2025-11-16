package it.unibo.mvc.controller;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * this class implements the game controller.
 */
public final class DrawNumberControllerImpl implements DrawNumberController {

    private final DrawNumber model;
    private final List<DrawNumberView> views = new ArrayList<>();

    /**
     * Builds a new game controller provided a game model.
     *
     * @param model the implementation of the game model
     */
    public DrawNumberControllerImpl(final DrawNumber model) {
        this.model = Objects.requireNonNull(model, "model must not be null");
    }

    @Override
    public void addView(final DrawNumberView view) {
        Objects.requireNonNull(view, "required not null");
        if (this.views == null) {
            throw new IllegalStateException("views not initialized");
        }
        this.views.add(view);
        view.setController(this);
        view.start();
    }

    @Override
    public void newAttempt(final int n) {
        if (this.views.isEmpty()) {
            throw new IllegalStateException("no view attached");
        }
        final var result = this.model.attempt(n);
        for (final DrawNumberView v : this.views) {
            v.result(result);
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        //System.exit(0);
    }

}
