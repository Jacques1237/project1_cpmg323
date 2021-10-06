CREATE TABLE IF NOT EXISTS account_type (
    acc_id UUID NOT NULL PRIMARY KEY,
    person_Id UUID REFERENCES person (person_id),
    created_at DATE NOT NULL,
    acc_miles INTEGER NOT NUll
);