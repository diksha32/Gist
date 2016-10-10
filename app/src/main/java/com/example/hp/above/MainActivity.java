package com.example.hp.above;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    private Button mSendData;
    private Firebase mRef1,mRef2;
    private Button mSelectImage;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;
    private Button mUploadBttn;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
              this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Firebase.setAndroidContext(this);

        mRef1 = new Firebase("https://gist-d99b4.firebaseio.com/");
        mSendData = (Button) findViewById(R.id.SendData);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase mRefChild= mRef1.child("English");

                mRefChild.setValue("EnglishContent");

            }
        });

        mRef2 = new Firebase("https://gist-d99b4.firebaseio.com/");
        mSendData = (Button) findViewById(R.id.SendData);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase mRefChild= mRef2.child("Logical Reasoning");

                mRefChild.setValue("LRContent");


            }
        });


        mStorage = FirebaseStorage.getInstance().getReference();

        mSelectImage = (Button) findViewById(R.id.SelectImage);

        mProgressDialog = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT );
            }
        });

        mUploadBttn = (Button) findViewById(R.id.UploadImage);
        mImageView =(ImageView) findViewById(R.id.imageView);

        mUploadBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT );
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT &&  resultCode==RESULT_OK)
        {
            mProgressDialog.setMessage("Uploading...");

            mProgressDialog.show();

            Uri uri= data.getData();

            StorageReference filePath = mStorage.child("EnglishPhotos").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(MainActivity.this, "Upload Done." , Toast.LENGTH_LONG).show();

                    mProgressDialog.dismiss();

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            // Handle the camera action
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);


        } else if (id == R.id.help) {
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }

        else if (id == R.id.feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:gist2908@gmail.com")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Gist");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void openaptitude(View view)
    {
        Intent i = new Intent(this, AptitudeActivity.class);
        startActivity(i);
    }

    public void openreasoning(View view)
    {
        Intent i = new Intent(this, ReasoningActivity.class);
        startActivity(i);
    }

    public void openenglish(View view)
    {
        Intent i = new Intent(this, EnglishActivity.class);
        startActivity(i);
    }

    public void opends(View view)
    {
        Intent i = new Intent(this, DataStrucActivity.class);
        startActivity(i);
    }

    public void openalgorithm(View view)
    {
        Intent i = new Intent(this, AlgorithmsActivity.class);
        startActivity(i);
    }

    public void openprogramming(View view)
    {
        Intent i = new Intent(this, ProgrammingActivity.class);
        startActivity(i);
    }

    public void openoops(View view)
    {
        Intent i = new Intent(this, OopsActivity.class);
        startActivity(i);
    }

    public void opendbms(View view)
    {
        Intent i = new Intent(this, DbmsActivity.class);
        startActivity(i);
    }

    public void openos(View view)
    {
        Intent i = new Intent(this, OperatingSysActivity.class);
        startActivity(i);
    }
    public void opencn(View view)
    {
        Intent i = new Intent(this, ComputerNetworksActivity.class);
        startActivity(i);
    }


}
