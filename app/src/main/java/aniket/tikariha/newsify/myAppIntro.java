package aniket.tikariha.newsify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

;

public class myAppIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);


        addSlide(AppIntroFragment.newInstance(
                "Welcome to Newsify App",
                 "A News app by Aniket Tikariha",
                 R.drawable.news123 ,
                ContextCompat.getColor(getApplicationContext(),R.color.black)

                ));
        addSlide(AppIntroFragment.newInstance(
                "Health related News",
                "New articles updated every 24Hr",
                R.drawable.news ,
                ContextCompat.getColor(getApplicationContext(),R.color.purple_200)

        ));



        addSlide(AppIntroFragment.newInstance(
                "Click on any news to read full content",
                "just like you would on chrome",
                R.drawable.open ,
                ContextCompat.getColor(getApplicationContext(),R.color.teal_200)

        ));
        addSlide(AppIntroFragment.newInstance(
                "Share News",
                "Click on share icon on top to share with your friends on various platforms",
                R.drawable.share,
                ContextCompat.getColor(getApplicationContext(),R.color.teal_200)

        ));
        addSlide(AppIntroFragment.newInstance(
                "Translate to any Language",
                "Click on ⋮ on top > Translate",
                R.drawable.trans,
                ContextCompat.getColor(getApplicationContext(),R.color.purple_200)

        ));

    }
    @Override
    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

        finish();
    }
    @Override
    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }


    }
