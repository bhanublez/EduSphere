package com.service.dynamic_view.Universal;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class holidayView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holidays);

        // Find the table layout
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Create dummy data
        String[] dates = {"2024-03-10", "2024-03-15", "2024-03-20"};
        String[] days = {"Monday", "Saturday", "Thursday"};
        String[] reasons = {"National Holiday", "Company Holiday", "Annual Leave"};

        // Add dummy data to the table
        for (int i = 0; i < dates.length; i++) {
            addRowToTable(tableLayout, dates[i], days[i], reasons[i]);
        }

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
