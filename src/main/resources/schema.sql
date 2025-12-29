CREATE TABLE IF NOT EXISTS products (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        name TEXT NOT NULL,
                                        category TEXT NOT NULL,
                                        price_cents INTEGER NOT NULL,
                                        active INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         name TEXT NOT NULL,
                                         email TEXT,
                                         created_at TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
                                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                                      customer_id INTEGER NOT NULL,
                                      status TEXT NOT NULL,
                                      created_at TEXT NOT NULL,
                                      FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS order_items (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           order_id INTEGER NOT NULL,
                                           product_id INTEGER NOT NULL,
                                           quantity INTEGER NOT NULL,
                                           unit_price_cents INTEGER NOT NULL,
                                           FOREIGN KEY (order_id) REFERENCES orders(id),
                                           FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS payments (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        order_id INTEGER NOT NULL,
                                        method TEXT NOT NULL,
                                        amount_cents INTEGER NOT NULL,
                                        paid_at TEXT NOT NULL,
                                        FOREIGN KEY (order_id) REFERENCES orders(id)
);