CREATE TABLE WHITE_CARDS
(
    white_card_id INT AUTO_INCREMENT PRIMARY KEY,
    text          VARCHAR(300) NOT NULL
);

CREATE TABLE BLACK_CARDS
(
    black_card_id    INT AUTO_INCREMENT PRIMARY KEY,
    text             VARCHAR(300) NOT NULL,
    number_of_blanks INT NOT NULL
);