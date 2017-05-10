//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// ModifierGroupBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import android.content.Context;

import com.scichart.charting.ClipMode;
import com.scichart.charting.XyDirection;
import com.scichart.charting.modifiers.AxisDragModifierBase;
import com.scichart.charting.modifiers.ChartModifierBase;
import com.scichart.charting.modifiers.CursorModifier;
import com.scichart.charting.modifiers.IChartModifier;
import com.scichart.charting.modifiers.LegendModifier;
import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.modifiers.PinchZoomModifier;
import com.scichart.charting.modifiers.RolloverModifier;
import com.scichart.charting.modifiers.RubberBandXyZoomModifier;
import com.scichart.charting.modifiers.SourceMode;
import com.scichart.charting.modifiers.TooltipModifier;
import com.scichart.charting.modifiers.XAxisDragModifier;
import com.scichart.charting.modifiers.YAxisDragModifier;
import com.scichart.charting.modifiers.ZoomExtentsModifier;
import com.scichart.charting.modifiers.ZoomPanModifier;
import com.scichart.core.annotations.Orientation;

public class ModifierGroupBuilder {
    private ModifierGroup modifierGroup;
    private Context context;

    ModifierGroupBuilder(Context context) {
        this.context = context;
        this.modifierGroup = new ModifierGroup();
    }

    public ModifierGroup build(){
        return modifierGroup;
    }

    public ModifierGroupBuilder withReceiveHandledEvents(boolean receiveHandledEvents){
        modifierGroup.setReceiveHandledEvents(receiveHandledEvents);
        return this;
    }

    public ModifierGroupBuilder withMotionEventsGroup(String eventsGroup){
        modifierGroup.setMotionEventGroup(eventsGroup);
        return this;
    }

    public ModifierGroupBuilder withModifier(IChartModifier modifier){
        modifierGroup.getChildModifiers().add(modifier);

        return this;
    }

    public ZoomExtentsModifierBuilder withZoomExtentsModifier(){
        return new ZoomExtentsModifierBuilder();
    }

    public PinchZoomModifierBuilder withPinchZoomModifier(){
        return new PinchZoomModifierBuilder();
    }

    public ZoomPanModifierBuilder withZoomPanModifier() {
        return new ZoomPanModifierBuilder();
    }

    public XAxisDragModifierBuilder withXAxisDragModifier() { return new XAxisDragModifierBuilder(); }

    public YAxisDragModifierBuilder withYAxisDragModifier() { return new YAxisDragModifierBuilder(); }

    public CursorModifierBuilder withCursorModifier(){
        return new CursorModifierBuilder();
    }

    public RolloverModifierBuilder withRolloverModifier() {
        return new RolloverModifierBuilder();
    }

    public RubberBandXyZoomModifierBuilder withRubberBandXyZoomModifier() {
        return new RubberBandXyZoomModifierBuilder();
    }

    public TooltipModifierBuilder withTooltipModifier() { return new TooltipModifierBuilder(); }

    public LegendModifierBuilder withLegendModifier() {
        return new LegendModifierBuilder();
    }

    public abstract class ModifierBuilderBase<TModifier extends ChartModifierBase, TBuilder extends ModifierBuilderBase<TModifier, TBuilder>>{
        protected final TModifier modifier;

        protected ModifierBuilderBase(TModifier modifier) {
            this.modifier = modifier;
        }

        protected abstract TBuilder getThis();

        public TBuilder withReceiveHandledEvents(boolean receiveHandledEvents){
            modifier.setReceiveHandledEvents(receiveHandledEvents);
            return getThis();
        }

        public ModifierGroupBuilder build(){
            return ModifierGroupBuilder.this.withModifier(modifier);
        }
    }

    public class ZoomExtentsModifierBuilder extends ModifierBuilderBase<ZoomExtentsModifier, ZoomExtentsModifierBuilder>{
        private ZoomExtentsModifierBuilder() {
            super(new ZoomExtentsModifier());
        }

        @Override
        protected ZoomExtentsModifierBuilder getThis() {
            return this;
        }

        public ZoomExtentsModifierBuilder withIsAnimated(boolean isAnimated){
            modifier.setIsAnimated(isAnimated);
            return this;
        }

        public ZoomExtentsModifierBuilder withXyDirection(XyDirection xyDirection){
            modifier.setXyDirection(xyDirection);
            return this;
        }
    }

    public class PinchZoomModifierBuilder extends ModifierBuilderBase<PinchZoomModifier, PinchZoomModifierBuilder>{

        private PinchZoomModifierBuilder() {
            super(new PinchZoomModifier());
        }

        @Override
        protected PinchZoomModifierBuilder getThis() {
            return this;
        }

        public PinchZoomModifierBuilder withScaleFactor(float scaleFactor){
            modifier.setScaleFactor(scaleFactor);
            return this;
        }

        public PinchZoomModifierBuilder withXyDirection(XyDirection xyDirection){
            modifier.setXyDirection(xyDirection);
            return this;
        }
    }

    public class ZoomPanModifierBuilder extends ModifierBuilderBase<ZoomPanModifier, ZoomPanModifierBuilder>{

