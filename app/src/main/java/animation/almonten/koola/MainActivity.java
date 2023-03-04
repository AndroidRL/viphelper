package animation.almonten.koola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appwood.mylibrarys.AnimationR;
import com.appwood.mylibrarys.BigAnimation;
import com.appwood.mylibrarys.NextAnimationActivity;
import com.appwood.mylibrarys.SmallAnimation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ADS(View view) {
        NextAnimationActivity.Slider_intents(this, new Intent(this, TestingActivity.class), null, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BigAnimation.Top_animation(this, findViewById(R.id.native_detail),
                findViewById(R.id.banner_native), findViewById(R.id.addcontain)
                , findViewById(R.id.ad_native_fb), findViewById(R.id.applovin_native), findViewById(R.id.custom_native));
        //banner
        SmallAnimation.Bottom_animation(this, findViewById( R.id.google_banner_container),
                findViewById(R.id.fb_banner_container),
                findViewById(R.id.applovin_banner),
                findViewById(R.id.custom_banner),
                findViewById(R.id.bottomsads));
    }

    @Override
    public void onBackPressed() {
        NextAnimationActivity.BackAnimation(this);
    }

//    @Override
//    public void onBackPressed() {
//      AnimationR.Slider_intents(this, new Intent(this, ExitScreen.class),null,0);
//    }


}





































