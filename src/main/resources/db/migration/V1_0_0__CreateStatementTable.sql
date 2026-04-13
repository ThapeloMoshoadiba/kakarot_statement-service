CREATE TABLE statement
(
    statement_id                        UUID PRIMARY KEY,
    created_at                          TIMESTAMP NOT NULL,
    statement_file                      BYTEA NOT NULL,
    extension                           VARCHAR(100) NOT NULL
);

CREATE INDEX statement_extension_idx ON statement (extension);
