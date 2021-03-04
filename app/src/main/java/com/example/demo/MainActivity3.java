package com.example.demo;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.media.tv.TvContract;
        import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {
    private int sleeptimer=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Logolauncher logolauncher=new Logolauncher();
        logolauncher.start();
    }
    private class Logolauncher extends Thread{
        public void run(){
            try{
                sleep(1000*sleeptimer);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            Intent intent=new Intent(MainActivity3.this,MainActivity.class);
            startActivity(intent);
            MainActivity3.this.finish();
        }
    }
}