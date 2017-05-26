package com.xstock.realm;

import com.xstock.models.GetTradeListItem;
import com.xstock.models.UserDetail;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    public RealmController() {
    }

    //Refresh the realm instance
    public void refresh(Realm realm) {

        realm.setAutoRefresh(true);
    }

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

    //query a single item with the given id
    public UserDetail getUserDetail(Realm realm, String id) {

        return realm.where(UserDetail.class).equalTo("id", id).findFirst();
    }

    //check if UserDetail.class is empty
    public boolean hasUserDetail(Realm realm) {

        return !realm.isEmpty();
    }

    //query example
    public RealmResults<UserDetail> queryUserDetail(Realm realm) {

        return realm.where(UserDetail.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }

//    public RealmList<GetTradeListItem> getTradeList(Realm realm) {
//        RealmResults<GetTradeListItem> GetTradeListItem = realm.where(GetTradeListItem.class).findAll();
//        RealmList<GetTradeListItem> GetTradeListItems = new RealmList<GetTradeListItem>();
//        GetTradeListItems.addAll(GetTradeListItem);
//        return GetTradeListItems;
//    }

//    public RealmResults<GetTradeListItem> getTradeList(Realm realm) {
//        RealmResults<GetTradeListItem> getTradeListItem = realm.where(GetTradeListItem.class).findAll();
//        int count = getTradeListItem.size();
//        for (int i = 0; i < count; i++) {
//            String name = getTradeListItem.get(i).getName();
////            String companyname = getTradeListItem.get(i).getCompany_name();
//        }
//        return getTradeListItem;
//    }
//
//    //clear all objects from GetTradeList.class
//    public void clearGetTradeList(Realm realm) {
//
//        realm.beginTransaction();
////        realm.delete(GetTradeListItem.class);
//        realm.commitTransaction();
//    }
}
