package com.example.demo;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    Button Login;

    boolean isValid=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Name = (EditText) findViewById(R.id.name);

        Password = (EditText) findViewById(R.id.pwd);
        Login = (Button) findViewById(R.id.button);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = Name.getText().toString();
                String userpassword = Password.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter the Username", Toast.LENGTH_SHORT).show();
                } else if (userpassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter the Password", Toast.LENGTH_SHORT).show();
                } else if(!PASSWORD_PATTERN.matcher(userpassword).matches())
                {
                    Password.setError("Password must contains a letter,a digit and a symbol[@#$&+]");
                }
                else{
                    isValid=getjson(username,userpassword);
                    /*isValid=validate(username,userpassword);*/
                    if(!isValid){
                        Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$&+])(?=\\S+$).{8,15}$");

    public boolean getjson(String uname,String upwd)
    {
        String json;
        int check=0;

        try {
            InputStream is=getAssets().open("demo.json");
            int size=is.available();
            byte[] buffer=new  byte[size];
            is.read(buffer);
            is.close();

            json=new String(buffer,"UTF-8");
            JSONArray jsonArray=new JSONArray(json);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj=jsonArray.getJSONObject(i);
                if(obj.getString("name").equals(uname))
                {
                    if(obj.getString("pwd").equals(upwd))
                    {
                        check=1;
                    }
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
        if(check==1)
        {
            return true;
        }
        else {
            return  false;
        }
    }
}