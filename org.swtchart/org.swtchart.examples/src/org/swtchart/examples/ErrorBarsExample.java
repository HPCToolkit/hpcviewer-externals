package org.swtchart.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IErrorBar;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

/**
 * An example for error bars.
 */
public class ErrorBarsExample {

    private static final double[] ySeries = { 0.0, 0.38, 0.71, 0.92, 1.0, 0.92,
            0.71, 0.38, 0.0, -0.38, -0.71, -0.92, -1.0, -0.92, -0.71, -0.38 };

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Error Bars");
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
        chart.getTitle().setText("Error Bars");

        // create series
        ISeries series = chart.getSeriesSet().createSeries(SeriesType.LINE,
                "line series");
        series.setYSeries(ySeries);

        // set error bars
        IErrorBar errorBar = series.getYErrorBar();
        errorBar.setVisible(true);
        errorBar.setError(0.1);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        return chart;
    }
}