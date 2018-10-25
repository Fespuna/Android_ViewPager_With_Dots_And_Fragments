package dev.fep.testingviewpager;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.activity_main);

               // DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
                SpringDotsIndicator springDotsIndicator = findViewById(R.id.spring_dots_indicator);
               // WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);

                final ViewPager viewPager = findViewById(R.id.view_pager);
                adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());

               // DotIndicatorPagerAdapter adapter = new DotIndicatorPagerAdapter();

                //viewPager.setAdapter(adapter);
        viewPager.setAdapter(adapterViewPager);
                viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

                //dotsIndicator.setViewPager(viewPager);
                springDotsIndicator.setViewPager(viewPager);
              //  wormDotsIndicator.setViewPager(viewPager);

                Button btn = findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    // viewPager.setCurrentItem(1);
                        try {
                            Field mScroller;
                            mScroller = ViewPager.class.getDeclaredField("mScroller");
                            mScroller.setAccessible(true);
                            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext());
                            // scroller.setFixedDuration(5000);
                            mScroller.set(viewPager, scroller);
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        } catch (NoSuchFieldException e) {
                        } catch (IllegalArgumentException e) {
                        } catch (IllegalAccessException e) {
                        }
                    }
                });
            }
        }


