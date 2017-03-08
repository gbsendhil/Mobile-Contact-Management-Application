package com.gojek.sendhil.mcm;

import android.view.View;

import com.gojek.sendhil.mcm.model.Contact;
import com.gojek.sendhil.mcm.util.MockModelFabric;
import com.gojek.sendhil.mcm.viewmodel.ContactViewModel;
import com.gojek.sendhil.mcm.model.MCMService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;


import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ContactViewModelTest {

    MCMService MCMService;
    MCMApplication application;
    ContactViewModel contactViewModel;
    ContactViewModel.DataListener dataListener;

    @Before
    public void setUp() {
        MCMService = mock(MCMService.class);
        dataListener = mock(ContactViewModel.DataListener.class);
        application = (MCMApplication) RuntimeEnvironment.application;
        // Mock the retrofit service so we don't call the API directly
        application.setMCMService(MCMService);
        // Change the default subscribe schedulers so all observables
        // will now run on the same thread
        application.setDefaultSubscribeScheduler(Schedulers.immediate());
        contactViewModel = new ContactViewModel(application, dataListener);
    }


    @Test
    public void shouldLoadContacts() {
        List<Contact> mockContacts = MockModelFabric.newListOfContacts(10);
        doReturn(Observable.just(mockContacts)).when(MCMService).listContacts();

        MCMService.listContacts();
        verify(dataListener).onContactsUpdated(mockContacts);
        assertEquals(contactViewModel.infoMessageVisibility.get(), View.INVISIBLE);
        assertEquals(contactViewModel.progressVisibility.get(), View.INVISIBLE);
        assertEquals(contactViewModel.recyclerViewVisibility.get(), View.VISIBLE);
    }


}
