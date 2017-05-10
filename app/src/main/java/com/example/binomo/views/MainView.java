package com.example.binomo.views;

import com.example.binomo.models.Point;

import java.util.List;

public interface MainView {

    public void updateView(List<Point> points);

    public void addNewPointToChart(Point point);
}
