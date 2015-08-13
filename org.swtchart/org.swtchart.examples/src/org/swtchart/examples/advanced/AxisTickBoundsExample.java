package org.swtchart.examples.advanced;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IBarSeries;
import org.swtchart.IAxis.Direction;
import org.swtchart.ISeries.SeriesType;

/**
 * An example to get bounds of axis tick.
 */
public class AxisTickBoundsExample {

    private static final double[] ySeries = { 0.1, 0.1, 0.2, 0.2, 0.3 };

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Axis Tick Bounds");
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
        final Chart chart = new Chart(parent, SWT.NONE);
        chart.getTitle().setText("Axis Tick Bounds");

        // create bar series
        IBarSeries series1 = (IBarSeries) chart.getSeriesSet().createSeries(
                SeriesType.BAR, "series");
        series1.setYSeries(ySeries);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        // add mouse move listener to chart
        chart.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                for (IAxis axis : chart.getAxisSet().getAxes()) {
                    Rectangle r = axis.getTick().getBounds();

                    // check if mouse cursor is on axis tick
                    if (r.x < e.x && e.x < r.x + r.width && r.y < e.y
                            && e.y < r.y + r.height) {

                        // get pixel coordinate on axis tick
                        int pixelCoord;
                        if (axis.getDirection() == Direction.X) {
                            pixelCoord = e.x - r.x;
                        } else {
                            pixelCoord = e.y - r.y;
                        }

                        // get data coordinate
                        double dataCoord = axis.getDataCoordinate(pixelCoord);

                        // show tool-tip
                        chart.setToolTipText(String.valueOf(dataCoord));
                        return;
                    }
                }
                chart.setToolTipText(null);
            }
        });

        return chart;
    }
}