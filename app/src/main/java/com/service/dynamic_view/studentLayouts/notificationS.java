package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class notificationS extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout = findViewById(R.id.viewContainer);
        back = findViewById(R.id.back_icon);
        title_id = findViewById(R.id.title_id);
        title_id.setText("Notification");

        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }
    @SuppressLint("MissingInflatedId")
    public void addCards() {

        String notT, assDate, sheduleTime, content;
        notT = "Notification 1";
        assDate = "2021-07-01";
        sheduleTime = "12:00";
        content = "This is a notification for the students to submit their assignments on time.";
        addCard(notT, assDate, sheduleTime, content);

    }

    private void addCard(String name, String adate, String stime, String data) {


        final View view2 =getLayoutInflater().inflate(R.layout.note_dialog,null);

//

        TextView notT = view2.findViewById(R.id.notification_ID);
        TextView assDate = view2.findViewById(R.id.date_assigned);
        TextView sheduleTime = view2.findViewById(R.id.sheduleTimeID);
        TextView content = view2.findViewById(R.id.txtArea_ve);
        ImageView delete = view2.findViewById(R.id.del_e);

        // Set values to respective TextViews
        notT.setText(name);
        assDate.setText(adate);
        sheduleTime.setText(stime);

        content.setText(data);
        content.setEnabled(false);
        //Remove the view of delete ImageView
        delete.setVisibility(View.GONE);
        delete.setEnabled(false);

        layout.addView(view2);
    }
}
