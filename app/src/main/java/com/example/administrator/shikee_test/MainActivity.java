package com.example.administrator.shikee_test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shikee_test.fragment.First;
import com.example.administrator.shikee_test.fragment.Fourth;
import com.example.administrator.shikee_test.fragment.Second;
import com.example.administrator.shikee_test.fragment.Third;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int  login_state = 0;
    public static MainActivity instance = null;
    private boolean isExit = false;
    private View fram1;
    private View fram2;
    private View fram3;
    private View fram4;
    private ImageView imgfram1;
    private ImageView imgfram2;
    private ImageView imgfram3;
    private ImageView imgfram4;
    private TextView textfram1;
    private TextView textfram2;
    private TextView textfram3;
    private TextView textfram4;
    private First first;
    private Second second;
    private Third third;
    private Fourth fourth;
    private TextView toolbar_text;

    private FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction transaction;

    private Handler mhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        toolbar.setTitle("");
        toolbar_text = (TextView) findViewById(R.id.toobar_text);
        toolbar_text.setText("首页");
        setSupportActionBar(toolbar);

        init();
        selectfram(0);

    }

    private void init() {
        instance = this;
        fram1 = findViewById(R.id.fragment1);
        fram2 = findViewById(R.id.fragment2);
        fram3 = findViewById(R.id.fragment3);
        fram4 = findViewById(R.id.fragment4);


        imgfram1 = (ImageView) findViewById(R.id.img_fram1);
        imgfram2 = (ImageView) findViewById(R.id.img_fram2);
        imgfram3 = (ImageView) findViewById(R.id.img_fram3);
        imgfram4 = (ImageView) findViewById(R.id.img_fram4);
        textfram1 = (TextView) findViewById(R.id.text_fram1);
        textfram2 = (TextView) findViewById(R.id.text_fram2);
        textfram3 = (TextView) findViewById(R.id.text_fram3);
        textfram4 = (TextView) findViewById(R.id.text_fram4);
        imgfram1.setImageResource(R.drawable.img_fram1_1);
        textfram1.setTextColor(Color.GREEN);


        fragmentManager = getSupportFragmentManager();
        fram1.setOnClickListener(this);
        fram2.setOnClickListener(this);
        fram3.setOnClickListener(this);
        fram4.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==2)
        {
            login_state=0;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exit0();
            return  true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit0(){
        if(!isExit)
        {
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
            mhandler.sendEmptyMessageDelayed(0,2000);
        }
        else
        {
            System.exit(0);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fragment1:
                selectfram(0);
                break;
            case R.id.fragment2:
                selectfram(1);
                break;
            case R.id.fragment3:
                selectfram(2);
                break;
            case R.id.fragment4:
                selectfram(3);
                break;
            default:
                break;

        }
    }

    private void selectfram(int index)
    {
        transaction = fragmentManager.beginTransaction();
        Hidefram(transaction);
        clearframSetting();
        switch (index)
        {
            case 0:
                toolbar_text.setText("首页");
                imgfram1.setImageResource(R.drawable.img_fram1_1);
                textfram1.setTextColor(Color.GREEN);
                if(first==null)
                {
                    first = new First();
                    transaction.add(R.id.fragment, first);
                }
                else
                    transaction.show(first);
                break;
            case 1:
                toolbar_text.setText("第二页");
                imgfram2.setImageResource(R.drawable.img_fram2_1);
                textfram2.setTextColor(Color.GREEN);
                if(second==null) {
                    second = new Second();
                    transaction.add(R.id.fragment,second);
                }
                else
                    transaction.show(second);

                break;
            case 2:
                toolbar_text.setText("第三页");
                imgfram3.setImageResource(R.drawable.img_fram3_1);
                textfram3.setTextColor(Color.GREEN);
                if(third==null)
                {
                    third = new Third();
                    transaction.add(R.id.fragment,third);
                }
                else
                    transaction.show(third);
                break;
            case 3:
                toolbar_text.setText("第四页");
                imgfram4.setImageResource(R.drawable.img_fram4_1);
                textfram4.setTextColor(Color.GREEN);
                if(fourth==null)
                {
                    fourth = new Fourth();
                    transaction.add(R.id.fragment,fourth);
                }
                else
                    transaction.show(fourth);
                break;
            default:
                break;
        }
        transaction.commit();

    }
    private void Hidefram(android.support.v4.app.FragmentTransaction transaction)
    {
        if(first!=null)
            transaction.hide(first);
        if(second!=null)
            transaction.hide(second);
        if(third!=null)
            transaction.hide(third);
        if(fourth!=null)
            transaction.hide(fourth);

    }

    private void clearframSetting()
    {
        imgfram1.setImageResource(R.drawable.img_fram1_2);
        textfram1.setTextColor(getResources().getColor(R.color.fram_default));
        imgfram2.setImageResource(R.drawable.img_fram2_2);
        textfram2.setTextColor(getResources().getColor(R.color.fram_default));
        imgfram3.setImageResource(R.drawable.img_fram3_2);
        textfram3.setTextColor(getResources().getColor(R.color.fram_default));
        imgfram4.setImageResource(R.drawable.img_fram4_2);
        textfram4.setTextColor(getResources().getColor(R.color.fram_default));
    }
}


