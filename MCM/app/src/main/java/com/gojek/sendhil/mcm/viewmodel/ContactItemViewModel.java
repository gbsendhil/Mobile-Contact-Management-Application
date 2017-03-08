package com.gojek.sendhil.mcm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.viewmodel.ViewModel;



/**
 * View model for each item in the contacts RecyclerView
 */
public class ContactItemViewModel extends BaseObservable implements ViewModel {

    private Contact contact;
    private Context context;

    public ContactItemViewModel(Context context, Contact contact) {
        this.contact = contact;
        this.context = context;
    }

    public String getProfilePic(){
        return contact.getProfilePic();
    }

    public String getContactDisplayName() {
        return contact.getFirstName() + " " + contact.getLastName();

    }

    public String getPhoneNumber() {
        return contact.getPhoneNumber();
    }

    public String getEmail() {
        return  contact.getEmail();
    }



    public void onItemClick(View view) {
        //TODO handle on click of item
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        notifyChange();
    }

    @Override
    public void destroy() {

    }

}
