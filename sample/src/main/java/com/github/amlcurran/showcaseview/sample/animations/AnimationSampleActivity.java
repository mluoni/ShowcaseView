/*
 * Copyright 2014 Alex Curran
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

package com.github.amlcurran.showcaseview.sample.animations;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ApiUtils;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.sample.R;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by Alex on 26/10/13.
 */
public class AnimationSampleActivity extends Activity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int counter = 0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private final ApiUtils apiUtils = new ApiUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        Drawable d =  getResources().getDrawable(R.drawable.diego5);
        Rect r = getRect(d);

        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.textView)))
                .setOnClickListener(this)
                .setImage(d, r)
                .build();
        showcaseView.setButtonText(getString(R.string.next));
    }


    protected Rect getRect(Drawable d){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        android.graphics.Rect r =new Rect();
        int right  = (int) (width - width*0.3);
        int bottom =  (int) (height - height*0.3);
        int left =  (int) (width*0.3);
        int  top =  (int) (height*0.3);
        r.set(left, top, right, bottom);
        return r;
    }

    protected Rect getRectTop(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        android.graphics.Rect r =new Rect();
        int right  = (int) (width - width*0.1);
        int bottom =  (int) (height - height*0.8);
        int left =  (int) (width*0.1);
        int  top =  (int) (height*0.1);
        r.set(left, top, right, bottom);
        return r;
    }

    private void setAlpha(float alpha, View... views) {
        if (apiUtils.isCompatWithHoneycomb()) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                Drawable d =  getResources().getDrawable(R.drawable.maradona_4);
                Rect r = getRectTop();
                showcaseView.setShowcase(new ViewTarget(textView2), true);
                showcaseView.setImage(d, r);
                break;

            case 1:
                 d =  getResources().getDrawable(R.drawable.diego1);
                 r = getRect(d);
                showcaseView.setShowcase(new ViewTarget(textView3), true);
                showcaseView.setImage(d, r);
                break;

            case 2:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("Check it out");
                showcaseView.setContentText("You don't always need a target to showcase");
                showcaseView.setButtonText(getString(R.string.close));

                setAlpha(0.4f, textView1, textView2, textView3);
                break;

            case 3:
                showcaseView.hide();
                setAlpha(1.0f, textView1, textView2, textView3);
                break;
        }
        counter++;
    }
}
