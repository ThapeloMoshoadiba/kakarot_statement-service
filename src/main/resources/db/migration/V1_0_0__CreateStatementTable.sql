CREATE TABLE statement
(
    statement_id                        UUID PRIMARY KEY,
    created_at                          TIMESTAMP,
    statement_file                      BYTEA,
    extension                           VARCHAR(100)
);

CREATE INDEX statement_extension_idx ON statement (extension);
