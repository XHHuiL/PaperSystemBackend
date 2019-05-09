package util;

import dao.impl.EntityBuilder;
import entity.Question;
import entity.Syllabus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class Querier<T> {
    private StringBuilder sqlBuilder;
    private EntityBuilder<T> entityBuilder;

    public Querier(String table, EntityBuilder<T> entityBuilder) {
        this(table, entityBuilder, true);
    }

    public Querier(String table, EntityBuilder<T> entityBuilder, boolean isTableName) {
        this.sqlBuilder = isTableName ? new StringBuilder("SELECT * FROM ").append(table) :
                new StringBuilder(table);
        this.entityBuilder = entityBuilder;
    }

    public Querier<T> where(String condition) {
        this.sqlBuilder.append(" WHERE ").append(condition);
        return this;
    }

    public Querier<T> and(String condition) {
        this.sqlBuilder.append(" AND ").append(condition);
        return this;
    }

    public List<T> query() {
        List<T> entities = new ArrayList<>();

        JdbcUtil util = JdbcUtil.getInstance();
        Connection conn = util.getConnection();
        String sql = sqlBuilder.toString();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                entities.add(entityBuilder.build(rs));
            }
        } catch (SQLException e) {
            entities = null;
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return entities;
    }
}
