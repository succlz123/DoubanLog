package org.succlz123.doubanbooklog;

import android.app.Application;
import org.succlz123.doubanbooklog.bean.DoubanAccount;
import db.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/3/29.
 */
public class DoubanApplication extends Application {

    public static interface AccountChangeListener {
        public void onAccount(DoubanAccount account);
    }

    private static DoubanApplication instance;

    private DoubanAccount account;
    private List<AccountChangeListener> accountChangeListenerList = new ArrayList<AccountChangeListener>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        List<Book> bookList = ReadingApi.getBooks("");
    }

    public static DoubanApplication getInstance() {
        return instance;
    }

    public DoubanAccount getAccount() {
        if (this.account == null) {
            this.account = MyDatabaseHelper.getInstance().get();
        }
        return this.account;
    }

    public void addAccount(DoubanAccount account) {
        this.account = account;
        MyDatabaseHelper.getInstance().insert(account);
        for (AccountChangeListener listener : accountChangeListenerList) {
            listener.onAccount(account);
        }
    }

    public void addAccountListener(AccountChangeListener listener) {
        this.accountChangeListenerList.add(listener);
    }

    public void removeAccountListener(AccountChangeListener listener) {
        this.accountChangeListenerList.remove(listener);
    }

}
