package com.example.villip.simplecalendar.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.villip.simplecalendar.R;
import com.example.villip.simplecalendar.data.orm.DatabaseHelper;
import com.example.villip.simplecalendar.data.orm.Note;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

public class ListEventsActivity extends AppCompatActivity {
    private static String dayMonthYear;

    private ListView listOfEvents;

    private List<Note> notes;
    private Note noteObject;
  /*  private static final String NOTES_TYPE = "notes_type"; // Верхний текст
    private static final String NOTES_TEXT = "notes_text"; // ниже главного*/

    DatabaseHelper dbHelper;

    CoordinatorLayout coordinatorLayout;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);

        // Убрать ActionBar
        getSupportActionBar().hide();

        listOfEvents = (ListView) findViewById(R.id.list_of_events);

        //получение и установка даты в заголовок
        Intent intent = getIntent();
        dayMonthYear = intent.getStringExtra("key_day");
        TextView date = (TextView) findViewById(R.id.textViewDate);
        date.setText(dayMonthYear);

        getDataFromDB();

      /*  SimpleAdapter adapter = new SimpleAdapter(this, notes,
                R.layout.list_events_item, new String[]{NOTES_TYPE, NOTES_TEXT},
                new int[]{R.id.text1, R.id.text2});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(itemClickListener);
*/
        ArrayList<Note> notes4 = (ArrayList<Note>)notes;
        NoteAdapter adapter = new NoteAdapter(this, notes4);
        listOfEvents.setAdapter(adapter);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coordinatorLayout.setVisibility(View.GONE);

                FormFragment fragment = new FormFragment();

                Bundle args = new Bundle();
                args.putString("key", dayMonthYear);
                fragment.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.list_events, fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        //если данных нет, открывает форму для ввода данных
        if(notes.isEmpty()){
            Log.d("myLog", "from getDataFromDB нет данных");
            coordinatorLayout.setVisibility(View.GONE);

            FormFragment fragment = new FormFragment();

            Bundle args = new Bundle();
            args.putString("key", dayMonthYear);
            fragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.list_events, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private class NoteAdapter extends ArrayAdapter<Note> {

        private NoteAdapter(Context context, ArrayList<Note> notes3) {
            super(context, R.layout.list_events_item, notes3);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Note note = getItem(position);

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext())
                            .inflate(R.layout.list_events_item, parent, false);
                }
                ((TextView) convertView.findViewById(R.id.text1))
                        .setText(note.getNotes_type());
                ((TextView) convertView.findViewById(R.id.text2))
                        .setText(note.getNotes_text());
                return convertView;
        }
    }

    //Этот метод берёт данные из БД и формирует список, если данных нет, открывает форму для ввода данных
    private void getDataFromDB(){
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Note, Integer> noteDao = dbHelper.getNoteDao();
        try{
            notes = noteDao.queryForEq("date", dayMonthYear);

            for (Note note : notes) {
                Log.d("demo", note.toString() + "\n");
            }

            //List<Note> notes = noteDao.queryForAll();
            //Log.d("demo", notes.get(0).toString());
        }catch (Throwable t){
            Log.d("demo", "Argument for 'date' is null");
        }

        OpenHelperManager.releaseHelper();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        coordinatorLayout.setVisibility(View.VISIBLE);

    }
}
