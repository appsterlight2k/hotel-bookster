package com.appsterlight.db.dao.impl;

import com.appsterlight.db.dao.ApartmentPhotosDao;
import com.appsterlight.db.entity.ApartmentPhotos;
import com.appsterlight.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.appsterlight.Messages.*;
import static com.appsterlight.db.Fields.*;
import static com.appsterlight.db.Queries.*;

@Slf4j
public class ApartmentPhotosDaoImpl implements ApartmentPhotosDao {
    private final Connection connection;

    public ApartmentPhotosDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(ApartmentPhotos apartment) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_INSERT,
                                                                    Statement.RETURN_GENERATED_KEYS)) {
            setPrStParametersForEntity(prst, apartment, false);
            result = prst.executeUpdate() > 0;
            if (result) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    apartment.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error(INSERT_ERROR, e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<ApartmentPhotos> get(Long id) throws DaoException {
        ApartmentPhotos apartmentPhotos = null;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                apartmentPhotos = mapEntity(rs);
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(apartmentPhotos);
    }

    @Override
    public boolean update(ApartmentPhotos apartmentPhotos) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_UPDATE)) {
            setPrStParametersForEntity(prst, apartmentPhotos, true);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(UPDATE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result;

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(DELETE_ERROR, e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<ApartmentPhotos> getAll() throws DaoException {
        List<ApartmentPhotos> apartmentPhotos = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_APARTMENT_PHOTOS_GET_ALL);
            while (rs.next()) {
                apartmentPhotos.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return apartmentPhotos;
    }

    @Override
    public List<String> getAllUrlOfPhotosById(Long id) throws DaoException {
        List<String> urls = new ArrayList<>();

        try (PreparedStatement prst = connection.prepareStatement(SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID)) {
            /*Statement st = connection.createStatement();
            st.setLong(1, id);
            ResultSet rs = st.executeQuery(SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID);*/
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                urls.add(mapEntity(rs).getPath());
            }

           /* Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_APARTMENT_PHOTOS_GET_ALL_PHOTOS_BY_ID);*/
            /*while (rs.next()) {
                urls.add(mapEntity(rs).getPath());
            }*/
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return urls;
    }

    private ApartmentPhotos mapEntity(ResultSet rs) throws SQLException {
        return  ApartmentPhotos.builder()
                .id(rs.getLong(ID))
                .apartmentId(rs.getLong(APARTMENT_PHOTOS_APARTMENT_ID))
                .path(rs.getString(APARTMENT_PHOTOS_PATH))
                .build();
    }

    private void setPrStParametersForEntity(PreparedStatement prst, ApartmentPhotos apartmentPhotos, boolean is_update) throws SQLException {
        int ind = 1;
        prst.setLong(ind++, apartmentPhotos.getApartmentId());
        prst.setString(ind++, apartmentPhotos.getPath());
        if (is_update) prst.setLong(ind++, apartmentPhotos.getId());

    }
}
