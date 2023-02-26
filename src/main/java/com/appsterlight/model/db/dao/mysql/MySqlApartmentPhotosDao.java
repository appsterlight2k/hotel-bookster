package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.ApartmentPhotosDao;
import com.appsterlight.model.domain.ApartmentPhotos;
import com.appsterlight.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlApartmentPhotosDao extends AbstractDao<ApartmentPhotos> implements ApartmentPhotosDao {
    private final Connection connection;

    public MySqlApartmentPhotosDao(Connection connection) {
        super(connection);
        this.connection = connection;
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


    @Override
    public Long add(ApartmentPhotos object) throws DaoException {
        Long id = super.add(object);
        object.setId(id);

        return id;
    }

    public List<String> getAllUrlOfPhotosById(Long id) throws DaoException {
        List<String> urls = new ArrayList<>();

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID)) {
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
    protected Object[] getAllFieldsOfObject(ApartmentPhotos object) throws DaoException {
        if (object == null) throw new DaoException("Booking object is null! Can't get fields!");

        return new Object[]{ object.getApartmentId(), object.getPath(), object.getId() };
    }

    @Override
    protected ApartmentPhotos mapEntity(ResultSet rs) throws DaoException {
        try {
            return  ApartmentPhotos.builder()
                    .id(rs.getLong(Fields.ID))
                    .apartmentId(rs.getLong(Fields.APARTMENT_PHOTOS_APARTMENT_ID))
                    .path(rs.getString(Fields.APARTMENT_PHOTOS_PATH))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }
}
