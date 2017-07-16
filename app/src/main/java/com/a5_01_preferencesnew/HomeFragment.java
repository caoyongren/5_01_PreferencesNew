package com.a5_01_preferencesnew;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends PreferenceFragment implements View.OnClickListener {
    
    Button settingsButton;
    TextView displayText;

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View content = inflater.inflate(R.layout.main, container, false);
        
        //Load the preference defaults
        PreferenceManager.setDefaultValues(getActivity(), R.xml.settings, false);
        
        displayText = (TextView) content.findViewById(R.id.display);
        settingsButton = (Button) content.findViewById(R.id.settings);
        settingsButton.setOnClickListener(this);

        return content;
    }
    */

    private ListPreference listPreference;
    private final static String LISTPREFERENCE = "colorPref";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        listPreference = (ListPreference) findPreference(LISTPREFERENCE);
        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), "Test ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //Display the current settings
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        StringBuilder builder = new StringBuilder();
        builder.append("User Name: "+settings.getString("namePref", "")+"\n");
        if(!settings.getBoolean("morePref", false)) {
            builder.append("More Settings is DISABLED");
        } else {
            builder.append("More Settings is ENABLED\n");
            builder.append("Favorite Color is "+settings.getString("colorPref", "")+"\n");
            builder.append(settings.getBoolean("gpsPref", false) ? "GPS is ENABLED\n" : "GPS is DISABLED\n");
            builder.append(settings.getBoolean("networkPref", false) ? "Network is ENABLED\n" : "Network is DISABLED\n");
        }
        displayText.setText(builder.toString());
    }
    
    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new SettingsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}