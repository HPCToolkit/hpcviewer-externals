package org.swtchart.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;

/**
 * An example for log scale.
 */
public class LogScaleExample {

    private static final double[] ySeries = { 0.6, 0.4, 0.7, 0.06, 1.9, 1.7,
            2.6, 5.4, 9.1, 11.2, 23.4, 10.6, 54.2, 40.6, 68.1, 110.5 };

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Log Scale");
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

        // set titles
        chart.getTitle().setText("Log Scale");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Data Points");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Amplitude");

        // create line series
        ILineSeries lineSeries = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "line series");
        lineSeries.setYSeries(ySeries);

        // set log scale
        chart.getAxisSet().getYAxis(0).enableLogScale(true);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        return chart;
    }
}