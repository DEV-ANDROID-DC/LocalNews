/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.debin.localnews.util

import android.transition.Transition
import android.animation.ObjectAnimator
import android.widget.ProgressBar
import android.view.ViewGroup
import android.animation.Animator
import android.transition.TransitionValues
import android.view.animation.DecelerateInterpolator

class ProgressBarTransition : Transition() {

  val PROGRESSBAR_PROPERTY = "progress"
  val TRANSITION_PROPERTY = "ProgressBarTransition:progress"

  // 1
  private fun captureValues(transitionValues: TransitionValues) {
    if (transitionValues.view is ProgressBar) {
      // Save current progress in the transitionValues Map
      val progressBar = transitionValues.view as ProgressBar
      transitionValues.values[TRANSITION_PROPERTY] = progressBar.progress
    }
  }

  // 2
  override fun captureStartValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
  }

  // 3
  override fun captureEndValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
  }

  // 4
  override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?,
                              endValues: TransitionValues?): Animator? {
    if (startValues != null && endValues != null && endValues.view is ProgressBar) {
      val progressBar = endValues.view as ProgressBar
      val startValue = startValues.values[TRANSITION_PROPERTY] as Int
      val endValue = endValues.values[TRANSITION_PROPERTY] as Int
      if (startValue != endValue) {
        // Animate progressBar from startValue to endValue
        val objectAnimator = ObjectAnimator
            .ofInt(progressBar, PROGRESSBAR_PROPERTY, startValue, endValue)
        objectAnimator.interpolator = DecelerateInterpolator()

        return objectAnimator
      }
    }

    return null
  }

}