package com.rocky.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Callback {

    Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}
