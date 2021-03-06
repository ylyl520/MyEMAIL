package com.example.lenovo.myemail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * Created by lenovo on 2016/11/12.
 */

public class WelcomeActivity extends Activity implements Animation.AnimationListener
{  private ImageView imageView = null;
    private Animation alphaAnimation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        imageView = (ImageView)findViewById(R.id.welcome_image_view);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this);
    }
    @Override
    public void onAnimationStart(Animation animation)
    {     }
    @Override
    public void onAnimationEnd(Animation animation)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    this.finish();
    }
        @Override
        public void onAnimationRepeat(Animation animation)
        {     }
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event)
        {
            if(keyCode==KeyEvent.KEYCODE_BACK)
            {
                return false;
            }
            return false;
        }
}
