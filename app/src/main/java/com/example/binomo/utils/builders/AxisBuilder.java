//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// AxisBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
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
import android.util.TypedValue;

import com.scichart.charting.visuals.axes.AutoRange;
import com.scichart.charting.visuals.axes.AxisAlignment;
import com.scichart.charting.visuals.axes.AxisBase;
import com.scichart.charting.visuals.axes.AxisTitleOrientation;
import com.scichart.charting.visuals.axes.AxisTitlePlacement;
import com.scichart.charting.visuals.axes.CategoryDateAxis;
import com.scichart.charting.visuals.axes.DateAxis;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.core.utility.DateIntervalUtil;
import com.scichart.data.model.DateRange;
import com.scichart.data.model.DoubleRange;
import com.scichart.data.model.IRange;
import com.scichart.drawing.common.FontStyle;

import java.util.Date;

public abstract class AxisBuilder<TAxis extends AxisBase<?,?>, TBuilder extends AxisBuilder<TAxis, TBuilder>> {

    protected final TAxis axis;
    private final DisplayMetrics displayMetrics;

    AxisBuilder(TAxis axis, DisplayMetrics displayMetrics) {
        this.axis = axis;
        this.displayMetrics = displayMetrics;
    }

    public TAxis build() {
        return axis;
    }

    protected abstract TBuilder getThis();

    public TBuilder withVisibility(int visibility) {
        axis.setVisibility(visibility);
        return getThis();
    }

    public TBuilder withGrowBy(DoubleRange growBy) {
        axis.setGrowBy(growBy);
        return getThis();
    }

    public TBuilder withGrowBy(double min, double max) {
        axis.setGrowBy(new DoubleRange(min, max));
        return getThis();
    }

    public TBuilder withVisibleRange(IRange visibleRange) {
        axis.setVisibleRange(visibleRange);
        return getThis();
    }

    public TBuilder withVisibleRangeLimit(IRange visibleRange) {
        axis.setVisibleRangeLimit(visibleRange);
        return getThis();
    }

    public TBuilder withFlipCoordinates(boolean flipCoordinates) {
        axis.setFlipCoordinates(flipCoordinates);
        return getThis();
    }

    public TBuilder withAxisAlignment(AxisAlignment axisAlignment) {
        axis.setAxisAlignment(axisAlignment);
        return getThis();
    }

    public TBuilder withIsCenterAxis(boolean isCenterAxis) {
        axis.setIsCenterAxis(isCenterAxis);
        return getThis();
    }

    public TBuilder withAutoRangeMode(AutoRange autoRangeMode) {
        axis.setAutoRange(autoRangeMode);
        return getThis();
    }

    public TBuilder withDrawMajorBands(boolean drawMajorBands) {
        axis.setDrawMajorBands(drawMajorBands);
        return getThis();
    }

    public TBuilder withDrawLabels(boolean drawLabels) {
        axis.setDrawLabels(drawLabels);
        return getThis();
    }

    public TBuilder withDrawMinorTicks(boolean drawMinorTicks) {
        axis.setDrawMinorTicks(drawMinorTicks);
        return getThis();
    }

    public TBuilder withDrawMajorTicks(boolean drawMajorTicks) {
        axis.setDrawMajorTicks(drawMajorTicks);
        return getThis();
    }

    public TBuilder withDrawMajorGridLines(boolean drawMajorGridLines) {
        axis.setDrawMajorGridLines(drawMajorGridLines);
        return getThis();
    }

    public TBuilder withDrawMinorGridLines(boolean drawMinorGridLines) {
        axis.setDrawMinorGridLines(drawMinorGridLines);
        return getThis();
    }

    public TBuilder withAxisId(String axisId) {
        axis.setAxisId(axisId);
        return getThis();
    }

    public TBuilder withAxisTitle(String title) {
        axis.setAxisTitle(title);
        return getThis();
    }

    public TBuilder withAxisTitleStyle(FontStyle axisTitleStyle) {
        axis.setTitleStyle(axisTitleStyle);
        return getThis();
    }

    public TBuilder withAxisTitleOrientation(AxisTitleOrientation axisTitleOrientation) {
        axis.setAxisTitleOrientation(axisTitleOrientation);
        return getThis();
    }

