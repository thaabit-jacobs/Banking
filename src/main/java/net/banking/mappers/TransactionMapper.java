package net.banking.mappers;

import net.banking.models.Account;
import net.banking.models.Transaction;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class TransactionMapper implements RowMapper<Transaction> {

    @Override
    public Transaction map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Transaction(rs.getInt("id"), rs.getString("transacs_type"), rs.getDouble("transacs_amount"),
                rs.getBoolean("is_success"), new Timestamp(rs.getDate("date_created").getTime()).toLocalDateTime(), rs.getInt("account_id"));
    }
}
