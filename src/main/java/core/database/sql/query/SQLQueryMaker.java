package core.database.sql.query;

import data.query.Query;

public interface SQLQueryMaker<Q extends Query<?>> {
    default String createQuery(Query<?> query) {
        Q q = null;
        try {
            q = (Q) query;
        } catch (ClassCastException ignored) {
            q = null;
        }
        return q == null ? null : makeQuery(q);
    }

    String makeQuery(Q query);
}
