package common;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper<T> {
    private Class<T> type;
    private ResultSet resultSet;

    public Mapper(Class<T> type, ResultSet resultSet) {
        this.type = type;
        this.resultSet = resultSet;
    }

    @SuppressWarnings("unchecked")
    public T create() throws Exception {
        var constructors = type.getConstructors();
        var x = (T) constructors[0].newInstance(
                /* TODO: getObject gebruiken -> reflection gebruiken om
                het type en naam van de attributen te achterhalen.
                Attributen moeten dan wel de kolomnaam matchen.
                Moet ook een manier verzienen om te achterhalen om de juiste
                constructor te gebruiken.
                 */

                resultSet.getString("id"),
                resultSet.getString("email"),
                resultSet.getString("firstname"),
                resultSet.getString("prefix"),
                resultSet.getString("lastname"),
                resultSet.getString("ref_university")
        );

        return x;
    }

    public void test() {
        var x = type.getDeclaredFields();
    }
}
