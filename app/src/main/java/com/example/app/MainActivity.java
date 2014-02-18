package com.example.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.app.Contracts.UserDataContract;
import com.example.app.Repository.UserDataRepository;
import com.example.app.services.ImageHandler;

public class MainActivity extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    // Define a DialogFragment that displays the error dialog
/*    public static class ErrorDialogFragmet extends DialogFragment{

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            Bitmap imageBitmap = ImageHandler.extractImage(data);
            ImageView mImageView = (ImageView) findViewById(R.id.ImageView);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    public void uploadImage(View view){
        Intent takePicktureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicktureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicktureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void saveUserInput(View view)
    {
        UserDataRepository dbHelper = new UserDataRepository(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // extract data
        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText firstName = (EditText) findViewById(R.id.FirstName);
        EditText lastName = (EditText) findViewById(R.id.LastName);
        EditText age = (EditText) findViewById(R.id.Age);

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserDataContract.UserDataEntry.COLUMN_NAME_ENTRY_ID, 1);
        values.put(UserDataContract.UserDataEntry.COLUMN_NAME_USERNAME, userName.getText().toString());
        values.put(UserDataContract.UserDataEntry.COLUMN_NAME_FIRSTNAME, firstName.getText().toString());
        values.put(UserDataContract.UserDataEntry.COLUMN_NAME_LASTNAME, lastName.getText().toString());
        values.put(UserDataContract.UserDataEntry.COLUMN_NAME_AGE, age.getText().toString());

        // Insert the new row, returning the primary key value of the new row

        long newRowId;
        newRowId = db.insert(UserDataContract.UserDataEntry.TABLE_NAME,
                   UserDataContract.UserDataEntry.COLUMN_NAME_NULLABLE,
                   values);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
