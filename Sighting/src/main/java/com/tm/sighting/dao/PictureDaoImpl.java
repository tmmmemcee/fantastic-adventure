/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Picture;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tmmmemcee
 */
public class PictureDaoImpl implements PictureDao {
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_PHOTO = "insert into photo (photoId, "
            + "title, fileName) values (?, ?, ?)";
    private static final String SQL_SELECT_PHOTO_BY_ID = "select * from photo where "
            + "photoId = ?";
    private static final String SQL_SELECT_ALL_PHOTOS = "select * from photo";
    private static final String SQL_DELETE_PHOTO = "delete from photo where photoId = ?";
    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Picture addPicture(Picture picture) {
        jdbcTemplate.update(SQL_INSERT_PHOTO, picture.getPictureId(), 
                picture.getTitle(), picture.getFilename());
        return picture;
    }

    @Override
    public void deletePicture(long pictureId) {
        jdbcTemplate.update(SQL_DELETE_PHOTO, pictureId);
    }

    @Override
    public Picture getPictureById(long pictureId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PHOTO_BY_ID, new PictureMapper(), pictureId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PHOTOS, new PictureMapper());
    }
    
    private static final class PictureMapper implements RowMapper<Picture> {
        @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException {
            Picture pic = new Picture();
            pic.setPictureId(rs.getInt("photoId"));
            pic.setTitle(rs.getString("title"));
            pic.setFilename(rs.getString("fileName"));
            return pic;
        }
    }
}
