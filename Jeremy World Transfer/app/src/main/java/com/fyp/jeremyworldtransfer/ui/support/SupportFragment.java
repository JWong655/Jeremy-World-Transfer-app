package com.fyp.jeremyworldtransfer.ui.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.fyp.jeremyworldtransfer.AboutActivity;
import com.fyp.jeremyworldtransfer.HelpActivity;
import com.fyp.jeremyworldtransfer.LoginActivity;
import com.fyp.jeremyworldtransfer.R;
import com.google.firebase.auth.FirebaseAuth;

public class SupportFragment extends Fragment {


    private Button help;
    private Button about;
    private Button logOut;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

       View inflatedView = inflater.inflate(R.layout.fragment_support, container, false);
       help = inflatedView.findViewById(R.id.supportHelp);
       about = inflatedView.findViewById(R.id.supportAbout);
       logOut = inflatedView.findViewById(R.id.logOutButton);

       helpButton();
       aboutButton();
       loggingOut();

       return inflatedView;
       //https://blog.avenuecode.com/android-basics-activities-fragments

        };

    private void helpButton(){
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startHelp = new Intent(getActivity(), HelpActivity.class);
                startActivity(startHelp);
                //https://www.youtube.com/watch?v=TY-2Cx4IW9A
            }
        });
    }

    private void aboutButton(){
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAbout = new Intent(getActivity(), AboutActivity.class);
                startActivity(startAbout);
                //https://www.youtube.com/watch?v=TY-2Cx4IW9A
            }
        });
    }

    private void loggingOut(){
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();
                Intent loggedOut = new Intent(getActivity(), LoginActivity.class);
                getActivity().onBackPressed();       //https://stackoverflow.com/a/40777335
                startActivity(loggedOut);
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });
    }
    }

