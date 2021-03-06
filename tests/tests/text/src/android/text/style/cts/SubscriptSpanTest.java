/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.text.style.cts;

import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestTargetNew;
import dalvik.annotation.TestTargets;
import dalvik.annotation.ToBeFixed;

import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.SubscriptSpan;

import junit.framework.TestCase;

@TestTargetClass(SubscriptSpan.class)
public class SubscriptSpanTest extends TestCase {
    @TestTargets({
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "SubscriptSpan",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "SubscriptSpan",
            args = {android.os.Parcel.class}
        )
    })
    public void testConstructor() {
        SubscriptSpan subscriptSpan = new SubscriptSpan();

        Parcel p = Parcel.obtain();
        try {
            subscriptSpan.writeToParcel(p, 0);
            p.setDataPosition(0);
            new SubscriptSpan(p);
        } finally {
            p.recycle();
        }
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "updateMeasureState",
        args = {android.text.TextPaint.class}
    )
    @ToBeFixed(bug="1695243", explanation="miss javadoc")
    public void testUpdateMeasureState() {
        // the expected result is: tp.baselineShift -= (int) (tp.ascent() / 2)
        SubscriptSpan subscriptSpan = new SubscriptSpan();

        TextPaint tp = new TextPaint();
        float ascent = tp.ascent();
        int baselineShift = 100;
        tp.baselineShift = baselineShift;

        subscriptSpan.updateMeasureState(tp);
        assertEquals(baselineShift - (int) (ascent / 2), tp.baselineShift);

        try {
            subscriptSpan.updateMeasureState(null);
            fail("should throw NullPointerException.");
        } catch (NullPointerException e) {
            // expected, test success.
        }
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "updateDrawState",
        args = {android.text.TextPaint.class}
    )
    @ToBeFixed(bug="1695243", explanation="miss javadoc")
    public void testUpdateDrawState() {
        // the expected result is: tp.baselineShift -= (int) (tp.ascent() / 2)
        SubscriptSpan subscriptSpan = new SubscriptSpan();

        TextPaint tp = new TextPaint();
        float ascent = tp.ascent();
        int baselineShift = 50;
        tp.baselineShift = baselineShift;

        subscriptSpan.updateDrawState(tp);
        assertEquals(baselineShift - (int) (ascent / 2), tp.baselineShift);

        try {
            subscriptSpan.updateDrawState(null);
            fail("should throw NullPointerException.");
        } catch (NullPointerException e) {
            // expected, test success.
        }
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "describeContents",
        args = {}
    )
    public void testDescribeContents() {
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        subscriptSpan.describeContents();
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getSpanTypeId",
        args = {}
    )
    public void testGetSpanTypeId() {
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        subscriptSpan.getSpanTypeId();
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "writeToParcel",
        args = {Parcel.class, int.class}
    )
    public void testWriteToParcel() {
        Parcel p = Parcel.obtain();
        try {
            SubscriptSpan subscriptSpan = new SubscriptSpan();
            subscriptSpan.writeToParcel(p, 0);
            p.setDataPosition(0);
            new SubscriptSpan(p);
        } finally {
            p.recycle();
        }
    }
}
