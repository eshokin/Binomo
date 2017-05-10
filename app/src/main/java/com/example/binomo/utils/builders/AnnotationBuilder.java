//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// AnnotationBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.scichart.charting.XyDirection;
import com.scichart.charting.visuals.annotations.AnchorPointAnnotation;
import com.scichart.charting.visuals.annotations.AnnotationBase;
import com.scichart.charting.visuals.annotations.AnnotationCoordinateMode;
import com.scichart.charting.visuals.annotations.AnnotationSurfaceEnum;
import com.scichart.charting.visuals.annotations.AxisMarkerAnnotation;
import com.scichart.charting.visuals.annotations.BoxAnnotation;
import com.scichart.charting.visuals.annotations.CustomAnnotation;
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint;
import com.scichart.charting.visuals.annotations.HorizontalLineAnnotation;
import com.scichart.charting.visuals.annotations.LineAnnotation;
import com.scichart.charting.visuals.annotations.LineAnnotationBase;
import com.scichart.charting.visuals.annotations.LineArrowAnnotation;
import com.scichart.charting.visuals.annotations.TextAnnotation;
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint;
import com.scichart.charting.visuals.annotations.VerticalLineAnnotation;
import com.scichart.drawing.common.FontStyle;
import com.scichart.drawing.common.PenStyle;

public abstract class AnnotationBuilder<TAnnotation extends AnnotationBase, TBuilder extends AnnotationBuilder<TAnnotation, TBuilder>> {
    protected final TAnnotation annotation;

    AnnotationBuilder(TAnnotation annotation){
        this.annotation = annotation;
    }

    public TAnnotation build() { return annotation; }

    protected abstract TBuilder getThis();

    public TBuilder withX1(Comparable x1) {
        this.annotation.setX1(x1);
        return getThis();
    }

    public TBuilder withY1(Comparable y1) {
        this.annotation.setY1(y1);
        return getThis();
    }

    public TBuilder withX2(Comparable x2) {
        this.annotation.setX2(x2);
        return getThis();
    }

    public TBuilder withY2(Comparable y2) {
        this.annotation.setY2(y2);
        return getThis();
    }

    public TBuilder withPosition(Comparable x1, Comparable y1){
        return getThis().withX1(x1).withY1(y1);
    }

    public TBuilder withPosition(Comparable x1, Comparable y1, Comparable x2, Comparable y2){
        return getThis().withX1(x1).withY1(y1).withX2(x2).withY2(y2);
    }

    public TBuilder withXAxisId(String xAxisId){
        this.annotation.setXAxisId(xAxisId);
        return getThis();
    }

    public TBuilder withYAxisId(String yAxisId){
        this.annotation.setYAxisId(yAxisId);
        return getThis();
    }

    public TBuilder withIsEditable(boolean isEditable){
        this.annotation.setEditable(isEditable);
        return getThis();
    }

    public TBuilder withIsHidden(boolean isHidden){
        this.annotation.setIsHidden(isHidden);
        return getThis();
    }

    public TBuilder withDragDirections(XyDirection dragDirections){
        this.annotation.setDragDirections(dragDirections);
        return getThis();
    }

    public TBuilder withResizeDirections(XyDirection resizeDirections){
        this.annotation.setResizeDirections(resizeDirections);
        return getThis();
    }

    public TBuilder withAnnotationSurface(AnnotationSurfaceEnum annotationSurface){
        this.annotation.setAnnotationsSurface(annotationSurface);
        return getThis();
    }

    public TBuilder withCoordinateMode(AnnotationCoordinateMode coordinateMode){
        this.annotation.setCoordinateMode(coordinateMode);
        return getThis();
    }

    public TBuilder withBackgroundColor(int color){
        this.annotation.setBackgroundColor(color);
        return getThis();
    }

    public TBuilder withBackgroundDrawableId(@DrawableRes int drawableResId){
        this.annotation.setBackgroundResource(drawableResId);
        return getThis();
    }

    protected static abstract class LineAnnotationBuilderBase<TAnnotation extends LineAnnotationBase, TBuilder extends LineAnnotationBuilderBase<TAnnotation, TBuilder>> extends AnnotationBuilder<TAnnotation, TBuilder> {
        LineAnnotationBuilderBase(TAnnotation annotation) {
            super(annotation);
        }

        public TBuilder withStroke(PenStyle stroke){
            this.annotation.setStroke(stroke);
            return getThis();
        }

        public TBuilder withStroke(float thickness, int color) {
            return this.withStroke(new PenStyle.Builder(this.annotation.getContext()).withThickness(thickness).withColor(color).build());
        }
    }

    public static class LineAnnotationBuilder extends LineAnnotationBuilderBase<LineAnnotation, LineAnnotationBuilder>{

        LineAnnotationBuilder(Context context) {
            super(new LineAnnotation(context));
        }

        @Override
        protected LineAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class LineArrowAnnotationBuilder extends LineAnnotationBuilderBase<LineArrowAnnotation, LineArrowAnnotationBuilder>{

        LineArrowAnnotationBuilder(Context context) {
            super(new LineArrowAnnotation(context));
        }

        public LineArrowAnnotationBuilder withArrowHeadWidth(float headWidth) {
            final float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, headWidth, annotation.getContext().getResources().getDisplayMetrics());
            this.annotation.setHeadWidth(width);
            return getThis();
        }

        public LineArrowAnnotationBuilder withArrowHeadLength(float headLength) {
            final float length = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, headLength, annotation.getContext().getResources().getDisplayMetrics());
            this.annotation.setHeadLength(length);
            return getThis();
        }

