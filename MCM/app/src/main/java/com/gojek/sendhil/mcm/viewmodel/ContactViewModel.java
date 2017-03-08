package com.gojek.sendhil.mcm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.gojek.sendhil.mcm.MCMApplication;
import com.gojek.sendhil.mcm.R;
import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.model.MCMService;

import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * View model for the ContactBookActivity
 */
public class ContactViewModel implements ViewModel {

    private static final String TAG = "ContactViewModel";

    public ObservableInt infoMessageVisibility;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerViewVisibility;
    public ObservableField<String> infoMessage;

    private Context context;
    private Subscription subscription;
    private List<Contact> contactList;
    private DataListener dataListener;
    private String editTextUsernameValue;

    public ContactViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        infoMessageVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.INVISIBLE);
        recyclerViewVisibility = new ObservableInt(View.INVISIBLE);
        infoMessage = new ObservableField<>(context.getString(R.string.default_info_message));

        loadContacts();
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = null;
        context = null;
        dataListener = null;
    }

    private void loadContacts() {
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.INVISIBLE);
        infoMessageVisibility.set(View.INVISIBLE);
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        MCMApplication application = MCMApplication.get(context);
        MCMService MCMService = application.getMCMService();
        subscription = MCMService.listContacts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<List<Contact>>() {
                    @Override
                    public void onCompleted() {
                        if (dataListener != null) dataListener.onContactsUpdated(contactList);
                        progressVisibility.set(View.INVISIBLE);
                        if (!contactList.isEmpty()) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        } else {
                            infoMessage.set(context.getString(R.string.text_empty_contacts));
                            infoMessageVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading Contacts ", error);
                        progressVisibility.set(View.INVISIBLE);
                        if (isHttp404(error)) {
                            infoMessage.set(context.getString(R.string.error_loading_contacts));
                        } else {
                            infoMessage.set(context.getString(R.string.error_loading_contacts));
                        }
                        infoMessageVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<Contact> contactList) {
                        Log.i(TAG, "Contacts loaded " + contactList);
                        ContactViewModel.this.contactList = contactList;
                    }
                });
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }

    public interface DataListener {
        void onContactsUpdated(List<Contact> contacts);
    }
}
