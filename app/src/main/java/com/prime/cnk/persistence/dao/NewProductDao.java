package com.prime.cnk.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.prime.cnk.data.vo.NewProductVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

@Dao
public interface NewProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewProduct(List<NewProductVO> newProductVOList);

    @Query("SELECT * FROM newProducts")
    List<NewProductVO> getAllNewProduct();

    @Query("SELECT * FROM newProducts WHERE productId = :productId")
    NewProductVO getNewProductById(int productId);

    @Query("SELECT * FROM newProducts WHERE productId = :productId")
    LiveData<NewProductVO> getNewProductByIdLD(int productId);
}
