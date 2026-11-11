CREATE TABLE product_interest (
    id UUID PRIMARY KEY,
    invoice_id UUID NOT NULL UNIQUE,
    user_id TEXT NOT NULL,
    product_tag TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
