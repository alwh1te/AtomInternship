CREATE TABLE IF NOT EXISTS topics
(
    topicId BIGSERIAL PRIMARY KEY,
    title  VARCHAR(255) NOT NULL,
    date timestamp
);

CREATE TABLE IF NOT EXISTS messages
(
    messageId BIGSERIAL PRIMARY KEY,
    topicId INT NOT NULL,
    text  VARCHAR(255) NOT NULL,
    author  VARCHAR(255) NOT NULL,
    date timestamp
);