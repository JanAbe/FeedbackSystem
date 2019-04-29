package common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Mapper<T> {
    private Class<T> type;
    private Map<String, String> columnToField;

    public Mapper(Class<T> type, Map<String, String> columnToField) {
        this.type = type;
        this.columnToField = columnToField;
    }

    /**
     * <p>Create an object of type T.</p>
     * @return object of type T
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked") // veel te veel exceptions thrown imo
    public T create(ResultSet resultSet) throws SQLException, InstantiationException,
                             IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var constructors = type.getConstructors();
        var map = this.fieldToType();

        // TODO: op bepaalde plekken de email, voornaam, achternaam etc. omzetten naar kleine letters / hoofdletters.
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

    /**
     * <p>Cast the value, that belongs to the column from the resultSet,
     * to the data type of the corresponding attribute of its object. Making
     * it ready to use while creating an object of type T.</p>
     * @param map FieldToType map, containing all attributes of T + the corresponding data types.
     * @param column The column name of the database table.
     * @param rs resultSet.
     * @return a casted object
     * @throws SQLException
     */
    private Object cast(Map<String, String> map, String column, ResultSet rs) throws SQLException {
        var castedObject = new Object();
        var field = this.columnToField.get(column);

        try {
            castedObject = classByName(map.get(field)).cast(rs.getObject(column));
        } catch (ClassCastException e) {
            System.out.println("Exception occurred casting an object from resultSet value: " + e);
        }

        return castedObject;
    }

    /**
     * <p>Functions as a wrapper of the method Class.forName(name).
     * It returns the class based on the provided name.</p>
     * @param name name of a class
     * @return the class of the provided name;
     */ // TODO: log exception instead of printing it, maybe do something else too?
    private Class<?> classByName(String name) {
        Class<?> c = null;

        try {
            c = Class.forName(name);
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred while getting class based on name: " + e);
        }

        return c;
    }

    /**
     * <p>Get all attributes of an object (including attributes of attributes).</p>
     * @return All attributes
     */
    // TODO: rewrite it so it makes use of recursion? Need to fix naming of keys=attribute-names (StudentID and UniversityID both use id as attribute name);
    // this is super ghetto though, it only works specifically for Student, o god :c
    // IllegalArgumentException | IllegalAccessException | InvocationTargetException handlen
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
