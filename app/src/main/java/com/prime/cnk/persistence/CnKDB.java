package com.prime.cnk.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.persistence.dao.NewProductDao;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

@Database(entities = {
        NewProductVO.class
}, version = 2, exportSchema = false)
public abstract class CnKDB extends RoomDatabase {
    private static final String DB_NAME = "CNK.DB";
    private static CnKDB INSTANCE;

    // DEFINE DAOs
    public abstract NewProductDao newProductDao();

    public static CnKDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, CnKDB.class, DB_NAME)
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build();
        }
        return INSTANCE;
    }
}
