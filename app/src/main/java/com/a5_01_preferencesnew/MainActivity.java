package com.a5_01_preferencesnew;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new HomeFragment());
        ft.commit();
    }

}
