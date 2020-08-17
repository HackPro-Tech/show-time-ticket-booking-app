CREATE TABLE vault.refresh_token (
    token_id text PRIMARY KEY,
    authentication blob,
    "token" blob
);