package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.OfferedApartmentsDao;
import com.appsterlight.model.domain.OfferedAparment;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.appsterlight.model.db.constants.Fields.*;
import static com.appsterlight.model.db.constants.Messages.READ_ERROR;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlOfferedApartmentDao extends AbstractDao<OfferedAparment> implements OfferedApartmentsDao {
    private final DataSource dataSource;

    public MySqlOfferedApartmentDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public String getSelectQuery() { return SQL_OFFERED_APARTMENTS_GET; }

    @Override
    public String getCreateQuery() { return SQL_OFFERED_APARTMENTS_INSERT; }

    @Override
    public String getUpdateQuery() { return SQL_OFFERED_APARTMENTS_UPDATE; }

    @Override
    public String getDeleteQuery() { return SQL_OFFERED_APARTMENTS_DELETE; }

    @Override
    public String getSelectAllQuery() { return SQL_OFFERED_APARTMENTS_GET_ALL; }

    @Override
    public List<OfferedAparment> getAllOfferedApartmentsByBookingId(Long id) throws DaoException {
        ArrayList<OfferedAparment> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement prst = connection.prepareStatement(SQL_OFFERED_APARTMENTS_GET_BY_BOOKING_ID);) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                result.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error("Can't get Offered Apartments from table by booking id! " + e);
            throw new DaoException("Can't get Offered Apartments by booking id from table! " + e);
        }

        return result;
    }

    @Override
    protected Object[] getAllFieldsOfObject(OfferedAparment object) throws DaoException {
        try {
            return new Object[] {
                    object.getBookingId(),
                    object.getApartmentId(),
                    object.getMessage(),
                    object.getId()
            };
        } catch (NullPointerException e) {
            log.error("OfferedAparment object is null! Can't get fields! " + e);
            throw new DaoException("OfferedAparment object is null! Can't get fields!");
        }
    }

    @Override
    protected OfferedAparment mapEntity(ResultSet rs) throws DaoException {
        try {
            return OfferedAparment.builder()
                    .id(rs.getLong(ID))
                    .bookingId(rs.getLong(OFFERED_APARTMENTS_BOOKING_ID))
                    .apartmentId(rs.getLong(OFFERED_APARTMENTS_APARTMENT_ID))
                    .message(rs.getString(OFFERED_APARTMENTS_MESSAGE))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }


}
