package com.example.villip.simplecalendar.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.villip.simplecalendar.R;

import java.util.Calendar;


public class MainHeaderFragment extends Fragment {
    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2; */

    private OnFragmentHeaderInteractionListener mListener;

    Spinner spinner;
    Button btnPreviousMonth;
    Button btnNextMonth;
    int intMonthYear;

    public static String monthYear;

    private static final String TAG = "myLogs";

    public MainHeaderFragment() {
        // Required empty public constructor
    }

 /* *//*  *//**//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment MainHeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static MainHeaderFragment newInstance(String param1, String param2) {
        MainHeaderFragment fragment = new MainHeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_header, container, false);


        btnPreviousMonth = (Button) rootView.findViewById(R.id.btn_previous_month);
        btnNextMonth = (Button) rootView.findViewById(R.id.btn_next_month);

        Calendar calendar = Calendar.getInstance();
        //calendar.get(Calendar.YEAR) - 2016
        //selectedMonthYear / 12 + 2016;
        intMonthYear = calendar.get(Calendar.MONTH) + (calendar.get(Calendar.YEAR) - 2017)*12;

        Log.d(TAG, "onCreateView после определения intMonthYear " + intMonthYear );

        spinner = (Spinner) rootView.findViewById(R.id.months_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.months_years_array, R.layout.spinner_header);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_header_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(intMonthYear);
        monthYear = spinner.getSelectedItem().toString();
      /*  if (mListener != null) {
            mListener.onFragmentHeaderInteraction(monthYear);
        }*/

        Log.d(TAG, "onCreateView после определения Spinner monthYear=" + monthYear);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                // String[] choose = getResources().getStringArray(R.array.months_array);
                intMonthYear = selectedItemPosition;

                monthYear = spinner.getSelectedItem().toString();
                Log.d(TAG, "onResume.setOnItemSelectedListener monthYear=" + monthYear);
                updateDetail();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "onNothingSelected  intMonthYear=" + intMonthYear);

                spinner.setSelection(intMonthYear);
            }


        });

        btnPreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intMonthYear > 0) {
                    intMonthYear--;
                }
                spinner.setSelection(intMonthYear);

               /* monthYear = spinner.getSelectedItem().toString();
                updateDetail();*/
            }
        });

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Если intMonthYear будет больше длинны массива строк, то программа упадёт :)
                if(intMonthYear < 167){
                    intMonthYear++;
                    spinner.setSelection(intMonthYear);
                }
            }
        });


        monthYear = spinner.getSelectedItem().toString();
        Log.d(TAG, "onResume в конце" + monthYear);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentHeaderInteractionListener) {
            mListener = (OnFragmentHeaderInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentHeaderInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateDetail() {
        // генерируем некоторые данные
        //String newTime = String.valueOf(System.currentTimeMillis());
        // Посылаем данные Activity
        if (mListener != null) {
            mListener.onFragmentHeaderInteraction(monthYear);
        }
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
    public interface OnFragmentHeaderInteractionListener {
        // TODO: Update argument type and name

        void onFragmentHeaderInteraction(String monthYear);
    }



    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/
}
