

INSERT INTO users (username, password, email, name, surname, type, rewardpoints, penaltypoints, ismalicious, isbanned, isawarded)
VALUES
('FirstGuide', '1111', 'firstguide@example.com', 'Pera', 'Peric', 'Guide', 0, 0, FALSE, FALSE, FALSE);

INSERT INTO users (username, password, email, name, surname, type, rewardpoints, penaltypoints, ismalicious, isbanned, isawarded)
VALUES
('SecondGuide', '2222', 'secondguide@example.com', 'Ivan', 'Ivanovic', 'Guide', 0, 0, FALSE, FALSE, FALSE);

INSERT INTO users (username, password, email, name, surname, type, rewardpoints, penaltypoints, ismalicious, isbanned, isawarded)
VALUES
('badGuide', '0000', 'bad@example.com', 'Aca', 'Acic', 'Guide', 0, 11, TRUE, FALSE, FALSE);

INSERT INTO users (username, password, email, name, surname, type, rewardpoints, penaltypoints, ismalicious, isbanned, isawarded)
VALUES
('terribleGuide', '0000', 'terrible@example.com', 'Jovan', 'Jovanovic', 'Guide', 0, 20, TRUE, TRUE, FALSE);

INSERT INTO users (username, password, email, name, surname, type, rewardpoints, penaltypoints, ismalicious, isbanned)
VALUES
('badTourist', '0000', 'imbad@example.com', 'Marko', 'Markovic', 'Tourist', 0, 12, FALSE, TRUE, FALSE);

INSERT INTO users (username, password, email, name, surname, type)
VALUES
('admin', 'admin', 'admin@example.com', 'Bogi', 'Batina', 'Admin');
