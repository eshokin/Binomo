//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2016. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// DataSeriesBuilder.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.example.binomo.utils.builders;

import com.scichart.charting.model.dataSeries.IDataSeries;
import com.scichart.charting.model.dataSeries.OhlcDataSeries;
import com.scichart.charting.model.dataSeries.XyDataSeries;
import com.scichart.charting.model.dataSeries.XyyDataSeries;

public abstract class DataSeriesBuilder<TX extends Comparable<TX>, TY extends Comparable<TY>, TDataSeries extends IDataSeries<TX, TY>, TDataSeriesBuilder extends DataSeriesBuilder<TX, TY, TDataSeries, TDataSeriesBuilder>> {
    private final TDataSeries dataSeries;

    DataSeriesBuilder(TDataSeries dataSeries) {
        this.dataSeries = dataSeries;
    }

    public TDataSeries build(){
        return dataSeries;
    }

    protected abstract TDataSeriesBuilder getThis();

    public TDataSeriesBuilder withFifoCapacity(Integer fifoCapacity){
        dataSeries.setFifoCapacity(fifoCapacity);
        return getThis();
    }

    public TDataSeriesBuilder withSeriesName(String seriesName){
        dataSeries.setSeriesName(seriesName);
        return getThis();
    }

    public TDataSeriesBuilder withAcceptsUnsortedData(){
        dataSeries.setAcceptsUnsortedData(true);
        return getThis();
    }

    public static final class XyDataSeriesBuilder<TX extends Comparable<TX>, TY extends Comparable<TY>> extends DataSeriesBuilder<TX, TY, XyDataSeries<TX, TY>, XyDataSeriesBuilder<TX, TY>> {

        XyDataSeriesBuilder(Class<TX> xType, Class<TY> yType) {
            super(new XyDataSeries<>(xType, yType));
        }

        @Override
        protected XyDataSeriesBuilder<TX, TY> getThis() {
            return this;
        }
    }

    public static final class OhlcDataSeriesBuilder<TX extends Comparable<TX>, TY extends Comparable<TY>> extends DataSeriesBuilder<TX, TY, OhlcDataSeries<TX, TY>, OhlcDataSeriesBuilder<TX, TY>> {

        OhlcDataSeriesBuilder(Class<TX> xType, Class<TY> yType) {
            super(new OhlcDataSeries<>(xType, yType));
        }

        @Override
        protected OhlcDataSeriesBuilder<TX, TY> getThis() {
            return this;
        }
    }

    public static final class XyyDataSeriesBuilder<TX extends Comparable<TX>, TY extends Comparable<TY>> extends DataSeriesBuilder<TX, TY, XyyDataSeries<TX, TY>, XyyDataSeriesBuilder<TX, TY>> {

        XyyDataSeriesBuilder(Class<TX> xType, Class<TY> yType) {
            super(new XyyDataSeries<>(xType, yType));
        }

        @Override
        protected XyyDataSeriesBuilder<TX, TY> getThis() {
            return this;
        }
    }
}
