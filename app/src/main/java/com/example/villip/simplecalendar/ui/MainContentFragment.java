package com.example.villip.simplecalendar.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villip.simplecalendar.R;
import com.example.villip.simplecalendar.data.orm.DatabaseHelper;
import com.example.villip.simplecalendar.data.orm.Note;
import com.example.villip.simplecalendar.ui.recyclerView.ItemObject;
import com.example.villip.simplecalendar.ui.recyclerView.RecyclerViewAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainContentFragment extends Fragment {

  private RecyclerView recyclerViewDays;

  private GridLayoutManager lLayout;

  public static String staticMonthYear;

  private static final String TAG = "myLogs";

//  private String mParam1;

    public MainContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View rootView = inflater.inflate(R.layout.fragment_main_content, container, false);

      recyclerViewDays = (RecyclerView) rootView.findViewById(R.id.recycler_view);

      Log.d(TAG, "From MainContentFragment.onCreateView staticMonthYear = "+ staticMonthYear);

      return rootView;
    }

  @Override
  public void onStart() {
    super.onStart();

  }


  public void onSetupData (String monthYear){
    staticMonthYear = monthYear;

    //Log.d(TAG, "From MainContentFragment.SetupData monthYear = "+ monthYear);

    List<ItemObject> rowListItem = getAllItemList(monthYear);
    lLayout = new GridLayoutManager(getContext(), 7);


    recyclerViewDays.setHasFixedSize(true);
    recyclerViewDays.setLayoutManager(lLayout);

    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), rowListItem);
    recyclerViewDays.setAdapter(rcAdapter);
  }


  @Override
  public void onResume() {
    super.onResume();

    //Log.d(TAG, "From MainContentFragment.onResume staticMonthYear = "+ staticMonthYear);


    List<ItemObject> rowListItem = getAllItemList(staticMonthYear);
    lLayout = new GridLayoutManager(getContext(), 7);


    recyclerViewDays.setHasFixedSize(true);
    recyclerViewDays.setLayoutManager(lLayout);

    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), rowListItem);
    recyclerViewDays.setAdapter(rcAdapter);
  }

  private List<ItemObject> getAllItemList(String monthYear) {
    List<ItemObject> allItems = new ArrayList<>();

    DatabaseHelper dbHelper = OpenHelperManager.getHelper(getContext(), DatabaseHelper.class);
    RuntimeExceptionDao<Note, Integer> noteDao = dbHelper.getNoteDao();

    //Log.d(TAG, "getAllItemList    monthYear = " + monthYear);

    String[] monthYearArray = getResources().getStringArray(R.array.months_years_array);

    int selectedMonthYear = 0;
    int dayToday;

    for(int n = 0; n < monthYearArray.length; n++ ){

      //Log.d(TAG, "monthYearArray[" + n + "] = " + monthYearArray[n] + "\n");

      if(monthYearArray[n].equals(monthYear)) {
        selectedMonthYear = n;
             }
    }

    int selectedYear = selectedMonthYear / 12 + 2016;
    int selectedMonth = selectedMonthYear % 12;

    Calendar calendar = Calendar.getInstance();
    calendar.set(selectedYear, selectedMonth, 1);
    int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int firstDayofWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK);

    //Определение текущей даты сравнивая текущий год и месяц
    Calendar toCalendar = Calendar.getInstance();
    if(toCalendar.get(Calendar.MONTH) == selectedMonth && toCalendar.get(Calendar.YEAR) == selectedYear){
      dayToday = toCalendar.get(Calendar.DAY_OF_MONTH);
    }
    else {
      dayToday = 32;
    }

    for (int n = 2 - firstDayofWeekInMonth; n <= daysOfMonth; n++) {
      if (n < 1) {
        allItems.add(new ItemObject(0, false, false));
      }
      else if (n == dayToday) {
        allItems.add(new ItemObject(n, true, false));
      }
      else {
        try{
          List<Note> notes = noteDao.queryForEq("date", n + " " + monthYear);
          if(!notes.isEmpty()){
              allItems.add(new ItemObject(n, false, true));
          } else{
            allItems.add(new ItemObject(n, false, false));
             // Log.d("demo","n = " + n);
          }
        }catch (Throwable t){
          Log.d("demo", "Argument for 'date' is null");
            //Log.d("demo","n = " + n);
        }
      }
    }
      OpenHelperManager.releaseHelper();
    //Log.d(TAG, "firstDayofWeekInMonth = " + firstDayofWeekInMonth + "\n daysOfMonth = " + daysOfMonth);

    return allItems;
  }
}
