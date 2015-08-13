package org.swtchart.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.LineStyle;
import org.swtchart.ISeries.SeriesType;

/**
 * An example for scatter chart.
 */
public class ScatterChartExample {

    private static final double[] xSeries = { 0.0, 2.6, 6.5, 4.4, 5.6, 4.3,
            3.4, 10.8, 2.1, 8.9 };
    private static final double[] ySeries = { 1.3, 0.0, 3.9, 2.6, 1.1, 0.6,
            3.1, 3.5, 5.6, 4.4 };

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Scatter Chart");
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
        chart.getTitle().setText("Scatter Chart");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Score A");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Score B");

        // create scatter series
        ILineSeries scatterSeries = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "scatter series");
        scatterSeries.setLineStyle(LineStyle.NONE);
        scatterSeries.setXSeries(xSeries);
        scatterSeries.setYSeries(ySeries);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        return chart;
    }
}