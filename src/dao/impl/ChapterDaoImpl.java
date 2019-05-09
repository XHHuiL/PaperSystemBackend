package dao.impl;

import dao.ChapterDao;
import entity.Chapter;
import util.Querier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 *         Created on 2019/4/21.
 */
public class ChapterDaoImpl implements ChapterDao {
    private static final String tableName = "chapter";
    private static EntityBuilder<Chapter> builder = rs -> {
        Chapter chapter = new Chapter();
        try {
            chapter.setId(rs.getInt("id"));
            chapter.setNumber(rs.getString("number"));
            chapter.setTitle(rs.getString("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;
    };

    @Override
    public Chapter getOne(int id) {
        Querier<Chapter> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<Chapter> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }

    @Override
    public List<Chapter> getBySyllabus(int syllabusId) {
        String sql = "SELECT chapter.*\n" +
                "FROM syllabus\n" +
                "INNER JOIN chapter ON syllabus.id=chapter.syllabus_id\n" +
                "WHERE syllabus.id=" + syllabusId;
        Querier<Chapter> querier = new Querier<>(sql, builder, false);
        return querier.query();
    }
}
