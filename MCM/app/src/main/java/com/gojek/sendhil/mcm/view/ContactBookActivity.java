package com.gojek.sendhil.mcm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gojek.sendhil.mcm.ContactAdapter;
import com.gojek.sendhil.mcm.databinding.MainActivityBinding;
import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.viewmodel.ContactViewModel;
import com.gojek.sendhil.mcm.R;

import java.util.List;

public class ContactBookActivity extends AppCompatActivity implements ContactViewModel.DataListener {

    private MainActivityBinding binding;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        contactViewModel = new ContactViewModel(this, this);
        binding.setViewModel(contactViewModel);
        setSupportActionBar(binding.toolbar);
        setupRecyclerView(binding.contactsRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactViewModel.destroy();
    }

    @Override
    public void onContactsUpdated(List<Contact> contactList) {
        ContactAdapter adapter =
                (ContactAdapter) binding.contactsRecyclerView.getAdapter();
        adapter.setContacts(contactList);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        ContactAdapter adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
