package it.unibo.mvc;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args)
        throws ClassNotFoundException,
               NoSuchMethodException,
               InvocationTargetException,
               InstantiationException,
               IllegalAccessException {

        final DrawNumber model = new DrawNumberImpl();
        final DrawNumberController controller = new DrawNumberControllerImpl(model);

        final String swingClassName = "it.unibo.mvc.view.DrawNumberSwingView";
        final String stdoutClassName = "it.unibo.mvc.view.DrawNumberStandardOutputView";

        final Class<?> swingClass = Class.forName(swingClassName);
        final Class<?> stdoutClass = Class.forName(stdoutClassName);

        final Constructor<?> swingCtor = swingClass.getConstructor();
        final Constructor<?> stdoutCtor = stdoutClass.getConstructor();
        final int randomnum = 3;
        // 3 graphical views
        for (int i = 0; i < randomnum; i++) {
            final DrawNumberView view =
                (DrawNumberView) swingCtor.newInstance();
            controller.addView(view);
        }

        // 3 stdout views
        for (int i = 0; i < randomnum; i++) {
            final DrawNumberView view =
                (DrawNumberView) stdoutCtor.newInstance();
            controller.addView(view);
        }
    }
}
