RENAME TABLE categoriasocio TO categoryEntity;

ALTER TABLE categoryEntity
    ADD COLUMN amount_water DECIMAL(10,2),
    ADD COLUMN amount_partner DECIMAL(10,2);

ALTER TABLE categoryEntity RENAME COLUMN nome TO name;

UPDATE categoryEntity c
    INNER JOIN taxa t ON c.taxasId = t.id
SET
    c.amount_water = t.valor,
    c.amount_partner = t.valor_socio;

UPDATE categoryEntity
SET
    amount_water = COALESCE(amount_water, 0),
    amount_partner = COALESCE(amount_partner, 0);

ALTER TABLE categoryEntity
    MODIFY COLUMN amount_water DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    MODIFY COLUMN amount_partner DECIMAL(10,2) NOT NULL DEFAULT 0.00;

UPDATE categoryEntity c
set group_id = 3 where group_id is null;