package com.xstock.realm;

import com.xstock.models.UserDetail;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    //clear all objects from UserDetail.class
    public void clearUserDetail(Realm realm) {
        realm.beginTransaction();
        realm.delete(UserDetail.class);
        realm.commitTransaction();
    }

    //find all objects in the UserDetail.class
    public RealmResults<UserDetail> getUserDetail(Realm realm) {
        return realm.where(UserDetail.class).findAll();
    }
}
