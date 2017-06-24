package com.example.rajpatel.ljietcloud;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

import java.util.Random;

/**
 * Created by himangi on 13/03/16.
 */
public abstract class ProgressGenerator {

    public abstract void onComplete();

    public interface OnCompleteListener {

        public void onComplete();
    }

    private OnCompleteListener mListener;
    private int mProgress;

    public ProgressGenerator(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    handler.postDelayed(this, generateDelay());
                } else {
                    mListener.onComplete();
                }
            }
        }, generateDelay());
    }

    private Random random = new Random();

    private int generateDelay() {
        return random.nextInt(1000);
    }
}
