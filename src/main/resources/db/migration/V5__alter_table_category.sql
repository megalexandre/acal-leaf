RENAME TABLE categoriasocio TO categoryModel;

ALTER TABLE categoryModel
    ADD COLUMN amount_water DECIMAL(10,2),
    ADD COLUMN amount_partner DECIMAL(10,2);

ALTER TABLE categoryModel RENAME COLUMN nome TO name;

UPDATE categoryModel c
    INNER JOIN taxa t ON c.taxasId = t.id
SET
    c.amount_water = t.valor,
    c.amount_partner = t.valor_socio;

UPDATE categoryModel
SET
    amount_water = COALESCE(amount_water, 0),
    amount_partner = COALESCE(amount_partner, 0);

ALTER TABLE categoryModel
    MODIFY COLUMN amount_water DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    MODIFY COLUMN amount_partner DECIMAL(10,2) NOT NULL DEFAULT 0.00;

UPDATE categoryModel c
set group_id = 3 where group_id is null;