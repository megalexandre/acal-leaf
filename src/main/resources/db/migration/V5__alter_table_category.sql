RENAME TABLE categoriasocio TO category;

ALTER TABLE category
    ADD COLUMN amount_water DECIMAL(10,2),
    ADD COLUMN amount_partner DECIMAL(10,2);

ALTER TABLE category RENAME COLUMN nome TO name;

UPDATE category c
    INNER JOIN taxa t ON c.taxasId = t.id
SET
    c.amount_water = t.valor,
    c.amount_partner = t.valor_socio;

UPDATE category
SET
    amount_water = COALESCE(amount_water, 0),
    amount_partner = COALESCE(amount_partner, 0);

ALTER TABLE category
    MODIFY COLUMN amount_water DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    MODIFY COLUMN amount_partner DECIMAL(10,2) NOT NULL DEFAULT 0.00;