        private ZoomPanModifierBuilder() {
            super(new ZoomPanModifier());
        }

        @Override
        protected ZoomPanModifierBuilder getThis() {
            return this;
        }

        public ZoomPanModifierBuilder withClipModex(ClipMode clipModeX){
            modifier.setClipModeX(clipModeX);
            return this;
        }

        public ZoomPanModifierBuilder withZoomExtentsY(boolean zoomExtentsY){
            modifier.setZoomExtentsY(zoomExtentsY);
            return this;
        }

        public ZoomPanModifierBuilder withXyDirection(XyDirection xyDirection){
            modifier.setXyDirection(xyDirection);
            return this;
        }
    }

    public class XAxisDragModifierBuilder extends ModifierBuilderBase<XAxisDragModifier, XAxisDragModifierBuilder>{

        private XAxisDragModifierBuilder() {
            super(new XAxisDragModifier());
        }

        @Override
        protected XAxisDragModifierBuilder getThis() {
            return this;
        }

        public XAxisDragModifierBuilder withDragMode(AxisDragModifierBase.AxisDragMode dragMode){
            modifier.setDragMode(dragMode);
            return this;
        }

        public XAxisDragModifierBuilder withClipModex(ClipMode clipModeX){
            modifier.setClipModeX(clipModeX);
            return this;
        }
    }

    public class YAxisDragModifierBuilder extends ModifierBuilderBase<YAxisDragModifier, YAxisDragModifierBuilder>{

        private YAxisDragModifierBuilder() {
            super(new YAxisDragModifier());
        }

        public YAxisDragModifierBuilder withDragMode(AxisDragModifierBase.AxisDragMode dragMode){
            modifier.setDragMode(dragMode);
            return this;
        }

        @Override
        protected YAxisDragModifierBuilder getThis() {
            return this;
        }
    }

    public class RolloverModifierBuilder extends ModifierBuilderBase<RolloverModifier, RolloverModifierBuilder> {
        private RolloverModifierBuilder() {
            super(new RolloverModifier());
        }

        @Override
        protected RolloverModifierBuilder getThis() {
            return this;
        }
    }

    public class CursorModifierBuilder extends ModifierBuilderBase<CursorModifier, CursorModifierBuilder> {
        private CursorModifierBuilder() {
            super(new CursorModifier());
        }

        @Override
        protected CursorModifierBuilder getThis() {
            return this;
        }

        public CursorModifierBuilder withShowTooltip(boolean showTooltip){
            modifier.setShowTooltip(showTooltip);
            return this;
        }
    }

    public class TooltipModifierBuilder extends ModifierBuilderBase<TooltipModifier, TooltipModifierBuilder> {
        private TooltipModifierBuilder() {
            super(new TooltipModifier());
        }

        @Override
        protected TooltipModifierBuilder getThis() {
            return this;
        }

        public TooltipModifierBuilder withShowTooltip(boolean showTooltip){
            modifier.setShowTooltip(showTooltip);
            return this;
        }
    }

    public class RubberBandXyZoomModifierBuilder extends ModifierBuilderBase<RubberBandXyZoomModifier, RubberBandXyZoomModifierBuilder> {
        private RubberBandXyZoomModifierBuilder() {
            super(new RubberBandXyZoomModifier());
        }

        @Override
        protected RubberBandXyZoomModifierBuilder getThis() {
            return this;
        }

        public RubberBandXyZoomModifierBuilder withIsAnimated(boolean isAnimated){
            modifier.setIsAnimated(isAnimated);
            return this;
        }

        public RubberBandXyZoomModifierBuilder withIsXAxisOnly(boolean isXAxisOnly){
            modifier.setIsXAxisOnly(isXAxisOnly);
            return this;
        }

        public RubberBandXyZoomModifierBuilder withZoomExtentsY(boolean zoomExtentsY){
            modifier.setZoomExtentsY(zoomExtentsY);
            return this;
        }
    }

    public class LegendModifierBuilder extends ModifierBuilderBase<LegendModifier, LegendModifierBuilder> {
        private LegendModifierBuilder() {
            super(new LegendModifier(context));

            modifier.setShowLegend(true);
        }

        @Override
        protected LegendModifierBuilder getThis() {
            return this;
        }

        public LegendModifierBuilder withShowCheckBoxes(boolean showCheckBoxes){
            modifier.setShowCheckboxes(showCheckBoxes);
            return this;
        }

        public LegendModifierBuilder withShowSeriesMarkers(boolean showSeriesMarkers){
            modifier.setShowSeriesMarkers(showSeriesMarkers);
            return this;
        }

        public LegendModifierBuilder withGetLegendDataFor(SourceMode getLegendDataFor){
            modifier.setGetLegendDataFor(getLegendDataFor);
            return this;
        }
        public LegendModifierBuilder withOrientation(@Orientation.OrientationMode int orientation) {
            modifier.setOrientation(orientation);
            return this;
        }

        public LegendModifierBuilder withPosition(int gravity, int margin) {
            modifier.setLegendPosition(gravity, margin);
            return this;
        }
    }
}
