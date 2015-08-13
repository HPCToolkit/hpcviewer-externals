package org.swtchart.examples.advanced;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ICustomPaintListener;
import org.swtchart.IPlotArea;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

/**
 * An example for custom paint listener.
 */
public class CustomPaintListenerExample {

    private static final double[] ySeries = { 0.1, 0.38, 0.41, 0.92, 1.0 };

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Custom Paint Listener");
        shell.setSize(500, 400);
        shell.setLayout(new FillLayout());

        createChart(shell);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    /**
     * create the chart.
     * 
     * @param parent
     *            The parent composite
     * @return The created chart
     */
    static public Chart createChart(Composite parent) {

        // create a chart
        Chart chart = new Chart(parent, SWT.NONE);
        chart.getTitle().setText("Custom Paint Listener");

        ISeries lineSeries = chart.getSeriesSet().createSeries(SeriesType.LINE,
                "line series");
        lineSeries.setYSeries(ySeries);

        // add paint listeners
        IPlotArea plotArea = (IPlotArea) chart.getPlotArea();
        plotArea.addCustomPaintListener(new FrontPaintListener());
        plotArea.addCustomPaintListener(new BehindPaintListener());

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        return chart;
    }

    static class FrontPaintListener implements ICustomPaintListener {
        public void paintControl(PaintEvent e) {
            e.gc.setBackground(Display.getDefault().getSystemColor(
                    SWT.COLOR_CYAN));
            e.gc.fillRectangle(0, e.height / 2, e.width, 20);
        }

        public boolean drawBehindSeries() {
            return false;
        }
    }

    static class BehindPaintListener implements ICustomPaintListener {
        public void paintControl(PaintEvent e) {
            e.gc.fillGradientRectangle(e.x, e.y, e.width, e.height, true);
        }

        public boolean drawBehindSeries() {
            return true;
        }
    }
}
