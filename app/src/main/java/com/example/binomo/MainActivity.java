package com.example.binomo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.binomo.models.Point;
import com.example.binomo.presenters.MainPresenter;
import com.example.binomo.utils.PresenterManager;
import com.example.binomo.utils.builders.SciChartBuilder;
import com.example.binomo.views.MainView;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.scichart.charting.model.dataSeries.IXyDataSeries;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.annotations.LineAnnotation;
import com.scichart.charting.visuals.axes.AutoRange;
import com.scichart.charting.visuals.axes.CategoryDateAxis;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.drawing.utility.ColorUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private SciChartSurface mChartSurface;
    private IXyDataSeries<Date, Double> mDataSeries;
    private LineAnnotation mLineAnnotation;
    private MainPresenter presenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SciChartBuilder.init(this);
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new MainPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_main);

        mChartSurface = (SciChartSurface) findViewById(R.id.chart);
        mDataSeries = SciChartBuilder.instance().newXyDataSeries(Date.class, Double.class).withAcceptsUnsortedData().build();
        mLineAnnotation = SciChartBuilder.instance().newHorizontalLineAnnotation().withY1(0d).withStroke(3, ColorUtil.White).build();
        initChart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.unbindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.stopRunnableGetPoint();
        }

        SciChartBuilder.dispose();
    }

    @Override
    public void onSaveInstanceState(final Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        PresenterManager.getInstance().savePresenter(presenter, savedInstanceState);
    }

    @Override
    public void addNewPointToChart(Point point) {
        mDataSeries.append(point.getDate(), point.getRate());
        mLineAnnotation.setY1(point.getRate());
    }

    @Override
    public void updateView(final List<Point> points) {
        if (points != null && points.size() > 0) {
            UpdateSuspender.using(mChartSurface, new Runnable() {
                @Override
                public synchronized void run() {
                    mDataSeries.clear();
                    mDataSeries.append(Lists.transform(points, PointToDate.INSTANCE), Lists.transform(points, PointToRate.INSTANCE));
                }
            });
        }
    }

    private void initChart() {
        UpdateSuspender.using(mChartSurface, new Runnable() {
            @Override
            public synchronized void run() {
                CategoryDateAxis xAxis = SciChartBuilder.instance().newCategoryDateAxis()
                        .withBarTimeFrame(1)
                        .withDrawMinorGridLines(false)
                        .build();

                NumericAxis yAxis = SciChartBuilder.instance().newNumericAxis().
                        withGrowBy(0, 0.1).
                        withAutoRangeMode(AutoRange.Always).build();

                IRenderableSeries mRenderableSeries = SciChartBuilder.instance().newMountainSeries()
                        .withDataSeries(mDataSeries)
                        .withXAxisId(xAxis.getAxisId())
                        .withYAxisId(yAxis.getAxisId())
                        .withStrokeStyle(ColorUtil.Blue)
                        .withAreaFillColor(ColorUtil.DarkBlue)
                        .build();

                Collections.addAll(mChartSurface.getXAxes(), xAxis);
                Collections.addAll(mChartSurface.getYAxes(), yAxis);
                Collections.addAll(mChartSurface.getRenderableSeries(), mRenderableSeries);
                Collections.addAll(mChartSurface.getAnnotations(), mLineAnnotation);
                Collections.addAll(mChartSurface.getChartModifiers(), SciChartBuilder.instance().newModifierGroupWithDefaultModifiers().build());
            }
        });
    }

    private enum PointToDate implements Function<Point, Date> {
        INSTANCE;

        @Override
        public Date apply(Point point) {
            return point.getDate();
        }
    }

    private enum PointToRate implements Function<Point, Double> {
        INSTANCE;

        @Override
        public Double apply(Point point) {
            return point.getRate();
        }
    }
}