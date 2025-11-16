package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * An implementation that writes on stdout.
 */
@SuppressWarnings("PMD.SystemPrintln")  // required by the exercise
public final class DrawNumberStandardOutputView implements DrawNumberView {
    @Override
    public void setController(final DrawNumberController observer) {
        //no controller
    }

    @Override
    public void start() {
        System.out.println("view started");
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }
}
