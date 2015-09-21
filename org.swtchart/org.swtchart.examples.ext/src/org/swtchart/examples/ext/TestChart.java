package org.swtchart.examples.ext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ext.IChartSelectionListener;
import org.swtchart.ext.InteractiveChart;
import org.swtchart.ext.UserSelectionData;

public class TestChart {


    //private static final String[] categorySeries = { "Mon", "Tue", "Wed",
    //        "Thu", "Fri" };
    private static final double[] yLineSeries1 = { 4.6, 5.4, 6.9, 5.6, 0.0, 7.1 };
    private static final double[] yLineSeries2 = { 6.0, 5.1, 4.9, 0.0, 5.3, 4.2 };
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Angled Axis Tick Labels");
        shell.setSize(500, 400);
        shell.setLayout(new FillLayout());


        // create an interactive chart
        Chart chart = new InteractiveChart(shell, SWT.NONE);

        // set title
        chart.getTitle().setText("Sample Interactive Chart");

        ((InteractiveChart)chart).setChartSelectionListener(new IChartSelectionListener() {
			
			@Override
			public void selection(UserSelectionData data) {
				System.out.format("%d (%f, %f)\n", data.index, data.valueX, data.valueY);
			}
		});
        // set category series
        //chart.getAxisSet().getXAxis(0).enableCategory(true);
       // chart.getAxisSet().getXAxis(0).setCategorySeries(categorySeries);

        // create line series 1
        ILineSeries lineSeries1 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "line series 1");
        lineSeries1.setYSeries(yLineSeries1);

        // create line series 2
        ILineSeries lineSeries2 = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "line series 2");
        lineSeries2.setYSeries(yLineSeries2);
        lineSeries2.setLineColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));

        // create bar series 1
 /*       IBarSeries barSeries1 = (IBarSeries) chart.getSeriesSet().createSeries(
                SeriesType.BAR, "bar series 1");
        barSeries1.setYSeries(yBarSeries1);*/

        // create bar series 2
        /*IBarSeries barSeries2 = (IBarSeries) chart.getSeriesSet().createSeries(
                SeriesType.BAR, "bar series 2");
        barSeries2.setYSeries(yBarSeries2);
        barSeries2.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));*/

        // adjust the axis range
        chart.getAxisSet().adjustRange();


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();

	}

}
