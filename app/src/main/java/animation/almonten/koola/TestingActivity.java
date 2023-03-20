package animation.almonten.koola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appwood.mylibrarys.BigAnimation;
import com.appwood.mylibrarys.NextAnimation;
import com.appwood.mylibrarys.SmallAnimation;


public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation));
        SmallAnimation.BottomAnimation( this, findViewById(R.id.bottom_animation));
    }

    @Override
    public void onBackPressed() {
        NextAnimation.BackAnimation(TestingActivity.this);
    }

    public void ADS(View view) {
        NextAnimation.SliderAnimation(this);
    }



}