ALTER TABLE category
    ADD COLUMN has_hydrometer BOOLEAN NULL;

update category
    SET has_hydrometer = false;

UPDATE category
    SET has_hydrometer = true
    WHERE LOWER(name) LIKE '%hidr%metro%';

ALTER TABLE category ALTER COLUMN has_hydrometer SET DEFAULT false;

