//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// RenderableSeriesBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import android.util.DisplayMetrics;

import com.scichart.charting.model.dataSeries.IDataSeries;
import com.scichart.charting.visuals.renderableSeries.paletteProviders.IPaletteProvider;
import com.scichart.charting.visuals.pointmarkers.IPointMarker;
import com.scichart.charting.visuals.renderableSeries.BaseRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.ColorMap;
import com.scichart.charting.visuals.renderableSeries.ErrorDirection;
import com.scichart.charting.visuals.renderableSeries.ErrorMode;
import com.scichart.charting.visuals.renderableSeries.ErrorType;
import com.scichart.charting.visuals.renderableSeries.FastBandRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastCandlestickRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastColumnRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastErrorBarsRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastFixedErrorBarsRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastImpulseRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastLineRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastMountainRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastOhlcRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.FastUniformHeatmapRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.LineDrawMode;
import com.scichart.charting.visuals.renderableSeries.XyScatterRenderableSeries;
import com.scichart.data.numerics.ResamplingMode;
import com.scichart.drawing.common.FontStyle;
import com.scichart.drawing.common.LinearGradientBrushStyle;
import com.scichart.drawing.common.PenStyle;
import com.scichart.drawing.common.SolidBrushStyle;
import com.scichart.drawing.common.TileMode;

public abstract class RenderableSeriesBuilder<TRenderableSeries extends BaseRenderableSeries, TBuilder extends RenderableSeriesBuilder<TRenderableSeries, TBuilder>> {
    public static final float DEFAULT_STROKE_THICKNESS = 2F;
    public static final boolean DEFAULT_ANTIALIASING_MODE = false;

    protected final TRenderableSeries renderableSeries;
    protected final DisplayMetrics displayMetrics;

    RenderableSeriesBuilder(TRenderableSeries renderableSeries, DisplayMetrics displayMetrics) {
        this.renderableSeries = renderableSeries;
        this.displayMetrics = displayMetrics;
    }

    public TRenderableSeries build() {
        return renderableSeries;
    }

    protected abstract TBuilder getThis();

    public TBuilder withXAxisId(String id) {
        renderableSeries.setXAxisId(id);
        return getThis();
    }

    public TBuilder withYAxisId(String id) {
        renderableSeries.setYAxisId(id);
        return getThis();
    }

    public TBuilder withDataSeries(IDataSeries dataSeries) {
        renderableSeries.setDataSeries(dataSeries);
        return getThis();
    }

    public TBuilder withStrokeStyle(int seriesColor, float strokeThickness, boolean antiAliasing) {
        renderableSeries.setStrokeStyle(new PenStyle.Builder(displayMetrics).withThickness(strokeThickness).withColor(seriesColor).withAntiAliasing(antiAliasing).build());
        return getThis();
    }

    public TBuilder withStrokeStyle(int seriesColor, float strokeThickness) {
        return getThis().withStrokeStyle(seriesColor, strokeThickness, DEFAULT_ANTIALIASING_MODE);
    }

    public TBuilder withStrokeStyle(int seriesColor) {
        return getThis().withStrokeStyle(seriesColor, DEFAULT_STROKE_THICKNESS);
    }

    public TBuilder withStrokeStyle(PenStyle strokeStyle) {
        renderableSeries.setStrokeStyle(strokeStyle);
        return getThis();
    }

    public TBuilder withPointMarker(IPointMarker pointMarker) {
        renderableSeries.setPointMarker(pointMarker);
        return getThis();
    }

    public TBuilder withDrawLineMode(LineDrawMode lineDrawMode) {
        renderableSeries.setDrawNaNAs(lineDrawMode);
        return getThis();
    }

    public TBuilder withIsVisible(boolean isVisible) {
        renderableSeries.setIsVisible(isVisible);
        return getThis();
    }

    public TBuilder withResamplingMode(ResamplingMode resamplingMode) {
        renderableSeries.setResamplingMode(resamplingMode);
        return getThis();
    }

    public TBuilder withZeroLine(double zeroLine) {
        renderableSeries.setZeroLineY(zeroLine);
        return getThis();
    }

    public TBuilder withPaletteProvider(IPaletteProvider paletteProvider) {
        renderableSeries.setPaletteProvider(paletteProvider);
        return getThis();
    }

