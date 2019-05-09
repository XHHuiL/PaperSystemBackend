package dao.impl;

import dao.SyllabusDao;
import entity.Syllabus;
import util.Querier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class SyllabusDaoImpl implements SyllabusDao {
    private static final String tableName = "syllabus";
    private static EntityBuilder<Syllabus> builder = rs -> {
        Syllabus syllabus = new Syllabus();
        try {
            syllabus.setId(rs.getInt("id"));
            syllabus.setLevel(rs.getString("level"));
            syllabus.setVersion(rs.getString("version"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return syllabus;
    };

    @Override
    public Syllabus getOne(int id) {
        Querier<Syllabus> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<Syllabus> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }

    @Override
    public List<Syllabus> getAll() {
        Querier<Syllabus> querier = new Querier<>(tableName, builder);
        return querier.query();
    }
}
