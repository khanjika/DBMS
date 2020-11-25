package sql.parser;

import sql.Query;

public interface IParser {
    Query parse(String query);
}
