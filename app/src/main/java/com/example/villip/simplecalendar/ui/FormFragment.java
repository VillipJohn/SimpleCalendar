package com.example.villip.simplecalendar.ui;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.villip.simplecalendar.R;
import com.example.villip.simplecalendar.data.orm.DatabaseHelper;
import com.example.villip.simplecalendar.data.orm.Note;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class FormFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static TextView title;
    private Spinner selected_event;
    private EditText alarm, note;
    private Button ok;

    private String titleStr, eventStr, noteStr, alarmStr;

    DatabaseHelper dbHelper;



    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_form, container, false);

        title = (TextView) rootView.findViewById(R.id.title_text);
        selected_event = (Spinner) rootView.findViewById(R.id.spinner);
        alarm = (EditText) rootView.findViewById(R.id.editTextAlarm);
        note = (EditText) rootView.findViewById(R.id.editTextNote);
        ok = (Button) rootView.findViewById(R.id.btn_ok);

        Bundle bundle = getArguments();
        titleStr = bundle.getString("key");

        title.setText(titleStr);

        ok.setOnClickListener(onOkClick());


        return rootView;
    }

    private View.OnClickListener onOkClick() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eventStr = selected_event.getSelectedItem().toString();
                noteStr = note.getText().toString();
                alarmStr = alarm.getText().toString();

                dbHelper = OpenHelperManager.getHelper(getContext(), DatabaseHelper.class);
                RuntimeExceptionDao<Note, Integer> noteDao = dbHelper.getNoteDao();

                noteDao.create(new Note(titleStr, eventStr, noteStr, alarmStr));

                OpenHelperManager.releaseHelper();

                Intent intent = new Intent();
                intent.putExtra("key_day", titleStr);
                intent.setClass(getActivity(), ListEventsActivity.class);
                startActivity(intent);
            }
        };


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}
