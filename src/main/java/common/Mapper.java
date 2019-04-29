package common;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
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
        var attributes = this.attributes();


        /*
        itereer langs alle kolommen (keys van de map)
        pak het bijbehorende field.
        pak van dit field dmv reflection het type
        (Type) resultSet.getObject(column)
         */

        var fieldToType = this.fieldToType();
        var x = (Class.forName(fieldToType.get("id"))).cast(resultSet.getObject("id"));

        return (T) constructors[0].newInstance(
                /* TODO: getObject gebruiken -> reflection gebruiken om
                het type en naam van de attributen te achterhalen.
                Attributen moeten dan wel de kolomnaam matchen.
                Moet ook een manier verzienen om te achterhalen om de juiste
                constructor te gebruiken.

                Het probleem: email, firstname, prefix en lastname zijn geen
                attributen van Student, maar van Person en Email. Ik moet dus reflection
                gebruiken om de niet-primitieve attributen van student om daarvan de attributen te krijgen.
                 */
                resultSet.getString("id"),
                resultSet.getString("email"),
                resultSet.getString("firstname"),
                resultSet.getString("prefix"),
                resultSet.getString("lastname"),
                resultSet.getString("ref_university")
        );
    }

    private Map<String, String> fieldToType() {
        var fieldToType = new HashMap<String, String>();
        this.columnToField.forEach((c, f) -> {
            try {
                var field = type.getDeclaredField(f);
                var typex = field.getType();
                var namex = typex.getName();
                fieldToType.put(f, type.getDeclaredField(f).getType().getName());
            } catch (Exception e) {
                System.out.println("Field doesn't exist: " + e);
            }
        });

        return fieldToType;
    }

    public Field[] attributes() {
        var attributes = type.getDeclaredFields();
        var x = type.getFields();
        return attributes;
    }
}
