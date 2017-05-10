package com.example.binomo.service;


import android.os.Handler;

import com.example.binomo.models.Point;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PointGenerator {

    private final Map<Long, Handler> sHandlerByThreadId = new ConcurrentHashMap<>();
    private ExecutorService mExecutor = Executors.newFixedThreadPool(1);
    private double oldRange = 1000d;

    public interface ResultListener {
        void onResultListener(Point point);
    }

    public void getPoint(final ResultListener listener) {
        final Handler handler = getCurrentHandler();
        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                final Point result = generatePoint();
                if (handler != null)
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.onResultListener(result);
                            }
                        }
                    });
            }
        });
    }

    private Handler getCurrentHandler() {
        Handler handler = sHandlerByThreadId.get(Thread.currentThread().getId());
        if (handler == null) {
            synchronized (sHandlerByThreadId) {
                Handler newHandler = sHandlerByThreadId.get(Thread.currentThread().getId());
                if (newHandler == null) {
                    newHandler = new Handler();
                    sHandlerByThreadId.put(Thread.currentThread().getId(), newHandler);
                    handler = newHandler;
                }
            }
        }
        return handler;
    }

    private Point generatePoint() {
        Point point = new Point();
        point.setDate(new Date());
        point.setRate(getRate());
        return point;
    }

    private Double getRate() {
        Random rand = new Random();
        oldRange += (rand.nextInt(21) - 10) / 10.0;
        return oldRange;
    }

}
