package com.appsterlight.model.db.dao.mysql;

import com.appsterlight.exception.DaoException;
import com.appsterlight.model.db.constants.Fields;
import com.appsterlight.model.db.dao.AbstractDao;
import com.appsterlight.model.db.dao.ApartmentTagsDao;
import com.appsterlight.model.domain.ApartmentTag;
import com.appsterlight.model.domain.Tag;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.appsterlight.model.db.constants.Messages.READ_ERROR;
import static com.appsterlight.model.db.constants.Queries.*;

@Slf4j
public class MySqlApartmentTagsDao extends AbstractDao<ApartmentTag> implements ApartmentTagsDao {
    private final DataSource dataSource;

    public MySqlApartmentTagsDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public String getSelectQuery() {
        return SQL_APARTMENT_TAGS_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_APARTMENT_TAGS_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_APARTMENT_TAGS_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_APARTMENT_TAGS_DELETE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_APARTMENT_TAGS_GET_ALL;
    }


    @Override
    public Long add(ApartmentTag object) throws DaoException {
        Long id = super.add(object);
        object.setId(id);

        return id;
    }

    //get all apartment tags which related to some apartment
    @Override
    public List<Tag> getAllTagsByApartmentId(Long id) throws DaoException {
        List<Tag> tags = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(
                SQL_APARTMENT_TAGS_GET_ALL_TAGS_BY_APARTMENT_ID)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                tags.add(mapTag(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTagsByApartmentId(Long id, Boolean isBasic) throws DaoException {
        List<Tag> tags = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(
                     SQL_APARTMENT_TAGS_GET_ALL_TAGS_BY_TYPE_AND_APARTMENT_ID)) {
            prst.setLong(1, id);
            prst.setBoolean(2, isBasic);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                tags.add(mapTag(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }

        return tags;
    }

    //get all apartment tags which have some tag
    @Override
    public List<ApartmentTag> getAllApartmentTagsByTagId(Long id) throws DaoException {
        List<ApartmentTag> tags = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prst = connection.prepareStatement(
                SQL_APARTMENT_TAGS_GET_ALL_APARTMENT_TAGS_BY_TAG_ID)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                tags.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
        return tags;
    }

    @Override
    protected Object[] getAllFieldsOfObject(ApartmentTag object) throws DaoException {
        try {
            return new Object[] {
                    object.getTagId(),
                    object.getApartmentId(),
                    object.getId() };
        } catch (Exception e) {
            log.error("ApartmentTags object is null! Can't get fields!");
            throw new DaoException("ApartmentTags object is null! Can't get fields!");
        }
    }

    protected Tag mapTag(ResultSet rs) throws DaoException {
        try {
            return  Tag.builder()
                    .id(rs.getLong(Fields.ID))
                    .name(rs.getString(Fields.TAGS_NAME))
                    .description(rs.getString(Fields.DESCRIPTION))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    protected ApartmentTag mapEntity(ResultSet rs) throws DaoException {
        try {
            return  ApartmentTag.builder()
                    .id(rs.getLong(Fields.ID))
                    .tagId(rs.getLong(Fields.APARTMENT_TAGS_TAG_ID))
                    .apartmentId(rs.getLong(Fields.APARTMENT_TAGS_APARTMENT_ID))
                    .build();
        } catch (SQLException e) {
            log.error(READ_ERROR, e);
            throw new DaoException(e);
        }
    }
}
