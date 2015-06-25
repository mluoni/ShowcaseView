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

package com.github.amlcurran.showcaseview.sample;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class SingleShotActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_shot);


        Target viewTarget = new ViewTarget(R.id.button, this);
        Drawable d =  getResources().getDrawable(R.drawable.kitten);
        Rect r = getRect(d);
        ShowcaseView showcaseView = new ShowcaseView.Builder(this, false) //el true o false define si se muestra o no el dra
             //  .setTarget(viewTarget)
              //  .setStyle(R.style.CustomShowcaseTheme2)
                .setContentTitle(R.string.title_single_shot)
                .setTextPosition(20, r.bottom+5) //para poner el txt
                .setContentText(R.string.R_string_desc_single_shot)

                .setImage(d, r)

              //  .singleShot(44)
                .build();
       // showcaseView.setShowcase(viewTarget, true);
    }

    protected Rect getRect(Drawable d){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Rect r =new Rect();
        int right  = (int) (width - width*0.3);
        int bottom =  (int) (height - height*0.3);
        int left =  (int) (width*0.3);
        int  top =  (int) (height*0.3);
        r.set(left, top, right, bottom);
        return r;
    }
}
