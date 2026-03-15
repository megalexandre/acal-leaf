-- Corrige os seq_value que foram inicializados com valor menor que o MAX(id)
-- existente, causando Duplicate entry na PRIMARY KEY.
-- Fórmula: (FLOOR(MAX(id) / allocationSize) + 1) * allocationSize
-- garante que o próximo bloco alocado pelo Hibernate começa APÓS o maior ID já gravado.

UPDATE id_sequences
SET seq_value = (SELECT (FLOOR(COALESCE(MAX(id), 0) / 50) + 1) * 50 FROM conta)
WHERE seq_name = 'conta_id';

UPDATE id_sequences
SET seq_value = (SELECT (FLOOR(COALESCE(MAX(idhidrometro), 0) / 50) + 1) * 50 FROM hidrometro)
WHERE seq_name = 'hidrometro_id';

