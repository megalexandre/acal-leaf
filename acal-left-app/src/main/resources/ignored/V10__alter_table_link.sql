ALTER TABLE enderecopessoa RENAME COLUMN Numero TO numero;

ALTER TABLE enderecopessoa
    ADD UNIQUE INDEX idx_unique_active_number (
    id_endereco,
    numero,
    (CASE WHEN inativo = 0 THEN 1 ELSE NULL END)
    );