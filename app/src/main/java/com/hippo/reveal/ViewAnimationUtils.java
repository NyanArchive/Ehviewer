/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.reveal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.hippo.yorozuya.SimpleAnimatorListener;

public final class ViewAnimationUtils {
    private ViewAnimationUtils() {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator createCircularReveal(View view,
            int centerX,  int centerY, float startRadius, float endRadius) {
        return android.view.ViewAnimationUtils.createCircularReveal(
                view, centerX, centerY, startRadius, endRadius);
    }

    private static Animator createRevealAnimator(Reveal reveal, int centerX, int centerY,
            float startRadius, float endRadius) {
        ValueAnimator animator = ValueAnimator.ofFloat(startRadius, endRadius);
        animator.addUpdateListener(new RevealAnimatorUpdateListener(reveal, centerX, centerY));
        animator.addListener(new RevealAnimatorListener(reveal));
        return animator;
    }

    private static class RevealAnimatorUpdateListener
            implements ValueAnimator.AnimatorUpdateListener {

        private final Reveal mReveal;
        private final int mCenterX;
        private final int mCenterY;

        private RevealAnimatorUpdateListener(Reveal reveal, int centerX, int centerY) {
            mReveal = reveal;
            mCenterX = centerX;
            mCenterY = centerY;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Object value = animation.getAnimatedValue();
            if (value instanceof Float) {
                mReveal.setReveal(mCenterX, mCenterY, (Float) value);
            }
        }
    }

    private static class RevealAnimatorListener extends SimpleAnimatorListener {

        private final Reveal mReveal;

        private RevealAnimatorListener(Reveal reveal) {
            mReveal = reveal;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mReveal.setRevealEnable(true);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mReveal.setRevealEnable(false);
        }
    }
}
