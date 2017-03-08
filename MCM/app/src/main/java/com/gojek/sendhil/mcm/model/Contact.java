
package com.gojek.sendhil.mcm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Contact VO
 */
public class Contact implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("first_name")

    private String firstName;
    @SerializedName("last_name")

    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("profile_pic")
    private String profilePic;
    @SerializedName("favorite")
    private Boolean favorite;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.firstName);
        dest.writeString(this.email);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.profilePic);
        dest.writeByte(favorite ? (byte) 1 : (byte) 0);
        dest.writeString(this.createdAt);
    }

    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.firstName = in.readString();
        this.email = in.readString();
        this.phoneNumber = in.readString();
        this.profilePic = in.readString();
        this.favorite = in.readByte() != 0;
        this.createdAt = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


}
