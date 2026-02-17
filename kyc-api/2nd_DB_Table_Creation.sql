CREATE TABLE customer_change_audit(
 id BIGSERIAL PRIMARY KEY,
 uuid VARCHAR(36) NOT NULL,
 action_type VARCHAR(10) NOT NULL,           -- CREATE, UPDATE, DELETE
  customer_id BIGINT,
  old_data TEXT,
  new_data TEXT,
  status VARCHAR(10) NOT NULL,           -- SUCCESS, FAILED
  message TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW() 
);

CREATE TABLE customer_read_audit(
id BIGSERIAL PRIMARY KEY,
  uuid VARCHAR(36) NOT NULL,
  read_type VARCHAR(20) NOT NULL,        -- GET_ALL, GET_BY_ID
  customer_id BIGINT,
  result_count INT,
  status VARCHAR(10) NOT NULL,           -- SUCCESS, FAILED
  message TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
)


