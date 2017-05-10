package com.example.binomo.presenters;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.binomo.models.Point;
import com.example.binomo.service.PointGenerator;
import com.example.binomo.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<List<Point>, MainView> {

    private static final Long TIMEOUT_DELAY = 1000L;
    private Handler mHandler = new Handler();
    private PointGenerator mPointGenerator = new PointGenerator();

    @Override
    protected void updateView() {
        view().updateView(model);
    }

    @Override
    public void bindView(@NonNull MainView view) {
        super.bindView(view);
        if (model == null) {
            model = new ArrayList<>();
        }
        restartRunnableGetPoint();
    }

    private void restartRunnableGetPoint() {
        stopRunnableGetPoint();
        startRunnableGetPoint();
    }

    public void stopRunnableGetPoint() {
        mHandler.removeCallbacks(mRunnableGetPoint);
    }

    private void startRunnableGetPoint() {
        mHandler.post(mRunnableGetPoint);
    }

    private Runnable mRunnableGetPoint = new Runnable() {
        @Override
        public void run() {
            try {
                getPoint();
                mHandler.postDelayed(mRunnableGetPoint, TIMEOUT_DELAY);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }
    };

    private void getPoint() {
        mPointGenerator.getPoint(new PointGenerator.ResultListener() {
            @Override
            public void onResultListener(Point point) {
                if (point != null) {
                    if (setupDone()) {
                        view().addNewPointToChart(point);
                    }
                    model.add(point);
                }
            }
        });
    }
}
