package com.example.oslotest;

import android.util.Log;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;

public class Application extends android.app.Application{
    private static final String TAG = Application.class.getSimpleName();

    private final static boolean LOGIN_FLOW_ENABLED = true;
    private final static boolean SYNC_ENABLED = true;

    private final static String DATABASE_NAME = "Oslo";
    private final static String SYNCGATEWAY_URL = "ws://127.0.0.1:8091/beer-sample/";

    private Database database = null;
    private Replicator replicator;
    private String username = DATABASE_NAME;

    private Database backup = null;
    private Replicator backupReplicator = null;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LOGIN_FLOW_ENABLED)
//            showLoginUI();
//        else
    }

}
