package dao.impl;

import dao.KnowledgePointDao;
import entity.KnowledgePoint;
import util.Querier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class KnowledgePointDaoImpl implements KnowledgePointDao {
    private static final String tableName = "knowledge_point";
    private static EntityBuilder<KnowledgePoint> builder = rs -> {
        KnowledgePoint knowledgePoint = new KnowledgePoint();
        try {
            knowledgePoint.setId(rs.getInt("id"));
            knowledgePoint.setTitle(rs.getString("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledgePoint;
    };

    @Override
    public KnowledgePoint getOne(int id) {
        Querier<KnowledgePoint> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<KnowledgePoint> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }
}
