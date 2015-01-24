package org.artdevs.meetingslog.facades;

import org.artdevs.meetingslog.core.model.User;

import java.sql.SQLException;

/**
 * Created by Slava on 25.12.2014.
 */
public interface MapFormData< Input, Output> {
    User mapToModel(Input data) throws RuntimeException;
    boolean verify(Input data);
}
