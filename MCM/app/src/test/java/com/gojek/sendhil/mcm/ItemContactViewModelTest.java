package com.gojek.sendhil.mcm;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.view.View;

import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.viewmodel.ContactItemViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemContactViewModelTest {

    MCMApplication application;

    @Before
    public void setUp() {
        application = (MCMApplication) RuntimeEnvironment.application;
    }

    @Test
    public void shouldGetContactFirstName() {
        Contact contact = new Contact();
        contact.setFirstName("sendhil");
        contact.setLastName("gb");
        ContactItemViewModel itemRepoViewModel = new ContactItemViewModel(application, contact);
        assertEquals(contact.getFirstName() + " " + contact.getLastName(), itemRepoViewModel.getContactDisplayName());
    }

    @Test
    public void shouldGetEmail() {
        Contact contact = new Contact();
        contact.setEmail("gbsendhil@gmail.com");
        ContactItemViewModel itemRepoViewModel = new ContactItemViewModel(application, contact);
        assertEquals(contact.getEmail(), itemRepoViewModel.getEmail());
    }


    @Test
    public void shouldStartActivityOnItemClick() {
        Contact contact = new Contact();
        Context mockContext = mock(Context.class);
        ContactItemViewModel itemRepoViewModel = new ContactItemViewModel(mockContext, contact);
        itemRepoViewModel.onItemClick(new View(application));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetContact() {
        Contact contact = new Contact();
        ContactItemViewModel itemContactViewModel = new ContactItemViewModel(application, contact);
        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);
        itemContactViewModel.addOnPropertyChangedCallback(mockCallback);

        itemContactViewModel.setContact(contact);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
