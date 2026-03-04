ALTER TABLE conta RENAME COLUMN dataPag TO data_pagamento;
CREATE INDEX idx_conta_data_referente ON conta (data_referente);