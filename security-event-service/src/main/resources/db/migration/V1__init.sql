CREATE TABLE security_event
(
    id  BIGSERIAL  PRIMARY KEY ,
 event_type      VARCHAR(100) NOT NULL,
  severity        VARCHAR(30)  NOT NULL,
 source_system   VARCHAR(100),
 description    VARCHAR(200),
 created_at     TIMESTAMP NOT NULL DEFAULT now()

);

CREATE INDEX idx_security_event_created_at
ON security_event(created_at);