package com.appsterlight.model.db.dao.mysql;


import com.appsterlight.exception.DaoException;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.ApartmentClassDao;
import com.appsterlight.model.domain.ApartmentClass;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;

import static com.appsterlight.model.db.constants.Fields.*;
import static com.appsterlight.model.db.constants.Messages.*;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlApartmentClassDao extends AbstractDao<ApartmentClass> implements ApartmentClassDao {
    private final DataSource dataSource;
    public MySqlApartmentClassDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public String getCreateQuery() {
        return SQL_APARTMENT_CLASS_INSERT;
    }

    @Override
    public String getSelectQuery() {
        return SQL_APARTMENT_CLASS_GET;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_APARTMENT_CLASS_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_APARTMENT_CLASS_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_APARTMENT_CLASS_GET_ALL;
    }


    @Override
    public Long add(ApartmentClass apartmentClass) throws DaoException {
        Long id = super.add(apartmentClass);
        apartmentClass.setId(id);

        return id;
    }
    @Override
    protected Object[] getAllFieldsOfObject(ApartmentClass object) throws DaoException {
        try {
            return new Object[] {
                    object.getName(),
                    object.getDescription(),
                    object.getId()
            };
        } catch (NullPointerException e) {
            log.error("Booking object is null! Can't get fields! " + e);
            throw new DaoException("Booking object is null! Can't get fields!");
        }

    }

    @Override
    protected ApartmentClass mapEntity(ResultSet rs) throws DaoException {
        try {
            return  ApartmentClass.builder()
                    .id(rs.getLong(ID))
                    .name(rs.getString(NAME))
                    .description(rs.getString(DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

}
