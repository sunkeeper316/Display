package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Point point;
    private DisplayMetrics displayMetrics;
    Context context;
    View view;
    int i;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        view = findViewById ( R.id.view );
        button = findViewById ( R.id.button );
    }

    @Override
    protected void onStart() {
        super.onStart ( );
        WindowManager windowManager=(WindowManager) this.getSystemService ( Context.WINDOW_SERVICE );
        Display display =windowManager.getDefaultDisplay ();
        displayMetrics = new DisplayMetrics ();
        display.getMetrics ( displayMetrics );
        ConstraintLayout.LayoutParams layoutParams =(ConstraintLayout.LayoutParams) view.getLayoutParams ();
        ConstraintLayout.LayoutParams btlayoutParams =(ConstraintLayout.LayoutParams) button.getLayoutParams ();
        Log.i("DEBUG","btlayoutParams" +btlayoutParams.height +"NavigationBarHeight"+ getNavigationBarHeight() );

        layoutParams.height = displayMetrics.heightPixels - btlayoutParams.height -getStatusBarHeight() -getActionBarHeight ();

        Log.i("DEBUG","displayMetrics" +displayMetrics.heightPixels +"NavigationBarHeight"+ getNavigationBarHeight() + "StatusBarHeigh" + getStatusBarHeight() + "actionBarHeight" +getActionBarHeight());

        view.setLayoutParams ( layoutParams );


    }

    public  int getActionBarHeight(){
        TypedValue tv = new TypedValue ();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            return  TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return 0;
    }

    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources ().getIdentifier ( "status_bar_height","dimen","android" );
        if (resourceId > 0){
            result = getResources ().getDimensionPixelSize ( resourceId );
        }

        return result;
    }
    public int getNavigationBarHeight()
    {
        boolean hasMenuKey = ViewConfiguration.get( this).hasPermanentMenuKey();
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey)
        {

            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
