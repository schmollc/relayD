-- ------------------------------------------------
-- Drop Table participant
-- ------------------------------------------------
DROP TABLE participant;

-- ------------------------------------------------
-- Create Table participant
-- ------------------------------------------------
CREATE TABLE participant (
	id								CHAR(36)		NOT NULL	COMMENT 'UUID as string representation.',
	personId						CHAR(36)		NOT NULL	COMMENT 'UUID of the referenced person, string representation.',
	relay2Id						CHAR(36) 		NOT NULL	COMMENT 'UUID of the referenced relay2, string representation.',
	relayPosition					INTEGER			NOT NULL	COMMENT 'The relay position the person participates in.',
	CONSTRAINT fk_participant_person
		FOREIGN KEY (personId) REFERENCES person (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	CONSTRAINT fk_participant_relay2
		FOREIGN KEY (relay2Id) REFERENCES relay2 (id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
) COMMENT 'relayD - The table for the relay participant entities.';


-- ------------------------------------------------
-- Create Index participant_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX participant_idx
        ON participant
        (id);

-- ------------------------------------------------
-- Create Primary Key
-- ------------------------------------------------
ALTER TABLE participant ADD PRIMARY KEY (id);

-- ------------------------------------------------
-- Create Index person_relay_position_IDX
-- ------------------------------------------------
CREATE UNIQUE INDEX person_relay_position_IDX
        ON participant
        (personId, relay2Id, relayPosition);

-- ------------------------------------------------
-- Grant participant
-- ------------------------------------------------
-- geht nicht, fehlende Rechte offenbar: GRANT ALL ON participant TO PUBLIC;