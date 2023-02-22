package com.appsterlight.db.dao.mysql;

import com.appsterlight.db.dao.AbstractDao;
import com.appsterlight.db.dao.ApartmentPhotosDao;
import com.appsterlight.db.entity.ApartmentPhotos;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public class MySqlApartmentPhotosDao extends AbstractDao<ApartmentPhotos> implements ApartmentPhotosDao {

    public MySqlApartmentPhotosDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SQL_APARTMENT_PHOTOS_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_APARTMENT_PHOTOS_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_APARTMENT_PHOTOS_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_APARTMENT_PHOTOS_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_APARTMENT_PHOTOS_GET_ALL;
    }

    public List<String> getAllUrlOfPhotosById(Long id, Connection con) throws DaoException {
        List<String> urls = new ArrayList<>();

        try (PreparedStatement prst = con.prepareStatement(SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                urls.add(mapEntity(rs).getPath());
            }


        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return urls;
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, ApartmentPhotos object, boolean isUpdate) throws DaoException {
        int ind = 1;
        try {
            statement.setLong(ind++, object.getApartmentId());
            statement.setString(ind++, object.getPath());
            if (isUpdate) statement.setLong(ind++, object.getId());
        } catch (SQLException e) {
            log.error(STATEMENT_ERROR, e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    protected ApartmentPhotos mapEntity(ResultSet rs) throws DaoException {
        try {
            return  ApartmentPhotos.builder()
                    .id(rs.getLong(ID))
                    .apartmentId(rs.getLong(APARTMENT_PHOTOS_APARTMENT_ID))
                    .path(rs.getString(APARTMENT_PHOTOS_PATH))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }
}
