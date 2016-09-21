package com.example.patientq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DragActivity extends Activity {
    public int i=0,j=0,k=0,m=0;
    String text;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag);
        findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.myimage5).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
        findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
        findViewById(R.id.topright).setOnDragListener(new MyDragListener());
        findViewById(R.id.linearLayout).setOnDragListener(new MyDragListener());
        findViewById(R.id.rellay).setOnDragListener(new MyDragListener());

        final TextView[] txt=new TextView[100];

        Button add=(Button)findViewById(R.id.button6);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text="";
                AlertDialog.Builder builder = new AlertDialog.Builder(DragActivity.this);
                builder.setTitle("Enter the Activity Name");
                final EditText input = new EditText(DragActivity.this);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text = input.getText().toString();
                        txt[i]=new TextView(DragActivity.this);
                        txt[i].setText(text);
                        txt[i].setId(i);
                        txt[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.blank,0,0);
                        int l=4;
                        txt[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        txt[i].setOnTouchListener(new MyTouchListener());
                        RelativeLayout ll=(RelativeLayout)findViewById(R.id.bottomright);
                        ll.addView(txt[i]);
                        i++;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });


        Button save = (Button) findViewById(R.id.button5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] treu=new String[100];
                String[] maybe=new String[100];
                String[] no=new String[100];
                j=0;
                k=0;
                m=0;
                RelativeLayout l = (RelativeLayout) findViewById(R.id.topright);
                TextView t = (TextView) l.findViewById(R.id.myimage2);
                TextView t1 = (TextView) l.findViewById(R.id.myimage5);
                TextView t2 = (TextView) l.findViewById(R.id.myimage4);
                TextView[] t3 =new TextView[100];
                for(int p=0;p<i;p++) {
                    t3[p]= (TextView) l.findViewById(txt[p].getId());
                }
                if (t != null) {
                    treu[j] = t.getText().toString();
                    j++;
                }
                if (t1 != null)
                {
                    treu[j]=t1.getText().toString();
                    j++;
                }
                if (t2 != null)
                {
                    treu[j]=t2.getText().toString();
                    j++;
                }
                for(int p=0;p<i;p++) {
                    if (t3[p] != null) {
                        treu[j] = t3[p].getText().toString();
                        j++;
                    }
                }

                RelativeLayout l1 = (RelativeLayout) findViewById(R.id.linearLayout);
                TextView im = (TextView) l1.findViewById(R.id.myimage2);
                TextView im1 = (TextView) l1.findViewById(R.id.myimage5);
                TextView im2 = (TextView) l1.findViewById(R.id.myimage4);
                 TextView im3[]=new TextView[100];
                for(int p=0;p<i;p++) {
                    im3[p] = (TextView) l1.findViewById(txt[p].getId());
                }

                if (im != null) {
                        maybe[k] = im.getText().toString();
                        k++;
                    }
                if (im1 != null)
                {
                    maybe[k]=im1.getText().toString();
                    k++;
                }
                if (im2 != null)
                {
                    maybe[k]=im2.getText().toString();
                    k++;
                }
                for(int p=0;p<i;p++) {
                    if (im3[p] != null) {
                        maybe[k] = im3[p].getText().toString();
                        k++;
                    }
                }

                RelativeLayout l2 = (RelativeLayout) findViewById(R.id.topleft);
                TextView tp = (TextView) l2.findViewById(R.id.myimage2);
                TextView tp1 = (TextView) l2.findViewById(R.id.myimage5);
                TextView tp2 = (TextView) l2.findViewById(R.id.myimage4);
                 TextView tp3[]=new TextView[100];
                for(int p=0;p<i;p++) {
                   tp3[p]= (TextView) l2.findViewById(txt[p].getId());
                }
                if (tp != null) {
                    no[m] = tp.getText().toString();
                    m++;
                }
                if (tp1 != null)
                {
                    no[m]=tp1.getText().toString();
                    m++;
                }
                if (tp2 != null)
                {
                    no[m]=tp2.getText().toString();
                    m++;
                }
                for(int p=0;p<i;p++) {
                    if (tp3[p] != null) {
                        no[m] = tp3[p].getText().toString();
                        m++;
                    }
                }

               // Toast.makeText(DragActivity.this,Integer.toString(j)+Integer.toString(k)+Integer.toString(m),Toast.LENGTH_SHORT).show();

               Intent ii = new Intent (DragActivity.this, Save.class);
                ii.putExtra("key1", treu);
                ii.putExtra("key2", maybe);
                ii.putExtra("key3",no);
                ii.putExtra("key4",j);
                ii.putExtra("key5",k);
                ii.putExtra("key6",m);
                startActivity(ii);

            }
        });

    }

    private final class MyTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    RelativeLayout container = (RelativeLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

}
