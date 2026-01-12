INSERT IGNORE INTO users (username, password, enabled) VALUES
    ('user', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', '1'),
    ('admin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', '1');

INSERT IGNORE INTO authorities (username, authority) VALUES
    ('user', 'user'),
    ('admin', 'user'),
    ('admin', 'admin');
	