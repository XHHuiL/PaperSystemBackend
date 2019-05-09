package dao.impl;

import java.sql.ResultSet;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public interface EntityBuilder<T> {
    T build(ResultSet rs);
}
