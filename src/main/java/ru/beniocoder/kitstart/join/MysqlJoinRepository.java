package ru.beniocoder.kitstart.join;

import ru.abstractcoder.benioapi.database.util.QueryFactory;

import java.util.concurrent.CompletableFuture;

public class MysqlJoinRepository implements JoinRepository {

    private final QueryFactory queryFactory;

    public MysqlJoinRepository(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public CompletableFuture<Boolean> isJoinedBefore(String name) {
        //language=MySQL
        String sql = "select joined from kitstart where username = ?";

        return queryFactory.completableQuery().query(sql,
                rs -> rs.next() && rs.getBoolean("joined"),
                name
        );
    }

    @Override
    public CompletableFuture<Void> setJoinedBefore(String name) {
        //language=MySQL
        String sql = "insert into kitstart values (?, ?)";

        return queryFactory.completableQuery().update(sql, name, true)
                .thenRun(() -> {});
    }

}