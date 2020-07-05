package com.example.gcuitfesst;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pl.droidsonroids.gif.GifImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageScreen extends AppCompatActivity {
    EditText Username,Password;
    TextView login,Register;
    private FirebaseAuth firebaseAuth;
    GifImageView foodloader;
    public  static final String extravlue="com.example.gcuitfesst";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_screen);
        firebaseAuth=FirebaseAuth.getInstance();



    }
     void login_button(View view)
     {
         Username=(EditText) findViewById(R.id.Username);
         Password=(EditText) findViewById(R.id.Password);
         login=(TextView) findViewById(R.id.Login);

         final String username=Username.getText().toString().trim();
         String password=Password.getText().toString().trim();
         if(TextUtils.isEmpty(username))
         {
             Toast toast=Toast.makeText(LoginPageScreen.this,"Please enter Username",Toast.LENGTH_LONG);
             toast.show();
             return;

         }
         if(TextUtils.isEmpty(password))
         {
             Toast toast =Toast.makeText(LoginPageScreen.this,"Please enter Password",Toast.LENGTH_LONG);
             toast.show();
             return;

         }
         if(password.length()<6)
         {
             Toast toast=Toast.makeText(LoginPageScreen.this,"Password too short",Toast.LENGTH_LONG);
             toast.show();

         }
         login = (TextView) findViewById(R.id.Login);
         Register = (TextView) findViewById(R.id.Register);
         login.setVisibility(View.INVISIBLE);
         Register.setVisibility(View.INVISIBLE);
         foodloader = (GifImageView) findViewById(R.id.foodloader);
         foodloader.setVisibility(View.VISIBLE);

         firebaseAuth.signInWithEmailAndPassword(username, password)
                 .addOnCompleteListener(LoginPageScreen.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

                             Intent intent = new Intent(LoginPageScreen.this,Home.class);
                             intent.putExtra(extravlue,username);
                             startActivity(intent);
                             overridePendingTransition(R.anim.slide_left,R.anim.slide_out);
                             foodloader.setVisibility(View.GONE);
                             login.setVisibility(View.VISIBLE);
                             Register.setVisibility(View.VISIBLE);

                         } else {
                             Toast toast=Toast.makeText(LoginPageScreen.this,"Login Unsuccessful Or user not Available",Toast.LENGTH_LONG);
                             foodloader.setVisibility(View.GONE);
                             login.setVisibility(View.VISIBLE);
                             Register.setVisibility(View.VISIBLE);
                             toast.show();


                         }


                     }
                 });



     }
    void register_button(View view)
    {
        Intent register_page= new Intent(LoginPageScreen.this,Registration_page.class);
        startActivity(register_page);

    }

}
