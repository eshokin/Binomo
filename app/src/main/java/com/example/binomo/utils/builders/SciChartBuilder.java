//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// SciChartBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import android.content.Context;
import android.util.DisplayMetrics;

import com.scichart.charting.visuals.pointmarkers.IPointMarker;
import com.scichart.drawing.common.FontStyle;
import com.scichart.drawing.common.PenStyle;
import com.example.binomo.utils.builders.AnnotationBuilder.AxisMarkerAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.BoxAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.CustomAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.HorizontalLineAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.LineAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.LineArrowAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.TextAnnotationBuilder;
import com.example.binomo.utils.builders.AnnotationBuilder.VerticalLineAnnotationBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastBandRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastCandlestickRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastColumnRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastErrorBarsRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastFixedErrorBarsRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastImpulseRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastLineRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastMountainRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastOhlcRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.FastUniformHeatmapRenderableSeriesBuilder;
import com.example.binomo.utils.builders.RenderableSeriesBuilder.XyScatterRenderableSeriesBuilder;

public final class SciChartBuilder {
    private final DisplayMetrics displayMetrics;
    private final Context context;

    private static SciChartBuilder INSTANCE;

    public static void init(Context context) {
        INSTANCE = new SciChartBuilder(context);
    }

    public static void dispose() {
        INSTANCE = null;
    }

    public static SciChartBuilder instance() {
        return INSTANCE;
    }

    private SciChartBuilder(Context context) {
        this.context = context;
        this.displayMetrics = context.getResources().getDisplayMetrics();
    }

    public PenStyle.Builder newPen() {
        return new PenStyle.Builder(displayMetrics);
    }

    public FontStyle.Builder newFont() {
        return new FontStyle.Builder(displayMetrics);
    }


    public <T extends IPointMarker> PointMarkerBuilder<T> newPointMarker(T pointMarker) {
        return new PointMarkerBuilder<>(pointMarker, displayMetrics);
    }


    public FastLineRenderableSeriesBuilder newLineSeries() {
        return new FastLineRenderableSeriesBuilder(displayMetrics);
    }

    public FastMountainRenderableSeriesBuilder newMountainSeries() {
        return new FastMountainRenderableSeriesBuilder(displayMetrics);
    }

    public FastColumnRenderableSeriesBuilder newColumnSeries() {
        return new FastColumnRenderableSeriesBuilder(displayMetrics);
    }

    public FastImpulseRenderableSeriesBuilder newImpulseSeries() {
        return new FastImpulseRenderableSeriesBuilder(displayMetrics);
    }

    public XyScatterRenderableSeriesBuilder newScatterSeries() {
        return new XyScatterRenderableSeriesBuilder(displayMetrics);
    }

    public FastBandRenderableSeriesBuilder newBandSeries() {
        return new FastBandRenderableSeriesBuilder(displayMetrics);
    }

    public FastCandlestickRenderableSeriesBuilder newCandlestickSeries() {
        return new FastCandlestickRenderableSeriesBuilder(displayMetrics);
    }

    public FastOhlcRenderableSeriesBuilder newOhlcSeries() {
        return new FastOhlcRenderableSeriesBuilder(displayMetrics);
    }

    public FastErrorBarsRenderableSeriesBuilder newErrorBarsSeries() {
        return new FastErrorBarsRenderableSeriesBuilder(displayMetrics);
    }

    public FastFixedErrorBarsRenderableSeriesBuilder newFixedErrorBarsSeries() {
        return new FastFixedErrorBarsRenderableSeriesBuilder(displayMetrics);
    }

    public FastUniformHeatmapRenderableSeriesBuilder newUniformHeatmap() {
        return new FastUniformHeatmapRenderableSeriesBuilder(displayMetrics);
    }


    public AxisBuilder.NumericAxisBuilder newNumericAxis() {
        return new AxisBuilder.NumericAxisBuilder(context);
    }

    public AxisBuilder.DateAxisBuilder newDateAxis() {
        return new AxisBuilder.DateAxisBuilder(context);
    }

    public AxisBuilder.CategoryDateAxisBuilder newCategoryDateAxis() {
        return new AxisBuilder.CategoryDateAxisBuilder(context);
    }


    public <TX extends Comparable<TX>, TY extends Comparable<TY>> DataSeriesBuilder.XyDataSeriesBuilder<TX, TY> newXyDataSeries(Class<TX> xType, Class<TY> yType) {
        return new DataSeriesBuilder.XyDataSeriesBuilder<>(xType, yType);
    }

    public <TX extends Comparable<TX>, TY extends Comparable<TY>> DataSeriesBuilder.OhlcDataSeriesBuilder<TX, TY> newOhlcDataSeries(Class<TX> xType, Class<TY> yType) {
        return new DataSeriesBuilder.OhlcDataSeriesBuilder<>(xType, yType);
    }

    public <TX extends Comparable<TX>, TY extends Comparable<TY>> DataSeriesBuilder.XyyDataSeriesBuilder<TX, TY> newXyyDataSeries(Class<TX> xType, Class<TY> yType) {
        return new DataSeriesBuilder.XyyDataSeriesBuilder<>(xType, yType);
    }


    public ModifierGroupBuilder newModifierGroup() {
        return new ModifierGroupBuilder(context);
    }

    public ModifierGroupBuilder newModifierGroupWithDefaultModifiers() {
        return newModifierGroup()
                .withPinchZoomModifier().build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .withZoomExtentsModifier().build();
    }

    public TextAnnotationBuilder newTextAnnotation() {
        return new TextAnnotationBuilder(context);
    }

    public BoxAnnotationBuilder newBoxAnnotation() {
        return new BoxAnnotationBuilder(context);
    }

    public LineAnnotationBuilder newLineAnnotation() {
        return new LineAnnotationBuilder(context);
    }

    public LineArrowAnnotationBuilder newLineArrowAnnotation() {
        return new LineArrowAnnotationBuilder(context);
    }

    public CustomAnnotationBuilder newCustomAnnotation() {
        return new CustomAnnotationBuilder(context);
    }

    public AxisMarkerAnnotationBuilder newAxisMarkerAnnotation() {
        return new AxisMarkerAnnotationBuilder(context);
    }

    public HorizontalLineAnnotationBuilder newHorizontalLineAnnotation() {
        return new HorizontalLineAnnotationBuilder(context);
    }

    public VerticalLineAnnotationBuilder newVerticalLineAnnotation() {
        return new VerticalLineAnnotationBuilder(context);
    }
}
