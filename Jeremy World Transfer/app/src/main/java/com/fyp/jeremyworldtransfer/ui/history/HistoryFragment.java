package com.fyp.jeremyworldtransfer.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.fyp.jeremyworldtransfer.R;

public class HistoryFragment extends Fragment {

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_history, container, false);

        return inflatedView;
        //https://blog.avenuecode.com/android-basics-activities-fragments

    }
}
