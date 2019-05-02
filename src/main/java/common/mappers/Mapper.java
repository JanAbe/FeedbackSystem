package common.mappers;

import java.sql.ResultSet;

public interface Mapper<T> {

    T createFrom(ResultSet resultSet);
}
