package dao;

import entity.Syllabus;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public interface SyllabusDao {

    Syllabus getOne(int id);

    List<Syllabus> getAll();
}
