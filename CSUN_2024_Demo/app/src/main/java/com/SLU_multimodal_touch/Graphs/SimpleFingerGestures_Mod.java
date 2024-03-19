package com.SLU_multimodal_touch.Graphs;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SimpleFingerGestures_Mod implements View.OnTouchListener {
    private boolean debug = true;
    private boolean consumeTouchEvents = false;
    public static final long GESTURE_SPEED_SLOW = 1500L;
    public static final long GESTURE_SPEED_MEDIUM = 1000L;
    public static final long GESTURE_SPEED_FAST = 500L;
    private static final String TAG = "SFG_Mod";
    protected boolean[] tracking = new boolean[]{false, false, false, false, false};
    private GestureAnalyser ga;
    private OnFingerGestureListener onFingerGestureListener;

    //// Added by Wilfredo ////
    // Initialize the finger counts and IDs
    int finger_count = 0;
    int no_finger_value = 9999;
    public double[] X_coords = new double[5];
    public double[] Y_coords = new double[5];
    public int[] finger_ids = {no_finger_value, no_finger_value, no_finger_value, no_finger_value, no_finger_value};

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getConsumeTouchEvents() {
        return this.consumeTouchEvents;
    }

    public void setConsumeTouchEvents(boolean consumeTouchEvents) {
        this.consumeTouchEvents = consumeTouchEvents;
    }

    public SimpleFingerGestures_Mod() {
        this.ga = new GestureAnalyser();
    }

    public SimpleFingerGestures_Mod(int swipeSlopeIntolerance, int doubleTapMaxDelayMillis, int doubleTapMaxDownMillis) {
        this.ga = new GestureAnalyser(swipeSlopeIntolerance, doubleTapMaxDelayMillis, doubleTapMaxDownMillis);
    }
//    Original
//    public void setOnFingerGestureListener(in.championswimmer.sfg.lib.SimpleFingerGestures.OnFingerGestureListener omfgl) {
//        this.onFingerGestureListener = omfgl;
//    }
    public void setOnFingerGestureListener(OnFingerGestureListener omfgl) {
        this.onFingerGestureListener = omfgl;
    }

    public boolean onTouch(View view, MotionEvent ev) {

        //////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////// NEW ////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////
        int touch_action = ev.getActionMasked();

        // Get the amount of fingers on screen and reset their respective IDs
        finger_count = ev.getPointerCount();

        // Reset the finger_ids array
        finger_ids = new int[finger_count];
        for (int i = 0; i < finger_count; i++) {
            finger_ids[i] = ev.getPointerId(i);
        }

        //Reset the X and Y coords array
        X_coords = new double[5];
        Y_coords = new double[5];


        // If finger(s) have been pressed on the screen or are moving
        if (touch_action == ev.ACTION_DOWN || touch_action == ev.ACTION_MOVE || touch_action == ev.ACTION_HOVER_MOVE || touch_action == ev.ACTION_HOVER_ENTER) {
            // Based on the fingers on screen, update the X_coords and Y_coords arrays
            int count = 0;
            for (int element : finger_ids) {
                X_coords[element] = Math.round(ev.getX(count));
                Y_coords[element] = Math.round(ev.getY(count));
                count += 1;
            }
        }
        // If a finger has been removed from the screen
        else if (touch_action == ev.ACTION_UP || touch_action == ev.ACTION_HOVER_EXIT) {
            //Clean the coordinate list
            X_coords = new double[5];
            Y_coords = new double[5];
            finger_count = 0;
        }

        if (this.debug) {
            Log.d(TAG, "onTouch");
        }

        switch (ev.getAction() & 255) {
            case 0:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_DOWN");
                }

                this.startTracking(0);
                this.ga.trackGesture(ev);
                return this.consumeTouchEvents;
            case 1:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_UP");
                }

                if (this.tracking[0]) {
                    this.doCallBack(this.ga.getGesture(ev));
                }

                this.stopTracking(0);
                this.ga.untrackGesture();
                return this.consumeTouchEvents;
            case 2:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_MOVE");
                }

                return this.consumeTouchEvents;
            case 3:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_CANCEL");
                }

                return true;
            case 4:
            default:
                return this.consumeTouchEvents;
            case 5:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_POINTER_DOWN num" + ev.getPointerCount());
                }

                this.startTracking(ev.getPointerCount() - 1);
                this.ga.trackGesture(ev);
                return this.consumeTouchEvents;
            case 6:
                if (this.debug) {
                    Log.d("SimpleFingerGestures", "ACTION_POINTER_UP num" + ev.getPointerCount());
                }

                if (this.tracking[1]) {
                    this.doCallBack(this.ga.getGesture(ev));
                }

                this.stopTracking(ev.getPointerCount() - 1);
                this.ga.untrackGesture();
                return this.consumeTouchEvents;
        }

    }

    private void doCallBack(GestureAnalyser.GestureType mGt) {
        switch (mGt.getGestureFlag()) {
            case 11:
                this.onFingerGestureListener.onSwipeUp(1, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 12:
                this.onFingerGestureListener.onSwipeDown(1, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 13:
                this.onFingerGestureListener.onSwipeLeft(1, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 14:
                this.onFingerGestureListener.onSwipeRight(1, mGt.getGestureDuration(), mGt.getGestureDistance());
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 27:
            case 28:
            case 29:
            case 30:
            case 37:
            case 38:
            case 39:
            case 40:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            default:
                break;
            case 21:
                this.onFingerGestureListener.onSwipeUp(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 22:
                this.onFingerGestureListener.onSwipeDown(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 23:
                this.onFingerGestureListener.onSwipeLeft(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 24:
                this.onFingerGestureListener.onSwipeRight(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 25:
                this.onFingerGestureListener.onPinch(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 26:
                this.onFingerGestureListener.onUnpinch(2, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 31:
                this.onFingerGestureListener.onSwipeUp(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 32:
                this.onFingerGestureListener.onSwipeDown(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 33:
                this.onFingerGestureListener.onSwipeLeft(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 34:
                this.onFingerGestureListener.onSwipeRight(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 35:
                this.onFingerGestureListener.onPinch(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 36:
                this.onFingerGestureListener.onUnpinch(3, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 41:
                this.onFingerGestureListener.onSwipeUp(4, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 42:
                this.onFingerGestureListener.onSwipeDown(4, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 43:
                this.onFingerGestureListener.onSwipeLeft(4, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 44:
                this.onFingerGestureListener.onSwipeRight(4, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 45:
                this.onFingerGestureListener.onPinch(4, mGt.getGestureDuration(), mGt.getGestureDistance());
                break;
            case 46:
                this.onFingerGestureListener.onUnpinch(4, mGt.getGestureDuration(), mGt.getGestureDistance());
            case 107:
                this.onFingerGestureListener.onDoubleTap(1);
        }

    }

    private void startTracking(int nthPointer) {
        for(int i = 0; i <= nthPointer; ++i) {
            this.tracking[i] = true;
        }

    }

    private void stopTracking(int nthPointer) {
        for(int i = nthPointer; i < this.tracking.length; ++i) {
            this.tracking[i] = false;
        }

    }

    public interface OnFingerGestureListener {
        boolean onSwipeUp(int var1, long var2, double var4);

        boolean onSwipeDown(int var1, long var2, double var4);

        boolean onSwipeLeft(int var1, long var2, double var4);

        boolean onSwipeRight(int var1, long var2, double var4);

        boolean onPinch(int var1, long var2, double var4);

        boolean onUnpinch(int var1, long var2, double var4);

        boolean onDoubleTap(int var1);
    }

}
