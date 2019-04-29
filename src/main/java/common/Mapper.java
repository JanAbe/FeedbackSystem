package common;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper<T> {
    private Class<T> type;
    private Map<String, String> columnToField;
    private ResultSet resultSet;

    public Mapper(Class<T> type, ResultSet resultSet, Map<String, String> columnToField) {
        this.type = type;
        this.resultSet = resultSet;
        this.columnToField = columnToField;
    }

    @SuppressWarnings("unchecked")
    public T create() throws Exception {
        var constructors = type.getConstructors();
        var map = this.fieldToType();
        var x = this.cast(map, "id", resultSet);

        // dit wil ik alleen niet hardcoden, wil door keys van this.columnToField heen loopen,
        // maar weet niet hoe dat moet in de .newInstance() method;
        return (T) constructors[0].newInstance(
                this.cast(map, "id", resultSet),
                this.cast(map, "email", resultSet),
                this.cast(map, "firstname", resultSet),
                this.cast(map, "prefix", resultSet),
                this.cast(map, "lastname", resultSet),
                this.cast(map, "ref_university", resultSet)
        );
    }

    private Object cast(Map<String, String> map, String column, ResultSet rs) throws Exception {
        var field = this.columnToField.get(column);
        return (Class.forName(map.get(field))).cast(rs.getObject(column));
    }

    /**
     * <p>Get all attributes of an object (including attributes of attributes).</p>
     * @return All attributes
     */ // TODO: rewrite it so it makes use of recursion? Need to fix naming of keys=attribute-names (StudentID and UniversityID both use id as attribute name);
    // this is super ghetto though, it only works specifically for Student, o god :c
    private Map<String, String> fieldToType() {
        var fieldToType = new HashMap<String, String>();
        for (Field field : type.getDeclaredFields()) {
            for (Field f : field.getType().getDeclaredFields()) {
                if (f.getType().isPrimitive() || (f.getType() == String.class)) {
                    fieldToType.put(field.getName(), f.getType().getName());
                    continue;
                }
                for (Field f1 : f.getType().getDeclaredFields()) {
                    fieldToType.put(f1.getName(), f1.getType().getName());
                }
            }
        }
        return fieldToType;
    }
}