    public TBuilder withAxitTitleGravity(int axisTitleGravity){
        axis.setAxisTitleGravity(axisTitleGravity);
        return getThis();
    }

    public TBuilder withAxisTitlePlacement(AxisTitlePlacement axisTitlePlacement) {
        axis.setAxisTitlePlacement(axisTitlePlacement);
        return getThis();
    }

    public TBuilder withTickLabelStyle(FontStyle tickLabelStyle) {
        axis.setTickLabelStyle(tickLabelStyle);
        return getThis();
    }

    public TBuilder withTextColor(int color) {
        final FontStyle tickStyle = new FontStyle.Builder(displayMetrics).withTextSize(12, TypedValue.COMPLEX_UNIT_SP).withTextColor(color).build();
        final FontStyle titleStyle = new FontStyle.Builder(displayMetrics).withTextSize(18, TypedValue.COMPLEX_UNIT_SP).withTextColor(color).build();

        return this.withAxisTitleStyle(titleStyle).withTickLabelStyle(tickStyle);
    }

    public TBuilder withTextFormatting(String textFormatting) {
        axis.setTextFormatting(textFormatting);
        return getThis();
    }

    public TBuilder withCursorTextFormating(String textFormating) {
        axis.setCursorTextFormatting(textFormating);
        return getThis();
    }

    public TBuilder withAutoFitMarginalLabels(boolean autoFitMarginalLabels) {
        axis.setAutoFitMarginalLabels(autoFitMarginalLabels);
        return getThis();
    }

    public TBuilder withIsLabelCullingEnabled(boolean isLabelCullingEnabled) {
        axis.setIsLabelCullingEnabled(isLabelCullingEnabled);
        return getThis();
    }

    public TBuilder withMinorsPerMajor(int minorsPerMajor) {
        axis.setMinorsPerMajor(minorsPerMajor);
        return getThis();
    }

    public TBuilder withMaxAutoTicks(int maxAutoTicks) {
        axis.setMaxAutoTicks(maxAutoTicks);
        return getThis();
    }

    public static class NumericAxisBuilder extends AxisBuilder<NumericAxis, NumericAxisBuilder> {

        NumericAxisBuilder(Context context) {
            super(new NumericAxis(context), context.getResources().getDisplayMetrics());

            axis.setMinimalZoomConstrain(0.01);
        }

        @Override
        protected NumericAxisBuilder getThis() {
            return this;
        }

        public NumericAxisBuilder withVisibleRange(double min, double max) {
            return super.withVisibleRange(new DoubleRange(min, max));
        }

        public NumericAxisBuilder withVisibleRangeLimit(double min, double max) {
            return super.withVisibleRangeLimit(new DoubleRange(min, max));
        }
    }

    public static class DateAxisBuilder extends AxisBuilder<DateAxis, DateAxisBuilder> {

        DateAxisBuilder(Context context) {
            super(new DateAxis(context), context.getResources().getDisplayMetrics());

            axis.setMinimalZoomConstrain(DateIntervalUtil.fromSeconds(1));
        }

        @Override
        protected DateAxisBuilder getThis() {
            return this;
        }


        public DateAxisBuilder withVisibleRange(Date min, Date max) {
            return super.withVisibleRange(new DateRange(min, max));
        }

        public DateAxisBuilder withVisibleRangeLimit(Date min, Date max) {
            return super.withVisibleRangeLimit(new DateRange(min, max));
        }
    }

    public static class CategoryDateAxisBuilder extends AxisBuilder<CategoryDateAxis, CategoryDateAxisBuilder> {

        public CategoryDateAxisBuilder(Context context) {
            super(new CategoryDateAxis(context), context.getResources().getDisplayMetrics());

            axis.setMinimalZoomConstrain(0.01);
        }

        @Override
        protected CategoryDateAxisBuilder getThis() {
            return this;
        }

        public CategoryDateAxisBuilder withVisibleRangeLimit(double min, double max) {
            return super.withVisibleRangeLimit(new DoubleRange(min, max));
        }

        public CategoryDateAxisBuilder withVisibleRange(double min, double max) {
            return super.withVisibleRange(new DoubleRange(min, max));
        }

        public CategoryDateAxisBuilder withBarTimeFrame(double barTimeFrame){
            axis.setBarTimeFrame(barTimeFrame);
            return getThis();
        }
    }
}