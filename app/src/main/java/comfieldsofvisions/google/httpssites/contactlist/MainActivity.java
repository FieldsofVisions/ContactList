package comfieldsofvisions.google.httpssites.contactlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView contactListView;
    EditText name, phone, email, address;
    ImageView contactImage;
    List<Contacts> contact = new ArrayList<Contacts>();
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText) findViewById(R.id.editTextName);
        phone = (EditText) findViewById(R.id.editTextPhone);
        email = (EditText) findViewById(R.id.editTextEmail);
        address = (EditText) findViewById(R.id.editTextAddress);
        contactImage = (ImageView) findViewById(R.id.add_image);
        final Button addButton = (Button) findViewById(R.id.addButton);
        contactListView = (ListView) findViewById(R.id.list);




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact(name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), address.getText().toString(), image);

                populateList();

                Toast.makeText(getApplicationContext(),
                        name.getText().toString() + "Has been added to your contacts", Toast.LENGTH_SHORT).show();
            }
        });
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
//This sets up the tabs
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("list");
        tabHost.addTab(tabSpec);


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(!name.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Your image"), 1);
            }
        });
    }
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if (resCode == RESULT_OK){
            if (reqCode == 1)
                image = data.getData();
                contactImage.setImageURI(data.getData());
        }

    }
private void populateList(){
    ArrayAdapter<Contacts> adapter = new ContactListAdapter();
    contactListView.setAdapter(adapter);
}
    private void addContact(String name, String phone, String email, String address, Uri image) {
        contact.add(new Contacts(name, phone, email, address,image ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ContactListAdapter extends ArrayAdapter<Contacts> {
        public ContactListAdapter() {
            super(MainActivity.this, R.layout.contact_list, contact);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null)
                v = getLayoutInflater().inflate(R.layout.contact_list, parent, false);

            Contacts currentContact = contact.get(position);

            TextView name = (TextView) v.findViewById(R.id.textViewName);
            name.setText(currentContact.get_name());

            TextView phone = (TextView) v.findViewById(R.id.textViewPhone);
            phone.setText(currentContact.get_phone());

            TextView email = (TextView) v.findViewById(R.id.textViewEmail);
            email.setText(currentContact.get_email());

            TextView address = (TextView) v.findViewById(R.id.textViewAddress);
            address.setText(currentContact.get_address());

            ImageView contactImageView = (ImageView)v.findViewById(R.id.image);
            contactImageView.setImageURI(currentContact.get_image());

            return v;

        }

    }
}
