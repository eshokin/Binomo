//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// PointMarkerBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.scichart.charting.visuals.pointmarkers.IPointMarker;
import com.scichart.drawing.common.BrushStyle;
import com.scichart.drawing.common.PenStyle;
import com.scichart.drawing.common.SolidBrushStyle;

public class PointMarkerBuilder<T extends IPointMarker> {
    private final T pointMarker;
    private final DisplayMetrics displayMetrics;

    PointMarkerBuilder(T pointMarker, DisplayMetrics displayMetrics) {
        this.pointMarker = pointMarker;
        this.displayMetrics = displayMetrics;
    }

    public T build(){
        return pointMarker;
    }

    public PointMarkerBuilder<T> withSize(int width, int height){
        final int pixelWidth = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, displayMetrics));
        final int pixelHeight = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, displayMetrics));

        pointMarker.setSize(pixelWidth, pixelHeight);

        return this;
    }

    public PointMarkerBuilder<T> withSize(int diameter){
        final int pixelDiameter = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, diameter, displayMetrics));

        pointMarker.setSize(pixelDiameter, pixelDiameter);

        return this;
    }

    public PointMarkerBuilder<T> withStroke(int stroke, float strokeThickness){
        pointMarker.setStrokeStyle(new PenStyle.Builder(displayMetrics).withThickness(strokeThickness).withColor(stroke).build());
        return this;
    }

    public PointMarkerBuilder<T> withFill(int fill){
        pointMarker.setFillStyle(new SolidBrushStyle(fill));
        return this;
    }

    public PointMarkerBuilder<T> withFill(BrushStyle fillStyle){
        pointMarker.setFillStyle(fillStyle);
        return this;
    }

    public PointMarkerBuilder<T> withStroke(PenStyle strokeStyle){
        pointMarker.setStrokeStyle(strokeStyle);
        return this;
    }
}
