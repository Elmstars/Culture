package com.example.culture;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.culture.Adapter.TabFragmentPagerAdpter;
import com.example.culture.Fragment.AboutFragment;
import com.example.culture.Fragment.HomeFragment;
import com.example.culture.Fragment.LearnFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
  public static String BASEURL="https://hello-cloudbase-7gnumlfk3ea295b0-1305330793.ap-shanghai.app.tcloudbase.com";
  private Fragment homeFragment, learnFragment, aboutFragment;
  private TabFragmentPagerAdpter tabFragmentPagerAdpter;
  private ViewPager2 viewPager2;
  private RadioGroup radioGroup;
  private RadioButton home, learn, about;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    viewPager2 = findViewById(R.id.main_pager);
    radioGroup = findViewById(R.id.main_radio_group);
    home = findViewById(R.id.main_home);
    learn = findViewById(R.id.main_learn);
    about = findViewById(R.id.main_about);
    home.setChecked(true);
    radioGroup.setOnCheckedChangeListener(this);
    List<Fragment> list = new ArrayList<>();
    homeFragment = new HomeFragment();
    learnFragment = new LearnFragment();
    aboutFragment = new AboutFragment();
    list.add(homeFragment);
    list.add(learnFragment);
    list.add(aboutFragment);
    FragmentManager fragmentManager = getSupportFragmentManager();
    tabFragmentPagerAdpter = new TabFragmentPagerAdpter(fragmentManager, getLifecycle(), list);
    viewPager2.setAdapter(tabFragmentPagerAdpter);
    viewPager2.setCurrentItem(0);
    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
      }

      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        switch (position) {
          case 0:
            home.setChecked(true);
            break;
          case 1:
            learn.setChecked(true);
            break;
          case 2:
            about.setChecked(true);
            break;
          default:
            break;
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
      }
    });
  }

  @Override
  public void onCheckedChanged(RadioGroup group, int checkedId) {
    switch (checkedId) {
      case R.id.main_home:
        viewPager2.setCurrentItem(0);
        break;
      case R.id.main_learn:
        viewPager2.setCurrentItem(1);
        break;
      case R.id.main_about:
        viewPager2.setCurrentItem(2);
        break;
    }
  }
}
