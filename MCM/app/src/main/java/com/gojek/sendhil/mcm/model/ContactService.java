package com.gojek.sendhil.mcm.model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ContactService {

    @GET("contacts.json")
    Observable<List<Contact>> listContacts();

    @GET("contacts/{id}.json")
    Observable<Contact> contactFromId(@Path("id") String id);

    @POST("/contacts.json")
    Observable<Contact> createContact(@Body Contact contact);

    class Factory {
        public static ContactService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://gojek-contacts-app.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ContactService.class);
        }
    }
}
