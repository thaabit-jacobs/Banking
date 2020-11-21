package net.banking.mappers;

import net.banking.models.Account;
import net.banking.models.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Account(rs.getInt("id"), UUID.fromString(rs.getString("account_number")), rs.getString("account_type"),
                rs.getDouble("balance"), new Timestamp(rs.getDate("date_created").getTime()).toLocalDateTime(), rs.getInt("user_id"));
    }
}
