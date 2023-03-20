package animation.almonten.koola;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appwood.mylibrarys.BigAnimation;
import com.appwood.mylibrarys.NextAnimation;
import com.appwood.mylibrarys.SmallAnimation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation));
        SmallAnimation.BottomAnimation( this, findViewById(R.id.bottom_animation));
    }

    public void ADS(View view) {
        NextAnimation.SliderAnimation(this);

    }


    @Override
    public void onBackPressed() {
        NextAnimation.BackAnimation(this);
    }

//    @Override
//    public void onBackPressed() {
//      AnimationR.Slider_intents(this, new Intent(this, ExitScreen.class),null,0);
//    }


}





































