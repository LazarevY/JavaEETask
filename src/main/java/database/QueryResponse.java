package database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;

@AllArgsConstructor
public class QueryResponse {

    @Getter @Setter
    private ResultSet resultSet;

    @Getter @Setter
    private int code;

    @Getter @Setter
    private String msg;


}
