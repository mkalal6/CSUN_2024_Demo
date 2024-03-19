package com.SLU_multimodal_touch.Graphs;

import android.view.MotionEvent;
import android.view.View;

public class HoverToTouchAdapter implements View.OnHoverListener {
    private final View.OnTouchListener mTouchListener;
    private final String TAG = "HoverAdapter";

    public HoverToTouchAdapter(View.OnTouchListener touchListener) {
        mTouchListener = touchListener;
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        // Redirect hover events to touch events
        return mTouchListener.onTouch(v, event);
    }
}
