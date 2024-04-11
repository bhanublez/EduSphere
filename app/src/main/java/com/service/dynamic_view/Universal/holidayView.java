package com.service.dynamic_view.Universal;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class holidayView extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private FirebaseUser currentUser ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holidays);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Find the table layout
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

// Get reference to the "Holiday" node in Firebase Realtime Database
        DatabaseReference holidayRef = FirebaseDatabase.getInstance().getReference().child("Holiday");

// Fetch data from Firebase
        holidayRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Data retrieval is successful
                DataSnapshot dataSnapshot = task.getResult();

                if (dataSnapshot.exists()) {
                    // Iterate through each child node (holiday) in the "Holiday" node
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Get holiday details from each child
                        String name = childSnapshot.child("Name").getValue(String.class);
                        String date = childSnapshot.child("Date").getValue(String.class);
                        String type = childSnapshot.child("Type").getValue(String.class);

                        // Now you can use the retrieved holiday details as needed
//                        System.out.println("Holiday Name: " + name);
//                        System.out.println("Date: " + date);
//                        System.out.println("Type: " + type);
                        addRowToTable(tableLayout, date, name, type);
                    }
                } else {
                    // "Holiday" node does not exist or is empty
                    Toast.makeText(this, "No Holiday Founded", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Data retrieval failed
                Exception exception = task.getException();
                if (exception != null) {
                    System.out.println("Error fetching holidays: " + exception.getMessage());
                } else {
                    System.out.println("Error fetching holidays: Unknown error.");
                }
            }
        });

        // Set onClickListener for the back icon
        ImageView back = findViewById(R.id.back_icon);
        back.setOnClickListener(v -> {
            finish();
        });
    }

    // Method to add a row to the table
    private void addRowToTable(TableLayout tableLayout, String date, String day, String reason) {
        // Create a new row
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Add date TextView to the row
        TextView dateTextView = new TextView(this);
        dateTextView.setText(date);
        dateTextView.setPadding(10, 10, 10, 10);
        row.addView(dateTextView);

        // Add day TextView to the row
        TextView dayTextView = new TextView(this);
        dayTextView.setText(day);
        dayTextView.setPadding(10, 10, 10, 10);
        row.addView(dayTextView);

        // Add reason TextView to the row
        TextView reasonTextView = new TextView(this);
        reasonTextView.setText(reason);
        reasonTextView.setPadding(10, 10, 10, 10);
        row.addView(reasonTextView);

        // Add the row to the table
        tableLayout.addView(row);
    }
}
