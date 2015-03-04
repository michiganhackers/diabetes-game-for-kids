package com.michiganhackers.gamediabetesparentandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mail_000 on 3/3/2015.
 */
public class Login extends ActionBarActivity {

        EditText name, email, pwd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_screen);

            name = ( EditText ) findViewById( R.id.ParentName );
            email = ( EditText ) findViewById( R.id.ParentEmail );
            pwd = ( EditText ) findViewById( R.id.ParentPassword );
            Button endBtn = ( Button ) findViewById( R.id.button );

        }

        public void buttonOnClick ( View v ) {
            Button endBtn = (Button ) v;

            //to get to next view
           // startActivity( new Intent( getApplicationContext(), Activityname.class ));
           //https://www.youtube.com/watch?v=KhSM_CRCLRo
        }
}

