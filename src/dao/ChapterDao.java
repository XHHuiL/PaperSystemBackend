package dao;

import entity.Chapter;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public interface ChapterDao {

    Chapter getOne(int id);

    List<Chapter> getBySyllabus(int syllabusId);
}
