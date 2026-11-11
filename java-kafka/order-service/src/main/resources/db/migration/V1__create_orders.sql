CREATE TABLE orders (
    id UUID PRIMARY KEY,
    user_id TEXT NOT NULL,
    product_name TEXT NOT NULL,
    product_tag TEXT NOT NULL,
    product_qty INTEGER NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT orders_product_qty_positive CHECK (product_qty > 0)
);
