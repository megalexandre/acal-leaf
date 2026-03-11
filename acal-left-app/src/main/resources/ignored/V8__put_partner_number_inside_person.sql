ALTER TABLE pessoa
    ADD COLUMN partner_number VARCHAR(10);

UPDATE pessoa p
    INNER JOIN socio s ON p.id = s.pessoa_id
SET p.partner_number = s.numeroSocio;