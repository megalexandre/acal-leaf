-- Tabela de sequências usada pelo Hibernate TABLE generator
-- Substitui o AUTO_INCREMENT (IDENTITY) para permitir batch inserts reais
CREATE TABLE id_sequences (
    seq_name  VARCHAR(50)  NOT NULL,
    seq_value BIGINT       NOT NULL DEFAULT 1,
    PRIMARY KEY (seq_name)
);

-- Inicializa os contadores a partir do maior ID já existente em cada tabela,
-- arredondado para o próximo múltiplo de 50 (allocationSize), garantindo que
-- nenhum ID futuro colida com registros existentes.
INSERT INTO id_sequences (seq_name, seq_value)
SELECT 'conta_id', CEIL((COALESCE(MAX(id), 0) + 1) / 50.0) * 50
FROM conta;

INSERT INTO id_sequences (seq_name, seq_value)
SELECT 'hidrometro_id', CEIL((COALESCE(MAX(idhidrometro), 0) + 1) / 50.0) * 50
FROM hidrometro;

