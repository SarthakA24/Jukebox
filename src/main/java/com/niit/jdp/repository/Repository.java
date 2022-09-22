package com.niit.jdp.repository;

import java.sql.Connection;
import java.util.List;

public interface Repository<T> {
    List<T> getAll(Connection connection);
}