        @Override
        protected LineArrowAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class HorizontalLineAnnotationBuilder extends LineAnnotationBuilderBase<HorizontalLineAnnotation, HorizontalLineAnnotationBuilder> {

        HorizontalLineAnnotationBuilder(Context context) {
            super(new HorizontalLineAnnotation(context));
        }

        public HorizontalLineAnnotationBuilder withYValue(Comparable yValue) {
            this.annotation.setY1(yValue);
            return this;
        }

        public HorizontalLineAnnotationBuilder withHorizontalGravity(int horizontalGravity) {
            this.annotation.setHorizontalGravity(horizontalGravity);
            return this;
        }

        @Override
        protected HorizontalLineAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class VerticalLineAnnotationBuilder extends LineAnnotationBuilderBase<VerticalLineAnnotation, VerticalLineAnnotationBuilder> {

        public VerticalLineAnnotationBuilder(Context context) {
            super(new VerticalLineAnnotation(context));
        }

        public VerticalLineAnnotationBuilder withXValue(Comparable xValue) {
            this.annotation.setX1(xValue);
            return this;
        }

        public VerticalLineAnnotationBuilder withVerticalGravity(int verticalGravity) {
            this.annotation.setVerticalGravity(verticalGravity);
            return this;
        }

        @Override
        protected VerticalLineAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class BoxAnnotationBuilder extends AnnotationBuilder<BoxAnnotation, BoxAnnotationBuilder>{

        BoxAnnotationBuilder(Context context) {
            super(new BoxAnnotation(context));
        }

        @Override
        protected BoxAnnotationBuilder getThis() {
            return this;
        }
    }

    public abstract static class AnchorPointAnnotationBuilder<TAnnotation extends AnchorPointAnnotation, TBuilder extends AnchorPointAnnotationBuilder<TAnnotation, TBuilder>> extends AnnotationBuilder<TAnnotation, TBuilder>{

        AnchorPointAnnotationBuilder(TAnnotation annotation) {
            super(annotation);
        }

        public TBuilder withHorizontalAnchorPoint(HorizontalAnchorPoint horizontalAnchorPoint){
            this.annotation.setHorizontalAnchorPoint(horizontalAnchorPoint);
            return getThis();
        }

        public TBuilder withVerticalAnchorPoint(VerticalAnchorPoint verticalAnchorPoint){
            this.annotation.setVerticalAnchorPoint(verticalAnchorPoint);
            return getThis();
        }
    }

    public static class TextAnnotationBuilder extends AnchorPointAnnotationBuilder<TextAnnotation, TextAnnotationBuilder>{

        TextAnnotationBuilder(Context context) {
            super(new TextAnnotation(context));
        }

        public TextAnnotationBuilder withText(String text){
            this.annotation.setText(text);
            return getThis();
        }

        public TextAnnotationBuilder withFontStyle(FontStyle fontStyle){
            this.annotation.setFontStyle(fontStyle);
            return getThis();
        }

        public TextAnnotationBuilder withFontStyle(Typeface typeface, float textSize, int color){
            return getThis().withFontStyle(
                    new FontStyle.Builder(this.annotation.getContext())
                            .withTypeface(typeface)
                            .withTextSize(textSize, TypedValue.COMPLEX_UNIT_SP)
                            .withTextColor(color)
                            .build());
        }

        public TextAnnotationBuilder withFontStyle(float textSize, int color){
            return getThis().withFontStyle(Typeface.DEFAULT, textSize, color);
        }

        public TextAnnotationBuilder withTextGravity(int textGravity) {
            this.annotation.setTextGravity(textGravity);
            return getThis();
        }

        public TextAnnotationBuilder withCanEditText(boolean canEditText) {
            this.annotation.setCanEditText(canEditText);
            return getThis();
        }

        public TextAnnotationBuilder withPadding(int left, int top, int right, int bottom){
            final DisplayMetrics displayMetrics = annotation.getContext().getResources().getDisplayMetrics();

            left = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, left, displayMetrics));
            top = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, top, displayMetrics));
            right = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, right, displayMetrics));
            bottom = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, bottom, displayMetrics));

            this.annotation.setPadding(left, top, right, bottom);

            return getThis();
        }

        public TextAnnotationBuilder withPadding(int padding) {
            return this.withPadding(padding, padding, padding, padding);
        }

        public TextAnnotationBuilder withRotationAngle(float rotationAngle) {
            this.annotation.setRotationAngle(rotationAngle);
            return getThis();
        }

        @Override
        protected TextAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class CustomAnnotationBuilder extends AnchorPointAnnotationBuilder<CustomAnnotation, CustomAnnotationBuilder>{

        CustomAnnotationBuilder(Context context) {
            super(new CustomAnnotation(context));
        }

        public CustomAnnotationBuilder withContent(@LayoutRes int contentId){
            this.annotation.setContentId(contentId);
            return getThis();
        }

        public CustomAnnotationBuilder withContent(View content){
            this.annotation.setContentView(content);
            return getThis();
        }

        @Override
        protected CustomAnnotationBuilder getThis() {
            return this;
        }
    }

    public static class AxisMarkerAnnotationBuilder extends AnchorPointAnnotationBuilder<AxisMarkerAnnotation, AxisMarkerAnnotationBuilder> {

        AxisMarkerAnnotationBuilder(Context context) {
            super(new AxisMarkerAnnotation(context));
        }

        public AxisMarkerAnnotationBuilder withFormattedValue(String formattedValue){
            this.annotation.setFormattedValue(formattedValue);
            return getThis();
        }

        @Override
        protected AxisMarkerAnnotationBuilder getThis() {
            return this;
        }
    }
}
