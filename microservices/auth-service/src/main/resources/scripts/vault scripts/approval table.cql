CREATE TABLE vault.approval (
    approval_id uuid PRIMARY KEY,
    client_id text,
    expires_at date,
    last_updated_at date,
    scope text,
    status text,
    user_id text
);