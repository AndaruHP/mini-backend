CREATE TABLE invoices (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE,
    user_id TEXT NOT NULL,
    product_name TEXT NOT NULL,
    product_tag TEXT NOT NULL,
    product_qty INTEGER NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
