package com.gojek.sendhil.mcm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gojek.sendhil.mcm.databinding.ItemContactBinding;
import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.viewmodel.ContactItemViewModel;

import java.util.Collections;
import java.util.List;



public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public ContactAdapter() {
        this.contactList = Collections.emptyList();
    }

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setContacts(List<Contact> contactList) {
        this.contactList = contactList;

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContactBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_contact,
                parent,
                false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bindContact(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        final ItemContactBinding binding;

        public ContactViewHolder(ItemContactBinding binding) {
            super(binding.layoutContent);
            this.binding = binding;
        }

        void bindContact(Contact contact) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ContactItemViewModel(itemView.getContext(), contact));
            } else {
                binding.getViewModel().setContact(contact);
            }
        }
    }
}