    public static class FastLineRenderableSeriesBuilder extends RenderableSeriesBuilder<FastLineRenderableSeries, FastLineRenderableSeriesBuilder> {
        FastLineRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastLineRenderableSeries(), displayMetrics);
        }

        public FastLineRenderableSeriesBuilder withIsDigitalLine(boolean isDigitalLine) {
            this.renderableSeries.setIsDigitalLine(isDigitalLine);
            return this;
        }

        @Override
        protected FastLineRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastColumnRenderableSeriesBuilder extends RenderableSeriesBuilder<FastColumnRenderableSeries, FastColumnRenderableSeriesBuilder> {
        FastColumnRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastColumnRenderableSeries(), displayMetrics);
        }

        public FastColumnRenderableSeriesBuilder withFillColor(int fillColor) {
            this.renderableSeries.setFillBrushStyle(new SolidBrushStyle(fillColor));
            return this;
        }

        public FastColumnRenderableSeriesBuilder withLinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setFillBrushStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }

        public FastColumnRenderableSeriesBuilder withDataPointWidth(double dataPointWidth) {
            this.renderableSeries.setDataPointWidth(dataPointWidth);
            return this;
        }

        @Override
        protected FastColumnRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastMountainRenderableSeriesBuilder extends RenderableSeriesBuilder<FastMountainRenderableSeries, FastMountainRenderableSeriesBuilder> {
        FastMountainRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastMountainRenderableSeries(), displayMetrics);
        }

        public FastMountainRenderableSeriesBuilder withIsDigitalLine(boolean isDigitalLine) {
            this.renderableSeries.setIsDigitalLine(isDigitalLine);
            return this;
        }

        public FastMountainRenderableSeriesBuilder withAreaFillColor(int areaFillColor) {
            this.renderableSeries.setAreaStyle(new SolidBrushStyle(areaFillColor));
            return this;
        }

        public FastMountainRenderableSeriesBuilder withAreaFillLinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setAreaStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }

        @Override
        protected FastMountainRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastImpulseRenderableSeriesBuilder extends RenderableSeriesBuilder<FastImpulseRenderableSeries, FastImpulseRenderableSeriesBuilder> {
        FastImpulseRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastImpulseRenderableSeries(), displayMetrics);
        }

        @Override
        protected FastImpulseRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class XyScatterRenderableSeriesBuilder extends RenderableSeriesBuilder<XyScatterRenderableSeries, XyScatterRenderableSeriesBuilder> {
        XyScatterRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new XyScatterRenderableSeries(), displayMetrics);
        }

        @Override
        protected XyScatterRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastBandRenderableSeriesBuilder extends RenderableSeriesBuilder<FastBandRenderableSeries, FastBandRenderableSeriesBuilder> {

        FastBandRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastBandRenderableSeries(), displayMetrics);
        }

        public FastBandRenderableSeriesBuilder withStrokeY1Style(int seriesColor, float strokeThickness, boolean antiAliasing) {
            renderableSeries.setStrokeY1Style(new PenStyle.Builder(displayMetrics).withThickness(strokeThickness).withColor(seriesColor).withAntiAliasing(antiAliasing).build());
            return this;
        }

        public FastBandRenderableSeriesBuilder withStrokeY1Style(int seriesColor, float strokeThickness) {
            return this.withStrokeY1Style(seriesColor, strokeThickness, DEFAULT_ANTIALIASING_MODE);
        }

        public FastBandRenderableSeriesBuilder withStrokeY1Style(int seriesColor) {
            return this.withStrokeY1Style(seriesColor, DEFAULT_STROKE_THICKNESS);
        }

        public FastBandRenderableSeriesBuilder withStrokeY1Style(PenStyle strokeStyle) {
            renderableSeries.setStrokeY1Style(strokeStyle);
            return this;
        }

        public FastBandRenderableSeriesBuilder withFillColor(int fillColor) {
            this.renderableSeries.setFillBrushStyle(new SolidBrushStyle(fillColor));
            return this;
        }

        public FastBandRenderableSeriesBuilder withLinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setFillBrushStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }

        public FastBandRenderableSeriesBuilder withFillY1Color(int fillColor) {
            this.renderableSeries.setFillY1BrushStyle(new SolidBrushStyle(fillColor));
            return this;
        }

        public FastBandRenderableSeriesBuilder withY1LinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setFillY1BrushStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }

        public FastBandRenderableSeriesBuilder withIsDigitalLine(boolean isDigitalLine) {
            this.renderableSeries.setIsDigitalLine(isDigitalLine);
            return this;
        }

        @Override
        protected FastBandRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastOhlcRenderableSeriesBuilder extends RenderableSeriesBuilder<FastOhlcRenderableSeries, FastOhlcRenderableSeriesBuilder> {
        FastOhlcRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastOhlcRenderableSeries(), displayMetrics);
        }

        public FastOhlcRenderableSeriesBuilder withStrokeUp(int strokeUpColor) {
            return this.withStrokeUp(strokeUpColor, DEFAULT_STROKE_THICKNESS);
        }

        public FastOhlcRenderableSeriesBuilder withStrokeUp(int strokeUpColor, float strokeThickness) {
            this.renderableSeries.setStrokeUpStyle(new PenStyle.Builder(displayMetrics).withColor(strokeUpColor).withThickness(strokeThickness).build());
            return this;
        }

        public FastOhlcRenderableSeriesBuilder withStrokeDown(int strokeDownColor) {
            return this.withStrokeDown(strokeDownColor, DEFAULT_STROKE_THICKNESS);
        }

        public FastOhlcRenderableSeriesBuilder withStrokeDown(int strokeDownColor, float strokeThickness) {
            this.renderableSeries.setStrokeDownStyle(new PenStyle.Builder(displayMetrics).withColor(strokeDownColor).withThickness(strokeThickness).build());
            return this;
        }

        @Override
        protected FastOhlcRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastCandlestickRenderableSeriesBuilder extends RenderableSeriesBuilder<FastCandlestickRenderableSeries, FastCandlestickRenderableSeriesBuilder> {
        FastCandlestickRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastCandlestickRenderableSeries(), displayMetrics);
        }

        public FastCandlestickRenderableSeriesBuilder withStrokeUp(int strokeUpColor) {
            this.renderableSeries.setStrokeUpStyle(new PenStyle.Builder(displayMetrics).withColor(strokeUpColor).build());
            return this;
        }

        public FastCandlestickRenderableSeriesBuilder withStrokeDown(int strokeDownColor) {
            this.renderableSeries.setStrokeDownStyle(new PenStyle.Builder(displayMetrics).withColor(strokeDownColor).build());
            return this;
        }

        public FastCandlestickRenderableSeriesBuilder withFillUpColor(int fillUpColor) {
            this.renderableSeries.setFillUpBrushStyle(new SolidBrushStyle(fillUpColor));
            return this;
        }

        public FastCandlestickRenderableSeriesBuilder withFillDownColor(int fillDownColor) {
            this.renderableSeries.setFillDownBrushStyle(new SolidBrushStyle(fillDownColor));
            return this;
        }

        public FastCandlestickRenderableSeriesBuilder withFillUpLinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setFillUpBrushStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }


        public FastCandlestickRenderableSeriesBuilder withFillDownLinearGradientColors(int startColor, int endColor) {
            this.renderableSeries.setFillDownBrushStyle(new LinearGradientBrushStyle(0, 0, 0, 1, startColor, endColor, TileMode.CLAMP));
            return this;
        }

        @Override
        protected FastCandlestickRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastErrorBarsRenderableSeriesBuilder extends RenderableSeriesBuilder<FastErrorBarsRenderableSeries, FastErrorBarsRenderableSeriesBuilder> {
        FastErrorBarsRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastErrorBarsRenderableSeries(), displayMetrics);
        }

        @Override
        protected FastErrorBarsRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastFixedErrorBarsRenderableSeriesBuilder extends RenderableSeriesBuilder<FastFixedErrorBarsRenderableSeries, FastFixedErrorBarsRenderableSeriesBuilder> {
        FastFixedErrorBarsRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastFixedErrorBarsRenderableSeries(), displayMetrics);
        }

        public FastFixedErrorBarsRenderableSeriesBuilder withHighError(double highError) {
            this.renderableSeries.setErrorHigh(highError);
            return this;
        }

        public FastFixedErrorBarsRenderableSeriesBuilder withLowError(double lowError) {
            this.renderableSeries.setErrorLow(lowError);
            return this;
        }

        public FastFixedErrorBarsRenderableSeriesBuilder withErrorDirection(ErrorDirection errorDirection) {
            this.renderableSeries.setErrorDirection(errorDirection);
            return this;
        }

        public FastFixedErrorBarsRenderableSeriesBuilder withErrorType(ErrorType errorType) {
            this.renderableSeries.setErrorType(errorType);
            return this;
        }

        public FastFixedErrorBarsRenderableSeriesBuilder withErrorMode(ErrorMode errorMode) {
            this.renderableSeries.setErrorMode(errorMode);
            return this;
        }

        @Override
        protected FastFixedErrorBarsRenderableSeriesBuilder getThis() {
            return this;
        }
    }

    public static class FastUniformHeatmapRenderableSeriesBuilder extends RenderableSeriesBuilder<FastUniformHeatmapRenderableSeries, FastUniformHeatmapRenderableSeriesBuilder> {
        FastUniformHeatmapRenderableSeriesBuilder(DisplayMetrics displayMetrics) {
            super(new FastUniformHeatmapRenderableSeries(), displayMetrics);
        }

        public FastUniformHeatmapRenderableSeriesBuilder withColorMap(ColorMap colorMap) {
            this.renderableSeries.setColorMap(colorMap);
            return this;
        }

        public FastUniformHeatmapRenderableSeriesBuilder withMinimum(double minimum) {
            this.renderableSeries.setMinimum(minimum);
            return this;
        }

        public FastUniformHeatmapRenderableSeriesBuilder withMaximum(double maximum) {
            this.renderableSeries.setMaximum(maximum);
            return this;
        }

        public FastUniformHeatmapRenderableSeriesBuilder withDrawTextInCell(boolean drawTextInCell) {
            this.renderableSeries.setDrawTextInCell(drawTextInCell);
            return this;
        }

        public FastUniformHeatmapRenderableSeriesBuilder withCellTextStyle(FontStyle cellTextStyle) {
            this.renderableSeries.setCellTextStyle(cellTextStyle);
            return this;
        }

        @Override
        protected FastUniformHeatmapRenderableSeriesBuilder getThis() {
            return this;
        }
    }
}
