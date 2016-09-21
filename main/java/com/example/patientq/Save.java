package com.example.patientq;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
        import android.widget.Toast;

        import com.itextpdf.text.pdf.parser.Line;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class Save extends Activity {

    Bitmap myBitmap;
    String[] treu = new String[100];
    String[] maybe = new String[100];
    String[] no = new String[100];
    String tru = "";
    int j, k, m;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Intent intent = getIntent();
        treu = intent.getStringArrayExtra("key1");
        maybe = intent.getStringArrayExtra("key2");
        no = intent.getStringArrayExtra("key3");
        j = intent.getIntExtra("key4", 0);
        k = intent.getIntExtra("key5", 0);
        m = intent.getIntExtra("key6", 0);

        ScrollView sv=(ScrollView)findViewById(R.id.scrollView);
        RelativeLayout yes = (RelativeLayout) findViewById(R.id.topright);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        TextView t = new TextView(Save.this);
        for (int i = 0; i < j; i++) {
            tru = tru + treu[i] + "\n";
        }
        t.setText(tru);
        t.setLayoutParams(lp);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        sv.addView(t);

        tru = "";
        RelativeLayout maybee = (RelativeLayout) findViewById(R.id.linearLayout);
        ScrollView sv2=(ScrollView)findViewById(R.id.scrollView2);
        RelativeLayout.LayoutParams lp1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.CENTER_IN_PARENT);
        TextView t1 = new TextView(Save.this);
        for (int i = 0; i < k; i++) {
            tru = tru + maybe[i] + "\n";
        }
        t1.setText(tru);
        t1.setLayoutParams(lp1);
        t1.setGravity(Gravity.CENTER);
        t1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        sv2.addView(t1);

        tru = "";
        RelativeLayout noo = (RelativeLayout) findViewById(R.id.topleft);
        ScrollView sv3=(ScrollView)findViewById(R.id.scrollView3);
        RelativeLayout.LayoutParams lp2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT);
        TextView t2 = new TextView(Save.this);
        for (int i = 0; i < m; i++) {
            tru = tru + no[i] + "\n";
        }
        t2.setText(tru);
        t2.setLayoutParams(lp2);
        t2.setGravity(Gravity.CENTER);
        t2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        sv3.addView(t2);

        verifyStoragePermissions(Save.this);
        Button screen = (Button) findViewById(R.id.button6);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Save.this);
                builder1.setMessage("Data Is Exported as Image");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {

        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}