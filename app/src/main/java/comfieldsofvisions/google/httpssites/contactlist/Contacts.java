package comfieldsofvisions.google.httpssites.contactlist;

import android.net.Uri;

/**
 * Created by Andre on 6/28/2015.
 */
public class Contacts {

    private String _name, _phone, _email, _address;
    private Uri _image;


    public Contacts(String name, String phone, String email, String address, Uri image) {
        _name = name;
        _phone = phone;
        _email = email;
        _address = address;
        _image = image;

    }

    public String get_name() {
        return _name;
    }

    public String get_phone() {
        return _phone;
    }

    public String get_email() {
        return _email;
    }

    public String get_address() {
        return _address;
    }

    public Uri get_image() {
        return _image;
    }
}

