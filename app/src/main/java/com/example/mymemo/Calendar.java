package com.example.mymemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView entryListView;
    private TextView noEntriesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Initialize views
        calendarView = findViewById(R.id.calendarView);
        entryListView = findViewById(R.id.entryListView);
        //noEntriesTextView = findViewById(R.id.noEntriesTextView);

        // Hide the entry list initially
        entryListView.setVisibility(View.GONE);

        // Set listener for calendar item click
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Handle date selection
                displayEntries(year, month, dayOfMonth);
            }
        });
    }

    private void displayEntries(int year, int month, int dayOfMonth) {
        // Dummy data - replace with actual data from the database
        List<String> entries = getEntriesForDate(year, month, dayOfMonth);

        if (entries.isEmpty()) {
            // Show a message if there are no entries for the selected date
            entryListView.setVisibility(View.GONE);
            noEntriesTextView.setVisibility(View.VISIBLE);
        } else {
            // Display the entries in the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entries);
            entryListView.setAdapter(adapter);
            entryListView.setVisibility(View.VISIBLE);
            noEntriesTextView.setVisibility(View.GONE);
        }
    }

    // (replace with actual database query)
    private List<String> getEntriesForDate(int year, int month, int dayOfMonth) {
        // For demonstration purposes, return some dummy data
        List<String> entries = new ArrayList<>();
        entries.add("Entry 1 for " + year + "-" + (month + 1) + "-" + dayOfMonth);
        entries.add("Entry 2 for " + year + "-" + (month + 1) + "-" + dayOfMonth);
        // Add more entries as needed
        return entries;
    }
}
